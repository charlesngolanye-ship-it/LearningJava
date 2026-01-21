package part1;


import java.text.NumberFormat;

public class MortgageReport {

    private final NumberFormat currencyInstance;
    private MortgageCalculator calculator;

    public MortgageReport(MortgageCalculator calculator) {
        this.calculator = calculator;
        currencyInstance = getCurrencyInstance();
    }

    public void printPaymentSchedule(){
        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("________");
        for (double balance : calculator.getRemainingBalances())
            System.out.println(getCurrencyInstance().format(balance));

    }

    public void printMortgage(){
        double mortgage = calculator.calculateMortgage();

        String mortgageFormatted = currencyInstance.format(mortgage);

        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("________");
        System.out.println("Monthly Payments: " + mortgageFormatted);

    }

    private static NumberFormat getCurrencyInstance() {
        return NumberFormat.getCurrencyInstance();
    }
}
