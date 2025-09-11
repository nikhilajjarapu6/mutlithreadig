package completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserOrder {
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(2);
		
		CompletableFuture<String> future2 = CompletableFuture.supplyAsync(()->{
			System.out.println("Fetched user by "+Thread.currentThread().getName());
			return "user : rock";
		},pool);
		
		CompletableFuture<String> future = CompletableFuture.supplyAsync(()->{
			System.out.println("Fetched orders by "+Thread.currentThread().getName());
			return "orders : milk,banana,ice";
		},pool);
		
		CompletableFuture<String> combine = future2.thenCombineAsync(future, (user,order)->{
			  System.out.println("Combining results by " + Thread.currentThread().getName());
              return user + "\n" + order;
		},pool);
		
		System.out.println(combine.join());
//		combine.join();
		System.out.println("task finished");
		
		
	}
}
