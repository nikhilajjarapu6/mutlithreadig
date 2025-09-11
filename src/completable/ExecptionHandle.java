package completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecptionHandle {
		public static void main(String[] args) {
			ExecutorService pool = Executors.newFixedThreadPool(2);
			CompletableFuture<Void> future = CompletableFuture.supplyAsync(()->{
				System.out.println("task started by "+Thread.currentThread().getName());
				//this will return exception
				return 10/0;
			},pool)
			.exceptionally(e->{
				System.out.println("execption occured");
				//return default value when exception arised
				return 0;
			})
			.thenAcceptAsync(n->{
				System.out.println("task started by "+Thread.currentThread().getName());
				System.out.println("result "+n);
			},pool);
			
			
			future.join();
			System.out.println(future);
			
			
			CompletableFuture<String> future2 = CompletableFuture.supplyAsync(()->{
				System.out.println("task started by "+Thread.currentThread().getName());
				  // Simulate a failure
			    if (true) {
			        throw new IllegalStateException("Database is down!");
			    }
			    
			    return "User data from DB";
			},pool)
			.exceptionally(e->{
				System.out.println("execption occured "+e.getMessage());
				//return default value when exception arised
				return "return default value from data base";
			});
			
			System.out.println(future2.join());
			future2.join();
			
			
			pool.shutdown();
			System.out.println("from main thread");
			
		}
}
