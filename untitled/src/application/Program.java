package application;

import entities.Product;
import entities.Purchase;
import entities.User;
import parser.CsvParser;
import parser.ProductCsvParser;
import parser.PurchaseCsvParser;
import parser.UserCsvParser;

import java.io.*;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class Program {

    private static String getInitialAction(Scanner sc) {
        System.out.println("What procedure do you want to perform?");
        System.out.println("Insert (i) / Delete (d) / Show (s)");
        return sc.next();
    }

    private static String askStringInput(Scanner sc, String message) {
        System.out.printf("%s :", message);
        String answer = sc.nextLine();
        return answer;
    }

    private static Integer askIntegerInput(Scanner sc, String message) {
        System.out.printf("%s :", message);
        return sc.nextInt();
    }

    private static User getNewUser(Scanner sc) {
        var name = askStringInput(sc, "Please, enter the User Name");
        var id = askIntegerInput(sc, "Now, enter the User Number");

        User user = new User();
        user.setName(name);
        user.setId(id);
        return user;
    }

    private static Product getNewProduct(Scanner sc) {
        String productName = askStringInput(sc, "Please, enter the Product");
        int productId = askIntegerInput(sc, "Now, enter the Product Number");

        Product product = new Product();
        product.setName(productName);
        product.setId(productId);
        return product;
    }

    private static Purchase getNewPurchase(Scanner sc) {
        var userId = askIntegerInput(sc, "What user is purchasing");
        var productId = askIntegerInput(sc, "What product is being purchased");
        var purchaseId = askIntegerInput(sc, "What id for this purchase");

        User user = new User();
        user.setId(userId);

        Product product = new Product();
        product.setId(productId);

        Purchase purchase = new Purchase();
        purchase.setId(purchaseId);
        purchase.setUser(user);
        purchase.setProduct(product);

        return purchase;
    }

    private static void deleteUserInList(Integer id, List<User> users) {
        for (int i = 0; i < users.size(); i++) {
            var user = users.get(i);

            if (id == user.getId()) {
                users.remove(i);
                return;
            }
        }
    }

    private static void deleteProductInList(Integer id, List<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            var product = products.get(i);

            if (id == product.getId()) {
                products.remove(i);
                return;
            }
        }
    }

    private static void deletePurchaseInList(Integer id, List<Purchase> purchases) {
        for (int i = 0; i < purchases.size(); i++) {
            var purchase = purchases.get(i);

            if (id == purchase.getId()) {
                purchases.remove(i);
                return;
            }
        }
    }

    private static void deleteUser(Integer id, UserCsvParser parser) throws IOException {
        var users = parser.readAll();
        deleteUserInList(id, users);
        parser.storeAll(users);
    }

    private static void deleteProduct(Integer id, ProductCsvParser parser) throws IOException {
        var products = parser.readAll();
        deleteProductInList(id, products);
        parser.storeAll(products);
    }

    private static void deletePurchase(Integer id, PurchaseCsvParser parser) throws IOException {
        var purchases = parser.readAll();
        deletePurchaseInList(id, purchases);
        parser.storeAll(purchases);
    }

    private static void runDeleteActions(Scanner sc, UserCsvParser userCsvParser, ProductCsvParser productCsvParser, PurchaseCsvParser purchaseCsvParser) throws IOException {
        System.out.println("What do you want to delete? ");
        String deleteAnswer = askStringInput(sc, "Users (u) / Products (p) / Purchases (b): ");
        switch (deleteAnswer) {
            case "u" -> {
           Integer userToDelete = askIntegerInput(sc, "Please, insert user Id number: ");
            deleteUser(userToDelete, userCsvParser);
            }
            case "p" -> {
            Integer productToDelete = askIntegerInput(sc, "Please, insert product Id number: ");
            deleteProduct(productToDelete, productCsvParser);
            }
            case "b" -> {
            Integer purchaseToDelete = askIntegerInput(sc, "Please, insert purchase Id number: ");
            deletePurchase(purchaseToDelete, purchaseCsvParser);
            }
        }


    }

    private static void runShowActions (Scanner sc, UserCsvParser userCsvParser, ProductCsvParser productCsvParser, PurchaseCsvParser purchaseCsvParser) throws IOException {

        System.out.println("What do you want to delete? ");
        String showAnswer = askStringInput(sc, "Users (u) / Products (p) / Purchases (b): ");
        switch (showAnswer) {
            case "u" -> {
                var showUsers = userCsvParser.readAll();
                System.out.println(showUsers);
            }
            case "p" -> {
                var showProduct = productCsvParser.readAll();
                System.out.println(showProduct);
            }
            case "b" -> {
                var showPurchases = purchaseCsvParser.readAll();
                System.out.println(showPurchases);
            }
        }
    }

    private static void runInsertActions(Scanner sc, UserCsvParser userCsvParser, ProductCsvParser productCsvParser, PurchaseCsvParser purchaseCsvParser) throws IOException {
        System.out.println("What do you want to insert? ");

        String answer2 = askStringInput(sc, "Users (u) / Products (p) / Purchases (b)");
        switch (answer2) {
            case "u" -> {
                var user = getNewUser(sc);
                userCsvParser.append(user);
            }
            case "p" -> {
                var product = getNewProduct(sc);
                productCsvParser.append(product);
            }
            case "b" -> {
                var purchase = getNewPurchase(sc);
                purchaseCsvParser.append(purchase);
            }
        }
    }

        public static void main (String[]args){

            String userPath = "D:\\adonis\\estudosJavaDev\\purchaseAppChallenge\\users.csv";
            String productPath = "D:\\adonis\\estudosJavaDev\\purchaseAppChallenge\\products.csv";
            String purchasePath = "D:\\adonis\\estudosJavaDev\\purchaseAppChallenge\\purchases.csv";

            String columnSymbol = ",";
            UserCsvParser userCsvParser = new UserCsvParser(userPath, columnSymbol);
            ProductCsvParser productCsvParser = new ProductCsvParser(productPath, columnSymbol);
            PurchaseCsvParser purchaseCsvParser = new PurchaseCsvParser(purchasePath, columnSymbol);

            try (Scanner sc = new Scanner(System.in)) {
                String answer = getInitialAction(sc);
                switch (answer) {
                    case "i" -> runInsertActions(sc, userCsvParser, productCsvParser, purchaseCsvParser);
                    case "d" -> runDeleteActions(sc, userCsvParser, productCsvParser, purchaseCsvParser);
                    case "s" -> runShowActions(sc, userCsvParser, productCsvParser, purchaseCsvParser);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }





}
