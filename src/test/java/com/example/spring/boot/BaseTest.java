package com.example.spring.boot;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
public class BaseTest {

	@Autowired
	protected WebApplicationContext webApplicationContext;

	protected MockMvc mockMvc;

	@BeforeEach
	public void beforeTest() {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}
}
