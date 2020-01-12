package com.example.spring.boot.config.property;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author amipatil
 *
 */
// If this is to be used as a nested property, 
// then we do not need @ConfigurationProperty annotation here, otherwise it need it.
@Getter
@Setter
@ToString
public class AcmeSecurityConfigProperties {

	private String username;

	private String password;

	private List<String> roles = new ArrayList<>();

}
