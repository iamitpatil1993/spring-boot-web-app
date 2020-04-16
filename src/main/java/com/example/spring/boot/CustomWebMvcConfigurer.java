package com.example.spring.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author amipatil
 *
 */
// DO NOT USE @EnableWebMvc here to leverage auto-configuration.
@Configuration
@Slf4j
public class CustomWebMvcConfigurer implements WebMvcConfigurer {

	/**
	 * <p>
	 * By default, SimpleAsyncTaskExecutor is used for the blocking writes, but that
	 * is not suitable under load. This TaskExecutor implementation fires up a new
	 * Thread for each task, executing it asynchronously.
	 * <p>
	 * <p>
	 * Supports limiting concurrent threads through the "concurrencyLimit" bean
	 * property. By default, the number of concurrent threads is unlimited
	 * <p>
	 * <p>
	 * Hence we should configure our own AsyncTaskExecutor to handle async
	 * controller request for better load.
	 * <p>
	 * 
	 * @return
	 */
	@Bean
	public AsyncTaskExecutor threadPoolAsyncTaskExecutor() {
		log.debug("Creating Async Task Executor");
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(5);
		executor.setMaxPoolSize(10);
		executor.setQueueCapacity(25);
		return executor;
	}

	/**
	 * This configures Async Support in Spring MVC. We can configure may things
	 * here. Refer:
	 * https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-async-configuration-spring-mvc
	 * 
	 */
	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		int asyncRequestTimeoutSeconds = 300;
		configurer.setDefaultTimeout(asyncRequestTimeoutSeconds * 1000);

		// Use custom AsyncTaskExecutor over default SimpleAsyncTaskExecutor.
		configurer.setTaskExecutor(threadPoolAsyncTaskExecutor());

		// we can configure DeferredResultProcessingInterceptor implementations and
		// CallableProcessingInterceptor implementations.
		// as well.
		// See different way of configuring
		// https://medium.com/swlh/streaming-data-with-spring-boot-restful-web-service-87522511c071
	}

}
