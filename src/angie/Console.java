package angie;

import java.util.Scanner;

public class Console {
    private static final Scanner scanner = new Scanner(System.in);

    public static double readBaseCost() {
        System.out.println("Enter Base Cost");
        return scanner.nextDouble();

    }

    public static double readAllottedMinutes() {
        System.out.println("Enter number of allotted minutes");
        return scanner.nextDouble();
    }

    public static double readUsedMinutes() {
        System.out.println("Enter number of used minutes");
        return scanner.nextDouble();
    }

    public static int generateBillId() {
        return (int) (Math.random() * 100_000);
    }



}
