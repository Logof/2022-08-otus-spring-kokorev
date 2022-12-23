package ru.otus.homework.hw14.mapper;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import ru.otus.homework.hw14.entity.h2.Author;
import ru.otus.homework.hw14.entity.h2.Book;
import ru.otus.homework.hw14.entity.h2.Comment;
import ru.otus.homework.hw14.entity.h2.Genre;
import ru.otus.homework.hw14.entity.mongo.AuthorDocument;
import ru.otus.homework.hw14.entity.mongo.BookDocument;
import ru.otus.homework.hw14.entity.mongo.CommentDocument;
import ru.otus.homework.hw14.entity.mongo.GenreDocument;
import ru.otus.homework.hw14.repository.MongoAuthorRepository;
import ru.otus.homework.hw14.repository.MongoGenreRepository;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class EntityToDocumentConverter {

    private final MongoGenreRepository genreRepository;

    private final MongoAuthorRepository authorRepository;

    private final Map<Author, AuthorDocument> authorMap = new ConcurrentHashMap<>();

    private final Map<Genre, GenreDocument> genreMap = new ConcurrentHashMap<>();

    private final Map<String, BookDocument> bookMap = new ConcurrentHashMap<>();

    public EntityToDocumentConverter(MongoGenreRepository genreRepository,
                                     MongoAuthorRepository authorRepository) {
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
    }

    public AuthorDocument authorTransformation (Author source) {
        return new AuthorDocument(new ObjectId().toString(), source.getFullName());
    }

    public GenreDocument genreTransformation (Genre source) {
        return new GenreDocument(new ObjectId().toString(), source.getGenreName());
    }

    public BookDocument bookTransformation (Book source) {
        BookDocument target = new BookDocument();
        target.setId(source.getIsbn());
        target.setTitle(source.getTitle());
        target.setAuthors(new ArrayList<>());
        target.setGenres(new ArrayList<>());

        if (source.getGenres() != null && source.getGenres().size() != 0) {
            for (Genre genre : source.getGenres()){
                GenreDocument genreDocument = genreMap.get(genre);
                target.getGenres().add(genreDocument);
            }
        }

        if (source.getAuthors() != null && source.getAuthors().size() != 0) {
            for (Author author : source.getAuthors()) {
                AuthorDocument authorDocument = authorMap.get(author);
                target.getAuthors().add(authorDocument);
            }
        }
        bookMap.put(source.getIsbn(), target);
        return target;
    }

    public CommentDocument commentTransformation (Comment source) {
        CommentDocument target = new CommentDocument();
        target.setCommentText(source.getCommentText());
        target.setBook(bookMap.get(source.getBook().getIsbn()));
        return target;
    }

}
