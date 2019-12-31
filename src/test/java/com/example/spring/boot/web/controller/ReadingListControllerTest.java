package com.example.spring.boot.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;

import com.example.spring.boot.BaseTest;

class ReadingListControllerTest extends BaseTest {

	@Test
	void testShowReadingList() throws Exception {
		mockMvc.perform(get("/reading-list/{reader}", "foo")).andExpect(status().isOk())
				.andExpect(view().name("readingList"));
	}

}
