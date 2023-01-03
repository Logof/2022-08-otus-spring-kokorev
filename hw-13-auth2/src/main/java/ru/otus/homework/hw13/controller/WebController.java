package ru.otus.homework.hw13.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.homework.hw13.dto.BookDto;
import ru.otus.homework.hw13.mapper.BookMapper;
import ru.otus.homework.hw13.service.AuthorService;
import ru.otus.homework.hw13.service.BookService;
import ru.otus.homework.hw13.service.GenreService;

import javax.websocket.server.PathParam;

@Slf4j
@Controller
public class WebController {

    private final BookService bookService;

    private final AuthorService authorService;

    private final GenreService genreService;

    private final BookMapper bookMapper;

    public WebController(BookService bookService,
                         AuthorService authorService,
                         GenreService genreService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
        this.bookMapper = bookMapper;
    }

    @GetMapping(value = "/")
    public String indexPageBook(Model model) {
        model.addAttribute("bookList",  bookService.getAll());
        return "index";
    }

    @GetMapping(value = "/book")
    public String viewPageBook(@PathParam("isbn") long isbn, Model model) {
        model.addAttribute("book", bookService.getByIsbn(isbn));
        return "view";
    }

    @GetMapping(value = "/new")
    public String newPageBook(Model model) {
        model.addAttribute("authorList", authorService.getAll());
        model.addAttribute("genreList", genreService.getAll());
        return "new";
    }

    @GetMapping(value = "/edit")
    public String editPageBook(@PathParam("isbn") long isbn, Model model) {
        model.addAttribute("book", bookService.getByIsbn(isbn));
        model.addAttribute("authorList", authorService.getAll());
        model.addAttribute("genreList", genreService.getAll());
        return "edit";
    }

    @GetMapping(value = "/denied")
    public String denied() {
        return "redirect:/";
    }

    @GetMapping(value = "/delete")
    public String deletePageBook(@PathParam("isbn") long isbn, Model model) {
        model.addAttribute("isbn", isbn);
        return "delete";
    }

    @PostMapping(value = "/book")
    public String saveBook(BookDto book, Authentication authentication) {
        bookService.save(bookMapper.toEntity(book), authentication);
        return "redirect:/";
    }

    @PostMapping(value = "/delete")
    public String deleteBook(@PathParam("isbn") long isbn, Model model) {
        bookService.deleteById(isbn);
        return "redirect:/";
    }

}
