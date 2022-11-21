package ru.otus.homework.hw09.contoller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.homework.hw09.entity.dto.BookDto;
import ru.otus.homework.hw09.service.AuthorService;
import ru.otus.homework.hw09.service.BookService;
import ru.otus.homework.hw09.service.CommentService;
import ru.otus.homework.hw09.service.GenreService;

import javax.websocket.server.PathParam;

@Controller
@Slf4j
public class WebController {

    private final BookService bookService;

    private final GenreService genreService;

    private final AuthorService authorService;

    private final CommentService commentService;

    public WebController(BookService bookService,
                         GenreService genreService,
                         AuthorService authorService,
                         CommentService commentService) {
        this.bookService = bookService;
        this.genreService = genreService;
        this.authorService = authorService;
        this.commentService = commentService;
    }

    @GetMapping(value = "/")
    public String indexPageBook(Model model) {
        model.addAttribute("bookList", bookService.getAll());
        return "index";
    }

    @GetMapping(value = "/book")
    public String viewPageBook(@PathParam("isbn") String isbn, Model model) {
        model.addAttribute("book", bookService.getByIsbn(isbn));
        model.addAttribute("commentList", commentService.getAllByIsbn(isbn));
        return "view";
    }

    @GetMapping(value = "/new")
    public String newPageBook(Model model) {
        model.addAttribute("authorList", authorService.getAll());
        model.addAttribute("genreList", genreService.getAll());
        return "new";
    }

    @GetMapping(value = "/edit")
    public String editPageBook(@PathParam("isbn") String isbn, Model model) {
        model.addAttribute("book", bookService.getByIsbn(isbn));
        model.addAttribute("authorList", authorService.getAll());
        model.addAttribute("genreList", genreService.getAll());
        return "edit";
    }

    @GetMapping(value = "/delete")
    public String deletePageBook(@PathParam("isbn") String isbn, Model model) {
        model.addAttribute("isbn", isbn);
        return "delete";
    }

    @PostMapping(value = "/book")
    public String saveBook(BookDto book) {
        bookService.save(book);
        return "redirect:/";
    }

    @PostMapping(value = "/delete")
    public String deleteBook(@PathParam("isbn") String isbn, Model model) {
        bookService.deleteByIsbn(isbn);
        return "redirect:/";
    }
}
