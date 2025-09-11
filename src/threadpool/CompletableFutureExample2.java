package threadpool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CompletableFutureExample2 {
    public static void main(String[] args) {
        // Create a fixed thread pool with 2 threads to execute tasks concurrently
        // This limits the number of concurrent threads to 2, so tasks may queue if more than 2 are submitted
        ExecutorService pool = Executors.newFixedThreadPool(2);

        // Task 1: Process "John Doe" using Async methods with the custom Executor
        // This task simulates registering a student, transforming their name, and printing it
        CompletableFuture<Void> future = CompletableFuture
                // supplyAsync: Starts an asynchronous task that returns a value
                // The Supplier runs in a thread from the custom ExecutorService (pool)
                .supplyAsync(() -> {
                    // Simulate a time-consuming operation (e.g., database query) with a 1-second sleep
                    System.out.println("Registering Student.....:" + Thread.currentThread().getName());
                    sleep(1000);
                    return "John Doe"; // Return the student name
                }, pool)
                // thenApplyAsync: Transforms the result (name) asynchronously in a separate thread
                // Using the custom Executor ensures this runs in a thread from 'pool'
                .thenApplyAsync((name) -> {
                    // Print the thread name to show which thread is executing
                    System.out.println("Transforming Student....." + name + "," + Thread.currentThread().getName());
                    // Simulate another time-consuming operation (e.g., data transformation)
                    sleep(1000);
                    return name.toUpperCase(); // Transform the name to uppercase
                }, pool)
                // thenAcceptAsync: Consumes the transformed result (uppercase name) asynchronously
                // Also runs in a thread from the custom Executor (pool)
                .thenAcceptAsync(name -> {
                    // Print the final result (uppercase name) and the thread name
                    System.out.println("Student name: " + name + "," + Thread.currentThread().getName());
                }, pool);

        // Task 2: Process "Rock" using non-Async methods
        // Non-Async methods reuse the thread from the previous stage (from supplyAsync)
        CompletableFuture<Void> future2 = CompletableFuture
                // supplyAsync: Starts an asynchronous task in a thread from the custom Executor
                .supplyAsync(() -> {
                    System.out.println("Registering Student.....:" + Thread.currentThread().getName());
                    sleep(1000); // Simulate a delay
                    return "Rock"; // Return the student name
                }, pool)
                // thenApply: Transforms the result in the same thread that completed supplyAsync
                // This avoids thread-switching overhead for lightweight operations
                .thenApply((name) -> {
                    System.out.println("Transforming Student....." + name + "," + Thread.currentThread().getName());
                    sleep(1000); // Simulate a delay
                    return name.toUpperCase(); // Transform to uppercase
                })
                // thenAccept: Consumes the result in the same thread as thenApply
                // Prints the final result without creating a new thread
                .thenAccept(name -> {
                    System.out.println("Student name: " + name + "," + Thread.currentThread().getName());
                });

        // Task 3: Process "John Cena" using non-Async methods
        // Similar to Task 2, reusing the thread from supplyAsync for efficiency
        CompletableFuture<Void> future3 = CompletableFuture
                // supplyAsync: Runs in a thread from the custom Executor
                .supplyAsync(() -> {
                    System.out.println("Registering Student.....:" + Thread.currentThread().getName());
                    sleep(1000); // Simulate a delay
                    return "John Cena"; // Return the student name
                }, pool)
                // thenApply: Runs in the same thread as supplyAsync
                .thenApply((name) -> {
                    System.out.println("Transforming Student....." + name + "," + Thread.currentThread().getName());
                    sleep(1000); // Simulate a delay
                    return name.toUpperCase(); // Transform to uppercase
                })
                // thenAccept: Runs in the same thread as thenApply
                .thenAccept(name -> {
                    System.out.println("Student name: " + name + "," + Thread.currentThread().getName());
                });

        // CompletableFuture.allOf: Combines multiple CompletableFutures and waits for all to complete
        // .join(): Blocks the main thread until all tasks (future, future2, future3) are done
        CompletableFuture.allOf(future, future2, future3).join();

        // Print confirmation that all tasks are complete
        System.out.println("All tasks completed....");

        // Print the ExecutorService details (e.g., its toString representation)
        System.out.println("ExecutorService: " + pool);

        // Shut down the ExecutorService to prevent new tasks and allow graceful termination
        // This ensures resources are released properly
        pool.shutdown();
    }

    // Utility method to simulate a time-consuming operation by sleeping for the specified milliseconds
    private static void sleep(int millis) {
        try {
            Thread.sleep(millis); // Pause the current thread
        } catch (InterruptedException e) {
            // Handle interruption (e.g., if the thread is interrupted during sleep)
            e.printStackTrace();
        }
    }
}