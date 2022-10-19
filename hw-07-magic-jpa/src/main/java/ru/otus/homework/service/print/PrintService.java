package ru.otus.homework.service.print;

import java.util.Set;

public interface PrintService<T> {

    String objectsToPrint(Set<T> objects);

    String objectToPrint(T object);
}
