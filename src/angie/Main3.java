package angie;

import java.util.Scanner;

public class Main3 {
    static final int ROWS = 6;
    static final int COLUMNS = 3;

    public static void main(String[] args){
        // items is a 3 rows X 3 columns
        int[][] items = {
                {1002, 4, 456},
                {1001, 2, 789},
                {1003, 6, 675},
                {1005, 7, 300},
                {1004, 9, 199},
                {1006, 3, 980}
        };

        System.out.println("Before swap");
        System.out.println("Item Id item count price");

        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLUMNS; j++){
                System.out.print(items[i][j] + " ");
            }
            System.out.println();
        }

        // swap
        int[] tempId = items[0];
        items[0] = items[1];
        items[1] = tempId;




        System.out.println("After swap");
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLUMNS; j++){
                System.out.print(items[i][j] + " ");
            }
            System.out.println();
        }


    }
}
