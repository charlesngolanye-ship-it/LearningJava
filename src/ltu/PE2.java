package ltu;

import java.util.Scanner;

/**
 * Implement a bidding system for real estate
 * Handles info about different real estate properties, as well as the bidding process
 */
public class PE2 {
    // Global scanner object
    public static Scanner userInputScanner = new Scanner(System.in);


    // Menu item constants
    public static final int MENU_ITEM_1 = 1;
    public static final int MENU_ITEM_2 = 2;
    public static final int MENU_ITEM_3 = 3;
    public static final int MENU_ITEM_4 = 4;
    public static final int MENU_ITEM_5 = 5;
    public static final int MENU_ITEM_6 = 6;
    public static final int MENU_ITEM_Q = -1;

    // Constants for property objects
    public static final int MAX_NO_OF_PROPERTIES= 10;
    public static final int NUM_OF_PROPERTY_COLUMNS = 6;
    public static final int PROPERTY_ID = 0;
    public static final int PROPERTY_ADDRESS = 1;
    public static final int PROPERTY_TYPE = 2;
    public static final int PROPERTY_PRICE = 3;
    public static final int PROPERTY_STATUS = 4;
    public static final int PROPERTY_SELLING_PRICE = 5;

    // Constants for bid objects
    public static final int MAX_NO_OF_BIDS = 1000;
    public static final int NO_OF_BIDS_COLUMNS = 5;
    public static final int BID_ID= 0;
    public static final int BID_BIDDER = 1;
    public static final int BID_PRICE = 2;


    public static final String[] PROPERTY_TYPES = {"Apartment", "House", "Commercial"};
    public static final String[] PROPERTY_STATUSES = {"Unsold", "Sold"};


    public static void main(final String[] args) {

            // Index 0 - property id
            // Index 1 - property address
            // Index 2 - Type of property
            // Index 3 - asking price
            // Index 4 - selling price
        String[][] properties = new String[MAX_NO_OF_PROPERTIES][NUM_OF_PROPERTY_COLUMNS];

        // Index 0 - bid id
        // Index 1 - bidder
        // Index 2 - bid price
        String[][] bids = new String[MAX_NO_OF_BIDS][NO_OF_BIDS_COLUMNS];

        int noOfProperties = 0;
        int noOfBids = 0;

        noOfProperties = insertTestProperties(properties, noOfProperties);
        noOfBids = insertTestBids(bids, noOfBids);

//        loadTestData(properties);
//        noOfProperties = 3;


        while (true) {

            switch (menu()) {
                case MENU_ITEM_1:
                    System.out.println("Register new property");
                    noOfProperties = registerProperty(properties, noOfProperties);
                    break;
                case MENU_ITEM_2:
                    System.out.println("Register property bid");
                    noOfBids = registerBid(properties, bids, noOfProperties, noOfBids);
                    break;

                case MENU_ITEM_3:
                    System.out.println("End bidding process");
                    endBiddingProcess(properties, bids, noOfProperties, noOfBids);
                    break;

                case MENU_ITEM_4:
                    System.out.println("Print property bidding history");
                    printBidHistory(properties, bids, noOfProperties, noOfBids);
                    break;

                case MENU_ITEM_5:
                    System.out.println("Print all unsold properties");
                    printAllUnsoldProperties(properties, noOfProperties);
                    break;

                case MENU_ITEM_6:
                    System.out.println("Print all sold properties (by price)");
                    //printPropertiesByPrice(properties, noOfProperties);
                    printAllSoldPropertiesBySellingPrice(properties, noOfProperties);
                    break;

                case MENU_ITEM_Q:
                    System.exit(0);
                    break;

                default:
                    System.out.println("You must chose between 1 - 6, or press q to quit");
            }
        }
    }

    // Function adds some test data
    public static void loadTestData(final String[][] properties) {
        // Entry 1
        properties[0][0] = "5495";
        properties[0][1] = "Ursviksgatan 2";
        properties[0][2] = "Apartment";
        properties[0][3] = "1400000";
        properties[0][4] = "2000000";

        // Entry 2
        properties[1][0] = "4981";
        properties[1][1] = "Odengaran 10";
        properties[1][2] = "House";
        properties[1][3] = "2000000";
        properties[1][4] = "3100000";

        // Entry 3
        properties[2][0] = "9001";
        properties[2][1] = "Boviksbadet 1";
        properties[2][2] = "Commercial";
        properties[2][3] = "8000000";
        properties[2][4] = "7100000";
    }

    public static void printPropertiesSummary(final String[][] properties, final int noOfProperties) {
        System.out.printf("%-10s %-20s %-10s %-15s %-15s%n", "ID", "Address",  "Type", "Asking Price", "Selling Price");
        for (int i = 0; i < noOfProperties; i++) {
            System.out.printf("%-10s %-15s %-15s %-15s %-15s%n", properties[i][0], properties[i][1], properties[i][2], properties[i][3], properties[i][4]);
        }
    }

    public static void printPropertiesByPrice(final String[][] properties, final int noOfProperties) {
        // Copy of the original array
        String[][] tempProperties = new String[MAX_NO_OF_PROPERTIES][5]; // String[][] tempProperties = new String[noOfProperties][5];
        System.arraycopy(properties, 0, tempProperties, 0, noOfProperties);

        // Bubble sort - selling price in index 4 - comparing numeric
        for (int i = 0; i < noOfProperties; i++) {
            for (int j = 0; j < noOfProperties - 1; j++) {
                if (Integer.parseInt(tempProperties[j][4]) > Integer.parseInt(tempProperties[j + 1][4])) {
                    String[] temp = tempProperties[j];
                    tempProperties[j] = tempProperties[j + 1];
                    tempProperties[j + 1] = temp;
                }
            }
        }
        printPropertiesSummary(tempProperties, noOfProperties);
    }

    private static int insertTestBids(String[][] bids, int noOfBids) {
        bids[noOfBids][BID_ID] = "1001";
        bids[noOfBids][BID_BIDDER] = "John";
        bids[noOfBids][BID_PRICE] = "110000";
        ++noOfBids;

        bids[noOfBids][BID_ID] = "1001";
        bids[noOfBids][BID_BIDDER] = "Mary";
        bids[noOfBids][BID_PRICE] = "120000";
        ++noOfBids;

        bids[noOfBids][BID_ID] = "1001";
        bids[noOfBids][BID_BIDDER] = "Peter";
        bids[noOfBids][BID_PRICE] = "130000";
        ++noOfBids;

        bids[noOfBids][BID_ID] = "1002";
        bids[noOfBids][BID_BIDDER] = "John";
        bids[noOfBids][BID_PRICE] = "210000";
        ++noOfBids;

        bids[noOfBids][BID_ID] = "1002";
        bids[noOfBids][BID_BIDDER] = "Mary";
        bids[noOfBids][BID_PRICE] = "220000";
        ++noOfBids;

        bids[noOfBids][BID_ID] = "1002";
        bids[noOfBids][BID_BIDDER] = "Peter";
        bids[noOfBids][BID_PRICE] = "230000";
        ++noOfBids;

        bids[noOfBids][BID_ID] = "1003";
        bids[noOfBids][BID_BIDDER] = "John";
        bids[noOfBids][BID_PRICE] = "310000";
        ++noOfBids;

        bids[noOfBids][BID_ID] = "1003";
        bids[noOfBids][BID_BIDDER] = "Mary";
        bids[noOfBids][BID_PRICE] = "320000";
        ++noOfBids;

        bids[noOfBids][BID_ID] = "1003";
        bids[noOfBids][BID_BIDDER] = "Peter";
        bids[noOfBids][BID_PRICE] = "400000";
        ++noOfBids;

        return noOfBids;
    }

    private static int insertTestProperties(String[][] properties, int noOfProperties) {
        properties[noOfProperties][PROPERTY_ID] = "1001";
        properties[noOfProperties][PROPERTY_ADDRESS] = "Street 1";
        properties[noOfProperties][PROPERTY_TYPE] = "Apartment";
        properties[noOfProperties][PROPERTY_PRICE] = "100000";
        properties[noOfProperties][PROPERTY_STATUS] = "Unsold";
        properties[noOfProperties][PROPERTY_SELLING_PRICE] = "0";
        ++noOfProperties;

        properties[noOfProperties][PROPERTY_ID] = "1002";
        properties[noOfProperties][PROPERTY_ADDRESS] = "Street 2";
        properties[noOfProperties][PROPERTY_TYPE] = "House";
        properties[noOfProperties][PROPERTY_PRICE] = "200000";
        properties[noOfProperties][PROPERTY_STATUS] = "Unsold";
        properties[noOfProperties][PROPERTY_SELLING_PRICE] = "0";
        ++noOfProperties;

        properties[noOfProperties][PROPERTY_ID] = "1003";
        properties[noOfProperties][PROPERTY_ADDRESS] = "Street 3";
        properties[noOfProperties][PROPERTY_TYPE] = "Commercial";
        properties[noOfProperties][PROPERTY_PRICE] = "300000";
        properties[noOfProperties][PROPERTY_STATUS] = "Sold";
        properties[noOfProperties][PROPERTY_SELLING_PRICE] = "400000";
        ++noOfProperties;
        return noOfProperties;
    }

    /**
     * This method prints sold properties by selling price
     * @param properties array of properties
     * @param noOfProperties number of properties
     */

    public static void printAllSoldPropertiesBySellingPrice(final String[][] properties, final int noOfProperties) {
        System.out.printf("%-10s %-20s %-10s %-15s %-15s\n", "ID", "Address", "Type", "Asking Price", "Selling Price");

        // Calculate number of sold properties
        int noOfSoldProperties = 0;
        for (int i = 0; i < noOfProperties; i++) {
            if (properties[i][PROPERTY_STATUS].equalsIgnoreCase(PROPERTY_STATUSES[1])) {
                noOfSoldProperties++;
            }
        }

        // Create a copy of the properties array of size sold properties
        String[][] tempProperties = new String[noOfSoldProperties][NUM_OF_PROPERTY_COLUMNS];
        int tempCopyIndex = 0;
        for (int i = 0; i < noOfProperties; i++) {
            if (properties[i][PROPERTY_STATUS].equalsIgnoreCase(PROPERTY_STATUSES[1])) {
                tempProperties[tempCopyIndex] = properties[i];
                tempCopyIndex++;
            }
        }

        // Sort copy of the properties array by selling price
        for (int i = 0; i < noOfSoldProperties; i++) {
            for (int j = 0; j < noOfSoldProperties - 1; j++) {
                if (Integer.parseInt(tempProperties[j][PROPERTY_SELLING_PRICE]) > Integer.parseInt(tempProperties[j + 1][PROPERTY_SELLING_PRICE])) {
                    String[] temp = tempProperties[j];
                    tempProperties[j] = tempProperties[j + 1];
                    tempProperties[j + 1] = temp;
                }
            }
        }

        // Print sorted copy of the properties array
        for (int i = 0; i < noOfSoldProperties; i++) {
            System.out.printf("%-10s %-20s %-10s %-15s %-15s\n", tempProperties[i][PROPERTY_ID], tempProperties[i][PROPERTY_ADDRESS], tempProperties[i][PROPERTY_TYPE], tempProperties[i][PROPERTY_PRICE], tempProperties[i][PROPERTY_SELLING_PRICE]);
        }
    }

    /**
    * This method registers a new property
     * A property is registered by providing its address, type, as well as its asking price
     * in the system, property is identified by using a unique randomized ID ( in range 1000 - 9999)
     * Make sure that multiple properties do not end up with same ID
     *
     * @param properties properties of the array of properties
     * @param noOfProperties is the number of properties
     * @return int the number of properties
     *
     */

    public static int registerProperty(String[][] properties, int noOfProperties) {
        if (noOfProperties < MAX_NO_OF_PROPERTIES) {
            System.out.print("Enter property address: ");
            String propertyAddress = userInputScanner.nextLine();

            // Get valid type
            String propertyType = "";
            do {
                System.out.print("Enter property type (Apartment, House, Commercial): ");
                propertyType = userInputScanner.nextLine();
            } while (!propertyType.equalsIgnoreCase(PROPERTY_TYPES[0]) && !propertyType.equalsIgnoreCase(PROPERTY_TYPES[1]) && !propertyType.equalsIgnoreCase(PROPERTY_TYPES[2]));

            String priceString = "";
            int askingPrice = 0;
            do {
                System.out.print("Enter property price: ");
                priceString = userInputScanner.nextLine();
                try {
                    askingPrice = Integer.parseInt(priceString);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input");
                }
            } while (askingPrice <= 0);

            // Generate random ID
            int propertyId = (int) (Math.random() * 8999) + 1000;

            // Check if ID already exists and generate id again if it exists
            for (int i = 0; i < noOfProperties; i++) {
                if (Integer.parseInt(properties[i][PROPERTY_ID]) == propertyId) {
                    propertyId = (int) (Math.random() * 8999) + 1000;
                    i = 0;// reset the index so that new id can now be compared against all others
                }
            }


            // Add property to properties array
            properties[noOfProperties][PROPERTY_ID] = Integer.toString(propertyId);
            properties[noOfProperties][PROPERTY_ADDRESS] = propertyAddress;
            properties[noOfProperties][PROPERTY_TYPE] = propertyType;
            properties[noOfProperties][PROPERTY_PRICE] = priceString;
            properties[noOfProperties][PROPERTY_STATUS] = PROPERTY_STATUSES[0]; // unsold
            properties[noOfProperties][PROPERTY_SELLING_PRICE] = "0"; // added
            noOfProperties++;

            System.out.println("Property ID: " + propertyId);
        } else {
            System.out.println("Maximum no. of properties exceeded");
        }
        return noOfProperties;
    }


    /**
     * the program should print a menu of options that can be selected by the user.
     * It should be possible to exit the program by pressing q
     *
     * @return int the option chosen by the user;
     */
    public static int menu() {
        System.out.println("---------------------------");
        System.out.println("# LTU REAL ESTATE");
        System.out.println("---------------------------");
        System.out.println("1. Register new property");
        System.out.println("2. Register property bid");
        System.out.println("3. End bidding process");
        System.out.println("4. Print property bidding history");
        System.out.println("5. Print all unsold properties");
        System.out.println("6. Print all sold properties (by price)");
        System.out.println("q. End your program");
        System.out.print("> Enter your option: ");

        return input();

    }

    /**
     * Handles user input with necessary validations
     *
     * @return number
     */
    public static int input() {
        int number = 0;

        while (true) {
            if (userInputScanner.hasNextInt()) {
                String temp = userInputScanner.nextLine();
                number = Math.abs(Integer.parseInt(temp));
                if (number > 0) {
                    break;
                }
                else  {
                    System.out.println("Invalid entry, please try again");
                }
            } else if (userInputScanner.hasNext()) {
                String inString = userInputScanner.nextLine();
                if (inString.equalsIgnoreCase("q")) {
                    number = MENU_ITEM_Q;
                    break;
                } else {
                    System.out.println("Invalid entry, please enter a number or 'q' to quit");
                }
            }
        }
        return number;
    }

    /**
     * This method prints all unsold properties
     * It prints the ID, address, type, asking price and the highest bid (if any)
     * @param properties is the array of properties
     * @param noOfProperties is the number of properties
     */
    public static void printAllUnsoldProperties(final String[][] properties, final int noOfProperties) {
        System.out.printf("%-10s %-20s %-15s %-15s %-15s\n", "ID", "Address", "Type", "Asking Price", "Highest Price");
        for (int i = 0; i < noOfProperties; i++) {
            if (properties[i][PROPERTY_STATUS].equalsIgnoreCase("Unsold")) {
                System.out.printf("%-10s %-20s %-15s %-15s %-15s\n", properties[i][PROPERTY_ID], properties[i][PROPERTY_ADDRESS], properties[i][PROPERTY_TYPE], properties[i][PROPERTY_PRICE], "N/A");
            }
        }
    }

    /**
     * This method registers a new bid
     * @param properties is teh properties array
     * @param bids is the bids array
     * @param noOfProperties number of properties
     * @param noOfBids number of bids
     * @return number of bids
     */

    public static int registerBid(String[][] properties,String[][] bids, int noOfProperties, int noOfBids) {
        System.out.println("Enter property ID: ");
        String propertyId = userInputScanner.nextLine();

        int propertyIndex = findPropertyIndex(properties, noOfProperties, propertyId);

        if (propertyIndex == -1) {
            System.out.println("Property not found");
            return noOfBids;
        }

        System.out.println("Enter bidder's name: ");
        String bidderName = userInputScanner.nextLine();

        String bidPrice = "";
        int bidPriceInt = 0;
        do {
            System.out.println("Enter bid price: ");
            bidPrice = userInputScanner.nextLine();

            try {
                bidPriceInt = Integer.parseInt(bidPrice);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }
        } while (bidPriceInt <= 0);

        // Get the highest bid for this property
        int highestBidIndex = getHighestBidIndex(bids, noOfBids, propertyId);

        //int highestBid;
        if (highestBidIndex != -1) {
            int highestBid = Integer.parseInt(bids[highestBidIndex][BID_PRICE]);
            if (Integer.parseInt(bidPrice) <= highestBid) {
                System.out.printf("There is a higher bid present (%s by %s)! Could not register teh bid.%n", bids[highestBidIndex][BID_PRICE], bids[highestBidIndex][BID_BIDDER]);
                return noOfBids;
            }

        } else {
            // Check if the bid is higher than the property price
            int propertyPrice = Integer.parseInt(properties[propertyIndex][PROPERTY_PRICE]);
            //highestBid = Integer.parseInt(properties[propertyIndex][3]);
            if (Integer.parseInt(bidPrice) <= propertyPrice) {
                System.out.printf("The bid is lower than the property's price (%s)! Could not register teh bid.%n", properties[propertyIndex][PROPERTY_PRICE]);
                return noOfBids;
            }
        }

        bids[noOfBids][BID_ID] = propertyId;
        bids[noOfBids][BID_BIDDER] = bidderName;
        bids[noOfBids][BID_PRICE] = bidPrice;
        noOfBids++;
        System.out.println("Bid registered successfully!");

        return noOfBids;
    }

    /**
     * This method returns teh index of highest bid for a property
     * @param bids is array of bids
     * @param noOfBids is number of bids
     * @param propertyId ID of the property
     * @return index of highest bid
     */

    public static int getHighestBidIndex(String[][] bids, int noOfBids, String propertyId) {
        int highestBidIndex = -1;
        int highestBid = 0;

        for (int i = 0; i < noOfBids; i++) {
            if (bids[i][BID_ID].equals(propertyId) && Integer.parseInt(bids[i][BID_PRICE]) > highestBid) {
                highestBid = Integer.parseInt(bids[i][BID_PRICE]);
                highestBidIndex = i;
            }
        }
        return highestBidIndex;
    }

    /**
     * This method returns the property's index in the properties array
     * @param properties is the array of properties
     * @param noOfProperties is the number of properties
     * @param propertyId is the ID of the property to be found
     * @return is the index of the property in the properties array, or -1 if not found
     */

    public static int findPropertyIndex(String[][] properties, int noOfProperties, String propertyId) {
        for (int i = 0; i < noOfProperties; i++) {
            if (properties[i][PROPERTY_ID].equalsIgnoreCase(propertyId)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * This method prints bids history of a given property
     * @param properties array of properties
     * @param bids array of bids
     * @param noOfProperties number of properties
     * @param noOfBids number of bids
     */

    public static void printBidHistory(String[][] properties, String[][] bids, int noOfProperties, int noOfBids) {
        System.out.println("Enter property ID: ");
        String propertyId = userInputScanner.nextLine();

        int propertyIndex = findPropertyIndex(properties, noOfProperties, propertyId);
        if (propertyIndex == -1) {
            System.out.println("Property not found");
        } else {
            System.out.printf("%-20s%-20s%-20s\n", "Bidder", "Price", "Accepted");
            for (int i = 0; i < noOfBids; i++) {
                if (bids[i][BID_ID].equals(propertyId)) {
                    if (properties[propertyIndex][PROPERTY_STATUS].equalsIgnoreCase(PROPERTY_STATUSES[1]) && bids[i][BID_PRICE].equals(properties[propertyIndex][PROPERTY_SELLING_PRICE])) {
                        System.out.printf("%-20s%-20s%-20s\n", bids[i][BID_BIDDER], bids[i][BID_PRICE], "Yes");
                    } else {
                        System.out.printf("%-20s%-20s%-20s\n", bids[i][BID_BIDDER], bids[i][BID_PRICE], "");
                    }
                }
            }

        }
    }

    /**
     * This method ends the bidding process of a given property
     * @param properties array of properties
     * @param bids array of bids
     * @param noOfProperties number of properties
     * @param noOfBids number of bids
     */

    public static void endBiddingProcess(String[][] properties, String[][] bids, int noOfProperties, int noOfBids) {
        System.out.println("Enter property ID: ");
        String propertyId = userInputScanner.nextLine();

        int propertyIndex = findPropertyIndex(properties, noOfProperties, propertyId);
        if (propertyIndex == -1) {
            System.out.println("Property not found");
            return;
        }

        int highestBidIndex = getHighestBidIndex(bids, noOfBids, propertyId);
        if (highestBidIndex == -1) {
            System.out.println("No bid present for this property, cannot end bidding.");
            return;
        }
        System.out.printf("Accept bid by %s (%s)? (Yes/No): ", bids[highestBidIndex][BID_BIDDER], bids[highestBidIndex][BID_PRICE]);
        String answer = userInputScanner.nextLine();
        if (answer.equalsIgnoreCase("yes")) {
            properties[propertyIndex][PROPERTY_STATUS] = PROPERTY_STATUSES[1];
            System.out.println("Bid Accepted, property is sold");
            properties[propertyIndex][PROPERTY_SELLING_PRICE] = bids[highestBidIndex][BID_PRICE];
        } else {
            System.out.println("Bid not accepted, property is still unsold");
        }

    }


}


