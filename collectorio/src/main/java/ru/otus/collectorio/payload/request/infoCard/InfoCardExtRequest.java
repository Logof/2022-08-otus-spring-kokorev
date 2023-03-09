package ru.otus.collectorio.payload.request.infoCard;

import lombok.Getter;
import lombok.Setter;
import ru.otus.collectorio.payload.request.Request;

import java.util.Date;

@Getter
@Setter
public class InfoCardExtRequest extends InfoCardRequest implements Request {

    private Date releaseDate;

    private String releaseRegion;

    private String publisher;

    private String developer;

    private String genre;

    private Integer rating;

    private String boxText;

}
