package basics;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueExample {
    static BlockingQueue<Integer> numbers = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) {

        System.out.println("--- Starting BlockingQueue Example ---");
        System.out.println("Queue initialized with capacity: " + numbers.remainingCapacity() + " (empty)");

        System.out.println("\n--- Filling the queue with 0 to 9 ---");
        for (int i = 0; i < 10; i++) {
            numbers.add(i); // fills the queue
            System.out.println("Added " + i + ". Queue size: " + numbers.size());
        }
        System.out.println("Queue is now full: " + numbers); // Should be [0, 1, ..., 9]

        System.out.println("\n--- Polling one item ---");
        Integer poll = numbers.poll(); // removes one item
        System.out.println("Polled item: " + poll);
        System.out.println("Queue after first poll: " + numbers + ". Remaining capacity: " + numbers.remainingCapacity());

        System.out.println("\n--- Attempting to add 10 ---");
        add(10); // attempts to put 10 in a loop using put()
        System.out.println("Queue after adding 10: " + numbers + ". Remaining capacity: " + numbers.remainingCapacity());

        System.out.println("\n--- Polling another item ---");
        numbers.poll();
        System.out.println("Queue after second poll: " + numbers + ". Remaining capacity: " + numbers.remainingCapacity());

        System.out.println("\n--- Attempting to add 11 ---");
        add(11);
        System.out.println("Queue after adding 11: " + numbers + ". Remaining capacity: " + numbers.remainingCapacity());

        System.out.println("\n--- Attempting to add 12 (Queue should now be full) ---");
        add(12); // This call will cause the program to block indefinitely if no items are removed
        System.out.println("Queue after attempting to add 12: " + numbers + ". Remaining capacity: " + numbers.remainingCapacity());
        System.out.println("--- Program finished (or blocked) ---");
    }

    public static void add(int number) {
        System.out.println("  Attempting to put " + number + " into the queue...");

        // Add this check and log for clarity
        if (numbers.remainingCapacity() == 0) {
            System.out.println("  WARNING: Queue is currently full. Adding " + number + " will cause this thread to block until space is available.");
        }

        while (true) {
            try {
                numbers.put(number); // this blocks when full
                System.out.println("  Successfully put " + number + " into the queue.");
                break; // Exit the loop if put is successful
            } catch (InterruptedException e) {
                System.out.println("  InterruptedException caught while putting " + number + ": " + e.getMessage());
                Thread.currentThread().interrupt(); // Re-interrupt the current thread
                break;
            }
        }
    }
}