package angie;

public class CalculatePhoneBill implements BillCalculator {
    private static final double TAX_RATE = 0.15;
    private static final double USAGE_RATE = 0.25;

    private final double baseCost;
    private final double allottedMinutes;
    private final double usedMinutes;

    public CalculatePhoneBill(double baseCost, double allottedMinutes, double usedMinutes) {
        this.baseCost = baseCost;
        this.allottedMinutes = allottedMinutes;
        this.usedMinutes = usedMinutes;
    }

    @Override
    public double calculateBill() {
        return baseCost + calculateOverage() + calculateTax();
    }

    @Override
    public double calculateOverage() {
        if (usedMinutes <= allottedMinutes)
            return 0;
        return (usedMinutes - allottedMinutes) * USAGE_RATE;
    }

    @Override
    public double calculateTax() {
        double overage = (usedMinutes - allottedMinutes) * USAGE_RATE;
        return (baseCost + overage) * TAX_RATE;
    }

    @Override
    public double getBaseCost() {
        return baseCost;
    }
}

