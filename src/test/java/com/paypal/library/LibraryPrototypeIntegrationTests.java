package com.paypal.library;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import com.paypal.library.entities.Book;
import com.paypal.library.entities.BookRatings;
import com.paypal.library.repositories.BookRepository;
import com.paypal.library.repositories.LibraryRepository;

/**
 * @author Sandip
 * @apiNote This jUnit file contains Integration Test cases
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LibraryPrototypeApplication.class,webEnvironment=WebEnvironment.RANDOM_PORT)
@Sql({"/import.sql"})
class LibraryPrototypeIntegrationTests {

	@LocalServerPort
    private int port;
	
	@Autowired
    private TestRestTemplate restTemplate;
	
	@Autowired
	LibraryRepository libRepo;
	
	@Autowired
	BookRepository bookRepo;
	
	@Test
	void contextLoads() {
	}
	
	
	@Test
	public void testGetAllBooks() {
		Book[] books=this.restTemplate.getForObject("http://localhost:"+port+"/api/library/getAllBooks", Book[].class);
		assertEquals(5, books.length);
	}
	
	@Test
	public void testGetAllBooksForLibrary() {
		Book[] books=this.restTemplate.getForObject("http://localhost:"+port+"/api/library/getBooksForLibrary/LIB-988-453", Book[].class);
		assertEquals(4, books.length);
		
		Book[] books2=this.restTemplate.getForObject("http://localhost:"+port+"/api/library/getBooksForLibrary/LIB-654-798", Book[].class);
		assertEquals(0, books2.length);
	}
	
	@Test
	public void testGetAllBooksForNonExistingLibrary() {
		Book[] books=this.restTemplate.getForObject("http://localhost:"+port+"/api/library/getBooksForLibrary/LIB-988-659", Book[].class);
		assertEquals(0, books.length);
	}
	
	@Test
	public void testUpdateExistingBook() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		String jsonReq="{\n" + 
				"    \"book_title\": \"Power Of Now\",\n" + 
				"    \"author_name\": \"Eckhart Tolle\",\n" + 
				"    \"ratings\": { \"languageAccuracy\": 3, \"printQualityRating\" : 1 }"
				+    "}";
		
		 String url = "http://localhost:"+port+"/api/library/updateBook";     
		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON); 
		    HttpEntity<String> entity = new HttpEntity<String>(jsonReq, headers); 
		    ResponseEntity<HttpStatus> response = restTemplate.exchange(url, HttpMethod.PUT, entity, HttpStatus.class);
		    assertEquals(HttpStatus.OK, response.getStatusCode());
		    
		    Optional<Book> updatedBook=bookRepo.findByTitleAndAuthorName("Power Of Now", "Eckhart Tolle");		
		    if(updatedBook.isPresent()) {
		    	Field ratings = Book.class.getDeclaredField("ratings");
		    	ratings.setAccessible(true);
		    	BookRatings ratingsActual =(BookRatings) ratings.get(updatedBook.get());
		    	assertEquals(3, ratingsActual.getLanguageAccuracy().intValue());
		    	assertEquals(1, ratingsActual.getPrintQualityRating().intValue());
		    }else {
		    	assertTrue(false);
		    }
	}
	
	
	@Test
	public void testUpdateExistingBookAndCustomUpdateTimeProvided() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, ClassNotFoundException {
		String jsonReq="{\n" + 
				"    \"book_title\": \"Power Of Now\",\n" + 
				"    \"author_name\": \"Eckhart Tolle\",\n" + 
				"    \"ratings\": { \"languageAccuracy\": 3, \"printQualityRating\" : 1 },\n" + 
				"    \"updateDateTime\": \"2021-05-02 14:45:49.323\"\n" + 
				"}";
		
		 String url = "http://localhost:"+port+"/api/library/updateBook";     
		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON); 
		    HttpEntity<String> entity = new HttpEntity<String>(jsonReq, headers); 
		    ResponseEntity<HttpStatus> response = restTemplate.exchange(url, HttpMethod.PUT, entity, HttpStatus.class);
		    assertEquals(HttpStatus.OK, response.getStatusCode());
		    
		    Optional<Book> updatedBook=bookRepo.findByTitleAndAuthorName("Power Of Now", "Eckhart Tolle");		
		    if(updatedBook.isPresent()) {
		    	Field updateDateTimeField = Book.class.getDeclaredField("updateDateTime");
		    	updateDateTimeField.setAccessible(true);
		    	Timestamp actualUpdateTimeStamp =(Timestamp) updateDateTimeField.get(updatedBook.get());
		    	assertNotEquals(actualUpdateTimeStamp.toString(),"2021-05-02 14:45:49.323");
		    }else {
		    	assertTrue(false);
		    }
	}
	
	@Test
	public void testUpdateNonExistingBook() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		String jsonReq="{\n" + 
				"    \"book_title\": \"Book1\",\n" + 
				"    \"author_name\": \"Author1\",\n" + 
				"    \"ratings\": { \"languageAccuracy\": 1, \"printQualityRating\" : 3 }"
				+    "}";
		
		 String url = "http://localhost:"+port+"/api/library/updateBook";     
		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    List<MediaType> type=new ArrayList();
		    type.add(MediaType.TEXT_PLAIN);
		    headers.setAccept(type);
		    HttpEntity<String> entity = new HttpEntity<String>(jsonReq, headers); 
		    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, entity,String.class);
		    assertEquals(HttpStatus.OK, response.getStatusCode());
		    assertEquals("Book Not Found.", response.getBody());
	}
}
