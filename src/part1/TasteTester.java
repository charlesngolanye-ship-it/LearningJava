package part1;

public class TasteTester {

    public static void main (String[] args){
        /*
        var cake1 = new BirthdayCake("Vanilla");
        var cake2 = new WeddingCake("Chocolate");

        cake1.setPrice(20);
        cake2.setPrice(100);

        System.out.println("Birthday Cake: Flavor = " + cake1.getFlavor() + ", Price = $" + cake1.getPrice());
        System.out.println("Wedding Cake: Flavor = " + cake1.getFlavor() + ", Price = $" + cake2.getPrice());

         */


       Cake cake1 = new BirthdayCake("Vanilla");
       Cake cake2 = new WeddingCake("Chocolate");
       Cake cake3 = new BirthdayCake("Strawberry");


       if (cake1 instanceof BirthdayCake) {
           var birthday = (BirthdayCake)cake1;
           birthday.setCandles(5);
           birthday.setPrice(25);
           System.out.println("Birthday Cake: Flavor = " + birthday.getFlavor() + ", Price = $" + birthday.getPrice() + ", Candles = " + birthday.getCandles());
       }

       if (cake2 instanceof WeddingCake) {
           var wedding = (WeddingCake)cake2;
           wedding.setTiers(3);
           wedding.setPrice(120);
           System.out.println("Birthday Cake: Flavor = " + wedding.getFlavor() + ", Price = $" + wedding.getPrice() + ", Candles = " + wedding.getTiers());
       }

       if (cake3 instanceof BirthdayCake) {
           var thisCake = (BirthdayCake)cake3;
           thisCake.setCandles(10);
           System.out.println(thisCake.getFlavor() + " Birthday Cake costs $" + thisCake.getPrice() + " and has " + thisCake.getCandles() + " candles");
           cake3.setPrice(25);
       }





    }
}
