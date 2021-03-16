package com.gbgs.gateway.config;

import com.gbgs.gateway.models.AuthorizationPolicy;
import com.gbgs.gateway.utils.YamlPropertySourceFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
@PropertySource(factory = YamlPropertySourceFactory.class,value="classpath:authorization.yml")
@ConfigurationProperties(prefix="authorization")
public class AuthorizationConfig {

	private List<AuthorizationPolicy> policies;

	public List<AuthorizationPolicy> getPolicies() {
		return policies;
	}

	public void setPolicies(List<AuthorizationPolicy> policies) {
		this.policies = policies;
	}
}
