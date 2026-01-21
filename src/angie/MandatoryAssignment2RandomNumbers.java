package angie;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;
/**
 * handles random numbers
 * Enter number of random numbers () 1 - Integer.MAX_VALUE);
 * Generate random numbers -> int randomNumber = rand.nextInt(999);
 * Place random numbers array int[] randomArray = new int[array.length]; - array int[] should have exactly the required
 * size - neither larger nor smaller
 * Use try catch (OutOfMemoryError e), continue
 * Print numbers in the order they were randomized
 * order least even to largest even integer
 * order largest odd to least odd integer
 * Can use separate arrays of same size as original array to store the odd and even integers
 * Print integers in their array order, punctuate mark between even and odd integers and no. of even and odd integers
 *
 @author Charles Ngolanye (Chango-5)
 */

class MandatoryAssignment2RandomNumbers {
    static final String USER_INPUT_PROMPT = "How many random numbers in the range 0 - 999 are desired?";
    static final int MAX_RANDOM_NUMBER = 1000;
    static final String RANDOM_NUMBERS_LIST_MESSAGE = "Here are the random numbers:";
    static final String RANDOM_NUMBERS_SORTED_MESSAGE = "Here are the random numbers arranged:";
    static final String NO_ODD_NUMBERS_MESSAGE = "No Odd Numbers";
    static final String NO_EVEN_NUMBERS_MESSAGE = "No Even Numbers";
    static final String INVALID_INPUT_MESSAGE = "Invalid Input";
    static final int MAX_ALLOWED_NUMBERS = 2_147_483_647;

    public  static  void main (final String[] args) {
        int[] randomArray =  null; // how to user a while loop instead of initializing the array to null
        int numberOfRandomNumbers = 0;

        int[] evenArray = null; // how to user a while loop instead of initializing the array to null
        int evenCount = 0;

        int[] oddArray = null; // how to user a while loop instead of initializing the array to null
        int oddCount = 0;

        int max = 0;
        int temp = 0;

        //System.out.printf("The max integer value is %d%n", Integer.MAX_VALUE);
        //Integer max is 2147483647


        System.out.print(USER_INPUT_PROMPT);
        Scanner userInput = new Scanner(System.in);
        Random rand = new Random();

        try {
            numberOfRandomNumbers = userInput.nextInt();

            if (numberOfRandomNumbers <= 0 || numberOfRandomNumbers > MAX_RANDOM_NUMBER) {
                System.out.println(INVALID_INPUT_MESSAGE);
                System.exit(0); // how not to exit but have user enter a number again...
            }
            randomArray = new int[numberOfRandomNumbers];
            evenArray = new int[numberOfRandomNumbers];
            oddArray = new int[numberOfRandomNumbers];
        } catch(InputMismatchException e) {
            System.out.println(INVALID_INPUT_MESSAGE);
            System.exit(0); // how not to exit but have user enter a number again...
        } catch(OutOfMemoryError e) {
            System.out.println(INVALID_INPUT_MESSAGE);
            System.exit(0);
        }



        System.out.println(RANDOM_NUMBERS_LIST_MESSAGE);
        for (int i = 0; i < numberOfRandomNumbers; i++) {
            int randomNumber = rand.nextInt(MAX_RANDOM_NUMBER);
            randomArray[i]= randomNumber;
            System.out.printf("%d%s", randomArray[i]," ");

            if (randomArray[i] % 2 == 0) {
                evenArray[evenCount] = randomArray[i];
                evenCount++;
            } else  {
                oddArray[oddCount] = randomArray[i];
                oddCount++;
            }
        }


        System.out.println();

        System.out.println(RANDOM_NUMBERS_SORTED_MESSAGE);

        for (int i = 0; i < evenCount; i++) {
            for (int j = 0; j < evenCount - 1; j++) {
                if (evenArray[j] > evenArray[j + 1]) {
                    temp = evenArray[j];
                    evenArray[j] = evenArray[j + 1];
                    evenArray[j + 1] = temp;
                }
            }
        }

        for (int i = 0; i < evenCount; i++) {
            System.out.print( evenArray[i] + " ");
        }

        System.out.print(" - ");

        for (int i = 0; i < oddCount - 1; i++) {
            max = i;
            for (int k = i + 1; k < oddCount ; k++) {
                if (oddArray[k] > oddArray[max]) {
                    max = k;
                }
            }
            temp = oddArray[i];
            oddArray[i] = oddArray[max];
            oddArray[max] = temp;
        }
        for (int i = 0; i < oddCount; i++) {
            System.out.print(oddArray[i] + " ");
        }
        System.out.println();

        if (evenCount == 0){
            System.out.println(NO_EVEN_NUMBERS_MESSAGE);
        }
        if (oddCount == 0){
            System.out.println(NO_ODD_NUMBERS_MESSAGE);
        }

        System.out.println();
        System.out.println("Of the above " + numberOfRandomNumbers + " numbers, " + evenCount + " were even and " + oddCount + " odd");
    }
}

/**
 * Another solution
 * package angie;
 * import java.util.InputMismatchException;
 * import java.util.Scanner;
 * import java.util.Random;
 *
 * public class Main {
 *     static final int MAX_ALLOWED_NUMBERS = 1_000_000;
 *
 *         public static void main(final String[] args) {
 *             Scanner userInput = new Scanner(System.in);
 *             Random rand = new Random();
 *
 *             int numberOfRandomNumbers = 0;
 *             int[] randomArray;
 *             int[] evenArray;
 *             int[] oddArray;
 *
 *             // Step 1: Ask for user input until it's valid
 *             while (true) {
 *                 System.out.print("How many random numbers in the range 0 - 999 are desired? ");
 *                 try {
 *                     numberOfRandomNumbers = userInput.nextInt();
 *
 *                     if (numberOfRandomNumbers <= 0 || numberOfRandomNumbers > MAX_ALLOWED_NUMBERS) {
 *                         System.out.println("Error, please enter a positive integer " + " (enter a number between 1 and " + MAX_ALLOWED_NUMBERS + ")");
 *                         continue;// ask again
 *                     }
 *                     break; // if valid, break out of the loop
 *
 *                 } catch (InputMismatchException e) {
 *                     System.out.println("Error, please enter an integer");
 *                     userInput.next(); // clear invalid token from the input buffer
 *                     continue; // ask again
 *                 } catch (OutOfMemoryError e) {
 *                     System.out.println("Error, cannot allocate so much memory");
 *                     continue; // ask again, maybe smaller number next time
 *                 }
 *             }
 *
 *             // Step 2: Create arrays not that input is valid
 *             randomArray = new int[numberOfRandomNumbers];
 *             evenArray = new int[numberOfRandomNumbers];
 *             oddArray = new int[numberOfRandomNumbers];
 *
 *             int evenCount = 0;
 *             int oddCount = 0;
 *             int max = 0;
 *             int temp = 0;
 *
 *             // Step 3: Generate random numbers
 *             System.out.println("Here are the random numbers: ");
 *             for (int i = 0; i < numberOfRandomNumbers; i++) {
 *                 int randomNumber = rand.nextInt(1000);
 *                 randomArray[i]= randomNumber;
 *                 System.out.printf("%d%s", randomArray[i]," ");
 *
 *                 if (randomArray[i] % 2 == 0) {
 *                     evenArray[evenCount] = randomArray[i];
 *                     evenCount++;
 *                 } else  {
 *                     oddArray[oddCount] = randomArray[i];
 *                     oddCount++;
 *                 }
 *             }
 *
 *             System.out.println();
 *             System.out.println("Here are the random numbers arranged: ");
 *
 *             // Step 4: Sort even numbers ascending
 *             for (int i = 0; i < evenCount; i++) {
 *                 for (int j = 0; j < evenCount - 1; j++) {
 *                     if (evenArray[j] > evenArray[j + 1]) {
 *                         temp = evenArray[j];
 *                         evenArray[j] = evenArray[j + 1];
 *                         evenArray[j + 1] = temp;
 *                     }
 *                 }
 *             }
 *
 *             // Step 5: Print even numbers
 *             for (int i = 0; i < evenCount; i++) {
 *                 System.out.print( evenArray[i] + " ");
 *             }
 *
 *             System.out.print(" - ");
 *
 *             // Step 5: Sort odd numbers descending
 *             for (int i = 0; i < oddCount - 1; i++) {
 *                 max = i;
 *                 for (int k = i + 1; k < oddCount ; k++) {
 *                     if (oddArray[k] > oddArray[max]) {
 *                         max = k;
 *                     }
 *                 }
 *                 temp = oddArray[i];
 *                 oddArray[i] = oddArray[max];
 *                 oddArray[max] = temp;
 *             }
 *
 *             // Step 6: Print odd numbers
 *             for (int i = 0; i < oddCount; i++) {
 *                 System.out.print(oddArray[i] + " ");
 *             }
 *             System.out.println();
 *
 *             // Step 7: Print summary
 *             if (evenCount == 0){
 *                 System.out.println("No Even numbers");
 *             }
 *             if (oddCount == 0){
 *                 System.out.println("No Odd numbers");
 *             }
 *
 *             System.out.println();
 *             System.out.println("Of the above " + numberOfRandomNumbers + " numbers, " + evenCount + " were even and " + oddCount + " odd");
 *         }
 * }
 */
