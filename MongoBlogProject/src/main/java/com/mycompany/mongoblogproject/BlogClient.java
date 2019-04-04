package com.mycompany.mongoblogproject;



import java.util.*;

public class BlogClient {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        //Variables
        boolean flag = true;

        //Show login menu
        loginMenu();
        System.out.print("\nEnter command: ");
        int userCommand = scanner.nextInt();

        while (flag) {
            switch (userCommand) {
                case 1:
                    break;
                case 2:
                    break;
                    default:
                        System.out.println("invalid command");

            }
        }



    }

    public static void loginMenu(){
        System.out.println("\n----------Welcome----------------\n" +
                             "\n1 - Login " +
                            "\n2 - Register");

    }

    public static void login(String userName, String password){

    }

    public static void register(String userName, String password) {

    }


}






