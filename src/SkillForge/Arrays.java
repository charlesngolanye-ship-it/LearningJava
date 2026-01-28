package SkillForge;


import java.util.Random;
import java.util.Scanner;

public class Arrays {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        final int ROWS = 4;
        final int COLUMNS = 5;
        final String[] C_VALUES = {"ID", "D0017D", "D0009E", "D0014E", "D0029E"}; // C-values[0]

        int[][] studentMarks = new int[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {

                if (j == 0) {
                    System.out.printf("Enter %s: ", C_VALUES[j]); // at this point C_VALUES[J] is same as studentMarks[i][0]?..what tels C_VALUES to keep incrementing from id to the last course?
                } else {
                    System.out.printf("Enter marks for Student %d, and course %s: ", studentMarks[i][0], C_VALUES[j]);
                }

                studentMarks[i][j] = userInput.nextInt();
            }
        }

        System.out.printf("%10s %10s %10s %10s %10s %n", "ID", "D0017D", "D0009E", "D0014E", "D0029E");
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                System.out.printf("%10d ", studentMarks[i][j]);
            }
            System.out.println();
        }
    }
}


