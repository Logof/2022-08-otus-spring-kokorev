package ru.otus.homework.hw03.service;

public interface ConverterService<T> {

    String toPrintable(T object);
}
