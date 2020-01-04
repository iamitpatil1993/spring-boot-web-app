package com.example.spring.boot;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;

import liquibase.integration.spring.SpringLiquibase;

/**
 * Spring Liquibase configuration. @SpringLiquibase will automatically execute
 * liquibase at application context startup.
 * 
 * @author amipatil
 *
 */
//@Configuration // We do not need this anymore, spring boot will auto-configure liquibase based on liquibase in classpath
public class LiquibaseConfiguration {

	@Bean
	public SpringLiquibase springLiquibase(final DataSource dataSource) {
		final SpringLiquibase liquibase = new SpringLiquibase();

		liquibase.setDataSource(dataSource);
		liquibase.setChangeLog("classpath:/db/liquibase-changeLog.xml");

		return liquibase;
	}

}
