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
/**
 * In order to map configuration properties, class needs to be spring bean.;
 * 
 * @author amipatil
 *
 */
@Component // In order to map configuration properties, class needs to be spring bean
@ConfigurationProperties(prefix = "spring.datasource") // maps all properties with prefix
@Getter
@Setter
public class DataSourceConfigProperties implements InitializingBean {

	private String url;

	private String username;

	@Override
	public void afterPropertiesSet() throws Exception {
		// we can have validations/assertions here on properties, if we use assertion
		// and assertion fails spring will mark
		// bean initialization failure and application will not start.

	}

}
