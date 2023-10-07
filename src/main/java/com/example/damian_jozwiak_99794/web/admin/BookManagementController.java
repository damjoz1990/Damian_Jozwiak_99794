package com.example.damian_jozwiak_99794.web.admin;

import com.example.damian_jozwiak_99794.domain.book.BookService;
import com.example.damian_jozwiak_99794.domain.book.dto.BookSaveDto;
import com.example.damian_jozwiak_99794.domain.genre.GenreService;
import com.example.damian_jozwiak_99794.domain.genre.dto.GenreDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BookManagementController {
    private final BookService bookService;
    private final GenreService genreService;

    public BookManagementController(BookService bookService, GenreService genreService) {
        this.bookService = bookService;
        this.genreService = genreService;
    }

    @GetMapping("/admin/dodaj-ksiazke")
    public String addBookFrom(Model model){
        List<GenreDto> allGenres = genreService.findAllGenres();
        model.addAttribute("genres", allGenres);
        BookSaveDto book = new BookSaveDto();
        model.addAttribute("book", book);
        return "admin/book-form";

    }

    @PostMapping("/admin/dodaj-ksiazke")
    public String addBook(BookSaveDto book, RedirectAttributes redirectAttributes){
        bookService.addBook(book);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Książka została zapisana".formatted(book.getName()));
        return "redirect:/admin";
    }
}
