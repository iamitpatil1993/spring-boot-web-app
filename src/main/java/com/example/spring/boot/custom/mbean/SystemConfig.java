package com.example.spring.boot.custom.mbean;

import lombok.extern.slf4j.Slf4j;

import javax.management.ObjectName;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

/**
 * Since this is spring bean, it will be registered to MBean server
 * automatically using default {@link ObjectName}
 * 
 * @author amipatil
 *
 */
@Slf4j
@Component
// We can use annotations to customize details of Mbean and attributes/operations it expose, See ManagedAttribute, ManagedOperation annotations.
@ManagedResource(description = "SystemConfig managed bean", objectName = "com.example.spring.boot:name=SystemConfig")
public class SystemConfig implements SystemConfigMBean {

	private int threadCount = 10;

	@Override
	public void setThreadCount(int count) {
		this.threadCount = count;
	}

	@Override
	public int getThreadCount() {
		return threadCount;
	}

	@Override
	public void doSomething() {
		log.info("SystemConfig.doSomething, with threadCount :: " + threadCount);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void startCron() throws InterruptedException {
		log.debug("Received application startup callback, starting cron");
		do {
			Thread.sleep(5000);
			doSomething();
		} while (threadCount != 0);
		log.info("Exiting cron since instance count reached to zero...");
	}
}
