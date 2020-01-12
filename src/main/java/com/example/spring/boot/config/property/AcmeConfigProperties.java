package com.example.spring.boot.config.property;

import java.net.InetAddress;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

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
@Validated // Enables validation of property mappings, NOTE: All required libs must be in classpath
public class AcmeConfigProperties {

	// spring will automatically map property to boolean field. Getter/Setter for
	// this needs to be as per java-bean spec. i.e isEnabled() and setEnabled()
	private boolean enabled;

	// spring automatically maps IP address to InetAddress
	@NotNull
	private InetAddress remoteAddress;

	// This is how we can map nested properties, name of property in bean and in
	// propery should match, class name can be anything.
	@NotNull
	@Valid // By default it applies validation on properties of this field but, to ensure
			// that validation is always triggered for nested properties, even when no
			// properties are found, the associated field must be annotated with @Valid.
	private AcmeSecurityConfigProperties security;

}
