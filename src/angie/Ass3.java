package angie;

import java.util.Scanner;
// Please don't forget to add pseudocode to your methods and classes.
public class Ass3 {
        //Creation of scanner object.
        private static Scanner userInputScanner = new Scanner(System.in);
        //Constants
        static final int QUIT = -1;

        /**
         * This method should be used only for unit testing on CodeGrade. Do not change this method!
         * Do not remove this method!
         * Swaps userInputScanner with a custom scanner object bound to a test input stream
         *
         * @param inputScanner - test scanner object
         */
        public static void injectInput(final Scanner inputScanner) {
            userInputScanner = inputScanner;
        }


        public static void main(final String[] args) {
            int radius = 0;
            int height = 0;
            int numerator = 0;
            int denominator = 0;

            //Print the header of the program for area and volume.
            System.out.println("----------------------------------");
            System.out.println("# Test of area and volume methods");
            System.out.println("----------------------------------");

            // While loop that runs until user enters "q" for area and volume.
            while (true) {

                radius = input();
                if (radius == QUIT) {
                    break;
                }

                height = input();
                if (height == QUIT) {
                    break;
                }

                System.out.println("r = " + radius + ", h = " + height);
                System.out.printf("Circle area: %.2f %n", area(radius));
                System.out.printf("Cone area: %.2f %n", area(radius, height));
                System.out.printf("Cone volume: %.2f %n", volume(radius, height));
            }

            //Print the header of the program for fractional methods.
            System.out.println("----------------------------------");
            System.out.println("# Test of the fractional methods");
            System.out.println("----------------------------------");


            // While loop that runs until user enters "q" for the fraction part
            while (true) {

                numerator = input();
                if (numerator == QUIT) {
                    break;
                }

                denominator = input();
                if (denominator == QUIT) {
                    break;
                }

                System.out.printf("%d/%d = ", numerator, denominator);
                printFraction(fraction(numerator, denominator));
            }
            userInputScanner.close();
        }

    /**
     * Method takes user input from the scanner object
     * @return user input
     */
    public static int input() {
            int number = 0;
            while (true) {
                if (userInputScanner.hasNextInt()) {
                    number = Math.abs(userInputScanner.nextInt());
                   if (number >= 0) {
                       break;
                   }
                    break;
                }
                else if (userInputScanner.hasNext()) {
                    String inString = userInputScanner.next(); // nextLine()?
                    if (inString.equalsIgnoreCase("q")) {
                        number = -1;
                        break;
                    }
                }
            }
            return number;
    }

    /**
     * Method calculates area of a circle
     * @param radius radius
     * @return area
     */
    public static double area(final int radius) {
            double area = Math.PI * Math.pow(radius, 2);
            return area;
    }

    /**
     * Method calculates surface area of a cone
     * @param radius radius
     * @param height height
     * @return area
     */
    public static double area(final int radius, final int height) {
            double area = Math.PI * radius  * pythagoras(radius, height); // not side A, side B?
            return area;
    }

    /**
     * Method calculates hypotenuse using pythagoras theorem
     * @param sideA base
     * @param sideB height
     * @return s
     */
    public static double pythagoras(final int sideA, final int sideB) {
            double sSquared = Math.pow(sideA, 2) + Math.pow(sideB, 2);
            double s = Math.sqrt(sSquared);
            return s;
    }

    /**
     * Method calculates volume?
      * @param radius radius
     * @param height height
     *
     */
    public static double volume(final int radius, final int height) {
            double volume = (Math.PI * Math.pow(radius, 2) * height) / 3;
            return volume;
    }

    /**
     *
     * @param numerator numerator
     * @param denominator denominator
     * @return array?
     */
    public static int[] fraction(final int numerator, final int denominator) {
        int[] array = new int[3];
        array[0] = 0;
        array[1] = 0;
        array[2] = 0;

        if (denominator == 0) {
            return null;
        }
        else if (numerator == 0) {
            return array;
        }
        else {
            array[0] = numerator / denominator;
            array[1] = (numerator % denominator) / gcd(numerator, denominator);
            array[2] = (denominator) / gcd(numerator, denominator);
        }

        return array; // an issue
    }

    /**
     * Method calculates GCD using Euclid's algorithm as below
     * Make sure that a>b(change the place if necessary)
     * As long as b is not 0
     * c = a % b
     * a = b     *  = c
     * return a
     *
     * @param a first integer a
     * @param b second integer b
     * @return GCD of a and b
     */
        public static int gcd(final int a, final int b) {
            int tempA = a;
            int tempB = b;
            int tempC = 0;

            // Make sure that a>b(change the place if necessary)
            if (a <= b) {
                int temp =  tempA;
                tempA = tempB;
                tempB = temp;
            }
            while (tempB != 0) {
                tempC = tempA % tempB;
                tempA = tempB;
                tempB = tempC;
            }
            return tempA;

        }
    /**
     *
     * @param parts array
     */
    public static void printFraction(final int[] parts) {
       if (parts == null) {
            System.out.println("Error");
       }
       else if (parts[0] == 0 && parts[1] == 0 && parts[2] == 0) {
           System.out.println("0");
       }
       else if (parts[1] == 0) {
           System.out.printf("%d%n", parts[0]);
       }
       else if (parts[0] == 0) {
           System.out.printf("%d/%d%n", parts[1], parts[2]);
       }
       else {
           System.out.printf("%d %d/%d%n",parts[0], parts[1], parts[2]);
       }

        // another way to implement above is to have else and else -ifs in a try block then catch the null pointer exception
        // in that catch System.out.println("Error"); -> think if quotes should be part of print statement..use escape character to actually print the quote


    }


}
