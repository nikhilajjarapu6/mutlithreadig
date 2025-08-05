package threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ProducerConsumerExample {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(5);

        ExecutorService producerPool = Executors.newFixedThreadPool(1);
        ExecutorService consumerPool = Executors.newFixedThreadPool(2);

        Runnable producer = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("producing task " + i + " by " + Thread.currentThread().getName());
                try {
                    Thread.sleep(2000);
                    queue.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("produced task " + i + " by " + Thread.currentThread().getName());
            }
        };

        Runnable consumer = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("consuming task by " + Thread.currentThread().getName());
                try {
                    Thread.sleep(2000);
                    Integer task = queue.take();
                    System.out.println("consumed task " + task + " by " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        producerPool.submit(producer);

        // Submit two consumer threads to consume from the queue concurrently
        consumerPool.submit(consumer);
        consumerPool.submit(consumer);

        producerPool.shutdown();
        consumerPool.shutdown();

        try {
            producerPool.awaitTermination(60, TimeUnit.SECONDS);
            consumerPool.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("all producer and consumer threads completed");
    }
}
