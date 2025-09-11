package completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Email {
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(1);
		String emailInput="rock@gmail.com";
//		String emailInput=null;
		CompletableFuture<String> future = CompletableFuture.supplyAsync(()->{
			System.out.println("Getting user email....");
			if(emailInput.length()<4 || emailInput==null) {
				throw new IllegalArgumentException("Email is null");
			}
			return emailInput;
			
		},pool)
		.thenApply(email->{
			System.out.println("validating email....");
			if(!email.contains("@")) {
				throw new IllegalArgumentException("Email should contain @");
			}
			return email;
		})
		.thenApply(validtedEmail->{
			System.out.println("Sending welcome msg....");
			return "Welcome "+validtedEmail+" on board";
		})
		.exceptionally(ex->{
			if(ex!=null) {
				System.err.println("failed registation" +ex.getMessage());
			}
			return "Please Try again";
		});
		
		System.out.println(future.join());
		pool.shutdown();
		
	}
}
