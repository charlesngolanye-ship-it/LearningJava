package angie;

public class Banana extends Fruit{

    public Banana() {
        setCalories(200);
    }

    public void peel(){
        System.out.println("Peeling banana");
    }

    @Override
    public void makeJuice() {
        System.out.println("Banana juice is made");
    }

}
