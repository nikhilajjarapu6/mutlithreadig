package threadpool;

import java.util.concurrent.*;

public class InfiniteCallableExample {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(2);

        // Task 1: Runnable (submitted before get)
        Runnable preTask = () -> {
            System.out.println(Thread.currentThread().getName() + ": Running preTask");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": Finished preTask");
        };
        pool.submit(preTask);

        // Task 2: Infinite Callable
        Callable<String> infiniteTask = () -> {
            System.out.println(Thread.currentThread().getName() + ": Started infinite task");
            while (true) {
                // Simulate stuck task
                Thread.sleep(1000);
            }
        };
        Future<String> future = pool.submit(infiniteTask);

        try {
            System.out.println("Main thread waiting for result...");
            String result = future.get();  // Blocks forever here
//            String result =  future.get(2, TimeUnit.SECONDS);
            System.out.println("Result: " + result); // never reached

            // Task 3: Runnable submitted after get() — never runs
            Runnable postTask = () -> {
                System.out.println(Thread.currentThread().getName() + ": Running postTask");
            };
            pool.submit(postTask);

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }

        System.out.println("Main thread completed");
    }
}
