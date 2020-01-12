package com.example.spring.boot.config.property;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

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
// No need to add any annotation here in order to apply validation.
public class AcmeSecurityConfigProperties {

	@NotNull
	@Length(min = 4)
	private String username;

	@NotNull
	@Length(min = 4)
	private String password;

	@NotEmpty
	private List<String> roles = new ArrayList<>();

}
