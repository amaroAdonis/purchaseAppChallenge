package parser;

import entities.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCsvParser implements CsvParser<Product> {

    private final String filePath;
    private final String columnSymbol;

    public ProductCsvParser(String filePath, String columnSymbol) {
        this.filePath = filePath;
        this.columnSymbol = columnSymbol;
    }

    private String generateLine(Product product) {
        return product.getId() + "," + product.getName();
    }

    private Product createProduct(String line) {
        String[] column = line.split(columnSymbol);
        Integer id = Integer.valueOf(column[0]);
        Product product = new Product();
        product.setId(id);
        product.setName(column[1]);
        return product;
    }

    private void writeProductInCsv(BufferedWriter bw, Product product) throws IOException {
        var csvLine = generateLine(product);
        bw.write(csvLine);
        bw.newLine();
    }

    @Override
    public void append(Product product) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            writeProductInCsv(bw, product);
        }
    }

    @Override
    public void storeAll(List<Product> products) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (var product : products) {
                writeProductInCsv(bw, product);
            }
        }
    }

    @Override
    public List<Product> readAll() throws IOException {
        var productList = new ArrayList<Product>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            List<String> lines = br.lines().toList(); // ["1,Maçã","2,Pera"]
            for (var line : lines) {// "1,Macã"
                var product = createProduct(line);
                productList.add(product);
            }
        }
        return productList;
    }
}
