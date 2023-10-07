package com.example.damian_jozwiak_99794.web.admin;

import com.example.damian_jozwiak_99794.domain.webscraper.Book;
import com.example.damian_jozwiak_99794.domain.webscraper.ScraperService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/csv")
public class CsvController {
    private final ScraperService scraperService;

    public CsvController(ScraperService scraperService) {
        this.scraperService = scraperService;
    }

    @GetMapping("/generate")
    public String generateCsv(Model model) {
        return "index"; // To jest nazwa widoku HTML, który będzie zawierać przycisk do generowania CSV
    }

    @GetMapping("/download")
    @ResponseBody
    public void downloadCsv(HttpServletResponse response) throws IOException {
        List<Book> books = scraperService.scrapeBooks();

        // Ustaw nagłówki odpowiedzi HTTP do obsługi pliku CSV
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=books.csv");

        // Uzyskaj dostęp do strumienia odpowiedzi
        ServletOutputStream outputStream = response.getOutputStream();

        // Zapisz dane do strumienia odpowiedzi
        scraperService.saveBooksToCsv(books, outputStream);

        // Wymuś opróżnienie i zamknięcie strumienia odpowiedzi
        outputStream.flush();
        outputStream.close();
    }
}
