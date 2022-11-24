package parser;

import entities.Product;
import entities.Purchase;
import entities.User;

import java.io.*;
import java.util.ArrayList;
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
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Purchase purchase : purchases) {
                bw.write(entityToString(purchase));
                bw.newLine();
            }
        }
    }

    @Override
    public List<Purchase> readAll() throws IOException {
        List<Purchase> purchases = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
            for (String line : br.lines().toList()){
                String[] splitValues = line.split(columnSymbol);

                Integer id = Integer.valueOf(splitValues[0]);
                Integer userId = Integer.valueOf(splitValues[1]);
                Integer productId = Integer.valueOf(splitValues[2]);

                User user = new User();
                user.setId(userId);
                Product product = new Product();
                product.setId(productId);

                Purchase purchase = new Purchase();
                purchase.setId(id);
                purchase.setUser(user);
                purchase.setProduct(product);

                purchases.add(purchase);
            }

        }
        return purchases;
    }
}

