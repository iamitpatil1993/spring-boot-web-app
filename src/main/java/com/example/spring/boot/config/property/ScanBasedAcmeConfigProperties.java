package com.example.spring.boot.config.property;

import java.net.InetAddress;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 
 * Demonstrates use of {@link ConfigurationPropertiesScan} to scan
 * {@link ConfigurationProperties} annotated classes in classpath. So, we do not
 * need to declare this class as a spring bean (which spring bean does
 * internally) and do not need to list this class in
 * {@link EnableConfigurationProperties}.
 * 
 * This method is helpful in case, where we have many @ConfigurationProperties
 * classes in classpath and listing each of them in
 * {@link EnableConfigurationProperties} is not practical and we want to use
 * {@link ConstructorBinding}.
 * 
 * If we do not want @ConstructorBinding based mapping and then we can
 * explicitly declare mapping classes as spring bean using {@link Component}
 * annotation and spring automatically pick that class based on component
 * scanning.
 * 
 * @author amipatil
 *
 */
//@Component // NOT NEEDED
@ConfigurationProperties(prefix = "acme")
@Getter
//@Setter we do not need these now,
@ToString
@ConstructorBinding
@AllArgsConstructor
public class ScanBasedAcmeConfigProperties {

	// spring will automatically map property to boolean field. Getter/Setter for
	// this needs to be as per java-bean spec. i.e isEnabled() and setEnabled()
	private boolean enabled;

	// spring automatically maps IP address to InetAddress
	private InetAddress remoteAddress;

	// This is how we can map nested properties, name of property in bean and in
	// Property should match, class name can be anything.
	private AcmeSecurityConfigProperties security;

}
