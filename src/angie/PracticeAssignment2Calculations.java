package angie;
    import java.util.Scanner;

public class PracticeAssignment2Calculations {


    /**
     * calculate value on a sunny summer day
     * 26 panels @1.7*1 size, produce max290W
     * assume panels working at 100% efficiency
     * assume solar radiation 166 Wh/m2/h
     * assume efficiency of solar cell 20%
     * price = SEK 0.9 /kWh includes tax rebate
     * Production (Wh) = Solar radiation (Wh / m / hour) * efficiency * surface (m2) * hours of sunshine
     @author Charles Ngolanye (chango-5)
     */

    class Main {
        static final double EFFICIENCY_RATE = 0.20;
        static final double W_TO_KW = 1000.0;
        static final double SOLAR_RADIATION = 166.0;
        static final double SOLAR_PANEL_AREA = 1.7;
        static final double NUMBER_OF_PANELS = 26;
        static final double ELECTRICITY_PRICE = 0.9;
        static final String INVALID_DATE = "Error! Invalid date format.";
        static final String INVALID_MONTH = "Error! Invalid month.";
        static final String INVALID_DAY = "Error! Invalid day.";
        static final String INVALID_TIME = "Error! Invalid time format.";
        static final String INVALID_MINUTES = "Error! Invalid time."; //
        static final String INVALID_SUNRISE = "Error! Sunrise must be before sunset.";
        static final String INVALID_RANGE = "Error! Invalid time.";
        static final int JUNE = 6;
        static final int JULY = 7;
        static final int FIRST_DAY_OF_MONTH = 1;
        static final int LAST_DAY_OF_JUNE = 30;
        static final int LAST_DAY_OF_JULY = 31;
        static final double MINUTES_PER_HOUR = 60;
        static final double MAX_HRS_IN_A_DAY = 23.0;
        static final double MAX_MINS_IN_AN_HR = 59.0;

        public static void main(final String[] args) {
            Scanner userInput = new Scanner(System.in);
            int month = 0;
            int day = 0;
            double sunRiseHour = 0.0;
            double sunRiseMinute = 0.0;
            double sunSetHour = 0.0;
            double sunSetMinute = 0.0;
            double numberOfHoursOfSunshine = 0.0;
            double productionOutput = 0.0;
            double profit = 0.0;


            // user enter sun rise + sun set -> hh:mm
            System.out.print("Enter today's date [mm-dd]> "); // 07-31
            userInput.useDelimiter("[-|\\s]+");

            if (userInput.hasNextInt()) {
                month = userInput.nextInt();
                if(month > JULY || month < JUNE){
                    System.out.println(INVALID_MONTH);
                }
            } else {
                System.out.println(INVALID_DATE);
                System.exit(0);
            }

            if (userInput.hasNextInt()) {
                day = userInput.nextInt();
            } else {
                System.out.println(INVALID_DATE);
                System.exit(0);
            }

            if (month == JUNE) {
                if (day < FIRST_DAY_OF_MONTH || day > LAST_DAY_OF_JUNE) {
                    System.out.println(INVALID_DAY);
                    System.exit(0);
                }
            }

            if (month == JULY) {
                if (day < FIRST_DAY_OF_MONTH || day > LAST_DAY_OF_JULY) {
                    System.out.println(INVALID_DAY);
                    System.exit(0);
                }
            }

            System.out.print("Enter sunrise time [hh:mm]> "); // use double for hours
            userInput.useDelimiter("[:\\s]+");

            if (userInput.hasNextDouble()) { //hasNextInt()
                sunRiseHour = userInput.nextDouble();
            } else {
                System.out.println(INVALID_TIME);
                System.exit(0);
            }

            if (userInput.hasNextDouble()) { //hasNextInt()
                sunRiseMinute = userInput.nextDouble();
            } else {
                System.out.println(INVALID_TIME);
                System.exit(0);
            }


            System.out.print("Enter sunset time [hh:mm]> ");
            userInput.useDelimiter("[:\\s]+");
            if (userInput.hasNextDouble()) {  //hasNextInt()
                sunSetHour = userInput.nextDouble();
            } else {
                System.out.println(INVALID_TIME);
                System.exit(0);
            }

            if (userInput.hasNextDouble()) {
                sunSetMinute = userInput.nextDouble();//hasNextInt()
            } else {
                System.out.println(INVALID_TIME);
                System.exit(0);
            }

            if (sunRiseHour < 0 || sunRiseHour > MAX_HRS_IN_A_DAY) {
                System.out.println(INVALID_RANGE); // here we have an issue
                System.exit(0);
            }
            if (sunSetHour < 0 || sunSetHour > MAX_HRS_IN_A_DAY) {
                System.out.println(INVALID_RANGE); // here is the issue
                System.exit(0);
            }
            if (sunSetMinute < 0 || sunSetMinute > MAX_MINS_IN_AN_HR) {
                System.out.println(INVALID_MINUTES); // “Error! Invalid time. Minutes must be between 0 and 59.”
                System.exit(0);
            }
            if (sunRiseMinute < 0 || sunRiseMinute > MAX_MINS_IN_AN_HR) {
                System.out.println(INVALID_MINUTES); // “Error! Invalid time. Minutes must be between 0 and 59.”
                System.exit(0);
            }
            if (sunRiseHour >= sunSetHour) {
                System.out.println(INVALID_SUNRISE);
                System.exit(0);
            }

            System.out.println("-------------------------------");
            System.out.println("-------------------------------");

            userInput.close();

            // calculate no. of hrs of sunshine
            numberOfHoursOfSunshine = Math.abs(sunSetHour - sunRiseHour) + Math.abs(sunSetMinute - sunRiseMinute) / MINUTES_PER_HOUR;
            System.out.println("Sun hours: " + numberOfHoursOfSunshine + " hours");

            // calculate production output -> 166 * .2 * 1.7 * 26 * hrs of sunshine
            productionOutput = SOLAR_RADIATION * EFFICIENCY_RATE * SOLAR_PANEL_AREA * NUMBER_OF_PANELS * numberOfHoursOfSunshine;

            // calculate profit -> 0.9 * production output. 2 decimal places
            profit = (productionOutput / W_TO_KW) * ELECTRICITY_PRICE;

            //System.out.println("The production on " + month + "/" + day + " is: " + productionOutput + " kWh " + "to a value of: " + "SEK " + profit);
            System.out.printf("The production on %d/%d is: %.2f kWh to a value of: SEK %.2f%n", month, day, productionOutput, profit );
        }

    }

}
