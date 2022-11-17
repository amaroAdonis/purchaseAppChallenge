package parser;

import java.io.IOException;
import java.util.List;

public interface CsvParser <T> {
    void append(T entity) throws IOException;
    void storeAll(List<T> entities) throws IOException;
    List<T> readAll() throws IOException;
}
