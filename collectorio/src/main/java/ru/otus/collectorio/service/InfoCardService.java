package ru.otus.collectorio.service;

import ru.otus.collectorio.payload.request.infoCard.InfoCardExtRequest;
import ru.otus.collectorio.payload.request.infoCard.InfoCardRequest;
import ru.otus.collectorio.payload.response.infoCard.InfoCardExtResponse;
import ru.otus.collectorio.payload.response.infoCard.InfoCardResponse;

import java.util.List;

public interface InfoCardService {

    List<InfoCardResponse> getAllInCategory(Long id);

    InfoCardExtResponse findById(Long id);

    InfoCardResponse save(InfoCardRequest infoCardExtRequest);

    InfoCardExtResponse save(InfoCardExtRequest infoCardExtRequest);

    void deleteById(Long id);

}
