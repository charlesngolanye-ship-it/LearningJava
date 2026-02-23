package SkillForge;

public class BankAccount {
    private final String name;
    private final int accountNumber;
    private double balance;

    public BankAccount(String name, int accountNumber, double balance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }



   @Override
   public String toString() {
        return String.format("Account name:%s%nAccount number:%d%nBalance: %.2f", getName(), getAccountNumber(), getBalance());
   }

    public void deposit(double amount) {
        if (amount <= 0.0) {
            throw new IllegalArgumentException("Amount must be greater than 0.");
        }
        this.balance += amount;
    }

    public void withdraw(double amount) {
        if (amount > getBalance()) {
            throw new IllegalArgumentException("Amount must be less than balance.");
        }
        this.balance -= amount;
    }
}
