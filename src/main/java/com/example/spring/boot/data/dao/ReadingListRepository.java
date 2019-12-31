package com.example.spring.boot.data.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring.boot.data.model.Book;

/**
 * 
 * @author amipatil
 *
 */
@Repository
public interface ReadingListRepository extends JpaRepository<Book, Long> {

	List<Book> findByReader(final String reader);

}
