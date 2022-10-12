package ru.otus.homework.service;

import java.util.List;
import java.util.Optional;

public interface PrintService<T> {

    String objectsToPrint(List<T> objects);

    String objectToPrint(Optional<T> object);

}
