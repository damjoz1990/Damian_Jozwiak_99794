package com.example.damian_jozwiak_99794.web;

import com.example.damian_jozwiak_99794.domain.book.BookService;
import com.example.damian_jozwiak_99794.domain.book.dto.BookDto;
import com.example.damian_jozwiak_99794.domain.genre.GenreService;
import com.example.damian_jozwiak_99794.domain.genre.dto.GenreDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
public class GenreController {
    private final GenreService genreService;
    private final BookService bookService;

    public GenreController(GenreService genreService, BookService bookService) {
        this.genreService = genreService;
        this.bookService = bookService;
    }

    @GetMapping("/gatunek/{name}")
    public String getGenre(@PathVariable String name, Model model) {
        GenreDto genre = genreService.findGenreByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<BookDto> books = bookService.findBookByGenreName(name);
        model.addAttribute("genre", genre.getName());
        model.addAttribute("books", books);
        return "genre-books";
    }

    @GetMapping("/gatunk")
    public String getGenreList(Model model) {
        List<GenreDto> genres = genreService.findAllGenres();
        model.addAttribute("genres", genres);
        return "genre";
    }
}
