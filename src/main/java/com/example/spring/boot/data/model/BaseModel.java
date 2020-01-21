package com.example.spring.boot.data.model;

import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author amipatil
 *
 */
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class BaseModel {

	@Basic
	@Id
	// In order to support both H2 and MYSQL, we need to change generator to native.
	// Because Starting with version 5.0 when Generation Type is selected as AUTO,
	// Hibernate uses SequenceStyleGenerator regardless of the database. In case of
	// MySql Hibernate emulates a sequence using a table and is why you are seeing
	// the hibernate_sequence table. MySql doesn't support the standard sequence
	// type natively.
	// Using native generator solves this issue, which means it will use IDENTITY
	// generation strategy in case of MYSQL since it supports it.
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	protected Long id;

	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(name = "created_on")
	protected Calendar createdOn;

	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	@Column(name = "updatedOn")
	protected Calendar updatedOn;

	@Basic
	@Column(name = "is_deleted")
	protected boolean isDeleted;

}
