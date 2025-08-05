package basics;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingExample {
    // Shared BlockingQueue with capacity 5
    private static final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== BlockingQueue Example (Producer-Consumer) ===");
        System.out.println("Queue capacity: " + queue.remainingCapacity());
        System.out.println();

        // Producer thread
        Thread producer = new Thread(() -> {
            int value = 1;
            System.out.println("[Producer] Started adding items into queue...\n");
            while (value <= 5) {
                try {
                    System.out.println("[Producer] Trying to add item: " + value);
                    queue.put(value); // blocks if queue is full
                    System.out.println("[Producer] ✅ Produced item: " + value + "\n");
                    value++;
                } catch (InterruptedException e) {
                    System.out.println("[Producer] ❌ Interrupted while producing.");
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("[Producer] Finished producing all 5 items.\n");
        });

        // Consumer thread
        Thread consumer = new Thread(() -> {
            System.out.println("[Consumer] Started consuming items from queue...\n");
            while (true) {
                try {
                    System.out.println("[Consumer] Waiting to take item...");
                    Integer item = queue.take(); // blocks if queue is empty
                    System.out.println("[Consumer] ✅ Consumed item: " + item + "\n");
                } catch (InterruptedException e) {
                    System.out.println("[Consumer] ❌ Interrupted while consuming.");
                    Thread.currentThread().interrupt();
                }
//                System.out.println(j);
            }
        });

        // Start both threads
        producer.start();
        consumer.start();

        // Wait for threads to finish (consumer is infinite, will block)
        producer.join();
//        consumer.join(); // Will block forever, since consumer never ends

        // This line will never be reached, but keeping for clarity
        System.out.println("[Main] Final queue content: " + queue);
    }
}
