package completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Handle {
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(2);
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			System.out.println("Step 1: Fetching user ID in " + Thread.currentThread().getName());
			if (Math.random() > 0.5) {
                throw new RuntimeException("Failed to fetch user ID");
            }
			return "user123";
		}, pool).handle((result,ex)->{
			if(ex!=null) {
				System.err.println("exception occured "+ex.getMessage());
			}
			//make sure default value passed to chain if exception raises or send the result
			return ex!=null?"default value":"user123";
//			return result;
		}).thenApply(userId -> {
			System.out.println("Step 2: Fetching profile for " + userId + " in " + Thread.currentThread().getName());
			return "Profile(" + userId + ")";
		}).thenApplyAsync(profile -> {
			System.out.println("Step 3: Processing " + profile + " in " + Thread.currentThread().getName());
			return "Processed " + profile;
		});

	        System.out.println("Final Result: " + future.join());
	        

	}
}
