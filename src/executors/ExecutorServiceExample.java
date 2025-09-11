package executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceExample {
	public static void main(String[] args) {
		// creating thread pool with a fixed number of threads
		ExecutorService executor=Executors.newSingleThreadExecutor();
		
		//running tasks using the executor service
		
		//task on
//		executor.submit(()->{
//			System.out.println(Thread.currentThread().getName()+" started executing");
//			System.out.println("task one is exeuted by "+Thread.currentThread().getName());
//			
//		});
//		
//		//task two
//		executor.submit(()->{
//			System.out.println(Thread.currentThread().getName()+" started executing");
//			System.out.println("task two is exeuted by "+Thread.currentThread().getName());
//		
//		});
//		
//		//task three
//		executor.submit(()->{
//			System.out.println(Thread.currentThread().getName()+" started executing");
//			System.out.println("task three is exeuted by "+Thread.currentThread().getName());
//			
//		});
		
		//loop for submitting multiple tasks
		for(int i=0;i<5;i++) {
			int taskId = i;
            executor.submit(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println("Task " + taskId + " started by " + threadName);
                try {
                    Thread.sleep(1000); // Simulate work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Task " + taskId + " completed by " + threadName);
            });
		}
		
		executor.shutdown();
		
	}
	
	
}
