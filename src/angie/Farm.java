package angie;

public class Farm {
    public static void main(String[] args){
    var myAnimal = new Pig();
    myAnimal.makeSound();

    var ourAnimal = new Duck();
    ourAnimal.makeSound();

    Animal theirAnimal = new Duck();
    theirAnimal.eat();
    theirAnimal.makeSound();
}
}
