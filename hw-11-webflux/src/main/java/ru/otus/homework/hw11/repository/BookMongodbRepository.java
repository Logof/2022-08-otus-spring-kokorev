package ru.otus.homework.hw11.repository;

public interface BookMongodbRepository {
    void updateDocumentTitle(String id, String title);
}
