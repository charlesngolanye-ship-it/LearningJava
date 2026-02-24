package SkillForge;

public class CheckingAccount extends BankAccount {
    private double overdraftAmount;
    private double overdraftFee;

    public CheckingAccount(String name, int accountNumber, double balance, double overdraftAmount, double overdraftFee) {
        super(name, accountNumber, balance);
        this.overdraftAmount = overdraftAmount;
        this.overdraftFee = overdraftFee;
    }

    public double getOverdraftAmount() {
        return overdraftAmount;
    }

    public void setOverdraftAmount(double overdraftAmount) {
        this.overdraftAmount = overdraftAmount;
    }

    public double getOverdraftFee() {
        return overdraftFee;
    }

    public void setOverdraftFee(double overdraftFee) {
        this.overdraftFee = overdraftFee;
    }

    @Override
    public String toString() {
        return String.format("Checking Account:\n %n%s %.2f%n %s %.2f%n %s", "Overdraft Fee:",
                getOverdraftFee(),
                "Overdraft Amount",getOverdraftAmount(),
                super.toString());
    }


    @Override
    public void withdraw(double amount) {
        if (amount <= getBalance()) {
            super.withdraw(amount);
        } else if ( amount <= getBalance() - amount -overdraftFee) {
            setBalance(getBalance() - amount -overdraftFee);
        }
        else  {
            throw new IllegalArgumentException(" Amount exceeds overdraft limit.");
        }
    }
}

/**
 * balance here should deduct overdraft amount + overdraft fee
 */
