package completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Combine {
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(2);
		CompletableFuture<String> productFuture = CompletableFuture.supplyAsync(()->{
			System.out.println("fetching product detailes.....");
			System.out.println("ordering product : "+Thread.currentThread().getName());
			return "Laptop [16GB RAM, 512GB SSD]";
		},pool);
		
		CompletableFuture<String> priceFuture = CompletableFuture.supplyAsync(()->{
			System.out.println("Fetching price info");
			System.out.println("Price of Product: "+Thread.currentThread().getName());
			 return "₹75,000 (10% Discount)";
		},pool);
		
		CompletableFuture<String> paymentFuture = CompletableFuture.supplyAsync(()->{
			System.out.println("Payment Method :"+Thread.currentThread().getName());
			return "Credit Card";
		});
		
		CompletableFuture<Void> future = CompletableFuture.allOf(productFuture,priceFuture,paymentFuture);
		System.out.println(future.join());
		System.out.println("confirming order....");
		 CompletableFuture<String> finalPage=future.thenApply((v)->{
			String product = productFuture.join();
			String price = priceFuture.join();
			String payment = paymentFuture.join();
			return "\n✅ Order Page Ready:\n" + product + "\n" + price + "\n" + payment;
		});
		System.out.println(finalPage.join());
		pool.shutdown();
	}
}
