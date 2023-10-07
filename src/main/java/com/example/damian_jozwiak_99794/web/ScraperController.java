package com.example.damian_jozwiak_99794.web;

import com.example.damian_jozwiak_99794.domain.webscraper.Book;
import com.example.damian_jozwiak_99794.domain.webscraper.ScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
public class ScraperController {
    @Autowired
    private ScraperService scraperService;

    public ScraperController(ScraperService scraperService) {
        this.scraperService = scraperService;
    }

    @GetMapping("/search")
    public String searchBooks(@RequestParam("searchQuery") String searchQuery, Model model) throws IOException {

        // Wywołaj metodę do wyszukiwania książek na podstawie searchQuery
        List<Book> matchingBooks = scraperService.searchBooksByQuery(searchQuery);

        // Przekaż wyniki wyszukiwania do widoku
        model.addAttribute("matchingBooks", matchingBooks);

        return "searchResults"; // Nazwa widoku
    }

    @GetMapping("/top10Books")
    public String top10Books(Model model) throws IOException {
        List<Book> top10Books = scraperService.getTop10Books(); // Pobierz top 10 książek z serwisu
        model.addAttribute("top10Books", top10Books); // Przekaz top 10 książek do widoku
        return "top10Books"; // Nazwa widoku HTML do wyświetlenia listy top 10 książek
    }

}
