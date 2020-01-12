package com.example.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.example.spring.boot.config.property.ConstructorBasedAcmeConfigProperties;

/**
 * Main Bootstrap and configuration class, which act as a starting
 * point/launcher of application.
 * 
 * This classes defined as a 'start-class' in Maifest.mf, where Launcher/main
 * class of application is JarLauncher/WarLauncher. Check manifest.mf
 *
 * 
 * @author amipatil
 *
 */
@SpringBootApplication // This enables ComponentScan (from this package), auto-configuration and
						// configuration
@EnableConfigurationProperties(value = { ConstructorBasedAcmeConfigProperties.class }) // In order to use @ConfigurationProperties in
																		// application, we need to enable it using this
																		// config annotation, but it's already enabled
																		// in auto-config classes in spring, so we do
																		// not need to enable it explicitly. If we are
																		// not using auto-config at all then we will
																		// have to use it
public class SpringBootWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebAppApplication.class, args);
	}

}
