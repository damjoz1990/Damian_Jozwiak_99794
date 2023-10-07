package com.example.damian_jozwiak_99794.domain.book;

import com.example.damian_jozwiak_99794.domain.book.dto.BookDto;

public class BookDtoMapper {
    static BookDto map(Book book){
        return new BookDto(
                book.getId(),
                book.getName(),
                book.getPrice(),
                book.getStock(),
                book.getStars(),
                book.getGenre().getName()
        );
    }

    static BookDto mapBookToBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setName(book.getName());
        bookDto.setGenre(book.getGenre().getName());
        bookDto.setStock(book.getStock());
        bookDto.setStars(book.getStars());
        bookDto.setPrice(book.getPrice());
        // Tutaj możesz przekopiować pozostałe pola z obiektu Book do obiektu BookDto

        return bookDto;
    }
}
