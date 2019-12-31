package com.example.spring.boot.data.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author amipatil
 *
 */
@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Book extends BaseModel {

	@Basic
	@Column(name = "reader")
	private String reader;

	@Basic
	@Column(name = "isbn")
	@EqualsAndHashCode.Include
	private String isbn;

	@Basic
	@Column(name = "title")
	private String title;

	@Basic
	@Column(name = "author")
	private String author;

	@Basic
	@Column(name = "description")
	private String description;

}
