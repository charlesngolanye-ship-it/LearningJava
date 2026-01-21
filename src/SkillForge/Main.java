package SkillForge;

import java.time.LocalTime;
import java.util.Random;
import java.util.Scanner;

public class Main {
    final public static Scanner userInputScanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        double a = 0;
        double b = 0;

        System.out.println("Welcome to Simple Calculator!");

        while (true) {

            switch (menu()) {
                case 1:
                    add(a, b);
                    break;

                case 2:
                    sub(a, b);
                    break;

                case 3:
                    multiply(a, b);
                    break;

                case 4:
                    divide(a, b);
                    break;

                case 5:
                    multiplicationTable(a,b);
                    break;

                case 0:
                    System.out.println("Thank you for using Simple Calculator!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid entry, please try again");
            }
        }
    }


    public static int menu() {
        System.out.println("---------------------------");
        System.out.println("# Calculator Menu");
        System.out.println("---------------------------");
        System.out.println("1. Add two numbers");
        System.out.println("2. Subtract two numbers");
        System.out.println("3. Multiply two numbers");
        System.out.println("4. Divide two numbers");
        System.out.println("5. Create multiplication table");
        System.out.println("q. End your program");
        System.out.print("> Enter your option: ");

        return input();

    }

    public static int input() {
        int number = 0;

        while (true) {
            if (userInputScanner.hasNextInt()) {
                number = Math.abs(userInputScanner.nextInt());
                userInputScanner.nextLine();
                if (number > 0) {
                    break;
                } else {
                    System.out.println("Invalid entry, please try again");
                }
            } else if (userInputScanner.hasNext()) {
                String inString = userInputScanner.nextLine();
                if (inString.equalsIgnoreCase("q")) {
                    number = 0;
                    break;
                } else {
                    System.out.println("Invalid entry, please enter a number or 'q' to quit");
                }
            }
        }
        return number;
    }

    public static void add (double a, double b) {
        System.out.println("Enter first number: ");
        a = userInputScanner.nextDouble();
        System.out.println("Enter second number: ");
        b = userInputScanner.nextDouble();
        double additionResult =  a + b;

        System.out.println("Result: " + a + " + " + b + " = " + additionResult );
        System.out.println();

    }

    public static void sub (double a, double b) {
        System.out.println("Enter first number: ");
        a = userInputScanner.nextDouble();
        System.out.println("Enter second number: ");
        b = userInputScanner.nextDouble();

        double subtractionResult = a - b;

        System.out.println("Result: " + a + " - " + b + " = " + subtractionResult );
        System.out.println();
    }

    public static void multiply (double a, double b) {
        System.out.println("Enter first number: ");
        a = userInputScanner.nextDouble();
        System.out.println("Enter second number: ");
        b = userInputScanner.nextDouble();

        double multiplicationResult = a * b;

        System.out.println("Result: " + a + " * " + b + " = " + multiplicationResult);
        System.out.println();
    }

    public static void divide (double a, double b) {

            System.out.println("Enter first number: ");
            a = userInputScanner.nextDouble();
            System.out.println("Enter second number: ");
            b = userInputScanner.nextDouble();

            if (b == 0.0) {
                throw new IllegalArgumentException("Divide by zero");
            }

            double divideResult = a / b;

            System.out.println("Result: " + a + " / " + b + " = " + divideResult);
            System.out.println();
    }

    public static void multiplicationTable (double a, double b) {
        System.out.print("Enter a number to multiply: ");
        a = userInputScanner.nextInt();
        System.out.print("Enter number of rows: ");
        b = userInputScanner.nextInt();

        System.out.println("\nMultiplication Table for " + a + ":");
        for (int i = 1; i <= b; i++) {
            System.out.println(a + " x " + i + " = " + (a * i));
        }

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
