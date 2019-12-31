package com.example.spring.boot.web.rest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

import com.example.spring.boot.BaseTest;

class PingControllerTest extends BaseTest {

	@Test
	void testPingHandler() throws Exception {
		// WHEN
		MvcResult mvcResult = mockMvc.perform(get("/ping")).andExpect(status().isOk()).andReturn();
		String responseString = mvcResult.getResponse().getContentAsString();

		// THEN
		assertNotNull(responseString);
		assertEquals("pong", responseString.toLowerCase());
	}

}
