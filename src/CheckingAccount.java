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
        return super.toString();
    }

    @Override
    public void deposit(double amount) {
        super.deposit(amount);
    }

    @Override
    public void withdraw(double amount) {
        if (amount > (getBalance() + getOverdraftAmount())) {
            throw new IllegalArgumentException("Amount must be less than balance and overdraft amount.");
        }
        super.withdraw(amount);
    }
}
