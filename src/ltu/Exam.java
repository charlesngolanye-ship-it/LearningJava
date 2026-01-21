package ltu;

import java.util.Scanner;
/**
 * Implement a Lab Assistant's Management system at LTU -
 * Register's lab assistant's working hours and generates payslips
 * Generates payslips for a single assistant
 * Finally generates a total summary of all assistants
 * @author charlesngolanye chango-5
 */

public class Exam {
    private static Scanner userInputScanner = new Scanner(System.in);

    // Menu item constants
    public static final int MENU_ITEM_1 = 1;
    public static final int MENU_ITEM_2 = 2;
    public static final int MENU_ITEM_3 = 3;
    public static final int MENU_ITEM_4 = 4;
    public static final int MENU_ITEM_5 = 5;
    public static final int MENU_ITEM_Q = -1;

    // Randon number generation
    public static final int NUMBER_ONE = 9000;
    public static final int NUMBER_TWO = 1000;

    public static final int MONTHS_IN_A_YEAR= 12;
    public static final int DAYS_IN_A_MONTH= 31;

    public static final int MAX_MINUTES_IN_HOUR= 59;
    public static final int MAX_HOURS_IN_A_DAY= 23;
    public static final int MINUTES_IN_HOUR= 60;

    public static final int MAX_CREDITS_LOW = 99;
    public static final int MAX_CREDITS_MID = 249;

    public static final int RATE_LOW = 120;
    public static final int RATE_MID = 140;
    public static final int RATE_HIGH = 160;

    public static final int HOURLY_RATE_LESS_THAN_FOURHUNDRED = 160;

    public static final int MINUTES_TO_ROUND_OFF = 29;
    public static final int MINUTES_IN_HALF_HOUR = 30;
    public static final double DOUBLED_SALARY = 60.0;





    public static final int MAX_NO_OF_LAB_ASSISTANTS = 100;
    public static final int MAX_NO_OF_LAB_ASSISTANTS_COLUMNS = 4;
    public static final int MAX_NO_OF_PAYSLIPS = 100;
    public static final int MAX_NO_OF_PAYSLIPS_COLUMNS = 5;

    public static void main(final String[] args) {

        String[][] labAssistants = new String[MAX_NO_OF_LAB_ASSISTANTS][MAX_NO_OF_LAB_ASSISTANTS_COLUMNS];
        String[][] payslips = new String[MAX_NO_OF_PAYSLIPS][MAX_NO_OF_PAYSLIPS_COLUMNS];

        int noOfLabAssistants = 0;
        int noOfPayslips = 0;


        while (true) {

            switch (menu()) {
                case MENU_ITEM_1:
                    System.out.println("Add lab assistant: ");
                    if (registerLabAssistant(labAssistants, noOfLabAssistants)) {
                        System.out.printf("%s%s%s%s%s\n", "Lab Assistant ", labAssistants[noOfLabAssistants][0], " was assigned ID ", labAssistants[noOfLabAssistants][1], " and added to the system. ");

                        noOfLabAssistants++;
                    }

                    break;
                case MENU_ITEM_2:
                    System.out.println("Remove lab assistant: ");
                    removeLabAssistant(labAssistants, noOfLabAssistants);
                    noOfLabAssistants--; // only decrement if removal succeeds

                    break;

                case MENU_ITEM_3:
                    System.out.println("Register working hours");
                    registerWorkingHours(labAssistants, noOfLabAssistants, payslips);

                    break;

                case MENU_ITEM_4:
                    System.out.println("Print payslip ");
                    printPayslip(labAssistants, noOfLabAssistants, payslips);

                    break;

                case MENU_ITEM_5:
                    System.out.println("Print assistant summary");
                    printAssistantSummary(labAssistants, noOfLabAssistants,payslips);

                    break;

                case MENU_ITEM_Q:
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid menu item");
            }
        }
    }

    /**
     * prints summary of all assistants
     * @param labAssistants array of assistants
     * @param noOfLabAssistants number of assistants
     * @param payslips array of payslip
     */
    public static void printAssistantSummary(final String[][] labAssistants, final int noOfLabAssistants, final String[][] payslips) {
        System.out.println("LTU Lb Assistant Manager Summary: ");

        System.out.println("Lab assistants: ");
        System.out.printf("%s %s %s %s\n", "Name", "ID", "Credits", "Salary");


        int totalSessions = 0;
        int totalSalary = 0;


        for (int i = 0; i < noOfLabAssistants; i++) {
            String name = labAssistants[i][0];
            String id = labAssistants[i][1];
            int credits = Integer.parseInt(labAssistants[i][2]);

            // Sum salary from payslips for this assistant
            int assistantSalary = 0;
            int assistantSessions = 0;

            for (int j = 0; j < MAX_NO_OF_PAYSLIPS; j++) {
                if (payslips[j][0] != null && Integer.parseInt(payslips[j][0]) == i) {
                    assistantSalary += Integer.parseInt(payslips[j][4]);
                    assistantSessions++;
                }
            }

            totalSalary += assistantSalary;
            totalSessions += assistantSessions;

            System.out.printf("%s %s %d %d kr\n", name, id, credits, assistantSalary);
        }
        System.out.println("Total number of sessions: " + totalSessions);
        System.out.println("Total Salary: " + totalSalary + " kr");

    }

    /**
     * prints summary of an assistants
     * @param labAssistants array of assistants
     * @param noOfLabAssistants number of assistants
     * @param payslips array of payslip
     */

    public static void printPayslip(final String[][] labAssistants, final int noOfLabAssistants, final String[][] payslips) {
        System.out.print("Enter lab assistant's ID number: ");
        String assistantId = userInputScanner.nextLine();

        int assistantIndex = findAssistantIndexById(labAssistants, noOfLabAssistants, assistantId);
        if (assistantIndex == -1) {
            System.out.println("There is no lab assistant registered with ID " + assistantId);
            return;
        }

        System.out.println("Pay slip LTU");
        System.out.println("Name: " + labAssistants[assistantIndex][0] + " (" + labAssistants[assistantIndex][1] + ")");
        System.out.println("Number of education credits: " + labAssistants[assistantIndex][2]);

        System.out.println("Sessions");
        System.out.printf("%s %s %s %s\n", "Date", "Start", "End", "Salary");

        int totalSessions = 0;
        int totalSalary = 0;
        for (int i = 0; i < payslips.length; i++) {
            if (payslips[i][0] != null && Integer.parseInt(payslips[i][0]) == assistantIndex) {
                System.out.printf("%s %s %s %s kr\n", payslips[i][1], payslips[i][2], payslips[i][3], payslips[i][4]);
                totalSessions++;
                totalSalary += Integer.parseInt(payslips[i][4]);
            }
        }

        System.out.println("Total number of sessions: " + totalSessions);
        System.out.println("Total salary: " + totalSalary + " kr");

    }

    /**
     * registers working hours of an assistant
     * @param labAssistants array of assistants
     * @param noOfLabAssistants number of assistants
     * @param payslips array of payslip
     */

    public static void registerWorkingHours(final String[][] labAssistants, final int noOfLabAssistants, final String[][] payslips) {
        System.out.print("Enter lab assistant's ID number: ");
        String assistantId = userInputScanner.nextLine();

        // check if assistant exists
        int assistantIndex = findAssistantIndexById(labAssistants, noOfLabAssistants, assistantId);
        if (assistantIndex == -1) {
            System.out.println("There is no lab assistant registered with ID " + assistantId);
            return;
        }

        String startTime;
        String endTime;
        String date;

        while (true) {
            System.out.print("Enter start time of the session: ");
            startTime = userInputScanner.nextLine();

            if (!isTimeFormatValid(startTime)) {
                System.out.println("Invalid time");
                continue;
            }
            if (!isTimeValid(startTime)) {
                System.out.println("Invalid time");
                continue;
            }

            System.out.print("Enter end time of the session: ");
            endTime = userInputScanner.nextLine();
            if (!isTimeFormatValid(endTime)) {
                System.out.println("Invalid time");
                continue;
            }
            if (!isTimeValid(endTime)) {
                System.out.println("Invalid time");
                continue;
            }

            System.out.print("Enter date of the session: ");
            date = userInputScanner.nextLine();
            if (!isDateFormatValid(date)) {
                System.out.println("Invalid date");
                continue;
            }
            if (!isDateValid(date)) {
                System.out.println("Invalid date");
                continue;
            }
            break;
        }

        // Time calculation
        int startMinutes = Integer.parseInt(startTime.substring(0, 2)) * MINUTES_IN_HOUR
                + Integer.parseInt(startTime.substring(3, 5));
        int endMinutes = Integer.parseInt(endTime.substring(0, 2)) * MINUTES_IN_HOUR
                + Integer.parseInt(endTime.substring(3, 5));

        if (endMinutes < startMinutes) {
            System.out.println("Invalid time");
            return;
        }

        int sessionMinutes = endMinutes - startMinutes;

        //if less than 60 paid for 1hr + round to next 30 min block
        int paidMinutes;

        if (sessionMinutes < MINUTES_IN_HOUR) {
            paidMinutes = MINUTES_IN_HOUR;
        } else {
            paidMinutes = ((sessionMinutes + MINUTES_TO_ROUND_OFF) / MINUTES_IN_HALF_HOUR) * MINUTES_IN_HALF_HOUR; // round off to nearest 30 minute block
        }
        double paidHours = paidMinutes / DOUBLED_SALARY;

        int credits = Integer.parseInt(labAssistants[assistantIndex][2]);

        int hourlyRate;
        if (credits <= MAX_CREDITS_LOW) {
            hourlyRate = RATE_LOW;
        } else if (credits <= MAX_CREDITS_MID) {
            hourlyRate = RATE_MID;
        } else {
            hourlyRate = RATE_HIGH;
        }

        double salary = paidHours * hourlyRate;

        // Store info in payslip array
        int payslipIndex = 0;
        while ( payslipIndex < MAX_NO_OF_PAYSLIPS && payslips[payslipIndex][0] != null) {
            payslipIndex++;
        }

        if (payslipIndex < MAX_NO_OF_PAYSLIPS) {
            payslips[payslipIndex][0] = Integer.toString(assistantIndex); // id
            payslips[payslipIndex][1] = date;
            payslips[payslipIndex][2] = startTime;
            payslips[payslipIndex][3] = endTime;
            payslips[payslipIndex][4] = Integer.toString((int) salary);

        }


        System.out.println();
        System.out.println("Lab Assistant: " + labAssistants[assistantIndex][0]);

        System.out.println("Session time: " + (sessionMinutes / MINUTES_IN_HOUR) + " hours " + (sessionMinutes % MINUTES_IN_HOUR) + " minutes");

        System.out.println("Salary: " + (int)salary + " kr");
    }

    /**
     * checks validity of date
     * @param date
     * @return true if date is valid
     */
    public static boolean isDateFormatValid(final String date) {
        //YYYY-MM-DD
        if (date.length() == 10
                && date.charAt(4) == '-'
                && date.charAt(7) == '-'
                && Character.isDigit(date.charAt(0))
                && Character.isDigit(date.charAt(1))
                && Character.isDigit(date.charAt(2))
                && Character.isDigit(date.charAt(3))
                && Character.isDigit(date.charAt(5))
                && Character.isDigit(date.charAt(6))
                && Character.isDigit(date.charAt(8))
                && Character.isDigit(date.charAt(9))){
            return true;
        }
        return false;
    }

    /**
     *  checks if time validity
     * @param time time to validate
     * @return true if valid
     */

    public static boolean isTimeFormatValid(final String time) {
        //09:30
        //01234
        if (time.length() == 5 && time.charAt(2) == ':' && Character.isDigit(time.charAt(0))
                && Character.isDigit(time.charAt(1)) && Character.isDigit(time.charAt(3)) && Character.isDigit(time.charAt(4))) {
            return true;
        }
        return false;
    }

    /**
     * Check if time is right
     * @param time is time to validate
     * @return true if time is valid
     */

    public static boolean isTimeValid(final String time) {
        //09:30, 13:00
        //01234
        // hour > 0 && <= 23, minutes > 0 && <= 59
        int hours = Integer.parseInt(time.substring(0, 2)); // confirm these ranges
        int minutes = Integer.parseInt(time.substring(3, 5));  //. confirm these ranges

        if (hours >= 0 && hours <= MAX_HOURS_IN_A_DAY && minutes >= 0 && minutes <= MAX_MINUTES_IN_HOUR) {
            return true;
        }
        return false;
    }

    /**
     *  checks date valid
     * @param date to be checked
     * @return true if valid is correct
     */

    public static boolean isDateValid(final String date) {
        // 2024-02-31
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8, 10));

        if (year > 0 && month > 0 && month <= MONTHS_IN_A_YEAR && day > 0 && day <= DAYS_IN_A_MONTH) {
            return true;
        }
        return false;
    }

    /**
     * method removes an assistant
     * @param labAssistants array of assistants
     * @param noOfLabAssistants number of assistants
     * @return true
     */


    public static boolean removeLabAssistant(final String[][] labAssistants, final int noOfLabAssistants) {
        if (noOfLabAssistants > 0) {
            System.out.print("Enter assistant's ID number: ");
            String assistantId = userInputScanner.nextLine();

            int removeIndex = findAssistantIndexById(labAssistants, noOfLabAssistants, assistantId);
            if (removeIndex == -1) {
                System.out.println("There is no lab assistant registered with ID " + assistantId);
                return false;
            }

            // Store name
            String[] removedAssistant = labAssistants[removeIndex];

            // shift rows left
            for (int i = removeIndex; i < noOfLabAssistants - 1; i++) {
                labAssistants[i] = labAssistants[i + 1];
            }

            // Clear the last row
            labAssistants[noOfLabAssistants - 1] = new String[MAX_NO_OF_LAB_ASSISTANTS_COLUMNS];

            System.out.println("Lab Assistant " + removedAssistant[0] + " was removed from the system. ");

        } else {
            System.out.println("Invalid ID");
            return false;
        }
        return true;
    }

    /**
     * Finds assistant by index
     * @param labAssistants array
     * @param noOfLabAssistants assistants
     * @param assistantId assistant id to be found
     * @return i if found
     */

    public static int findAssistantIndexById(final String[][] labAssistants, final int noOfLabAssistants ,final String assistantId) {
        for (int i = 0; i < noOfLabAssistants; i++) {
            if (labAssistants[i][1].equalsIgnoreCase(assistantId)) {
                return i;
            }
        }
        return -1;
    }

    /**
     *  Registers a lab assistant
     * @param labAssistants assistants array
     * @param noOfLabAssistants number of assistants
     * @return true
     */

    public static boolean registerLabAssistant(final String[][] labAssistants, final int noOfLabAssistants) {

        if (noOfLabAssistants < MAX_NO_OF_LAB_ASSISTANTS) {
            System.out.println("Enter lab assistant's name: ");
            String labAssistantName = userInputScanner.nextLine();

            System.out.println("Enter no of education credits: ");
            String noOfEducationCredits;

            while (true) {
                noOfEducationCredits = userInputScanner.nextLine();

                if (noOfEducationCredits.equalsIgnoreCase("q")) {
                    return false;
                }

                if (validNoOfEducationCredits(noOfEducationCredits)) {
                    break;
                } else {
                    System.out.println("Invalid credits");
                }
            }

            // Generate random ID
            int labAssistantId = (int) (Math.random() * NUMBER_ONE) + NUMBER_TWO;


            // Add lab assistant to labAssistants array
            labAssistants[noOfLabAssistants][0] = labAssistantName;
            labAssistants[noOfLabAssistants][1] = Integer.toString(labAssistantId);
            labAssistants[noOfLabAssistants][2] = noOfEducationCredits;
            labAssistants[noOfLabAssistants][3] = "";// empty for now, not recorded salary
        } else {
            System.out.println("You have reached the maximum number of lab assistants");
        }

        return true;
    }

    /**
     * checks validity of credits
     * @param noOfEducationCredits number of education credits
     * @return true
     */

    public static boolean validNoOfEducationCredits(final String noOfEducationCredits) {
        //0-400
        try {
            return (Integer.parseInt(noOfEducationCredits)) >= 0 && Integer.parseInt(noOfEducationCredits) <= 400;
        } catch (NumberFormatException e) {
            return false;
        }

    }



    /**
     * The method prints a menu of options that can be selected by the user.
     * It should be possible to exit the program by pressing q
     *
     * @return input
     */
    public static int menu() {
        // System.out.println("---------------------------");
        //System.out.println("# LTU Lab Assistant Manager");
        // System.out.println("---------------------------");
        System.out.println("1. Add lab assistant");
        System.out.println("2. Remove lab assistant");
        System.out.println("3. Register working hours");
        System.out.println("4. Print payslip");
        System.out.println("5. Print assistant summary");
        System.out.println("q. End program");
        // System.out.print("> Enter your option: ");

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
}


