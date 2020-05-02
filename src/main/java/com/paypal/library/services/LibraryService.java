package com.paypal.library.services;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paypal.library.entities.Book;
import com.paypal.library.entities.Library;
import com.paypal.library.repositories.BookRepository;
import com.paypal.library.repositories.LibraryRepository;
import com.paypal.library.utils.NullSkipPropertyAwareBeanUtilsBean;

/**
 * @author Sandip
 * @apiNote Service Layer Bean to provide book and library related functionalities
 */
@Service
public class LibraryService {

	@Autowired
	LibraryRepository librepository;
	
	@Autowired
	BookRepository bookrepository;

	@Autowired
	NullSkipPropertyAwareBeanUtilsBean beanUtil;
	
	public Set<Book> getAllBooksOf(String regCode){
		Optional<Library> lib=librepository.findByRegistrationCode(regCode);
		if(lib.isPresent())
			return lib.get().getBooks();
		else // if no such library with input registration code exists, return empty set
			return Collections.EMPTY_SET;
	}
	
	public List<Book> getAllBooks(){
		return bookrepository.findAll();
	}

	public boolean updateBook(Book updatedBook) throws IllegalAccessException, InvocationTargetException {
		Optional<Book> retrievedBook = bookrepository.findByTitleAndAuthorName(updatedBook.getTitle(),updatedBook.getAuthorName()); // Considering every book has unique combination of title and author. Better than assuming caller has book id which is internal to system.
		if(retrievedBook.isPresent()) {
			Book b=retrievedBook.get();
			beanUtil.setSkipProperties("ID","createDateTime","updateDateTime","ratings");/*
																						 * Skip updating values of ID, createdDateTime and updateDateTime in case
																						 * received from User.class This could be malformed practice from user to
																						 * override system populated values Skip rating to just create a shallow copy.
																						 * This is because beanUtil only supports shallow copy to be created.
																						 */
			beanUtil.copyProperties(b, updatedBook); // Created a shallow copy of non null values from updatedBook into b
			beanUtil.copyProperties(b.getRatings(), updatedBook.getRatings()); // Now make same API call to update BookRating values as well
			bookrepository.save(b);
			return true;
		}else {// If request received for non-existing book
			return false;
		}
	}
}