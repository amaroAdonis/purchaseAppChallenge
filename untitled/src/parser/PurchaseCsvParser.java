package parser;

import entities.Purchase;

import java.io.IOException;
import java.util.List;

public class PurchaseCsvParser implements CsvParser <Purchase> {


    @Override
    public void append(Purchase entity) throws IOException {

    }

    @Override
    public void storeAll(List<Purchase> entities) throws IOException {

    }

    @Override
    public List<Purchase> readAll() throws IOException {
        return null;
    }
}
