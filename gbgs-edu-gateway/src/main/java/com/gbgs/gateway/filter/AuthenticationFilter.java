package com.gbgs.gateway.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.gbgs.gateway.models.UserInfoDTO;
import com.gbgs.gateway.service.JWTService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import static com.gbgs.gateway.constants.FilterConstants.*;

@Component
public class AuthenticationFilter extends ZuulFilter {

    @Autowired
    private JWTService jwtService;

    public static String getTokenFromAuthorizationHeader(String header) {
        String token = header.replace("Bearer ", "");
        return token.trim();
    }

    public static UserInfoDTO extractUserInfoFromClaims(DecodedJWT decodedJWT) {
        JSONObject payloadObject = new JSONObject(StringUtils.newStringUtf8(Base64.decodeBase64(decodedJWT.getPayload())));
        final Long userId = payloadObject.getLong(USER_ID_CLAIM);
        final String userType = payloadObject.getString(USER_TYPE_CLAIM);
        final String userRole = payloadObject.getString(USER_ROLE_CLAIM);

        return new UserInfoDTO(userId, userType, userRole);
    }

    @Override
    public boolean shouldFilter() {
        final RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        //return !(request.getRequestURI().startsWith("/token"));
        return false;
    }

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 3;
    }

    @Override
    public Object run() throws ZuulException {
        final RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        Optional<String> authorizationHeader = Optional.ofNullable(request.getHeader("Authorization"));
        Optional<String> authorizationToken = authorizationHeader.map(AuthenticationFilter::getTokenFromAuthorizationHeader);
        Optional<DecodedJWT> decodedToken = authorizationToken.map(jwtService::verifyToken);
        if (decodedToken.isPresent()) {
            DecodedJWT decodedAT = decodedToken.get();
            Date expirationTime = decodedAT.getExpiresAt();
            LocalDateTime ldt = LocalDateTime.ofInstant(expirationTime.toInstant(), ZoneId.systemDefault());
            if (ldt.isBefore(LocalDateTime.now())) {
                throw new ZuulException("Invalid Token", HttpStatus.UNAUTHORIZED.value(), "Token Expired");
            }
            UserInfoDTO userInfoDTO = extractUserInfoFromClaims(decodedAT);
            requestContext.put(USER_ROLE_CLAIM, userInfoDTO.getUserRole());
            requestContext.put(USER_ID_CLAIM, userInfoDTO.getUserId());
            requestContext.put(USER_TYPE_CLAIM, userInfoDTO.getUserType());
            requestContext.put(IS_CLAIM_VERIFICATION_REQUIRED, false);
            //requestContext.put(IS_CLAIM_VERIFICATION_REQUIRED, true);
        } else {
            throw new ZuulException("Invalid Token", HttpStatus.UNAUTHORIZED.value(), "Token Signature is Invalid");
        }
        return null;
    }
}
