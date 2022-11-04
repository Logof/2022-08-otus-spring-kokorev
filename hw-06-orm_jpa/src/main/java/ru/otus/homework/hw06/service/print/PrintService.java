package ru.otus.homework.hw06.service.print;

import java.util.Set;

public interface PrintService<T> {

    String objectsToPrint(Set<T> objects);

    String objectToPrint(T object);
}
