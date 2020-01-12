package com.example.spring.boot.config.property;

import java.net.InetAddress;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Demonstrates mapping of config properties to bean using
 * {@link ConstructorBinding}. {@link ConstructorBinding} is useful in case
 * where we want to make class immutable or read-only
 * 
 * NOTE: In order to use @ConstructorBinding to map properties, we need to
 * use @EnableConfigurationProperties or configuration property scanning,
 * without this it will not work.
 * 
 * 
 * 
 * @author amipatil
 *
 */
//@Component We do not need this rather we should not use it with @ConstructorBinding classes, spring automatically creates bean for this class 
@ConfigurationProperties(prefix = "acme")
@Getter
//@Setter we do not need these now,
@ToString
@ConstructorBinding
@AllArgsConstructor
public class ConstructorBasedAcmeConfigProperties {

	// spring will automatically map property to boolean field. Getter/Setter for
	// this needs to be as per java-bean spec. i.e isEnabled() and setEnabled()
	private boolean enabled;

	// spring automatically maps IP address to InetAddress
	private InetAddress remoteAddress;

	// This is how we can map nested properties, name of property in bean and in
	// Property should match, class name can be anything.
	private AcmeSecurityConfigProperties security;

}
