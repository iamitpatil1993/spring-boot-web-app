package com.example.spring.boot;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import liquibase.integration.spring.SpringLiquibase;

/**
 * Spring Liquibase configuration. @SpringLiquibase will automatically execute
 * liquibase at application context startup.
 * 
 * @author amipatil
 *
 */
@Configuration
public class LiquibaseConfiguration {

	@Bean
	public SpringLiquibase springLiquibase(final DataSource dataSource) {
		final SpringLiquibase liquibase = new SpringLiquibase();

		liquibase.setDataSource(dataSource);
		liquibase.setChangeLog("classpath:/db/liquibase-changeLog.xml");

		return liquibase;
	}

}
