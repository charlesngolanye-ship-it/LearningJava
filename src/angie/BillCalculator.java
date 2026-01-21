package angie;

public interface BillCalculator {
    double calculateBill();
    double calculateOverage();
    double calculateTax();
    double getBaseCost();
}
