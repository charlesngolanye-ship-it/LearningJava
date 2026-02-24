package SkillForge;

public class SavingsAccount extends BankAccount{
    private double interestRate;
    private final double minimumBalance;

    public SavingsAccount(String name, int accountNumber, double balance, double minimumBalance, double interestRate) {
        super(name, accountNumber, balance);

        if (interestRate <= 0.0 || interestRate > 1.0) {
            throw new IllegalArgumentException("Interest rate must be greater than 0.0 and less than 1.0.");
        }

        this.minimumBalance = minimumBalance;
        this.interestRate = interestRate;
    }


    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        if (interestRate <= 0.0 || interestRate > 1.0) {
            throw new IllegalArgumentException("Interest rate must be greater than 0.0 and less than 1.0.");
        }
        this.interestRate = interestRate;
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }

    public void applyInterestRate() {
        setBalance (getBalance() * (1 + interestRate));

    }

    @Override
    public void deposit(double amount) {
        super.deposit(amount); // amount deposited
        applyInterestRate(); // balance is now increased -> not sure if this balance already includes amount and therefore should not call super.deposit
    }

    @Override
    public void withdraw(double amount) {
        if ((getBalance() - amount) < minimumBalance) {
            throw new IllegalArgumentException("Amount must be less than balance.");
        }
        super.withdraw(amount);
    }

    @Override
    public String toString() {
        return String.format("Savings Account:\n %n%s %.2f%n %s %.2f%n %s", "Interest Rate:",
                getInterestRate(),
                "Minimum Balance",getMinimumBalance(),
                super.toString());
    }
}
/**
 * How to return the increased amount after applying interest rate
 * getting confused by balance vs amount ..what is what? amount = money you are trying to deposit/withdraw
 */
