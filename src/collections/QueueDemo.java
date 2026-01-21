package collections;

import java.util.ArrayDeque;
import java.util.Queue;

public class QueueDemo {
    public static void show() {
        Queue<String> queue = new ArrayDeque<>();
        queue.add("Charles");
        queue.add("Emil");
        queue.add("Johanna");
        queue.offer("Mukulu");
        var front = queue.peek();
        System.out.println(front);
    }
}
