package ru.otus.homework.service;

public interface AuthorService {

    void add(String fullName);

    void delete(long authorId);

    void outputAll();
}
