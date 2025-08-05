package basics;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MultiProducerConsumerExample {
    // Producer class: puts items into the queue
    class Producer implements Runnable {
        private final BlockingQueue<Integer> queue;
        private final int id;

        public Producer(BlockingQueue<Integer> queue, int id) {
            this.queue = queue;
            this.id = id;
        }

        public void run() {
            try {
                // Each producer adds 5 items to the queue
                for (int i = 0; i < 5; i++) {
                    int value = id * 100 + i;
                    queue.put(value); // Put item into queue
                    System.out.println("Producer " + id + " produced: " + value);
                    Thread.sleep(100); // Simulate work
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // Consumer class: takes items from the queue
    class Consumer implements Runnable {
        private final BlockingQueue<Integer> queue;
        private final int id;

        public Consumer(BlockingQueue<Integer> queue, int id) {
            this.queue = queue;
            this.id = id;
        }

        public void run() {
            try {
                // Each consumer takes 5 items from the queue
                for (int i = 0; i < 5; i++) {
                    int value = queue.take(); // Take item from queue
                    System.out.println("Consumer " + id + " consumed: " + value);
                    Thread.sleep(150); // Simulate work
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("MultiProducerConsumerExample is running...");
        // Shared queue with capacity 10
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);

        MultiProducerConsumerExample example = new MultiProducerConsumerExample();

        int producerCount = 4; // Number of producers
        int consumerCount = 3; // Number of consumers

        Thread[] producers = new Thread[producerCount];
        Thread[] consumers = new Thread[consumerCount];

        // Create and start producer threads
        for (int i = 0; i < producerCount; i++) {
            producers[i] = new Thread(example.new Producer(queue, i + 1));
            producers[i].start();
        }

        // Create and start consumer threads
        for (int i = 0; i < consumerCount; i++) {
            consumers[i] = new Thread(example.new Consumer(queue, i + 1));
            consumers[i].start();
        }

        // Wait for all threads to finish
        try {
            for (Thread producer : producers) {
                producer.join();
            }
            for (Thread consumer : consumers) {
                consumer.join();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
