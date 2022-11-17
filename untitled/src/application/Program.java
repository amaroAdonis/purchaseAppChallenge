package application;

import entities.Product;
import entities.Purchase;
import entities.User;
import java.io.*;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) throws IOException {

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

                System.out.print("How many users will be entered? ");
                int total = sc.nextInt();

                for (int i = 1; i <= total; i++) {
                    System.out.print("Please, enter the User Name: ");
                    sc.nextLine();
                    String userName = sc.nextLine();
                    User user = new User();
                    user.setName(userName);
                    System.out.print("Now, enter the User Number: ");
                    int userId = sc.nextInt();
                    sc.nextLine();
                    user.setId(userId);


                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(userPath, true))) {


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
                    sc.nextLine();
                    product.setId(productId);


                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(productPath))) {

                        bw.write(product.toString());
                        bw.newLine();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    sc.nextLine();


                }
            } else if (answer2 == 'b') {
                Purchase<Object> purchase = new Purchase<>();
                System.out.print("Please, enter your user name: ");
                String name = sc.nextLine();
                System.out.println("How many products do you want to buy? ");
                int total = sc.nextInt();
                for (int i = 1; i <= total; i++) {
                    System.out.println("Please, insert the product number:");
                    int productToBuy = sc.nextInt();

                    try (BufferedReader br = new BufferedReader(new FileReader(userPath))) {
                        name = br.readLine();

                        try (BufferedWriter bw = new BufferedWriter(new FileWriter(purchasePath))) {
                            bw.write(name);

                        } catch (IOException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    } catch (IOException e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                    try (BufferedReader br2 = new BufferedReader(new FileReader(productPath))) {
                        productToBuy = br2.read();

                        try (BufferedWriter bw = new BufferedWriter(new FileWriter(purchasePath))){
                            bw.write(productToBuy);
                            bw.newLine();


                        }catch (IOException e2) {
                            System.out.println("Error: " + e2.getMessage());
                        }
                    } catch (IOException e3) {
                        System.out.println("Error: " + e3.getMessage());
                    }

                }


            } else {
                System.out.println("Invalid value. Please, insert a valid value (u/p/b)");
            }

        } else if (answer == 'd') {
            System.out.println("What do you want to delete (Users (u) / Products (p) / Purchases (b)) ");
            char answer3 = sc.next().charAt(0);
            if (answer3 == 'u') {
                System.out.println("Please, insert an user ID Number: ");
                int idToDelete = sc.nextInt();
                try (BufferedReader br = new BufferedReader(new FileReader(userPath))) {
                    int line = br.read();
                    while (line == idToDelete) {

                        System.out.println(line);
                        line = br.read();

                    }
                } catch (IOException e) {
                    System.out.println("Error: " + e.getMessage());
            }
        }
            }




         else if (answer == 's') {
            System.out.println();
        }




        sc.close();
    }


}
