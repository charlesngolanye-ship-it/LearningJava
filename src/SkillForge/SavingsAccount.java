package SkillForge;

public class SavingsAccount extends BankAccount{
    private double interestRate;

    public SavingsAccount(String name, int accountNumber, double balance, double interestRate) {
        super(name, accountNumber, balance);

        if (interestRate <= 0.0 || interestRate > 1.0) {
            throw new IllegalArgumentException("Interest rate must be greater than 0.0 and less than 1.0.");
        }
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

    public void applyInterestRate() {
        double newBalance =  (getBalance() * (1 + (getInterestRate())));
        setBalance(newBalance);
    }

    @Override
    public void deposit(double amount) {
       applyInterestRate(); // balance is now increased
    }

    @Override
    public void withdraw(double amount) {
        if (amount > getBalance()) {
            throw new IllegalArgumentException("Amount must be less than balance.");
        }
        super.withdraw(amount);
    }

    @Override
    public String toString() {
        return super.toString(); // i think this is fine
    }
}
