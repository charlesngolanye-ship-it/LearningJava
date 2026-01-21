package collections;

import java.util.*;

public class SetDemo {
    public static void show(){
        Set<String> set1 = new HashSet<>(Arrays.asList("a", "b", "c"));

        Set<String> set2 = new HashSet<>(Arrays.asList("b", "c", "d"));

        /*
         Intersection -> keep all the items in set2 and remove all in set1
        set1.retainAll(set2);
        System.out.println(set1);
        */

        /*
         Union -> combine two sets removing any duplicates
        set1.addAll(set2);
        System.out.println(set1);
        */

        // Difference -> what items we have in set1 that we do not have in set2
        set1.removeAll(set2);
        System.out.println(set1);
    }
}
