package parser;

import entities.Product;
import entities.Purchase;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PurchaseCsvParser implements CsvParser <Purchase> {

    private final String filePath;
    private final String columnSymbol;

    public PurchaseCsvParser(String filePath, String columnSymbol) {
        this.filePath = filePath;
        this.columnSymbol = columnSymbol;
    }

    String entityToString(Purchase purchase) {
        return purchase.getId() + columnSymbol
                + purchase.getUser().getId() + columnSymbol
                + purchase.getProduct().getId();
    }

    @Override
    public void append(Purchase purchase) throws IOException {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            String line = entityToString(purchase);
            bw.write(line);
            bw.newLine();
        }
    }



    @Override
    public void storeAll(List<Purchase> purchases) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))){
              for (Purchase purchase: purchases){
                bw.write(entityToString(purchase));
                bw.newLine();
            }


        }

    }

    @Override
    public List<Purchase> readAll() throws IOException {
        return null;
    }
}
