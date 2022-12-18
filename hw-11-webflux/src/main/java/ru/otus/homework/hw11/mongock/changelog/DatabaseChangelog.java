package ru.otus.homework.hw11.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.homework.hw11.entity.Author;
import ru.otus.homework.hw11.entity.Book;
import ru.otus.homework.hw11.entity.Comment;
import ru.otus.homework.hw11.entity.Genre;
import ru.otus.homework.hw11.repository.AuthorRepository;
import ru.otus.homework.hw11.repository.BookRepository;
import ru.otus.homework.hw11.repository.CommentRepository;
import ru.otus.homework.hw11.repository.GenreRepository;

import java.util.ArrayList;
import java.util.List;

@ChangeLog
public class DatabaseChangelog {
    private final List<Author> authors = new ArrayList<>();
    private final List<Genre> genres = new ArrayList<>();
    private final List<Comment> comments = new ArrayList<>();
    private final List<Book> books = new ArrayList<>();

    @ChangeSet(order = "001", id = "clearDb", runAlways = true, author = "logof")
    public void clearDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "initAuthors", author = "logof")
    public void initAuthors(AuthorRepository authorRepository) {
        authors.add(authorRepository.save(new Author( "Русские народные сказки")).block());
        authors.add(authorRepository.save(new Author( "Якоб и Вильгельм Гримм")).block());
        authors.add(authorRepository.save(new Author( "Шарль Перро")).block());
        authors.add(authorRepository.save(new Author( "Джозеф Джекобс")).block());
        authors.add(authorRepository.save(new Author( "Андерсен Ханс Кристиан")).block());
    }

    @ChangeSet(order = "003", id = "initGenres", author = "logof")
    public void initGenres(GenreRepository genreRepository) {
        genres.add(genreRepository.save(new Genre( "Сказки")).block());
    }

    @ChangeSet(order = "004", id = "initBooks", author = "logof")
    public void initBooks(BookRepository bookRepository) {
        books.add(bookRepository.save(new Book("978-5-699-12014-7",
                "Колобок",
                List.of(authors.get(0)),
                List.of(genres.get(0)))).block());
        books.add(bookRepository.save(new Book("978-5-04-094119-3",
                "100 лучших сказок всех времен и народов",
                List.of(authors.get(1), authors.get(2), authors.get(3), authors.get(4)),
                List.of(genres.get(0)))).block());
    }

    @ChangeSet(order = "005", id = "initComments", author = "logof")
    public void initComments(CommentRepository commentRepository) {
        comments.add(commentRepository.save(new Comment("Классный сборник сказок Андерсена", books.get(1))).block());
        comments.add(commentRepository.save(new Comment("Увлекательная", books.get(1))).block());
        comments.add(commentRepository.save(new Comment( "Классный сборник сказок!!!", books.get(1))).block());
        comments.add(commentRepository.save(new Comment( "Братья Гримм - форевер", books.get(1))).block());
        comments.add(commentRepository.save(new Comment( "Подарите мне эту книжку на день рождения. Костя 3 года", books.get(1))).block());
    }
}
