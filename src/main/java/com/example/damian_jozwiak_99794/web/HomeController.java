package com.example.damian_jozwiak_99794.web;

import com.example.damian_jozwiak_99794.domain.book.Book;
import com.example.damian_jozwiak_99794.domain.book.BookService;
import com.example.damian_jozwiak_99794.domain.book.dto.BookDto;
import com.example.damian_jozwiak_99794.domain.genre.GenreService;
import com.example.damian_jozwiak_99794.domain.genre.dto.GenreDto;
import com.example.damian_jozwiak_99794.domain.webscraper.ScraperService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@Controller
public class HomeController {
    private final BookService bookService;
    private final GenreService genreService;
    private final ScraperService scraperService;

    public HomeController(BookService bookService, GenreService genreService, ScraperService scraperService) {
        this.bookService = bookService;
        this.genreService = genreService;
        this.scraperService = scraperService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Book> randomBooks = bookService.generateRandomBooks();
        model.addAttribute("books", randomBooks);
        return "index"; // Zakładam, że masz widok o nazwie "bookListing" do wyświetlania książek
    }

    @GetMapping("/genre/{name}")
    public String getGenre(@PathVariable String name, Model model) {
        GenreDto genre = genreService.findGenreByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<BookDto> books = bookService.findBookByGenreName(name);
        model.addAttribute("genre", genre.getName());
        model.addAttribute("books", books);
        return "genre";
    }

    @GetMapping("/genres-book")
    public String getGenreList(Model model) {
        List<GenreDto> genres = genreService.findAllGenres();
        model.addAttribute("genres", genres);
        return "genre";
    }

    @GetMapping("/scrap")
    public String scrapeBooks(Model model) throws IOException {
        List<com.example.damian_jozwiak_99794.domain.webscraper.Book> booksScrap = scraperService.scrapeBooks();
        model.addAttribute("booksScrap", booksScrap);
        return "index";
    }

}


