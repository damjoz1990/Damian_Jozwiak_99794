package com.example.damian_jozwiak_99794.domain.book;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long>{
    @Query(value = "SELECT * FROM book ORDER BY RAND() LIMIT 5", nativeQuery = true)
    List<Book> findRandomBooks();

    List<Book> findAllByGenreName(String genre);

    Optional<Book> findByName(String name);


}
