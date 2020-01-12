package com.example.spring.boot.config.property;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author amipatil
 *
 */
@Component
@ConfigurationProperties(prefix = "spring.jpa")
@Getter
@Setter
public class JpaConfigProperties implements InitializingBean {

	private String database;

	private String databasePlatform;

	@Override
	public void afterPropertiesSet() throws Exception {
		// We can have validations here.
	}
}
