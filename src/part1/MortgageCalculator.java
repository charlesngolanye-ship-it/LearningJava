package part1;

public class MortgageCalculator {
    private final static int  MONTHS_IN_YEAR = 12;
    private final static int PERCENT = 100;

    private int principal;
    private double annualInterest;
    private int years;


    public MortgageCalculator(int principal, double annualInterest, int years) {
        this.principal = principal;
        this.annualInterest = annualInterest;
        this.years = years;
    }


    public double calculateBalance (int numberOfPaymentsMade) {
        double monthlyInterest = getMonthlyInterest();
        int numberOfPayments = getNumberOfPayments();


        double balance = principal * (Math.pow(1 + monthlyInterest, numberOfPayments) - Math.pow(1 + monthlyInterest, numberOfPaymentsMade)) /
                (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);

        return balance;

        /*
        * Given a specific month(number of payments made), this method returns the remaining balance
        * Used inside getRemainingBalances() to get balances for all months
         */
    }

    public double[] getRemainingBalances (){
        var balances = new double[getNumberOfPayments()];
        for (int month = 1; month <= balances.length; month++) {
            balances [month - 1] = calculateBalance(month);
        }
        return balances;

        /*
         * Calls calculateBalance(month) for each month of the loan period and stores it in an array
         * Used by printPaymentSchedule() to display all balances month by month
         */
    }

    public double calculateMortgage (){
        double monthlyInterest = getMonthlyInterest();
        int numberOfPayments = getNumberOfPayments();

       double mortgage = principal * (monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayments)) /
               (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);

       return mortgage;
    }



 

    private double getMonthlyInterest(){
        return annualInterest / MONTHS_IN_YEAR / PERCENT;
    }

    public int getNumberOfPayments() {
        return years * MONTHS_IN_YEAR;
    }
}
