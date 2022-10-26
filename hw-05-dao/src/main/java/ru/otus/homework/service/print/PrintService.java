package ru.otus.homework.service.print;

import java.util.List;

public interface PrintService<T> {

    String objectsToPrint(List<T> objects);

    String objectToPrint(T object);
}
