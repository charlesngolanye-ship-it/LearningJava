package part2;

public class Main {
    public static void main(String[] args){
        var calculator = new TaxCalculator2018(200_000);// concrete method being injected
        var report = new TaxReport();// we pass calculator to report
        report.show(calculator);

        //report.setCalculator(new TaxCalculator2019(200_000));
        report.show(new TaxCalculator2019(200_000));
    }
}
