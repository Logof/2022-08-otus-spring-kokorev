package ru.otus.homework.hw18.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.homework.hw18.dto.BookDto;
import ru.otus.homework.hw18.mapper.BookMapper;
import ru.otus.homework.hw18.service.AuthorService;
import ru.otus.homework.hw18.service.BookService;
import ru.otus.homework.hw18.service.GenreService;

import javax.websocket.server.PathParam;
import java.util.ArrayList;

@Slf4j
@Controller
public class WebController {

    private final BookService bookService;

    private final AuthorService authorService;

    private final GenreService genreService;

    private final BookMapper bookMapper;

    public WebController(@Qualifier("hystrixBookServiceImpl") BookService bookService,
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
    public String viewPageBook(@PathParam("isbn") Long isbn, Model model) {
        model.addAttribute("book", bookService.getByIsbn(isbn));
        return "view";
    }

    @GetMapping(value = "/new")
    @HystrixCommand(commandKey="getFallBook", fallbackMethod="fallbackNew")
    public String newPageBook(Model model) {
        model.addAttribute("authorList", authorService.getAll());
        model.addAttribute("genreList", genreService.getAll());
        return "new";
    }

    @GetMapping(value = "/edit")
    @HystrixCommand(commandKey="getFallBook", fallbackMethod="fallbackEdit")
    public String editPageBook(@PathParam("isbn") Long isbn, Model model) {
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
    public String deletePageBook(@PathParam("isbn") Long isbn, Model model) {
        model.addAttribute("isbn", isbn);
        return "delete";
    }

    @PostMapping(value = "/book")
    public String saveBook(BookDto book, Authentication authentication) {
        bookService.save(bookMapper.toEntity(book), authentication);
        return "redirect:/";
    }

    @PostMapping(value = "/delete")
    public String deleteBook(@PathParam("isbn") Long isbn, Model model) {
        bookService.deleteById(isbn);
        return "redirect:/";
    }


    private String fallback(Model model) {
        model.addAttribute("bookList",  new ArrayList<>());
        return "index";
    }

    private String fallbackId(Long isbn, Model model) {
        model.addAttribute("book", new BookDto(isbn, "N/A", new ArrayList<>(), new ArrayList<>()));
        return "view";
    }

    private String fallbackNew(Model model) {
        model.addAttribute("authorList", new ArrayList<>());
        model.addAttribute("genreList", new ArrayList<>());
        return "new";
    }

    private String fallbackEdit(Long isbn, Model model) {
        model.addAttribute("book", new BookDto(isbn, "N/A", new ArrayList<>(), new ArrayList<>()));
        model.addAttribute("authorList", new ArrayList<>());
        model.addAttribute("genreList", new ArrayList<>());
        return "edit";
    }

}
