package com.example.spring.boot.web.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.spring.boot.data.model.Book;
import com.example.spring.boot.data.service.BookService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author amipatil
 *
 */
@Controller
@RequestMapping(path = "/")
@Slf4j
public class ReadingListController implements InitializingBean {

	private BookService bookService;

	@Autowired
	public ReadingListController(final BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping
	public String showReadingList(final Model model, final Principal principal) {
		final String currentLoggedInUsername = principal.getName();
		log.info("current logged in username is :: {}", currentLoggedInUsername);
		List<Book> allByReader = bookService.getAllByReader(currentLoggedInUsername);
		model.addAttribute("books", allByReader);
		return "readingList";
	}

	@PostMapping(path = {"/reading-list"})
	public String addToReadingList(final Book book, HttpServletRequest request, final Principal principal) {
		final String currentLoggedInUsername = principal.getName();
		book.setReader(currentLoggedInUsername);
		bookService.addBookToReadingList(book);
		return "redirect:/";
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(bookService, "bookService injected null in ReadingListController");
	}

}
