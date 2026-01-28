package SkillForge;

import java.util.Scanner;

public class Main {
    final public static Scanner userInputScanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Simple Calculator!");

        int choice = 0;

        while (true) {
            menu();
            choice = userInputScanner.nextInt();

            if (choice == 0) {
                System.out.println("Thanks for using Simple Calculator!");
                userInputScanner.close();
                break;
            }

            performOperation(choice);
        }
    }

    private static void performOperation(int choice) {
        switch (choice) {
            case 1: {
               add();
                break;
            }

            case 2: {
               sub();
                break;
            }

            case 3: {
              divide();
                break;
            }

            case 4: {
                multiply();
                break;
            }

            case 5:
                multiplicationTable();
                break;

            default:
                System.out.println("Invalid entry, please try again");
        }
    }


    public static void menu() {
        System.out.println("-".repeat(27));
        System.out.println("# Calculator Menu");
        System.out.println("-".repeat(27));
        System.out.println("1. Add two numbers");
        System.out.println("2. Subtract two numbers");
        System.out.println("3. Multiply two numbers");
        System.out.println("4. Divide two numbers");
        System.out.println("5. Create multiplication table");
        System.out.println("0. End your program");
        System.out.print("> Enter your option: ");

    }


    public static void add() {
        double a = getUserInput("Enter first number: ");
        double b = getUserInput("Enter second number: ");

        double result = a + b;
        System.out.printf("Result of : %.2f / %.2f = %.2f%n", a, b, result);
    }

    public static void sub() {
        double a = getUserInput("Enter first number");
        double b = getUserInput("Enter second number");

        double result = a - b;
        System.out.printf("Result of : %.2f / %.2f = %.2f%n", a, b, result);
    }

    public static void multiply() {
        double a = getUserInput("Enter first number");
        double b = getUserInput("Enter second number");

        double result = a * b;
        System.out.printf("Result of : %.2f / %.2f = %.2f%n", a, b, result);
    }

    public static void divide() {
        double a = getUserInput("Enter first number");
        double b = getUserInput("Enter second number");

        if (b == 0.0) {
            System.out.println("You cannot divide by zero");
            return;
        }

        double result = a / b;
        System.out.printf("Result of : %.2f / %.2f = %.2f%n", a, b, result);

    }

    public static void multiplicationTable() {
        double a = getUserInput("Enter a number to multiply: ");
        double b = getUserInput("Enter number of rows: ");

        System.out.println("\nMultiplication Table for " + a + ":");
        for (int i = 1; i <= b; i++) {
            System.out.println(a + " x " + i + " = " + (a * i));
        }

    }

    public static double getUserInput(String prompt) {
        System.out.println(prompt);
        while (!userInputScanner.hasNextDouble()) {
            System.out.println("Invalid input, enter a number");
            userInputScanner.next();
        }
        return userInputScanner.nextDouble();
    }


}
        /*for (int i = 1; i <= 3; i++) {
            System.out.println("Outer loop: " + i);

            for (int j = 1; j <= 3; j++) {
                if (i == 2 && j == 2) {
                    System.out.println("Break out of everything!"); // breaks out of inner loop
                    break;
                }
                System.out.println(" Inner loop: " + j);
            }
        }
        System.out.println("All done!");


         */
