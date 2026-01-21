package collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapDemo {
    public static void show(){
        var c1 = new Customer("Charles", "cngolanye@gmail");
        var c2 = new Customer("Emil", "engolanye@gmail");
        var c3 = new Customer("Johanna", "jngolanye@gmail");

        Map<String, Customer> map = new HashMap<>();
        map.put(c1.getEmail(), c1);
        map.put(c2.getEmail(), c2);
        map.put(c3.getEmail(), c3);


        for (var entry : map.entrySet())
            System.out.println(entry);

    }
}
