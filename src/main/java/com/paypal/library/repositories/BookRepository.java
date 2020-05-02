package com.paypal.library.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.paypal.library.entities.Book;

/**
 * @author Sandip
 *
 */
public interface BookRepository  extends JpaRepository<Book, Long>{

	Optional<Book> findByTitleAndAuthorName(String title, String authorName);

}
