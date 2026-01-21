import java.util.Scanner;

public class PhoneBillCalculator {
    private static final double PLAN_RATE = 0.25;
    private static final double TAX_RATE = 0.15;
    private double basePlan;
    private int overageMinutes;

    static Scanner scanner = new Scanner(System.in);

    public PhoneBillCalculator(){
        setBasePlan();
        setOverageMinutes();
    }

    private double calculateOverage(){
        return overageMinutes * PLAN_RATE;
    }

    private double calculateTax(double overage){
        return (basePlan + overage) * TAX_RATE;
    }

    private double calculateTotal(double overage, double tax){
        return (basePlan + overage + tax);
    }

    private void setBasePlan(){
        System.out.print("Enter base cost of the plan");
        basePlan  = scanner.nextDouble();
        if(basePlan <= 0)
            throw new IllegalArgumentException("base plan cannot be 0 or less");
    }

    private void setOverageMinutes(){
        System.out.print("Enter overage minutes");
        overageMinutes = scanner.nextInt();
    }

    public void printPhoneBillStatement (){
        double overage = calculateOverage();
        double tax = calculateTax(overage);
        double total = calculateTotal(overage, tax);



        System.out.println("\nPhone Bill Statement");
        System.out.printf("Plan: $%.2f%n", basePlan);
        System.out.printf("Overage: $%.2f%n", overage);
        System.out.printf("Tax: $%.2f%n", tax);
        System.out.printf("Total: $%.2f%n", total);

    }

}
