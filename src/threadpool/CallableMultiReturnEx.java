package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CallableMultiReturnEx {
    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(2);

        Runnable runnable1 = () -> {
            System.out.println("executing task 1");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int newValue = (int) (Math.random() * 100);
            count.set(newValue);
            System.out.println("task 1 completed and count is " + count.get());
        };

        Runnable runnable2 = () -> {
            System.out.println("executing task 2");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int incrementedValue = count.incrementAndGet();
            System.out.println("task 2 completed and count is " + incrementedValue);
        };

        pool.submit(runnable1);
        pool.submit(runnable2);

        pool.shutdown();

        try {
            pool.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("all threads are completed");
    }
}
