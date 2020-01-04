package com.example.spring.boot;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import com.example.spring.boot.security.SecurityRole;

/**
 * Since we want to fully customize the security configuration and don't want
 * spring security auto-configuration, we will explicitly define spring security
 * configuration. Since configuration classes are beans in application context,
 * spring boot auto-configuration will find this bean as implementation of
 * {@link WebSecurityConfigurer}. Hence spring boot auto-configuration will skip
 * the spring security configuration. And hence our configuration will get
 * precedence.
 * 
 * @author amipatil
 *
 */
@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class CustomWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

	/**
	 * Enable formLogin and make authentication mandatory for all urls.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").hasRole(SecurityRole.READER.toString()).and().formLogin().and().csrf()
				.disable();
		// we need to add this to fix h2-console not working issue, as per https://stackoverflow.com/questions/53395200/h2-console-is-not-showing-in-browser
		http.headers().frameOptions().disable();
	}

	/**
	 * For simplicity we are keeping default user details hard coded here, we can
	 * configure in-memory/jdbc/ldap based backend for UserDetails source.
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance()).withUser("user")
				.password("password").roles(SecurityRole.READER.toString());
	}

}
