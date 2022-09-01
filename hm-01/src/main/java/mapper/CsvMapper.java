package mapper;

public interface CsvMapper {

    <T> T toEntity(String[] rawData);
}
