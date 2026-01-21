package InheritancePolymorphismInterface;

public interface Refundable {
    void refund();
}


//*Let's say we want some payments to offer Refunds.
//*We create an interface Refundable, which some (but not all) payments can implement.
//*Now, let's assume PayPal supports refunds, but Credit Cards don't.
//*So only PayPalPayment implements Refundable.
