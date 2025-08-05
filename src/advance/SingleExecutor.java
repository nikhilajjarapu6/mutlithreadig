
package advance;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleExecutor {
	public static void main(String[] args) throws InterruptedException {
		// Create an ExecutorService that uses a single worker thread
		ExecutorService executor = Executors.newSingleThreadExecutor();

		// Define the first task as a Runnable
		Runnable runnable = (() -> {
			System.out.println("single thread executor task 1 executed by " + Thread.currentThread().getName());
		});

		// Define the second task as a Runnable
		Runnable runnable2 = (() -> {
			System.out.println("single thread executor task 2 executed by " + Thread.currentThread().getName());
		});

		// Submit the first task to the executor for execution
		executor.submit(runnable);

		// Submit the second task to the executor for execution
		//executor services will execute tasks in the order they are submitted
		executor.submit(runnable2);

		// Note: The executor is not shut down in this example.
		
		executor.shutdown(); // It's a good practice to shut down the executor when done	
		executor.awaitTermination(1, java.util.concurrent.TimeUnit.SECONDS);
		System.out.println("All tasks submitted to the executor.");
	}
}
