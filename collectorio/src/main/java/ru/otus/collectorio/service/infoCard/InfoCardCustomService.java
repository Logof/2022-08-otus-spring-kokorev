package ru.otus.collectorio.service.infoCard;

import ru.otus.collectorio.payload.response.infoCard.InfoCardExtResponse;

import java.util.List;

public interface InfoCardCustomService {
    List<InfoCardExtResponse> getAllInCategory(Long categoryId);
}
