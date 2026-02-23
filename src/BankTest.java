public class BankTest {
    public static void main(String[] args) {
        CheckingAccount checkingAccount = new CheckingAccount("Charles", 11, 230.0, 10.0, 5.0);
        SavingsAccount savingsAccount = new SavingsAccount("Mosh",22, 300.0, 0.12);


        BankAccount[] bankAccounts = new BankAccount[2];
        bankAccounts[0] = checkingAccount;
        bankAccounts[1] = savingsAccount;

        for (BankAccount currentBankAccount : bankAccounts) {
            currentBankAccount.deposit(100.0);
            currentBankAccount.withdraw(100.0);
        }
        ;

    }
}
