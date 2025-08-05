package threadpool;

import java.util.concurrent.*;

public class ScheduledThreadPool {
    public static void main(String[] args) {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);

        Runnable task1 = () -> {
            System.out.println("task 1 started by " + Thread.currentThread().getName());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task 1 finished by " + Thread.currentThread().getName());
        };

        Runnable task2 = () -> {
            System.out.println("task 2 started by " + Thread.currentThread().getName());
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task 2 finished by " + Thread.currentThread().getName());
        };

        pool.schedule(task1, 3, TimeUnit.SECONDS);
        pool.scheduleAtFixedRate(task2, 0, 3, TimeUnit.SECONDS);

        // Delayed shutdown to allow recurring tasks to run
        Executors.newSingleThreadScheduledExecutor().schedule(() -> {
            System.out.println("Initiating shutdown...");
            pool.shutdown();
        }, 10, TimeUnit.SECONDS);

        try {
            pool.awaitTermination(15, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
