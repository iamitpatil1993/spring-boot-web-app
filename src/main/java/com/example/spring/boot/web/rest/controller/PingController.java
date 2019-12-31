package com.example.spring.boot.web.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author amipatil
 *
 */
@RestController
public class PingController {

	@GetMapping(path = "/ping")
	public String pingHandler() {
		return "pong";
	}
}
