package application;

import entities.Product;
import entities.Purchase;
import entities.User;
import parser.UserCsvParser;

import java.io.*;
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
        return sc.nextLine();
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
        String productName = askStringInput(sc,"Please, enter the Product");
        int productId = askIntegerInput(sc,"Now, enter the Product Number");

        Product product = new Product();
        product.setName(productName);
        product.setId(productId);
        return product;
    }

    private static Purchase getNewPurchase(Scanner sc) {
        var userId = askIntegerInput(sc,"What user is purchasing");
        var productId = askIntegerInput(sc,"What product is being purchased");
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
        for (int i=0; i < users.size(); i++) {
            var user = users.get(i);

            if (id == user.getId()) {
                users.remove(i);
                return;
            }
        }
    }

    private static void deleteUser(Integer id, UserCsvParser parser) throws IOException {
        var users = parser.readAll();
        deleteUserInList(id, users);
        parser.storeAll(users);
    }

    private static void runInsertActions(Scanner sc, UserCsvParser userCsvParser) throws IOException {
        System.out.println("What do you want to insert? ");

        String answer2 = askStringInput(sc,"Users (u) / Products (p) / Purchases (b)");
        switch (answer2) {
            case "u":
                var user = getNewUser(sc);
                userCsvParser.append(user);
                break;
            case "p":
                var product = getNewProduct(sc);
                // todo: productCsvParser.store(product);
                break;
            case "b":
                var purchage = getNewPurchase(sc);
                // todo: purchaseCsvParser.store(product);
                break;
        }
    }

    public static void main(String[] args) {

        String userPath = "D:\\adonis\\estudosJavaDev\\purchaseAppChallenge\\users.csv";
        String productPath = "D:\\adonis\\estudosJavaDev\\purchaseAppChallenge\\products.csv";
        String purchasePath = "D:\\adonis\\estudosJavaDev\\purchaseAppChallenge\\purchases.csv";

        String columnSymbol = ",";
        UserCsvParser userCsvParser = new UserCsvParser(userPath, columnSymbol);

        try (Scanner sc = new Scanner(System.in)) {
            String answer = getInitialAction(sc);
            switch (answer) {
                case "i":
                    runInsertActions(sc, userCsvParser);
                    break;
                case "d":

                    break;
                case "s":
                    break;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
