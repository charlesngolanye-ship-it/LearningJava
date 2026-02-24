package SkillForge;

public class BankTest {
    public static void main(String[] args) {
        CheckingAccount checkingAccount = new CheckingAccount("Charles", 11, 230.0, 10.0, 5.0);
        SavingsAccount savingsAccount = new SavingsAccount("Mosh",22, 300.0, 200.0,0.10);

        BankAccount[] bankAccounts = new BankAccount[2];
        bankAccounts[0] = checkingAccount;
        bankAccounts[1] = savingsAccount;

        for (BankAccount currentBankAccount : bankAccounts) {
            currentBankAccount.deposit(50.0);
            currentBankAccount.withdraw(20.0);
            System.out.println(currentBankAccount);
        }

    }
}
