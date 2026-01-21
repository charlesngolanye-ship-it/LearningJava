//package ltu;
//
//import ltu.dto.*;
//
//import java.util.Scanner;
//
///**
// * Implement parking lot mgt system at Luleå Airport
// * Handles info about entrance/exit dates, cost of parking and parking history
// */
//public class PE1 {
//
//    public static Scanner userInputScanner = new Scanner(System.in);
//
//    public static final int MAX_NO_OF_CARS = 10;
//
//
//    // Menu item constants
//    public static final int MENU_ITEM_1 = 1;
//    public static final int MENU_ITEM_2 = 2;
//    public static final int MENU_ITEM_3 = 3;
//    public static final int MENU_ITEM_4 = 4;
//    public static final int MENU_ITEM_5 = 5;
//    public static final int MENU_ITEM_6 = 6;
//    public static final int MENU_ITEM_Q = -1;
//
//
//    public static void main(final String[] args) {
//        ParkingLot parkingLot = new ParkingLot(MAX_NO_OF_CARS);
//
//        // Index 0 - registration number
//        // Index 1 - arrival date
//        // Index 2 - departure date
//        // Index 3 - yes/no for charging used
//        // Index 4 - parking fee
//        //String[][] parkingLot = new String[MAX_NO_OF_CARS][5];
////        Car[] parkingLot = new Car[MAX_NO_OF_CARS];
////        int numOfCars = 0;
//
////        loadTestData(parkingLot);
////        numOfCars = 4;
//
//
//        while (true) {
//            int userSelection = menu();
//
//            switch (userSelection) {
//                case MENU_ITEM_1:
//                    CheckInCommand cmd = new CheckInCommand();
//
//                    System.out.print("Enter registration number: ");
//                    cmd.registrationNumber = userInputScanner.nextLine();
//
//                    System.out.print("Enter arrival date (YYYY-MM-DD): ");
//                    cmd.arrivalDate = userInputScanner.nextLine();
//
//                    System.out.print("Charge electric car (Yes/No): ");
//                    cmd.chargingUsed = userInputScanner.nextLine();
//
//
//                    CheckInResult result = parkingLot.checkIn(cmd);
//
//
//                    if (!result.success) {
//                        System.out.println(result.message);
//                    } else {
//                        System.out.printf(
//                                "Car %s entered at %s%n",
//                                result.car.getRegistrationNumber(),
//                                result.car.getArrivalDate()
//                        );
//                    }
//
//                    break;
//                case MENU_ITEM_2:
//                    CheckOutCommand outCmd = new CheckOutCommand();
//
//                    System.out.print("Enter registration number: ");
//                    outCmd.registrationNumber = userInputScanner.nextLine();
//
//                    System.out.print("Enter departure date (YYYY-MM-DD): ");
//                    outCmd.departureDate = userInputScanner.nextLine();
//
//                    CheckOutResult outResult = parkingLot.checkOut(outCmd);
//
//
//                    if (!outResult.success) {
//                        System.out.println(outResult.message);
//                    } else {
//                        PE1.printParkingSummary(outResult.car);
//                    }
//                    break;
//
//                case MENU_ITEM_3:
//                    System.out.println("Car Status");
//
//                    System.out.print("Enter Registration Number: ");
//                    String reg = userInputScanner.nextLine();
//
//                    ParkingStatusResult status = parkingLot.getParkingStatus(reg);
//
//                    if (!status.success) {
//                        System.out.println(status.message);
//                    } else {
//                        System.out.printf(
//                                "Car %s is currently parked since %s%n",
//                                status.car.getRegistrationNumber(),
//                                status.car.getArrivalDate()
//                        );
//                    }
//
//                    break;
//
//                case MENU_ITEM_4:
//                    System.out.println("Print history by arrival date ");
//                    printCars(parkingLot.getHistorySortedByArrivalDate());
//                    break;
//
//                case MENU_ITEM_5:
//                    System.out.println("Print history by registration number ");
//                    printCars(parkingLot.getHistorySortedByRegistration());
//                    break;
//
//                case MENU_ITEM_6:
//                    System.out.println("Print parking status ");
//                    printCars(parkingLot.getCurrentParkingStatus());
//                    break;
//
//                case -1:
//                    System.exit(0);
//                    break;
//
//                default:
//                    System.out.println("Invalid entry, please try again");
//            }
//        }
//    }
//
//
//    // Check if car charging is Yes or No
//    public static boolean isChargingValid(String chargeCar) {
//        return chargeCar.equalsIgnoreCase("Yes") || chargeCar.equalsIgnoreCase("No");
//    }
//
//    public static boolean isRegistrationNumberInValidFormatRegex(String registrationNumber) {
//
//        // Check if registration number is in valid format of CCCNNC or CCCNNN
//        return registrationNumber.matches("[a-zA-Z]{3}[0-9]{2}[a-zA-Z]{1}") ||
//                registrationNumber.matches("[a-zA-Z]{3}[0-9]{3}");
//    }
//
//    // Check if registration number is in valid format
//    public static boolean isRegistrationNumberInValidFormat(String registrationNumber) {
//        return registrationNumber.length() < 3 || registrationNumber.length() > 8;
//    }
//
//    /**
//     * the program should print a menu of options that can be selected by the user.
//     * It should be possible to exit the program by pressing q
//     *
//     * @return
//     */
//    public static int menu() {
//        System.out.println("---------------------------");
//        System.out.println("# LULEA AIRPORT PARKING LOT");
//        System.out.println("---------------------------");
//        System.out.println("1. Drive in");
//        System.out.println("2. Drive out");
//        System.out.println("3. Check parking");
//        System.out.println("4. Print parking history (by arrival date)");
//        System.out.println("5. Print parking history (by registration number)");
//        System.out.println("6. Print parking status ");
//        System.out.println("q. End your program");
//        System.out.print("> Enter your option: ");
//
//        return input();
//
//    }
//
//    /**
//     * Handles user input with necessary validations
//     *
//     * @return
//     */
//    public static int input() {
//        int number = 0;
//
//        while (true) {
//            if (userInputScanner.hasNextInt()) {
//                String temp = userInputScanner.nextLine();
//                number = Math.abs(Integer.parseInt(temp));
//                if (number > 0) {
//                    break;
//                }
//            } else if (userInputScanner.hasNext()) {
//                String inString = userInputScanner.nextLine();
//                if (inString.equalsIgnoreCase("q")) {
//                    number = MENU_ITEM_Q;
//                    break;
//                } else {
//                    System.out.println("Invalid entry, please enter a number or 'q' to quit");
//                }
//            }
//        }
//        return number;
//    }
//
//    private static void printCars(Car[] cars) {
//
//        for (Car car : cars) {
//            System.out.printf("Reg: %s | Arrival: %s | Departure: %s | Charging: %s | Fee: %s%n",
//                    car.getRegistrationNumber(),
//                    car.getArrivalDate(),
//                    car.getDepartureDate().isEmpty() ? "-" : car.getDepartureDate(),
//                    car.getChargingUsed(),
//                    car.getParkingFee().isEmpty() ? "-" : car.getParkingFee()
//            );
//        }
//    }
//
//        private static void printParkingSummary(Car car) {
//        System.out.println("###############################################");
//        System.out.println("PARKING RECEIPT");
//        System.out.println("###############################################");
//        System.out.printf("# %-10s %-12s %-12s #%n", "Reg", "IN", "OUT");
//        System.out.printf("# %-10s %-12s %-12s #%n", car.getRegistrationNumber(), car.getArrivalDate(), car.getDepartureDate());
//        System.out.println("# #");
//        System.out.println("Number of days: " + DateUtil.getNumOfDays(car.getArrivalDate(), car.getDepartureDate()) + " days");
//        System.out.println("# Charging: " + car.getChargingUsed() + " #");
//        System.out.println("# Cost: " + car.getParkingFee() + " kr #");
//        System.out.println("##################################################");
//    }
//
//
//}
//
//
