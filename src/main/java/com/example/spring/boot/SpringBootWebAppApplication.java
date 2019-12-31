package com.example.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Bootstrap and configuration class, which act as a starting
 * point/launcher of application.
 * 
 * This classes defined as a 'start-class' in Maifest.mf, where Launcher/main class of application is JarLauncher/WarLauncher. Check manifest.mf
 *
 * 
 * @author amipatil
 *
 */
@SpringBootApplication // This enables ComponentScan (from this package), auto-configuration and
						// configuration
public class SpringBootWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebAppApplication.class, args);
	}

}
