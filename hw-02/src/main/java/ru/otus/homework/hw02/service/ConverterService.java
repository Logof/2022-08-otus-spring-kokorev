package ru.otus.homework.hw02.service;

public interface ConverterService<T> {

    String toPrintable(T object);
}
