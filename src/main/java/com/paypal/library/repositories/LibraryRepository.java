package com.paypal.library.repositories;

import com.paypal.library.entities.Library;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sandip
 *
 */
@Repository
public interface LibraryRepository extends JpaRepository<Library, Long>{
	
	Optional<Library> findByRegistrationCode(String regCode);

}
