package com.paypal.library.entities;

import java.sql.Timestamp;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Version;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author Sandip
 *
 */
@Entity
public class Library {

		/*
		 * Explicitly Sequence generator use specified considering future change in
		 * persistence provider other than hibernate. Right now hibernate uses SEQUENCe by default.
		*/
		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE)
		private Long ID;
		
		private String registrationName;
		
		/*
		 * No more than one library with same registration code should be allowed.
		 */
		@Column(unique = true, nullable = false)
		private String registrationCode;

		@Embedded
		private Address address;
		
		/* 
		 * Using lazy mode to fetch books details only when required 
		*/
	    @ManyToMany(fetch = FetchType.LAZY)
	    @JoinTable(name = "library_books", inverseJoinColumns = {
				@JoinColumn(name = "books_id", nullable = false, updatable = false) }, joinColumns = {
						@JoinColumn(name = "library_id", nullable = false, updatable = false) })
	    private Set<Book> books;

		@CreationTimestamp
		private Timestamp createDateTime;
		
		/*
		 * Used Version annotation for enabling OptimisticLocking in multithreadin
		 * environment
		 */
		@UpdateTimestamp 
		@Version
		private Timestamp updateDateTime;

		public Long getID() {
			return ID;
		}

		public String getRegistrationName() {
			return registrationName;
		}

		public String getRegistrationCode() {
			return registrationCode;
		}

		public Address getAddress() {
			return address;
		}

		public Set<Book> getBooks() {
			return books;
		}

		public void setID(Long iD) {
			ID = iD;
		}

		public void setRegistrationName(String registrationName) {
			this.registrationName = registrationName;
		}

		public void setRegistrationCode(String registrationCode) {
			this.registrationCode = registrationCode;
		}

		public void setAddress(Address address) {
			this.address = address;
		}

		public void setBooks(Set<Book> books) {
			this.books = books;
		}
	}
