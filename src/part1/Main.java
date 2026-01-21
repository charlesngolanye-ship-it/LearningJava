package part1;

public class Main {

    public static void main(String[] args) {
        int principal = (int)Console.readNumber("Principal: ", 1000, 1_000_000);
        double annualInterest = Console.readNumber("AnnualInterest: ",1,30);
        int years = (int)Console.readNumber("Period (Years): ",1,30);

        var calculator = new MortgageCalculator(principal, annualInterest, years);

        var report = new MortgageReport(calculator);
        report.printMortgage();
        report.printPaymentSchedule();

    }

}


