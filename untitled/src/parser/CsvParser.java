package parser;

import java.util.List;

public interface CsvParser <T> {
    void store(T entity);
    List<T> readAll();
}
