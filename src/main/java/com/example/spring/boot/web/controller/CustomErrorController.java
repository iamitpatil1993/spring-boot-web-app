package com.example.spring.boot.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // This can be be normal Controller as well, in which case we need to return
				// View/ModelAndView or view name string
public class CustomErrorController implements ErrorController {

	private static final String ERROR_PATH = "/error";

	@GetMapping(value = ERROR_PATH, produces = { "text/html" })
	public String errorHandler(final HttpServletRequest request) {
		// we can return View/ModelAndView or any type from here by changing this from
		// RestController to normal web MVC Controller.
		// we can have view for this and return model and view name from here instead of
		// returning HTML from here.
		
		final Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		final Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
		
		return String.format(
				"<html><body><h2>Error Page</h2><div>Status code: <b>%s</b></div>"
						+ "<div>Exception Message: <b>%s</b></div><body></html>",
				statusCode, exception == null ? "N/A" : exception.getMessage());
	}

	/**
	 * This returns error path that this error controller can handle.
	 */
	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}

}
