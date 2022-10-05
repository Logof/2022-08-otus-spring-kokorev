package ru.otus.homework.shell.publisher;

public interface EventsPublisher {
    void outputBook(String isbn);

    void outputAllBooks();

    void addBook(String isbn, String bookName);

    void deleteBookById(String isbn);

    void addAnAuthorToABook(String isbn, long authorId);

    void addAnGenreToABook(String isbn, long genreId);

    void removeTheAuthorFromTheBook(String isbn, long authorId);

    void removeTheGenreFromTheBook(String isbn, long genreId);

    void outputAllAuthors();

    void addAuthor(String fullName);

    void deleteAuthorById(long authorId);

    void outputAllGenres();

    void addGenre(String genreName);

    void deleteGenreById(long genreId);
}
