package com.example.spring.boot.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerMapping;

import com.example.spring.boot.data.model.Book;
import com.example.spring.boot.data.service.BookService;

/**
 * 
 * @author amipatil
 *
 */
@Controller
@RequestMapping(path = "/reading-list/{reader}")
public class ReadingListController implements InitializingBean {

	private BookService bookService;

	@Autowired
	public ReadingListController(final BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping
	public String showReadingList(final @PathVariable String reader, final Model model) {
		List<Book> allByReader = bookService.getAllByReader(reader);
		model.addAttribute("books", allByReader);
		return "readingList";
	}

	@PostMapping
	public String addToReadingList(final @PathVariable String reader, final Book book, HttpServletRequest request) {
		book.setReader(reader);
		bookService.addBookToReadingList(book);
		 String restOfTheUrl = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		return "redirect:" + restOfTheUrl;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(bookService, "bookService injected null in ReadingListController");
	}

}
