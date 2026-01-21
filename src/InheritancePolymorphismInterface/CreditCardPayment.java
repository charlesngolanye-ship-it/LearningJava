package InheritancePolymorphismInterface;
/*
* Polymorphism - implementing different payment methods
 */

public class CreditCardPayment extends Payment{
    private String cardNumber;

    public CreditCardPayment(double amount, String cardNumber){
        super(amount);
        this.cardNumber = cardNumber;
    }

    @Override
    public void processPayment(){
        System.out.println("Processing credit card payment of $" + amount + " for card: " + cardNumber);
    }
}
