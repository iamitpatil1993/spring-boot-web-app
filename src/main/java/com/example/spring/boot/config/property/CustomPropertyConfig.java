package com.example.spring.boot.config.property;

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
@ConfigurationProperties(prefix = "custom") // NOTE: prefix are optional and we can remove them to map non-hierarchical/flat properties
@Getter
@Setter
public class CustomPropertyConfig {

	private String foo;

	// this can be populated from foobar, fooBar, foo_bar, foo-bar properties
	private String fooBar;

}
