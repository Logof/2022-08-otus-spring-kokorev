package ru.otus.homework.hw08.repository;

public interface BookMongodbRepository {
    void updateDocumentTitle(String id, String title);
}
