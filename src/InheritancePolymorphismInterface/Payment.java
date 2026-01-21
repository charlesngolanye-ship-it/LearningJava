package InheritancePolymorphismInterface;
/*
* Example to illustrate abstraction, polymorphism and interface
* Let's say we are designing a payment system that supports different payment methods
* like Credit Card and PayPal
* We can create an abstract class Payment to define a blueprint for all payment types.
* It contains a concrete method printReceipt() and
* abstract method processPayment() which must be implemented by subclasses
 */

abstract class Payment {
    double amount;

    public Payment(double amount){
        this.amount = amount;
    }

    //Abstract method - implemented differently for each payment method
    abstract void processPayment();

    //Concrete method - common for all payment methods
    public void printReceipt(){
        System.out.println("Payment of $" + amount + " has been processed.");
    }
}
