package com.paypal.library.controllers;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.paypal.library.services.LibraryService;
import com.paypal.library.entities.Book;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/api/library")
public class MainController {

	@Autowired
	LibraryService libService;
	
	@GetMapping(value="/getBooksForLibrary/{regCode}")
	public Set<Book> getAllBooks(@PathVariable(name = "regCode", required = true) String regCode){
		return libService.getAllBooksOf(regCode);
	}
	
	@GetMapping(value="/getAllBooks")
	public List<Book> getAllBooks(){
		return libService.getAllBooks();
	}
	
	@PutMapping(value="/updateBook")
	public ResponseEntity<Object> updateBook(@RequestBody Book updatedBook){
		try {
			boolean isUpdated=libService.updateBook(updatedBook);
			if(isUpdated) {
				return ResponseEntity.ok().build();
			}else{
				return ResponseEntity.status(HttpStatus.OK).body("Book Not Found.");
			}
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Book Not Updated due to internal error.");
		}
	}
	
}
