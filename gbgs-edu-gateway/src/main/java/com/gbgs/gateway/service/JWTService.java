package com.gbgs.gateway.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static com.gbgs.gateway.constants.FilterConstants.*;

@Service
public class JWTService {

    private static final String ISSUER_NAME = "gbgs";
    private Algorithm algorithm;

    public JWTService() throws IllegalArgumentException, UnsupportedEncodingException {
        algorithm = Algorithm.HMAC512("test token");
    }


    public DecodedJWT verifyToken(String token) {
        final JWTVerifier jwtVerifier = JWT.require(algorithm).withIssuer(ISSUER_NAME).build();
        try {
            return jwtVerifier.verify(token);
        } catch (JWTVerificationException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public String createToken(Long userId, String userType, String userRole) {
        final JWTCreator.Builder jwtBuilder = JWT.create().withIssuer(ISSUER_NAME)
                .withSubject(userId.toString())
                .withClaim(USER_ID_CLAIM, userId)
                .withIssuedAt(new Date())
                .withClaim(USER_TYPE_CLAIM, userType)
                .withClaim(USER_ROLE_CLAIM, userRole);

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.add(Calendar.SECOND, 120);
        jwtBuilder.withExpiresAt(calendar.getTime());
        jwtBuilder.withClaim("tokenType", "accessToken");
		return jwtBuilder.sign(algorithm);
	}
	
}
