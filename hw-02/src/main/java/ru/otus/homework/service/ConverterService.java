package ru.otus.homework.service;

public interface ConverterService<T> {

    String toPrintable(T object);
}
