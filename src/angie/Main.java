package angie;
public class Main {
        public static void main(final String[] args) {
            final int MAX_NUM_STUDENTS = 10;
            final int FNAME = 0; // to avoid using wrong index anywhere
            final int LNAME = 1; // to avoid using wrong index anywhere
            int numStudents = 0;


            //[0] -  Name String
            //[1] - Grades int or Date or some other type
            String[][] studentNames = new String[MAX_NUM_STUDENTS][2];

            int[] studentGrades = new int[MAX_NUM_STUDENTS];

            // Test Data
            studentNames[0][FNAME] = "Sandeep";
            studentNames[0][LNAME] = "Patil";
            studentGrades[0] = 80;

            studentNames[1][FNAME] = "Charles";
            studentNames[1][LNAME] = "Ngolanye";
            studentGrades[1] = 90;

            numStudents = 2;

            for (int i = 0; i < numStudents; i++) {
                System.out.printf("Name = %s %s and grade is %d %n", studentNames[i][FNAME], studentNames[i][LNAME], studentGrades[i]);
            }

            // Sort by grades - highest to lowest
            int[] tempStudentGrades = new int[MAX_NUM_STUDENTS]; // copying the array. Important to create copy within same method to release memory after use
            System.arraycopy(studentGrades, 0, tempStudentGrades, 0, studentGrades.length);

            String[][] tempStudentNames = new String[MAX_NUM_STUDENTS][2];
            System.arraycopy(studentNames, 0, tempStudentNames, 0, studentNames.length);

            // why do we create a copy? creates duplicates so that any changes you make (like sorting or swapping) won't affect the original arrays.

            // Swap
            int tempGrade;
            String[] tempName = new String[2]; // holds first and Last names - each student’s name is a pair of strings ([FNAME, LNAME]), we need to store both together while swapping. .

            // Loop will be for the grades, studentGrades.length
            tempGrade = tempStudentGrades[1];
            tempStudentGrades[1] = tempStudentGrades[0];
            tempStudentGrades[0] = tempGrade;

            //Also swap related arrays
            tempName = tempStudentNames[1];
            tempStudentNames[1] = tempStudentNames[0];
            tempStudentNames[0] = tempName;

            System.out.println("After Sorting");
            for (int i = 0; i < numStudents; i++) {
                System.out.printf("Name = %s %s and grade is %d %n", tempStudentNames[i][FNAME], tempStudentNames[i][LNAME], tempStudentGrades[i]);
            }







//            for (int i = 0; i < numStudents -1; i++){
//                for (int j = 0; j < numStudents - i - 1; j++){
//                    if (studentGrades[j] > studentGrades[j+1]){
//                        int temp = studentGrades[j];
//                        studentGrades[j] = studentGrades[j+1];
//                        studentGrades[j+1] = temp;
//                    }
//                }
//            }
//
//            for (int i = 0; i < numStudents; i++) {
//                System.out.printf("Name = %s %s and grade is %d %n", studentNames[i][FNAME], studentNames[i][LNAME], studentGrades[i]);
//            }




        }
        // A program that calculates teh average of a set of numbers
        public static double calculateAverage(int[] numbers){
            double total = 0.0;
            for (int i = 0; i < numbers.length; i++){
                total += numbers[i];
            }
            return total / numbers.length;

        }
        // A program that calculates the factorial of a given number
        public static int calculateFactorial(int number){
            if (number == 0) {
                return 1;
            }
            int result = 1;
            for (int i = 1; i <= number; i++) {
                result *= i;
            }
            return result;
        }

        // A program that finds the maximum value in an array
        public static int findMax(int[] array){
            int max = array[0];
            for (int i = 1; i < array.length; i++){
                if (array[i] > max){
                    max = array[i];
                }
            }
            return max;
        }

        // Bubble Sort - method only
        // Repeatedly compares pairs of adjacent elements and swaps them if they are out of order
        public static void bubbleSort(int[] arr){
            int n = arr.length;
            for (int i = 0; i < n -1; i++){
                for (int j = 0; j < n - i - 1; j++){
                    if (arr[j] > arr[j+1]){
                        int temp = arr[j];
                        arr[j] = arr[j+1];
                        arr[j+1] = temp;
                    }
                }
            }
        }

        // Selection Sort - method only
        // repeatedly finds the smallest element from the unsorted part of the array and put it at the beginning
        // Think of it like sorting playing cards — each round, you pick the smallest card left and place it next in order
        public static void selectionSort(int[] arr){
            int n = arr.length;
            for (int i = 0; i < n - 1; i++){
                int minIndex = i;
                for (int j = i + 1; j < n; j++){
                    if (arr[j] < arr[minIndex]){
                        minIndex = j;
                    }
                }
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }

        // Insertion Sort - method only
        // Builds teh sorted part of the array one element at a time by inserting each new element in the correct spot
        // Think of sorting cards in your hand:
        // You take a new card and insert it where it belongs among the ones already sorted.
        public static void insertionSort(int[] arr){
            int n = arr.length;
            for (int i = 1; i < n; i++){
                int key = arr[i];
                int j = i - 1;
                while (j >= 0 && arr[j] > key){
                    arr[j+1] = arr[j];
                    j--;
                }
                arr[j+1] = key;
            }
        }

        // Bubble sort on an array of strings - method only
        public static void bubbleSortString(String[] arr) {
            int n = arr.length;
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (arr[j].compareTo(arr[j+1]) > 0) {
                        String temp = arr[j];
                        arr[j] = arr[j+1];
                        arr[j+1] = temp;
                    }
                }
            }
        }

       // Linear search in an array of strings - method only
       // It goes through each element one by one until it finds the target.
       //If found → returns its position (index).
       //If not found → returns -1.
       public static int linearSearchString(String[] arr, String target){
            for (int i = 0; i < arr.length; i++){
                if (arr[i].equals(target)){
                    return i;
                }
            }
            return -1;
       }




}



