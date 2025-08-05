package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutor {
	public static void main(String[] args) {
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
		
		//submitting 5 tasks
		for(int i=0;i<5;i++) {
			int task=i;
			String threadName = Thread.currentThread().getName();
			
			Runnable runnable3 = (() -> {
				System.out.println("Task "+task+" execution started by " + Thread.currentThread().getName());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Task "+task+" execution finished by " + Thread.currentThread().getName());
			});
			executor.submit(runnable3);
		}

		// Submit the first task to the executor for execution
		executor.submit(runnable);

		// Submit the second task to the executor for execution
		// executor services will execute tasks in the order they are submitted
		executor.submit(runnable2);

		// Note: The executor is not shut down in this example.

		executor.shutdown(); // It's a good practice to shut down the executor when done
		try {
			executor.awaitTermination(10, java.util.concurrent.TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("All tasks submitted to the executor.");

	}
}
