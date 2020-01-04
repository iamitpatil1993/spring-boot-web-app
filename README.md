
# spring-boot-web-app
This project coverts features of spring boot over spring features like auto-configuration, starter dependencies, actuator and deployment strategies.

## Ways to Override application configurations

 1. **Explicit configuration**
If we want to change/customize auto-configuration more than just a few properties and want complete control over configuration, then we should consider explicit configuration and provide complement configuration.
Since spring boot loads application configuration before auto-configuration and most of the beans/configurations are enabled only when beans of the same type do not already exist in application context (using `@ConditionalOnMissingBean`), we can provide explicit configuration and it will always take precedence over auto-configuration.
For example, most of the time spring security auto-configuration is not sufficient and we want to customize it a lot, so it's always better to provide explicit configuration instead of application properties.
 
 2. **Application properties**
 It's not always a good choice to give up auto-configuration for small configuration changes/customizations in the application. If we want small customizations to application configuration, we should consider using external application properties instead of defining an entire explicit configuration.
 For example, To provide connection details of the database, we can use application properties instead of defining `DataSource` on our own.

## External Property Sources
Spring can load external properties from a different source. Below mentioned are sources which we can use to provide application properties, mentioned in precedence in which spring boot lots them.

 1. Command line argument
 2. JNDI attributes from `java:comp/env` 
 3. JVM System parameters
 4. Operating System Environment Variables.
 5. `application.properties` or `application.yml` external to application.
 6. `application.properties` or `application.yml` external to application.
 7. Default 	properties

### Locations of a properties file
 - Externally, **/config** sub-directory of the directory from which the application is run.
 - Externally, in the directory from which the application is run.
 - Internally, in the `config` package.
 - Internally, in the root of the application classpath
 
