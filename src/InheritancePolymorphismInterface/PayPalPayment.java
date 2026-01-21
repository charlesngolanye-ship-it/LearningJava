package InheritancePolymorphismInterface;

public class PayPalPayment  extends Payment implements Refundable{

    private String email;

    public PayPalPayment(double amount, String email) {
        super(amount);
        this.email = email;
    }

    @Override
    public void processPayment(){
        System.out.println("Processing PayPal payment of $" + amount + " for email " + email);
    }

    @Override
    public void refund(){
        System.out.println("Refunding PayPal payment of $" + amount + " to email " + email);
    }
}

// *How is Polymorphism Used?
// *processPayment() behaves differently depending on the payment type.
// *We can store both CreditCardPayment and PayPalPayment objects in a Payment reference.
//Now, PayPal payments support refunds, but credit cards do not.
//This demonstrates multiple inheritance, since Java doesn't allow multiple class inheritance, but we can implement multiple interfaces.
