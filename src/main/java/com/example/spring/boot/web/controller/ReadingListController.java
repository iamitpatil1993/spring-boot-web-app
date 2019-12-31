package com.example.spring.boot.web.controller;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.spring.boot.data.model.Book;
import com.example.spring.boot.data.service.BookService;

/**
 * 
 * @author amipatil
 *
 */
@Controller
public class ReadingListController implements InitializingBean {

	private BookService bookService;

	@Autowired
	public ReadingListController(final BookService bookService) {
		this.bookService = bookService;
	}
	
	@GetMapping(path = "/reading-list/{reader}")
	public String showReadingList(final @PathVariable String reader, final Model model) {
		List<Book> allByReader = bookService.getAllByReader(reader);
		model.addAttribute("books", allByReader);
		return "readingList";
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(bookService, "bookService injected null in ReadingListController");
	}

}
