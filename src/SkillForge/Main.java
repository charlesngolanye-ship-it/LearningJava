package SkillForge;

import java.time.LocalTime;
import java.util.Random;
import java.util.Scanner;

public class Main {
    final public static Scanner userInputScanner = new Scanner(System.in);

    public static void main(String[] args) {
        double result;
        double a = 0;
        double b = 0;

        System.out.println("Welcome to Simple Calculator!");

        while (true) {

            switch (menu()) {
                case 1: {
                    double[] values = prompt();
                    a = values[0];
                    b = values[1];

                    result = add(a, b);
                    System.out.println("Result: " + a + " + " + b + " = " + result);
                    System.out.println();
                    break;
                }

                case 2: {
                    double[] values = prompt();
                    a = values[0];
                    b = values[1];

                    result = sub(a, b);
                    System.out.println("Result: " + a + " - " + b + " = " + result);
                    System.out.println();
                    break;
                }

                case 3: {
                    double[] values = prompt();
                    a = values[0];
                    b = values[1];

                    result = multiply(a, b);
                    System.out.println("Result: " + a + " * " + b + " = " + result);
                    System.out.println();
                    break;
                }

                case 4: {
                    try {
                        double[] values = prompt();
                        a = values[0];
                        b = values[1];

                        result = divide(a, b);
                        System.out.println("Result: " + a + " / " + b + " = " + result);
                        System.out.println();
                    } catch (ArithmeticException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println();
                    break;
                }

                case 5:
                    multiplicationTable(a,b);
                    break;

                default:
                    System.out.println("Invalid entry, please try again");
            }
        }
    }


    public static int menu() {
        System.out.println("-".repeat(27));
        System.out.println("# Calculator Menu");
        System.out.println("-".repeat(27));
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

    public static double add (double a, double b) {
        return a + b;
    }

    public static double sub (double a, double b) {
        return a - b;
    }

    public static double multiply (double a, double b) {
        return a * b;
    }

    public static double divide (double a, double b) {
            if (b == 0.0) {
                throw new IllegalArgumentException("Error, cannot divide by zero");
            }

            return a / b;
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

    public static double[] prompt() {
        System.out.println("Enter first number: ");
        double a = userInputScanner.nextDouble();

        System.out.println("Enter second number: ");
        double b = userInputScanner.nextDouble();

        return new double[] {a, b};
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
