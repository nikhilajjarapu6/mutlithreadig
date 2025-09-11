package executors;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MultiProducerConsumer {
    public static final int POISON_PILL = -1; // Special value for shutdown

    public static void main(String[] args) {
        int queueCapacity = 5;
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(queueCapacity);

        int producerCount = 3;
        int consumerCount = 2;
        int itemsPerProducer = 4;

        // Start producers
        for (int p = 0; p < producerCount; p++) {
            new Thread(new Producer(queue, itemsPerProducer), "Producer-" + p).start();
        }

        // Start consumers
        for (int c = 0; c < consumerCount; c++) {
            new Thread(new Consumer(queue), "Consumer-" + c).start();
        }

        // Optional: send enough poison pills to shut down all consumers when done
        new Thread(() -> {
            try {
                // Wait for all producers to finish (in a real app, use a better mechanism)
                Thread.sleep(1000);
                for (int i = 0; i < consumerCount; i++) {
                    queue.put(POISON_PILL);
                }
            } catch (InterruptedException e) { }
        }).start();
    }

    static class Producer implements Runnable {
        private BlockingQueue<Integer> queue;
        private int itemsToProduce;

        public Producer(BlockingQueue<Integer> queue, int itemsToProduce) {
            this.queue = queue;
            this.itemsToProduce = itemsToProduce;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < itemsToProduce; i++) {
                    int value = (int) (Math.random() * 100);
                    queue.put(value);
                    System.out.println(Thread.currentThread().getName() + " produced: " + value);
                }
            } catch (InterruptedException e) { }
        }
    }

    static class Consumer implements Runnable {
        private BlockingQueue<Integer> queue;

        public Consumer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Integer val = queue.take();
                    if (val == POISON_PILL) {
                        System.out.println(Thread.currentThread().getName() + " received poison pill, exiting.");
                        break;
                    }
                    System.out.println(Thread.currentThread().getName() + " consumed: " + val);
                }
            } catch (InterruptedException e) { }
        }
    }
}

