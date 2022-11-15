package Application;

import Entities.Product;
import Entities.User;

import javax.crypto.spec.PSource;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Program {

    public static String main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        String userPath = "D:\\adonis\\estudosJavaDev\\purchaseAppChallenge\\users.csv";
        String productPath = "D:\\adonis\\estudosJavaDev\\purchaseAppChallenge\\products.csv";
        String purchasePath = "D:\\adonis\\estudosJavaDev\\purchaseAppChallenge\\purchases.csv";


        System.out.println("What procedure do you want to perform?");
        System.out.println("Insert (i) / Delete (d) / Show (s)");
        char answer = sc.next().charAt(0);
        char answer2;
        if (answer == 'i') {

            System.out.println("What do you want to insert? ");
            System.out.println("Users (u) / Products (p) / Purchases (b)");
            answer2 = sc.next().charAt(0);

            if (answer2 == 'u') {

                System.out.print("How many users will be entered?");
                int total = sc.nextInt();

                for (int i = 1; i <= total; i++) {
                    System.out.print("Please, enter the User Name: ");
                    String userName = sc.nextLine();
                    User user = new User();
                    user.setName(userName);
                    System.out.print("Now, enter the User Number: ");
                    int userId = sc.nextInt();
                    user.setId(userId);
                    sc.nextLine();

                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(userPath))) {

                        bw.write(user.toString());
                        bw.newLine();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            } else if (answer2 == 'p') {

                System.out.print("How many products will be entered?");
                int total = sc.nextInt();

                for (int i = 1; i <= total; i++) {
                    System.out.print("Please, enter the Product: ");
                    String productName = sc.nextLine();
                    Product product = new Product();
                    product.setName(productName);
                    System.out.print("Now, enter the Product Number: ");
                    int productId = sc.nextInt();
                    product.setId(productId);
                    sc.nextLine();

                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(productPath))) {

                        bw.write(product.toString());
                        bw.newLine();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            } else {
                System.out.print("How many purchases will be entered?");
                int total = sc.nextInt();
                for (int i = 1; i <= total; i++) {
                    System.out.print("Please, enter the product name to search it:");

                    Scanner scanner = new Scanner(new File(purchasePath));
                    while (sc.hasNextLong()) {
                        long aLong = sc.nextLong();
                    }



                }
            }

        } else if () {


        }

        sc.close();
    }


}
