package com.example.spring.boot.data.service;

import java.util.List;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.example.spring.boot.data.dao.ReadingListRepository;
import com.example.spring.boot.data.model.Book;

/**
 * 
 * @author amipatil
 *
 */
@Service
public class BookService implements InitializingBean {

	private ReadingListRepository readingListRepository;

	@Autowired
	public BookService(final ReadingListRepository readingListRepository) {
		this.readingListRepository = readingListRepository;
	}

	@Transactional(readOnly = true)
	public List<Book> getAllByReader(final String reader) {
		return readingListRepository.findByReader(reader);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public Book addBookToReadingList(final Book book) {
		return readingListRepository.save(book);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(readingListRepository, "readingListRepository injected null in BookService");
	}
}
