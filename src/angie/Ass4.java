package angie;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Date;

/**
 * A program that works as a simple cash register system
 * The program adds / removes items, sells items , display a list of items
 * present sales history, and sort and display sales history
 * user chooses wanted action via text-based menu
 *
 * @author Charles Ngolanye (chango-5)
 */
public class Ass4 {

    // Constants for the item array
    public static final int ITEM_ID = 0;
    public static final int ITEM_COUNT = 1;
    public static final int ITEM_PRICE = 2;
    public static final int ITEM_COLUMN_SIZE = 3;
    public static final int INITIAL_ITEM_SIZE = 10;

    // Constants for the sales array
    public static final int SALE_ITEM_ID = 0;
    public static final int SALE_ITEM_COUNT = 1;
    public static final int SALE_ITEM_PRICE = 2;
    public static final int SALE_COLUMN_SIZE = 3;
    public static final int MAX_SALES = 1000;

    // Other constants
    public static final int MENU_ITEM_1 = 1;
    public static final int MENU_ITEM_2 = 2;
    public static final int MENU_ITEM_3 = 3;
    public static final int MENU_ITEM_4 = 4;
    public static final int MENU_ITEM_5 = 5;
    public static final int MENU_ITEM_6 = 6;
    public static final int MENU_ITEM_Q = -1;

    public static final int INITIAL_ITEM_NUMBER = 999;

    private static Scanner userInputScanner = new Scanner(System.in);

    public static final int ITEM_COUNT_MIN = 1;
    public static final int ITEM_COUNT_MAX = 10;
    public static final int ITEM_PRICE_MIN = 100;
    public static final int ITEM_PRICE_MAX = 1_000;


    /**
     * This method should be used only for unit testing on CodeGrade. Do not change this method!
     * Swaps userInputScanner with a custom scanner object bound to a test input stream
     *
     * @param inputScanner test scanner object
     */
    public static void injectInput(final Scanner inputScanner) {
        userInputScanner = inputScanner;
    }

    public static void main(final String[] args) {
        int[][] items = new int[INITIAL_ITEM_SIZE][ITEM_COLUMN_SIZE]; // Data structure to store items
        int[][] sales = new int[MAX_SALES][SALE_COLUMN_SIZE]; // Data structure to store sales
        Date[] saleDates = new Date[MAX_SALES]; // Data structure to store sale dates
        int lastItemNumber = INITIAL_ITEM_NUMBER; // Keep track of last added ItemNumber


        while (true) {
            int userSelection = menu();

            // find out why Sandeep has this method as last one and with system.exit
            if (userSelection == MENU_ITEM_Q) {
                System.out.println("Terminating....");
                //System.exit(0);
                break;
            }

            switch (userSelection) {
                case MENU_ITEM_1:
                    System.out.println("How many items do you want to add? ");
                    int noOfItems = input();

                    if (noOfItems == MENU_ITEM_Q) {
                        System.out.println("Returning to main menu....");
                        break;
                    }
                    items = insertItems(items, lastItemNumber, noOfItems);
                    lastItemNumber += noOfItems;
                    System.out.printf("%d%s%n", noOfItems, " items added!");
                    break;
                case MENU_ITEM_2:
                    System.out.println("Specify an item ID to remove: ");
                    int itemIdToDelete = input(); // this will be the final int itemId in the remove method

                    if (itemIdToDelete == MENU_ITEM_Q) {
                        System.out.println("Returning to main menu....");
                    }

                    int deletedStatus = removeItem(items, itemIdToDelete);
                    if (deletedStatus == 0) {
                        System.out.println("Successfully removed an item " + itemIdToDelete);
                    }
                    else {
                        System.out.println("Could not find item " + itemIdToDelete); //Could not find item -1 if i press q
                    }
                    break;
                case MENU_ITEM_3:
                    printItems(items);
                    break;
                case MENU_ITEM_4: // bug is present ..can sell even if an item is not there..might fail io test
                    System.out.println("Enter item ID and number of items to be sold: ");
                    int itemIdToSell = input();
                    int amountToSell = input(); // noOfItemsToSell
                    int sold = sellItem(sales, saleDates, items, itemIdToSell, amountToSell);
                    if (sold == 0) {
                        System.out.println("Sold " + amountToSell + " units of " + itemIdToSell );
                    } else if (sold == -1){
                        System.out.println("could not find item " + itemIdToSell);
                    } else {
                        System.out.println("Failed to sell specified amount, only " + sold + " units available ");
                    }
                    break;
                case MENU_ITEM_5:
                    printSales(sales, saleDates);
                    break;
                case MENU_ITEM_6:
                    sortedTable(sales, saleDates);
                    printSales(sales, saleDates);
                    break;
                default:
                    System.out.println("Invalid user selection: " + userSelection + ", please try again."); // invalid is caps
            }

        }

    }

    /**
     * Presents the menu, loads, and
     * @return the user's selection.
     */

    public static int menu() {
        System.out.println("1. Insert items");
        System.out.println("2. Remove an item");
        System.out.println("3. Display a list of items");
        System.out.println("4. Register a sale");
        System.out.println("5. Display sales history");
        System.out.println("6. Sort and display sales history table");
        System.out.println("q. Quit");
        System.out.print("Your selection: ");

        return input();
    }

    /**
     * Handles user input
     * The method waits for input and returns the integer entered by the user or -1 if user entered q.
     * @return userSelection
     */
    public static int input() {
        while (true) {
            if (userInputScanner.hasNextInt()) {
                return Math.abs(userInputScanner.nextInt());
            } else {
                String inString = userInputScanner.next();
                if (inString.equalsIgnoreCase("q")) {
                    return MENU_ITEM_Q;
                } else {
                    System.out.println("Invalid input, please enter a number or 'q' to quit");
                }
            }
        }
    }

    /**
     * Checks if items array can hold specified number of new items
     * @param items array
     * @param noOfItems new items to be added
     * @return true if full or cannot hold noOfItems
     */
    public static boolean checkFull(final int[][] items, final int noOfItems) {
        int i = 0;
        int freeSlots = 0;

        for (i = 0; i < items.length; i++) {
            if (items[i][ITEM_ID] == 0) { //was previously items[i][0] == 0
                freeSlots++;
            }
        }
        if (noOfItems > freeSlots) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Called from insertItems if checkFull returns true
     * @param items array
     * @param noOfItems new items to be added
     * @return newItemsArray
     */
    public static int[][] extendArray(final int[][] items, final int noOfItems) {
        // in this soln i am just allocating additional space by the noOfItems
        // not the actual additional space i need

        // in Java, you cannot extend an existing array
        // you create a new array then copy the contents of existing array into the new array
        int[][] newItemsArray = new int[items.length + noOfItems][ITEM_COLUMN_SIZE];
        System.arraycopy(items, 0, newItemsArray, 0, items.length);
        return newItemsArray;

         /*
        Another way to extend the array - by allocating space by the actual additional space i need
        int additionalSize = noOfItems - getFreeSlots(items); // would have created the method while handling checkfull
        int newSize = items.length + additionalSize;

        int[][] newItemsArray = new int[newSize][ITEM_COLUMN_SIZE];

        //manual copying from old array to newItemsArray
        for (int i = 0; i < items.length; i++) {
            newItemsArray[i][ITEM_ID] = items[i][ITEM_ID];
            newItemsArray[i][ITEM_COUNT] = items[i][ITEM_COUNT];
            newItemsArray[i][ITEM_PRICE] = items[i][ITEM_PRICE];
        }
        return newItemsArray;

          */


    }

    /**
     * Adds noOfItems,
     * @param items array
     * @param lastItemNumber last used item number
     * @param noOfItems new items to be added - randomly selected(1-10 pcs),the item price (SEK 100-1000 / pc) is also chosen randomly
     * @return workingArray
     */
    public static int[][] insertItems(final int[][] items, final int lastItemNumber, final int noOfItems) {
        int itemId = lastItemNumber + 1;

        int[][] newItemsArray = items;

        if (checkFull(items, noOfItems)) {
            newItemsArray = extendArray(items, noOfItems);
        }

        for (int i = 0; i < noOfItems; i++) {
            int index = getFreeIndex(newItemsArray);
            newItemsArray[index][ITEM_ID] = itemId++;
            newItemsArray[index][ITEM_COUNT] = (int) (Math.random() * ITEM_COUNT_MAX) + ITEM_COUNT_MIN;
            newItemsArray[index][ITEM_PRICE] = (int) (Math.random() * (ITEM_PRICE_MAX - ITEM_PRICE_MIN + 1)) + ITEM_PRICE_MIN;
        }
        return newItemsArray;
        /*
        int[][] workingArray = items;


        if (checkFull(items, noOfItems)) {
            workingArray = extendArray(items, noOfItems);
        }

        // Find first empty row
        int startIndex = 0;
        for (int i = 0; i < workingArray.length; i++) {
            if (workingArray[i][0] == 0) {
                startIndex = i;
                break;
            }
        }
        // - possible error - think about removeItem method - all fields for this item are set to 0...
        // if we remove 1002, if 1001, 1003, 1004 are filled then we should start inserting from 1002 then insert again from 1005
        // in real world, if you remove an Item you keep itemId, count goes to 0 and price remains but not in this qn

        // Insert items from first empty row
        for (int i = startIndex; i <  startIndex + noOfItems; i++) {
                int newItemNumber = lastItemNumber + 1 + (i - startIndex);
                int itemCount = (int) (Math.random() * ITEM_COUNT_MAX) + ITEM_COUNT_MIN;
                int price = (int) (Math.random() * (ITEM_PRICE_MAX - ITEM_PRICE_MIN + 1)) + ITEM_PRICE_MIN;

                workingArray[i][0] = newItemNumber;
                workingArray[i][1] = itemCount;
                workingArray[i][2] = price;

            }


        return workingArray;

         */
    }

    /**
     * Find the first free index
     * @param items array
     * @return i, the freeIndex
     */
    public static int getFreeIndex(final int[][] items) {
        for (int i = 0; i < items.length; i++) {
            if (items[i][ITEM_ID] == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Takes away items from items array
     * Inside the methods, all fields for this item are set to 0
     * @param items array
     * @param itemId item to be removed
     * @return 0 if item found else -1
     */
    public static int removeItem(final int[][] items, final int itemId) {
        for (int i = 0; i < items.length; i++) {
            if (items[i][ITEM_ID] == itemId) {
                items[i][ITEM_ID] = 0;
                items[i][ITEM_COUNT] = 0;
                items[i][ITEM_PRICE] = 0;
                return 0;
            }
        }
        return -1;
    }

    /**
     * Prints item number, number, and price for all items that have an item number.
     * The printout must be sorted into ascending item numbers.
     * @param items array
     */
    public static void printItems(final int[][] items) {
        int[][] sortedArray = new int[items.length][ITEM_COLUMN_SIZE];
        System.arraycopy(items, 0, sortedArray, 0, items.length);

        for (int i = 0; i < sortedArray.length - 1; i++) {
            for (int j = 0; j < sortedArray.length - i - 1; j++) {
                if (sortedArray[j][0] > sortedArray[j + 1][0]) {
                    int[] temp = sortedArray[j];
                    sortedArray[j] = sortedArray[j + 1];
                    sortedArray[j + 1] = temp;
                }
            }

        }
        System.out.printf(" %s  | %s  |%s%n", "Item Number", " Count ", " Price ");
        System.out.println("+=========+==========+========+");
        for (int i = 0; i < sortedArray.length; i++) {
            if (sortedArray[i][ITEM_ID] != 0) {
                System.out.printf("|%5d  |%10d  |%10d%n", sortedArray[i][ITEM_ID], sortedArray[i][ITEM_COUNT], sortedArray[i][ITEM_PRICE]);
            }
        }

    }


    /**
     * If there are enough items in stock, the number of items is reduced and a sale is registered in the intended array (item number, number, sum).
     * The date and time of the transaction are saved in the Date array at the corresponding position
     * @param sales items to be sold array
     * @param salesDate date when item is sold
     * @param items items array
     * @param itemIdToSell id of item to be sold
     * @param amountToSell price * noOfItems
     * @return -1 if item not found, available stock if not enough stock , 0 if item has enough stock
     */
    public static int sellItem(final int[][] sales, final Date[] salesDate ,final int[][] items, final int itemIdToSell, final int amountToSell) {
        int itemIndex = findItemIndexInItems(items, itemIdToSell);
        int salesIndex = findFreeSpaceInSales(sales);

        if (itemIndex == -1) {
            return -1;
        } // item not found... helper method finds index, main method decides what to do w result.

        int availableStock = items[itemIndex][ITEM_COUNT]; // this is not an array but an int

        if (availableStock < amountToSell) {
            return availableStock;
        } // not enough stock...return what is available

        items[itemIndex][ITEM_COUNT] -= amountToSell; // reduce stock

        // record sales
        sales[salesIndex][SALE_ITEM_ID] = items[itemIndex][ITEM_ID];
        sales[salesIndex][SALE_ITEM_COUNT] = amountToSell;
        sales[salesIndex][SALE_ITEM_PRICE] = amountToSell * items[itemIndex][ITEM_PRICE ];
        salesDate[salesIndex] = new Date(); // creates an object representing RIGHT NOW, using system time.

        return 0;
    }

    /**
     * Finds item to be sold id in the items array
     * @param items array
     * @param itemIdToSell id of item to be sold
     * @return item id if available or -1 if not found
     */
    public static int findItemIndexInItems(int[][] items, int itemIdToSell) {
        for (int i = 0; i < items.length; i++) {
            if (items[i][ITEM_ID] == itemIdToSell) {
                return i;
            }
        }
         return -1; // acts as the default return value when the loop does not find the id
    }

    /**
     * Finds first empty row in sales array to record sales history
     * @param sales array
     * @return startIndex
     */
    public static int findFreeSpaceInSales(int[][] sales) {
        // Find first empty row
        int startIndex = 0;
        for (int i = 0; i < sales.length; i++) {
            if (sales[i][SALE_ITEM_ID] == 0) {
                startIndex = i;
                break;
            }
        }
        return startIndex;
    }

    /**
     * Prints sales records vs their dates - unsorted
     * @param sales array
     * @param salesDate array corresponding to sales
     */
    public static void printSales(final int[][] sales, final Date[] salesDate) {
        System.out.printf("%-17s | %-7s | %-10s | %-11s%n"," Sale Date ", "Sale Item Number", " Sale Count ", " Sale Amount ");
        System.out.println("+======================+====================+=======================+=============================+");
        for (int i = 0; i < sales.length; i++) {
            if (sales[i][SALE_ITEM_ID] != 0) {
                System.out.printf("%-17tF  | %-10d | %-12d | %-12d%n",salesDate[i],sales[i][SALE_ITEM_ID],sales[i][SALE_ITEM_COUNT],sales[i][SALE_ITEM_PRICE]);
            }
        }
        System.out.println();
        // decide if to add minutes / hours like Sandeep

    }

    /**
     * Sorts sales records in ascending order
     * Calls printSales to print sales records vs their dates
     * @param sales array
     * @param salesDate array corresponding to sales
     */
    public static void sortedTable(final int[][] sales, final Date[] salesDate) {
        int[][] sortedSalesArray = new int [sales.length][SALE_COLUMN_SIZE];
        System.arraycopy(sales, 0, sortedSalesArray, 0, sales.length);

        Date[] tempSalesDate = new Date[salesDate.length];
        System.arraycopy(salesDate, 0, tempSalesDate, 0, salesDate.length);

        for (int i = 0; i < sortedSalesArray.length - 1; i++) {
            for (int j = 0; j < sortedSalesArray.length - 1 -i; j++) {
                if (sortedSalesArray[j][0] > sortedSalesArray[j + 1][0]) {
                    int[] temp = sortedSalesArray[j];
                    Date tempDate = tempSalesDate[j];

                    sortedSalesArray[j] = sortedSalesArray[j + 1];
                    tempSalesDate[j] = tempSalesDate[j + 1];

                    sortedSalesArray[j + 1] = temp;
                    tempSalesDate[j + 1] = tempDate;
                }
            }

        }

        printSales(sortedSalesArray, tempSalesDate);
    }


}

/*
2. seems to be an extra line before displaying sold units of....
Your selection: 4
Enter item ID and number of items to be sold:
1008 4

Sold 4 units of 1008

3. Your selection: 5
Sale Item Number Sale Count  Sale Amount  Sale Date  - i do not want to display anything if nothing is sold yet
 */