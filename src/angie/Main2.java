package angie;
import java.util.Scanner;
import java.util.Random;
/**
 * Initialize constants
 * Initialize variables
 *
 *  Prompt user to roll the first dice
 *  Input the first dice (possibly the sentinel)
 *
 * While user has not yet entered the sentinel
 *   Generate random score
 *   Validate is int, 1-3, not a repeat
 *   Add score into sum of scores
 *   Add one to the score counter
 *   Prompt user to roll the next dice
 *   Input the next dice (possibly the sentinel)
 *
 * If (dice1 + dice2 + dice3 = 12)
 *       Add one to wins
 * Else if (dice1 + dice2 + dice3 > 12)
 *        Add one to losses
 * Else
 *        noWin/noLoss
 *
 * Display 3 scores of the dice rolls, sum of scores, noOfWins, noOfLosses
 */

public class Main2 {
    static final String GAME_START = "Welcome to dice game 12. You must roll 1-3 dice and try to get the sum of 12 ...\n";
    static final String CHOOSE_DICE = "Enter which dice you want to roll [1,2,3] (exit with q):";
    static final String ROUND_WON = "You won!!";
    static final String ROUND_LOST = "You lost!!";
    static final String ROUND_TIE = "You neither won nor lost the round.";
    static final String NEXT_ROUND = "Next round!";
    static final String GAME_OVER = "Game Over!";
    static final String ALREADY_SELECTED_DICE = "Sorry, you have already rolled that dice. Try again";
    static final String INVALID_ENTRY = "Sorry, that is an invalid entry. Try again. Valid entries are 1, 2, 3, and q\n";
    static final String AMOUNT_WIN_STRING = "#win: ";
    static final String AMOUNT_LOST_STRING = " #loss: ";
    static final String SUM_STRING = " sum: ";
    static final int MAX_DICE_VALUE = 6;
    static final int MIN_DICE_VALUE = 1;
    static final int DICE_SUM_TARGET_VALUE = 12;

    public static void main(final String[] args) {

//            int dice1Value = 0; // The value of the first dice
//            boolean isDice1Rolled = false; // Whether the first dice is rolled or not
//
//            int dice2Value = 0; // The value of the second dice
//            boolean isDice2Rolled = false; // Whether the second dice is rolled or not
//
//            int dice3Value = 0; // The value of the third dice
//            boolean isDice3Rolled = false; // Whether the third dice is rolled or not
//
//            int sum = 0; // The sum of the dice values
//            int winCounter = 0; // The number of wins
//            int lossCounter = 0; // The number of losses
//            int num = 0; // own initialization

        boolean isPlaying = true; // Whether the game is being played or not ...need to understand more

        Scanner userInput = new Scanner(System.in);
        Random rand = new Random();

        System.out.println(GAME_START);

        while (isPlaying) {
            int dice1Value = 0; // The value of the first dice
            boolean isDice1Rolled = false; // Whether the first dice is rolled or not

            int dice2Value = 0; // The value of the second dice
            boolean isDice2Rolled = false; // Whether the second dice is rolled or not

            int dice3Value = 0; // The value of the third dice
            boolean isDice3Rolled = false; // Whether the third dice is rolled or not

            int sum = 0; // The sum of the dice values
            int winCounter = 0; // The number of wins
            int lossCounter = 0; // The number of losses
            int num = 0; // own initialization

            for (int i = 1; i <=3; i++) {

                System.out.print(CHOOSE_DICE);
                if (userInput.hasNextInt()) {
                    num = userInput.nextInt();
                }
                if (userInput.nextLine().equals("q")) {
                    System.out.println(GAME_OVER);
                    isPlaying = false;
                }

                switch (num){
                    case 1:
                        if (isDice1Rolled) {
                            System.out.println(ALREADY_SELECTED_DICE);
                            break;// was continue before
                        } else {
                            dice1Value = rand.nextInt(MAX_DICE_VALUE) + MIN_DICE_VALUE;
                            isDice1Rolled = true;
                        }
                        break;
                    case 2:
                        if (isDice2Rolled) {
                            System.out.println(ALREADY_SELECTED_DICE);
                            break;// was continue before
                        } else {
                            dice2Value = rand.nextInt(MAX_DICE_VALUE) + MIN_DICE_VALUE;
                            isDice2Rolled = true;
                        }
                        break;
                    case 3:
                        if (isDice3Rolled) {
                            System.out.println(ALREADY_SELECTED_DICE);
                            break;// was continue before
                        } else {
                            dice3Value = rand.nextInt(MAX_DICE_VALUE) + MIN_DICE_VALUE;
                            isDice3Rolled = true;
                        }
                        break;
                    default:
                        System.out.println(INVALID_ENTRY);
                }

                sum = dice1Value + dice2Value + dice3Value;

                if (sum == DICE_SUM_TARGET_VALUE) {
                    winCounter++;
                    System.out.println(ROUND_WON);
                    System.out.println(NEXT_ROUND);
                    //continue;
                }
                if (sum > DICE_SUM_TARGET_VALUE) {
                    lossCounter++;
                    System.out.println(ROUND_LOST);
                    System.out.println(NEXT_ROUND);
                    //continue;
                }
                if (sum < DICE_SUM_TARGET_VALUE && i == 3) {
                    System.out.println(ROUND_TIE);
                    System.out.println(NEXT_ROUND);
                    //continue;
                }

                System.out.printf("%d %d %d %s%d %s%d %s%d%n", dice1Value, dice2Value, dice3Value,SUM_STRING,sum,AMOUNT_WIN_STRING, winCounter,AMOUNT_LOST_STRING,lossCounter);




            }

        }
        userInput.close();
    }
}



