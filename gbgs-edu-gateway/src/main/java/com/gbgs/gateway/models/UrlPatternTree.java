package com.gbgs.gateway.models;

import com.gbgs.gateway.config.AuthorizationConfig;
import com.gbgs.gateway.utils.UrlPatternTreeNodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UrlPatternTree {

	private UrlPatternTreeNode rootNode;
	
	@Autowired
	public UrlPatternTree(AuthorizationConfig authorizationConfig) {
		rootNode = new UrlPatternTreeNode(null, "root");
		List<AuthorizationPolicy> claimRoutes = authorizationConfig.getPolicies();
		for(AuthorizationPolicy claimRoute : claimRoutes) {
			String prefix = claimRoute.getRoute();
			UrlPatternTreeNode routeNode = UrlPatternTreeNodeUtils.addNode(prefix,rootNode);
			List<AuthorizationPolicyClaim> claimPatterns = claimRoute.getClaims();
			for(AuthorizationPolicyClaim claimPattern : claimPatterns) {
				String urlPattern = claimPattern.getUrlPattern();
				String urlClaim = claimPattern.getUrlClaim();
				List<String> userRoleClaim = claimPattern.getUserRoleClaim();
				UrlPatternTreeNodeUtils.addUrlNode(urlPattern, userRoleClaim, prefix + urlClaim, routeNode);
			}
		}
	}	
	
	
	public UrlPatternTreeNode verifyIfRequestIsAuthorized(String requestURI) {
		return UrlPatternTreeNodeUtils.getUrlNode(requestURI, rootNode);
	}
}
