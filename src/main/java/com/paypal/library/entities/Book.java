package com.paypal.library.entities;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Sandip
 *
 */

@Entity
@Table(name = "books", uniqueConstraints= @UniqueConstraint(columnNames={"title", "authorName"}))
@org.hibernate.annotations.DynamicUpdate( 
		value = true
)  // Enabled to generate updates only for required columns and not for all columns by default.
public class Book {

	/*
	 * Explicitly Sequence generator use specified considering future change in
	 * persistence provider other than hibernate. Right now hibernate uses SEQUENCe by default.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	Long ID; 											
	
	/*
	 * jsonproperty used explicitly to hide actual field name from user.
	 */
	@JsonProperty("book_title")
	private String title;
	
	@Column( nullable = false)
	@JsonProperty("author_name")
	private String authorName;
	
	/*
	 * Used Embedded for better abstraction and re-usability across project in
	 * future
	 */
	@Embedded 
	@JsonProperty("ratings")
	private BookRatings ratings;
	
	/* 
	 * Enable when need to fetch library detail for a particular book. In a way creating bi-directional relationship. 
	 * Mapped by to prevent creation of multiple join tables.
	 * @ManyToMany(mappedBy = "books") 
	 * Set<Library> libraries;
	*/
	
	@CreationTimestamp
	private Timestamp createDateTime;
	
	/*
	 * Used Version annotation for enabling OptimisticLocking in multithreadin
	 * environment
	 */
	@UpdateTimestamp 
	@Version 
	private Timestamp updateDateTime;

	/*
	 * Hashcode and Equals used to enable Book as key in Set inside Library Entity.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		result = prime * result + ((authorName == null) ? 0 : authorName.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/*
	 * Equals below helps to define uniquness of a book based on title and
	 * authorname. Same is inline with JPA Uniqueness constraint defined in Book
	 * entity
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		if (authorName == null) {
			if (other.authorName != null)
				return false;
		} else if (!authorName.equals(other.authorName))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthorName() {
		return authorName;
	}
	
	public void setRatings(BookRatings ratings) {
		this.ratings = ratings;
	}

	public BookRatings getRatings() {
		return ratings;
	}
	
}
