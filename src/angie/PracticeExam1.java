package angie;

import java.util.Date;
import java.util.Scanner;

/**
 * Implement parking lot mgt system at Luleå Airport
 * Handles info about entrance/exit dates, cost of parking and parking history
 */
public class PracticeExam1 {
    private static Scanner userInputScanner = new Scanner(System.in);

    public static final int MAX_NO_OF_CARS = 500;


    // Menu item constants
    public static final int MENU_ITEM_1 = 1;
    public static final int MENU_ITEM_2 = 2;
    public static final int MENU_ITEM_3 = 3;
    public static final int MENU_ITEM_4 = 4;
    public static final int MENU_ITEM_5 = 5;

    public static final int MONTHS_IN_A_YEAR = 12;
    public static final int DAYS_IN_A_MONTH = 30;

    public static final int FIRST_TEN_DAYS_PARKING_COST_PER_DAY = 120;
    public static final int LAST_TWENTY_DAYS_PARKING_COST_PER_DAY = 50;


    public static void main(final String[] args) {

        // Registration numbers
        String[] carRegistrationNumbers = new String[MAX_NO_OF_CARS];

        // Entrance date
        int[] inYear = new int[MAX_NO_OF_CARS];
        int[] inMonth = new int[MAX_NO_OF_CARS];
        int[] inDay = new int[MAX_NO_OF_CARS];

        // Exit date (0,0,0 means car has not left)
        int[] outYear = new int[MAX_NO_OF_CARS];
        int[] outMonth = new int[MAX_NO_OF_CARS];
        int[] outDay = new int[MAX_NO_OF_CARS];

        // Charging used? (0 = no, 1 = yes)
        int[] charge = new int[MAX_NO_OF_CARS];

        // Cost after exit (0 until car leaves)
        int[] cost = new int[MAX_NO_OF_CARS];

        // Track status (1 = car currently inside, 0 = exited)
        int[] active = new int[MAX_NO_OF_CARS];

        // Number of entries stored so far..no. of cars?
        int count = 0;


        while (true) {
            int userSelection = menu();

            switch (userSelection) {
                case MENU_ITEM_1:
                    System.out.print("Enter car registration number: ");
                    carRegistrationNumbers[count] = carRegistrationNumber();

                    System.out.print("Enter current date (YYYY-MM-DD): ");
                    String date = carRegistrationDate();

                    inYear[count] = Integer.parseInt(date.substring(0, 4));
                    inMonth[count] = Integer.parseInt(date.substring(5, 7));
                    inDay[count] = Integer.parseInt(date.substring(8, 10));


                    System.out.print("Charge electric vehicle (yes/no): ");
                    String ch = chargeElectricVehicle();
                    charge[count] = ch.equalsIgnoreCase("yes") ? 1 : 0;

                    active[count] = 1;

                    System.out.println("\nCar " + carRegistrationNumbers[count] + " entered on " + inYear[count] + "-" + inMonth[count] + "-" + inDay[count] +
                            (charge[count] == 1 ? " (EV charging)" : ""));
                    count++;
                    break;
                case MENU_ITEM_2:
                    System.out.print("Enter car registration number: ");
                    String exitReg = carRegistrationNumber(); // A car is referenced by the registration number which was provided in previous step.

                    int index = findActiveCar(carRegistrationNumbers, active, exitReg);

                    if (index == -1) {
                        System.out.println("Car not currently parked");
                        break;
                    }

                    System.out.print("Enter exit date (YYYY-MM-DD): ");
                    String exitDate = carRegistrationDate();

                    outYear[index] = Integer.parseInt(exitDate.substring(0, 4));
                    outMonth[index] = Integer.parseInt(exitDate.substring(5, 7));
                    outDay[index] = Integer.parseInt(exitDate.substring(8, 10));

                    int days = calculateDays(inYear[index], inMonth[index], inDay[index],
                            outYear[index], outMonth[index], outDay[index]);

                    if (days < 0) {
                        System.out.println("Exit date cannot be before entrance date!");
                        break;
                    }

                    cost[index] = calculateCost(days, charge[index]);
                    active[index] = 0;

                    System.out.println("\n############### PARKING RECEIPT #############");
                    System.out.println("Registration number: " + carRegistrationNumbers[index]);
                    System.out.println("In : " + inYear[index] + "-" + inMonth[index] + "-" + inDay[index]);
                    System.out.println("Out : " + outYear[index] + "-" + outMonth[index] + "-" + outDay[index]);
                    System.out.println("Days parked: " + days);
                    System.out.println(charge[count] == 1 ? " (EV charging)" : "");
                    System.out.println("Total cost: " + cost[index] + "kr");
                    System.out.println("####################################\n");
                    break;
                case MENU_ITEM_3:
                    System.out.print("Enter car registration number: ");
                    String currentReg = carRegistrationNumber();

                    int indexOfCurrentReg = findActiveCar(carRegistrationNumbers, active, currentReg);

                    if (indexOfCurrentReg == -1) {
                        System.out.println("Car not currently parked");
                        break;
                    } else {
                        System.out.println("\nCar " + carRegistrationNumbers[indexOfCurrentReg] + " entered on " + inYear[indexOfCurrentReg] + "-" + inMonth[indexOfCurrentReg] + "-" + inDay[indexOfCurrentReg] +
                                (charge[indexOfCurrentReg] == 1 ? " (EV charging)" : ""));
                    }
                    break;
                case MENU_ITEM_4:
                    System.out.print("Enter your option ");
                    int option4 = input();
                    break;
                case MENU_ITEM_5:
                    System.out.print("Enter your option ");
                    int option5 = input();
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }

    }

    /**
     * the program should print a menu of options that can be selected by the user.
     * It should be possible to exit the program by pressing q
     *
     * @return
     */
    public static int menu() {
        System.out.println("---------------------------");
        System.out.println("# LULEA AIRPORT PARKING LOT");
        System.out.println("---------------------------");
        System.out.println("1. Drive in");
        System.out.println("2. Drive out");
        System.out.println("3. Check parking");
        System.out.println("4. Print parking history (by arrival date)");
        System.out.println("5. Print parking history (by registration number)");
        System.out.println("q. End your program");
        System.out.print("> Enter your option: ");

        return input();

    }

    /**
     * Handles user input with necessary validations
     *
     * @return
     */
    public static int input() {
        while (true) {
            if (userInputScanner.hasNextInt()) {
                return userInputScanner.nextInt();
            }

            String inString = userInputScanner.next();
            if (inString.equalsIgnoreCase("q")) {
                System.exit(0);
            } else {
                System.out.print("Invalid input. Try again");
            }

        }

    }

    /**
     * User is required to provide the registration number of the vehicle, as well as current date.
     * Assume that there are 12 months in a year and 30 days per month. The maximum parking time is one month or 30 days.
     * The date should be provided in current format: YYYY-MM-DD.
     * There are no constraints on the registration number besides from them being between 3 and 8 characters long.
     * The user should also be prompted with an option to charge the vehicle during parking .
     */
//    public static void driveIn() {
//
//
//
//    }
    public static String carRegistrationNumber() {
        while (true) {
            String reg = userInputScanner.next();
            if (reg.equalsIgnoreCase("q")) {
                System.exit(0);
            }
            if (reg.length() >= 3 && reg.length() <= 8) {
                return reg;
            } else {
                System.out.print("Invalid registration number. Enter 3-8 chars: ");
            }

        }
    }

    public static String carRegistrationDate() {
        while (true) {
            String date = userInputScanner.next();
            if (date.equalsIgnoreCase("q")) {
                System.exit(0);
            }
            if (date.length() == 10 && date.charAt(4) == '-' && date.charAt(7) == '-') {
                return date;
            } else {
                System.out.print("Invalid format. Use YYYY-MM-DD: ");
            }
        }
    }

    public static String chargeElectricVehicle() {
        while (true) {
            String s = userInputScanner.next();
            if (s.equalsIgnoreCase("q")) {
                System.exit(0);
            }
            if (s.equalsIgnoreCase("yes") || s.equalsIgnoreCase("no")) {
                return s;
            } else {
                System.out.print("Type 'yes' or 'no': ");
            }

        }
    }

    /**
     * Provide the date of departure.
     * A car is referenced by the registration number which was provided in previous step.
     * The cost of parking is 120kr per day for the first 10 days, 50kr per day for days 11-30.
     * If the vehicle has been charged during parking, an additional charge of 250kr is imposed.
     * The user should be presented with a receipt once the necessary details are provided.
     * Make sure to implement validations (for example that the car can not exit the parking earlier than the entrance date etc.)
     */
//    public static int[][] DriveOut(String[] carRegistrationNumbers, String carRegistrationNumber, String chargeElectricVehicle
//    , String carRegistrationDate) {
//        for (int i = 0; i < carRegistrationNumbers.length; i++) {
//            if (carRegistrationNumbers[i].equals(carRegistrationNumber)) { // not sure about where active[i] == 1;
//                return carRegistrationNumber;
//                // date of departure - arrival date
//                // if chargeElectricVehicle - yes calculate cost of parking
//            }
//            else {
//                System.out.print("Car not parked");
//            }
//        }
//        // print receipt
//
//
//    }
    public static int findActiveCar(String[] carRegistrationNumbers, int[] active, String target) {
        for (int i = 0; i < carRegistrationNumbers.length; i++) {
            if (active[i] == 1 && carRegistrationNumbers[i] != null && carRegistrationNumbers[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    public static int calculateDays(int inYear, int inMonth, int inDay, int outYear, int outMonth, int outDay) { // initialised as arrays why use as int
        int start = inYear * 360 + inMonth * 30 + inDay;
        int end = outYear * 360 + outMonth * 30 + outDay;
        return (end - start) + 1;
    }

    public static int calculateCost(int days, int charge) {
        int cost = 0;

        if (days <= 10) {
            cost = days * FIRST_TEN_DAYS_PARKING_COST_PER_DAY;
        } else {
            cost = 10 * FIRST_TEN_DAYS_PARKING_COST_PER_DAY + (days - 10) * LAST_TWENTY_DAYS_PARKING_COST_PER_DAY;
        }

        if (charge == 1) {
            cost += 250;
        }
        return cost;
    }

    /**
     * Checks whether a vehicle is currently parked
     * If parked, user should see the entrance date
     * else user sees car is not parked at the moment
     * @return
     */
//    public static int checkingTheParking() {
//        return 0;
//
//    }

    /**
     * Prints parking history sorted by entrance date.
     * The user should be able to see all previously parked cars and following information:
     * Registration number
     * Entrance date
     * Exit date (if exited)
     * Charging used (Yes/No)
     * Parking cost (if exited)
     */
    public static void printParkingHistoryByEntranceDate(String[] carRegistrationNumbers, int[] inYear, int[] inMonth, int[] inDay
            , int[] outYear, int[] outMonth, int[] outDay, int[] charge, int[] cost, int count) {

        // Copy arrays
        String[] tempReg = new String[count];
        int[] tempInY = new int[count], tempInM = new int[count], tempInD = new int[count];
        int[] tempOutY = new int[count], tempOutM = new int[count], tempOutD = new int[count];
        int[] tempChg = new int[count], tempCost = new int[count];


        System.arraycopy(carRegistrationNumbers,0,tempReg,0,count);
        System.arraycopy(inYear,0,tempInY,0,count);
        System.arraycopy(inMonth,0,tempInM,0,count);
        System.arraycopy(inDay,0,tempInD,0,count);
        System.arraycopy(outYear,0,tempOutY,0,count);
        System.arraycopy(outMonth,0,tempOutM,0,count);
        System.arraycopy(outDay,0,tempOutD,0,count);
        System.arraycopy(charge,0,tempChg,0,count);
        System.arraycopy(cost,0,tempCost,0,count);

        // Bubble sort using date convert Y*360 + M*30 + D
        for(int i=0;i<count-1;i++){
            for(int j=0;j<count-1-i;j++){
                int d1 = tempInY[j]*360 + tempInM[j]*30 + tempInD[j];
                int d2 = tempInY[j+1]*360 + tempInM[j+1]*30 + tempInD[j+1];

                if(d1 > d2){
                    String s = tempReg[j];  tempReg[j]=tempReg[j+1]; tempReg[j+1]=s;

                    int t;
                    t=tempInY[j]; tempInY[j]=tempInY[j+1]; tempInY[j+1]=t;
                    t=tempInM[j]; tempInM[j]=tempInM[j+1]; tempInM[j+1]=t;
                    t=tempInD[j]; tempInD[j]=tempInD[j+1]; tempInD[j+1]=t;

                    t=tempOutY[j]; tempOutY[j]=tempOutY[j+1]; tempOutY[j+1]=t;
                    t=tempOutM[j]; tempOutM[j]=tempOutM[j+1]; tempOutM[j+1]=t;
                    t=tempOutD[j]; tempOutD[j]=tempOutD[j+1]; tempOutD[j+1]=t;

                    t=tempChg[j]; tempChg[j]=tempChg[j+1]; tempChg[j+1]=t;
                    t=tempCost[j]; tempCost[j]=tempCost[j+1]; tempCost[j+1]=t;
                }
            }
        }

        System.out.println("\n---- Parking history by entrance date ---");
        System.out.printf("%-10s %-12s %-12s %-15s %-12s\n",
                "Reg", "Entered", "Exited", "Charging used", "Parking cost");


    }

    /**
     * Prints parking history sorted by registration number.
     * The user should be able to see all previously parked cars and following information:
     * Registration number
     * Entrance date
     * Exit date (if exited)
     * Charging used (Yes/No)
     * Parking cost (if exited)
     */

    public static void printParkingHistoryByRegistrationNumber(String[] carRegistrationNumbers, int[] inYear, int[] inMonth, int[] inDay,
                                                               int[] outYear, int[] outMonth, int[] outDay, int[] charge, int[] cost, int count) {

        //  copy arrays first so original is not changed
        String[] tempRegistrationNumbers = new String[count];
        int[] tempInYear = new int[count];
        int[] tempInMonth = new int[count];
        int[] tempInDay = new int[count];
        int[] tempOutYear = new int[count];
        int[] tempOutMonth = new int[count];
        int[] tempOutDay = new int[count];
        int[] tempCharge = new int[count];
        int[] tempCost = new int[count];

        System.arraycopy(carRegistrationNumbers, 0, tempRegistrationNumbers, 0, count);
        System.arraycopy(inYear, 0, tempInYear, 0, count);
        System.arraycopy(inMonth, 0, tempInMonth, 0, count);
        System.arraycopy(inDay, 0, tempInDay, 0, count);
        System.arraycopy(outYear, 0, tempOutYear, 0, count);
        System.arraycopy(outMonth, 0, tempOutMonth, 0, count);
        System.arraycopy(outDay, 0, tempOutDay, 0, count);
        System.arraycopy(charge, 0, tempCharge, 0, count);
        System.arraycopy(cost, 0, tempCost, 0, count);

        // Bubble sort by registration
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - 1 - i; j++) {
                if (tempRegistrationNumbers[j].compareToIgnoreCase(tempRegistrationNumbers[j + 1]) > 0) {

                    // swap all associated values
                    String s = tempRegistrationNumbers[j];
                    tempRegistrationNumbers[j] = tempRegistrationNumbers[j + 1];
                    tempRegistrationNumbers[j + 1] = s;

                    int t;
                    t = tempInYear[j];
                    tempInYear[j] = tempInYear[j + 1];
                    tempInYear[j + 1] = t;

                    t = tempInMonth[j];
                    tempInMonth[j] = tempInMonth[j + 1];
                    tempInMonth[j + 1] = t;

                    t = tempInDay[j];
                    tempInDay[j] = tempInDay[j + 1];
                    tempInDay[j + 1] = t;

                    t = tempOutYear[j];
                    tempOutYear[j] = tempOutYear[j + 1];
                    tempOutYear[j + 1] = t;

                    t = tempOutMonth[j];
                    tempOutMonth[j] = tempOutMonth[j + 1];
                    tempOutMonth[j + 1] = t;

                    t = tempOutDay[j];
                    tempOutDay[j] = tempOutDay[j + 1];
                    tempOutDay[j + 1] = t;

                    t = tempCost[j];
                    tempCost[j] = tempCost[j + 1];
                    tempCost[j + 1] = t;
                }
            }
        }

        // ---------------------- PRINT RESULT -------------------------
        System.out.println("\nParking history sorted by registration number:");
        System.out.println("Reg\tEntered\t\tExited\t\tCharging\tCost");

        for (int i = 0; i < count; i++) {
            System.out.println(tempRegistrationNumbers[i] + "\t"
                    + tempInYear[i] + "-" + tempInMonth[i] + "-" + tempInDay[i] + "\t"
                    + (tempOutYear[i] == 0 ? " - " : tempOutYear[i] + "-" + tempOutMonth[i] + "-" + tempOutDay[i]) + "\t"
                    + (tempCharge[i] == 1 ? "Yes" : "No") + "\t\t"
                    + (tempCost[i] == 0 ? "" : tempCost[i] + "kr"));


        }

    }
}
/*
System.out.print("Enter today's date [mm-dd]> "); // 07-31
            userInput.useDelimiter("[-|\\s]+");
 */