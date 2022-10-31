package ru.otus.homework.hw04.service;

public interface ConverterService<T> {

    String toPrintable(T object);
}
