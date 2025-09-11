package completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureEx {
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(2);
		
		CompletableFuture<Void> future = CompletableFuture.supplyAsync(()->{
			System.out.println("task started by "+Thread.currentThread().getName());
			return "hello";
		},pool)
		.thenApplyAsync(str->{
			System.out.println("applying function to result by "+Thread.currentThread().getName());
			return str.concat(" world");
		},pool)
		.thenAcceptAsync(str->{
			System.out.println("task final result by "+Thread.currentThread().getName());
			System.out.println(str+Thread.currentThread().getName());
		},pool);
		
		CompletableFuture.allOf(future).join();
		pool.shutdown();
		System.out.println("complted all tasks");
	}
}
