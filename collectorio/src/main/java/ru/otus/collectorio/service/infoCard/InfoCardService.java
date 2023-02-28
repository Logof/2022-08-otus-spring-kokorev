package ru.otus.collectorio.service.infoCard;

import ru.otus.collectorio.payload.request.Request;
import ru.otus.collectorio.payload.response.Response;

import java.io.Serializable;
import java.util.List;

public interface InfoCardService<Rq extends Request, Rs extends Response, ID extends Serializable> {
    List<Rs> getAll();

    Rs findById(ID id);

    void deleteById(ID id);
}
