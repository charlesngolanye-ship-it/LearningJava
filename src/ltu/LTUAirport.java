package ltu;

import java.util.Scanner;
    /**
     * Implement Airport AD Management system at LTU Airport
     * Handles info about flight scheduling and management of departures and arrivals
     */
public class LTUAirport {
        public static Scanner userInputScanner = new Scanner(System.in);

        // Menu item constants
        public static final int MENU_ITEM_1 = 1;
        public static final int MENU_ITEM_2 = 2;
        public static final int MENU_ITEM_3 = 3;
        public static final int MENU_ITEM_4 = 4;
        public static final int MENU_ITEM_5 = 5;
        public static final int MENU_ITEM_Q = -1;

        public static final int MAX_NO_OF_ARRIVALS = 10;
        public static final int MAX_NO_OF_DEPARTURES = 10;


        public static void main(final String[] args) {

            String[][] arrivals = new String[MAX_NO_OF_ARRIVALS][4];// 0->Flight, 1->From, 2->STA, 3->ATA
            String[][] departures = new String[MAX_NO_OF_DEPARTURES][4];

            int noOfArrivals = 0;
            int noOfDepartures = 0;


            while (true) {

                switch (menu()) {
                    case MENU_ITEM_1:
                        System.out.println("Register the scheduled arrival: ");
                        if (registerScheduledArrivals(arrivals, noOfArrivals, departures, noOfDepartures)) {
                            System.out.printf("%s%s%s%s%s%s\n", "Arrival of flight ", arrivals[noOfArrivals][0], " from ", arrivals[noOfArrivals][1], " is scheduled for ", arrivals[noOfArrivals][2]);// arrivals[i][2]??

                            noOfArrivals++;
                        }
                        break;
                    case MENU_ITEM_2:
                        System.out.println("Register the scheduled departure ");
                        if (registerScheduledDepartures(arrivals, noOfArrivals, departures, noOfDepartures)) {
                            System.out.printf("%s%s%s%s%s%s\n", "Departure of flight ", departures[noOfDepartures][0], " to ", departures[noOfDepartures][1], " is scheduled for ", departures[noOfDepartures][2]);

                            noOfDepartures++;
                        }
                        break;

                    case MENU_ITEM_3:
                        System.out.println("Register the actual arrival of a flight");
                        registerActualArrivals (arrivals, noOfArrivals);
                        break;

                    case MENU_ITEM_4:
                        System.out.println("Register the actual departure of a flight ");
                        registerActualDepartures (departures, noOfDepartures);
                        break;

                    case MENU_ITEM_5:
                        System.out.println("Print operations summary");
                        printOperationsSummary(arrivals, noOfArrivals, departures, noOfDepartures);
                        break;

                    case MENU_ITEM_Q:
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid entry, please try again");
                }
            }
        }


        /**
         * the program should print a menu of options that can be selected by the user.
         * It should be possible to exit the program by pressing q
         *
         * @return input();
         */
        public static int menu() {
            System.out.println("---------------------------");
            System.out.println("# LTU AIRPORT AD Management System");
            System.out.println("---------------------------");
            System.out.println("1. Register the scheduled arrival");
            System.out.println("2. Register the scheduled departure");
            System.out.println("3. Register the actual arrival of a flight");
            System.out.println("4. Register the actual departure of a flight");
            System.out.println("5. Print operations summary");
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
                    number = Math.abs(userInputScanner.nextInt());
                    userInputScanner.nextLine();
                    if (number > 0) {
                        break;
                    } else {
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
         * This method registers scheduled arrivals
         * @param arrivals array of an arriving flight
         * @param noOfArrivals number of arrivals
         * @param departures array of a departig flight
         * @param noOfDepartures number of departures
         * @return true if data added correctly
         */


        public static boolean registerScheduledArrivals(final String[][] arrivals, final int noOfArrivals, final String[][] departures, final int noOfDepartures) {
            if(noOfArrivals >= MAX_NO_OF_ARRIVALS) {
                System.out.println("You have reached the maximum number of arrivals");
                return false;
            }
            String flightNumber = "";

            while (true) {
                System.out.print("Enter Flight number: ");
                flightNumber = userInputScanner.nextLine();

                // Check if flight exists in arrivals -> so that you do not re-enter it
                for (int i = 0; i < noOfArrivals; i++) {
                    if (arrivals[i][0].equalsIgnoreCase(flightNumber)) { // beware of vs noOfArrivals errors -> use i while looping
                        System.out.println("Flight number already exists");
                        return false;
                    }
                }

                // Check if flight exists in departures -> should also not be possible to have an arriving and a departing flight with
                //the same flight number
                for (int i = 0; i < noOfDepartures; i++) {
                    if (departures[i][0].equalsIgnoreCase(flightNumber)) { // beware of vs noOfArrivals errors -> use i while looping
                        System.out.println("Flight number already exists");
                        return false;
                    }
                }

                // Check if flight number in valid format -> A flight number should start with two (2) capital
                //letters, followed by three (3) digits (for example SK137, HY230, SQ038, etc.)
                if (!isFlightNumberInValidFormatRegex(flightNumber)) {
                    System.out.println("Invalid flight number format");
                    continue;
                }

                break;
            }

            System.out.print("Enter airport of origin: ");
            String airportOfOrigin = userInputScanner.nextLine();

            String scheduledArrivalTime;
            while (true) {
                System.out.print("Enter scheduled time of arrival: ");
                scheduledArrivalTime = userInputScanner.nextLine();

                // Check if is in HH:MM format
                if (!isTimeFormatValid(scheduledArrivalTime)) {
                    System.out.println("Invalid time format");
                    continue;
                }

                // Check if time is a valid time ...this method should come after isTimeFormatValid otherwise the substring method would not work
                if (!isTimeValid(scheduledArrivalTime)) {
                    System.out.println("Invalid time");
                    continue;
                }
                break;
            }

            // Add flight to arrivals array
            arrivals[noOfArrivals][0] = flightNumber;
            arrivals[noOfArrivals][1] = airportOfOrigin;
            arrivals[noOfArrivals][2] = scheduledArrivalTime;
            arrivals[noOfArrivals][3] = ""; // ActualTimeOfArrival is empty

            return true;
        }

        /**
         * Checks if flight number has the right Regular expressions format
         * @param flightNumber flight number
         * @return true if format is valid
         */

        public static boolean isFlightNumberInValidFormatRegex(final String flightNumber) {
            // Check if flight number is in valid format of CCNNN
            return flightNumber.matches("[a-zA-Z]{2}[0-9]{3}"); // confirm if 2 capital letters, 3 digits
        }

        /**
         * Checks if time format is valid
         * @param scheduledArrivalTime scheduled arrival time
         * @return true if right format
         */

        public static boolean isTimeFormatValid(final String scheduledArrivalTime) {
            //09:30
            //01234
            if (scheduledArrivalTime.length() == 5 && scheduledArrivalTime.charAt(2) == ':' && Character.isDigit(scheduledArrivalTime.charAt(0))
                    && Character.isDigit(scheduledArrivalTime.charAt(1)) && Character.isDigit(scheduledArrivalTime.charAt(3)) && Character.isDigit(scheduledArrivalTime.charAt(4))) {
                return true;
            }
            return false;
        }

        /**
         * Check if time is right
         * @param scheduledArrivalTime scheduled arrival time
         * @return true if time is valid
         */

        public static boolean isTimeValid(final String scheduledArrivalTime) {
            //09:30, 13:00
            //01234
            // hour > 0 && <= 23, minutes > 0 && <= 59
            int hours = Integer.parseInt(scheduledArrivalTime.substring(0, 2)); // confirm these ranges
            int minutes = Integer.parseInt(scheduledArrivalTime.substring(3, 5));  //. confirm these ranges

            if (hours >= 0 && hours <= 23 && minutes >= 0 && minutes <= 59) {
                return true;
            }
            return false;
        }

        /**
         * This method registers scheduled departures
         * @param arrivals array of arrivals
         * @param noOfArrivals number of arrivals
         * @param departures array of departures
         * @param noOfDepartures number of departures
         * @return true if data added to departures
         */
        // should have implemented same design as registerScheduledDepartures - use while loop for flight number.


        public static boolean registerScheduledDepartures(final String[][] arrivals, final int noOfArrivals, final String[][] departures, final int noOfDepartures) {
            if(noOfDepartures >= MAX_NO_OF_DEPARTURES) {
                System.out.println("You have reached the maximum number of departures");
                return false;
            }

            System.out.print("Enter Flight number: ");
            String flightNumber = userInputScanner.nextLine();

            // Check if flight number in valid format
            if (!isFlightNumberInValidFormatRegex(flightNumber)) {
                System.out.println("Invalid flight number format");
                return false;
            }


            // check if flight exists in departures
            for (int i = 0; i < noOfDepartures; i++) {
                if (departures[i][0].equalsIgnoreCase(flightNumber)) {
                    System.out.println("Invalid Flight number, already exists");
                    return false;
                }
            }

            // check if flight exists in arrivals
            for (int i = 0; i < noOfArrivals; i++) {
                if (arrivals[i][0].equalsIgnoreCase(flightNumber)) {
                    System.out.println("Invalid Flight number, already exists in arrivals");
                    return false;
                }
            }

            System.out.print("Enter destination airport: ");
            String destinationAirport = userInputScanner.nextLine();

            String scheduledDepartureTime;
            while (true) {
            System.out.print("Enter scheduled time of departure: ");
            scheduledDepartureTime = userInputScanner.nextLine();

            // Check if is in HH:MM format
            if (!isTimeFormatValid(scheduledDepartureTime)) {
                System.out.println("Invalid time format");
                continue;
            }

            // Check if time is a valid time
            if (!isTimeValid(scheduledDepartureTime)) {
                System.out.println("Invalid time");
                continue;
            }

            break;
            }



            // Add flight to departures array
            departures[noOfDepartures][0] = flightNumber;
            departures[noOfDepartures][1] = destinationAirport;
            departures[noOfDepartures][2] = scheduledDepartureTime;
            departures[noOfDepartures][3] = ""; // ActualTimeOfDeparture is empty

            return true;

        }


        /**
         * Checks if time is after Scheduled arrival time
         *
         * @param arrivalTime          arrival time
         * @param scheduledArrivalTime scheduled departure time
         * @return true if time is after scheduled arrival time
         */
        // this method is redudant here - you could have arrivals coming in early before scheduled time
        // but used to check if a flight is delayed when printing total number of delayed flights
        // isTimeAfterOrEqual(String earlierTime, String laterTime)
        public static boolean isTimeAfterOrEqual(final String arrivalTime, final String scheduledArrivalTime) {
            int hours1 = Integer.parseInt(arrivalTime.substring(0, 2));
            int minutes1 = Integer.parseInt(arrivalTime.substring(3, 5));

            int hours2 = Integer.parseInt(scheduledArrivalTime.substring(0, 2));
            int minutes2 = Integer.parseInt(scheduledArrivalTime.substring(3, 5));

            if (hours2 < hours1) {
                return false;
            }
            if (hours2 == hours1 && minutes2 < minutes1) {
                return false;
            } else {
                return true;
            }

        }

        /**
         * The method registers actual arrivals
         * @param arrivals array of arrivals
         * @param noOfArrivals number of arrivals
         */
        // should have used same design...wrap flightNumber in a while loop
        public static void registerActualArrivals (final String[][] arrivals, final int noOfArrivals) {
            System.out.print("Enter flight number for arriving flight: ");
            String flightNumber = userInputScanner.nextLine();

            if (!isFlightNumberInValidFormatRegex(flightNumber)) {
                System.out.println("Invalid flight number format");
                return;
            }

            int arrivalIndex = -1;

            // Check if flight number exists in scheduled arrivals
            for (int i = 0; i < noOfArrivals; i++) {
                if (arrivals[i][0].equalsIgnoreCase(flightNumber)) {
                    arrivalIndex = i;
                    break;
                }
            }

            if (arrivalIndex == -1) {
                System.out.println("Invalid flight number, does not exist in scheduled arrivals");
                return;
            }

            // prevent double registration -> flight might exist in scheduleArrivals and also have its ATA already registered
            // should this validation have gone to actualArrivalTime validation? No, it is not about time format or time correctness...it is about state
            // has this flight already arrived? that belongs right after you locate teh flight, before asking for a time
            if (!arrivals[arrivalIndex][3].isEmpty()) {
                System.out.println("Invalid flight number, actual arrival already exists");
                return;
            }

            String actualArrivalTime;
            while (true) {
                System.out.print("Enter actual time of arrival: ");
                actualArrivalTime = userInputScanner.nextLine();

                // Check if is in HH:MM format
                if (!isTimeFormatValid(actualArrivalTime)) {
                    System.out.println("Invalid time format");
                    continue;
                }

                // Check if time is a valid time
                if (!isTimeValid(actualArrivalTime)) {
                    System.out.println("Invalid time");
                    continue;
                }
                break;
            }

            // Add actual arrivalTime to arrivals array - probably that is why the method is void...just adding something...
            arrivals[arrivalIndex][3] = actualArrivalTime; // was previously empty is scheduledArrivals

            System.out.printf("Flight %s from %s has arrived at %s%n", arrivals[arrivalIndex][0], arrivals[arrivalIndex][1], arrivals[arrivalIndex][3]);
        }

        /**
         * Method registers actual departures
         * @param departures array of departures
         * @param noOfDepartures number of departures
         */
        public static void registerActualDepartures (final String[][] departures, final int noOfDepartures) {
            System.out.print("Enter flight number of departing flight: ");
            String flightNumber = userInputScanner.nextLine();

            if (!isFlightNumberInValidFormatRegex(flightNumber)) {
                System.out.println("Invalid flight number format");
                return;
            }

            int departureIndex = -1;

            for (int i = 0; i < noOfDepartures; i++) {
                if (departures[i][0].equalsIgnoreCase(flightNumber)) {
                    departureIndex = i;
                    break;
                }
            }

            if (departureIndex == -1) {
                System.out.println("Invalid flight number, does not exist in departures");
                return;
            }

            // prevent double registration
            if (!departures[departureIndex][3].isEmpty()) {
                System.out.println("Invalid flight number, actual departure already exists");
                return;
            }

            String actualDepartureTime;
            while (true) {
                System.out.print("Enter actual time of departure: ");
                actualDepartureTime = userInputScanner.nextLine();

                // Check if is in HH:MM format
                if (!isTimeFormatValid(actualDepartureTime)) {
                    System.out.println("Invalid time format");
                    continue;
                }

                // Check if time is a valid time
                if (!isTimeValid(actualDepartureTime)) {
                    System.out.println("Invalid time");
                    continue;
                }

                break;
            }

            // Add actual departure Time to departures array
            departures[departureIndex][3] = actualDepartureTime;

            System.out.printf("Flight %s to %s has departed at %s%n", departures[departureIndex][0], departures[departureIndex][1], departures[departureIndex][3]);
        }

        /**
         * The method prints summary of flight operations
         * @param arrivals array of arrivals
         * @param noOfArrivals number of arrivals
         * @param departures array of departures
         * @param noOfDepartures number of departures
         */
        public static void printOperationsSummary (final String[][] arrivals, final int noOfArrivals, final String[][] departures, final int noOfDepartures) {
            System.out.print("LTU Airport operations summary: ");
            System.out.println();

            System.out.println("Arrivals: ");
            System.out.printf("%-8s %-12s %-6s %-6s\n", "Flight", "From", "STA", "ATA");

            // copy & sort arrivals array by scheduled arrival time - index 2
            String[][] tempArrivals = new String[MAX_NO_OF_ARRIVALS][4];
            System.arraycopy(arrivals, 0, tempArrivals, 0, noOfArrivals);


            for (int i = 0;i < noOfArrivals;i++) {
                for (int j = 0; j < noOfArrivals - 1; j++) {
                    if (tempArrivals[j][2].compareToIgnoreCase(tempArrivals[j + 1][2]) > 0) {
                        String[] temp = tempArrivals[j];
                        tempArrivals[j] = tempArrivals[j + 1];
                        tempArrivals[j + 1] = temp;
                    }
                }

            }

            for (int i = 0;i < noOfArrivals;i++) {
                System.out.printf("%-8s %-12s %-6s %-6s\n", tempArrivals[i][0], tempArrivals[i][1], tempArrivals[i][2], tempArrivals[i][3]);
            }

            System.out.println("Departures: ");
            System.out.printf("%-8s %-12s %-6s %-6s\n", "Flight", "To", "STD", "ATD");

            // copy & sort departures array by scheduled departure time - index 2
            String[][] tempDepartures = new String[MAX_NO_OF_DEPARTURES][4];
            System.arraycopy(departures, 0, tempDepartures, 0, noOfDepartures);

            for (int i = 0; i < noOfDepartures;i++) {
                for (int j = 0; j < noOfDepartures - 1; j++) {
                    if (tempDepartures[j][2].compareToIgnoreCase(tempDepartures[j + 1][2]) > 0) {
                        String[] temp = tempDepartures[j];
                        tempDepartures[j] = tempDepartures[j + 1];
                        tempDepartures[j + 1] = temp;
                    }
                }

            }
            for (int i = 0;i < noOfDepartures;i++) {
                System.out.printf("%-8s %-12s %-6s %-6s\n", tempDepartures[i][0], tempDepartures[i][1], tempDepartures[i][2], tempDepartures[i][3]);
            }

            int totalFlights = noOfArrivals + noOfDepartures;
            System.out.println("Total number of flights: " + totalFlights);
            System.out.println();

            // Delayed arrivals
            int delayedFlights = 0;

            for (int i = 0; i < noOfArrivals; i++) {
                if (!arrivals[i][3].isEmpty()) {
                    if (isTimeAfterOrEqual(arrivals[i][2], arrivals[i][3])) {
                        delayedFlights++;
                    }
                }
            }

            // Delayed departures
            for (int i = 0; i < noOfDepartures; i++) {
                if (!departures[i][3].isEmpty()) {
                    if (isTimeAfterOrEqual(departures[i][2], departures[i][3])) {
                        delayedFlights++;
                    }
                }
            }

            System.out.println("Total number of delayed flights: " + delayedFlights);

        }
}
