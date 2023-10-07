package com.example.damian_jozwiak_99794.domain.book;

import com.example.damian_jozwiak_99794.domain.book.dto.BookDto;
import com.example.damian_jozwiak_99794.domain.book.dto.BookSaveDto;
import com.example.damian_jozwiak_99794.domain.genre.Genre;
import com.example.damian_jozwiak_99794.domain.genre.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;


    public BookService(BookRepository bookRepository,  GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
    }

    // Generowanie 5 losowych książek
    public List<Book> generateRandomBooks() {
        return bookRepository.findRandomBooks();
    }

    public Optional<BookDto> findBookById(Long id){
        return bookRepository.findById(id).map(BookDtoMapper ::map);
    }

    public List<BookDto> findBookByGenreName(String genre) {
        return bookRepository.findAllByGenreName(genre).stream()
                .map(BookDtoMapper::map)
                .toList();
    }

    public void addBook(BookSaveDto bookSaveDto){
        Book book = new Book();
        book.setName(bookSaveDto.getName());
        book.setPrice(bookSaveDto.getPrice());
        book.setStars(bookSaveDto.getStars());
        book.setStock(bookSaveDto.getStock());
        Genre genre = genreRepository.findByNameIgnoreCase(bookSaveDto.getGenre()).orElseThrow();
        book.setGenre(genre);
        bookRepository.save(book);
    }

}
