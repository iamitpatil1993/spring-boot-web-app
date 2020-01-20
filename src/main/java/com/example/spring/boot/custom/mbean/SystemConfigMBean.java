package com.example.spring.boot.custom.mbean;

/**
 * JMX Mbean interface, which control which attributes and operations to be
 * exposed to JMX
 * 
 * @author amipatil
 *
 */
public interface SystemConfigMBean {

	void setThreadCount(final int count);

	int getThreadCount();

	void doSomething();
}
