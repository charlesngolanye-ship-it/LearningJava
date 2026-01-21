package InheritancePolymorphismInterface;
/*
* Upcasting (Generalization)
* We can store different payment types in a single Payment reference
*/

public class Main {
    public static void main(String[] args) {
        Payment payment1 = new CreditCardPayment(100.0, "1234-5678-9876");
        Payment payment2 = new PayPalPayment(50.0, "user@example.com");

        payment1.processPayment();
        payment2.processPayment();

        payment1.printReceipt();
        payment2.printReceipt();


        // Downcasting (Specialization)
        //if we need refund functionality, we check if the object is refundable and downcast it
        if (payment2 instanceof Refundable){
            Refundable refundablePayment = (Refundable) payment2;
            refundablePayment.refund();
        }

    }
}

/*
Summary of How Everything is Related
Abstraction	Payment (abstract class) defines a common structure but lets subclasses implement processPayment().
Polymorphism	processPayment() behaves differently in CreditCardPayment and PayPalPayment, even when called on a Payment reference.
Interfaces	Refundable defines a behavior that only PayPalPayment implements.
Upcasting	Payment payment = new PayPalPayment(50.0, "user@example.com"); - Allows treating different payment methods the same way.
Downcasting	if (payment instanceof Refundable) ((Refundable) payment).refund(); - Calls refund() only if supported.
 */