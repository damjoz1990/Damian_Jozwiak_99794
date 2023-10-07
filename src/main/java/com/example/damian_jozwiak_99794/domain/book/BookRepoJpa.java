package com.example.damian_jozwiak_99794.domain.book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepoJpa extends JpaRepository<Book, Long> {
    List <Book> findBookByName(String string);
}
