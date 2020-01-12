package com.example.spring.boot.config.property;

import java.net.InetAddress;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Demonstrates mapping of config properties to bean, with data type mapping and
 * NESTED mappings.
 * 
 * @author amipatil
 *
 */
@Component
@ConfigurationProperties(prefix = "acme")
@Getter
@Setter
@ToString
public class AcmeConfigProperties {

	// spring will automatically map property to boolean field. Getter/Setter for
	// this needs to be as per java-bean spec. i.e isEnabled() and setEnabled()
	private boolean enabled;

	// spring automatically maps IP address to InetAddress
	private InetAddress remoteAddress;

	// This is how we can map nested properties, name of property in bean and in
	// propery should match, class name can be anything.
	private AcmeSecurityConfigProperties security;

}
