package ru.otus.collectorio.payload.response.infoCard;

import lombok.Getter;
import lombok.Setter;
import ru.otus.collectorio.payload.response.Response;

import java.util.Date;

@Getter
@Setter
public class InfoCardExtResponse extends InfoCardResponse implements Response {

    private Date releaseDate;

    private String releaseRegion;

    private String publisher;

    private String developer;

    private String genre;

    private Integer rating;

    private String boxText;

}