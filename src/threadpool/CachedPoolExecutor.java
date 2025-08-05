// This code demonstrates the use of a cached thread pool executor in Java.
package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedPoolExecutor {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();

        Runnable runnable = () -> {
            System.out.println("cache thread started");
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                System.out.println("Task was interrupted");
////                Thread.currentThread().interrupt();
//            }
            System.out.println("task completed");
        };
        
		for (int i = 0; i < 5; i++) {
			int taskno=i;
			Runnable task = () -> {
				System.out.println("Executing task "+taskno +" thread: " + Thread.currentThread().getName());
				try {
					Thread.sleep(1000); // Simulate work
				} catch (InterruptedException e) {
					System.out.println("Task was interrupted");
				}
				System.out.println("task completed in thread: " + Thread.currentThread().getName());
				System.out.println(service);
			};
			
			//delay to show reuse of threads in cached thread pool
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			service.submit(task);
		}

        service.submit(runnable);
    
        

        service.shutdown(); // Initiates an orderly shutdown
        //delay to allow the tasks to complete and showing the state of the service
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //service state after shutdown
        System.out.println(service);

    }
}
