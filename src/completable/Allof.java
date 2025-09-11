package completable;

import java.util.concurrent.CompletableFuture;

public class Allof {
	public static void main(String[] args) {
		CompletableFuture<String> productFuture = CompletableFuture.supplyAsync(()->{
			System.out.println("fetching product...");
			if(Math.random()>0.3) {
				throw new RuntimeException("failed to load product...");
			}
			return "pixel";
		}).exceptionally(ex->{
			System.err.println("server down...");
			return "N/A";
		});
		
		CompletableFuture<String> priceFuture = CompletableFuture.supplyAsync(()->{
			System.out.println("fetching price.....");
			if(Math.random()>0.3) {
				throw new RuntimeException("failed to load price...");
			}
			return "1000";
		}).exceptionally(ex->{
			System.err.println("server down...");
			return "N/A";
		});
		CompletableFuture<String> paymentFuture =
			    CompletableFuture.supplyAsync(() -> {
			        System.out.println("Fetching payment method...");
			        if (Math.random() < 0.3) throw new RuntimeException("Payment service down!");
			        return "Credit Card";
			    }).exceptionally(ex -> {
			        System.out.println("❌ Payment error: " + ex.getMessage());
			        return "Cash on Delivery"; // fallback
			    });
		
		CompletableFuture<Void> allOf = CompletableFuture.allOf(productFuture,priceFuture,paymentFuture);
		allOf.thenRun(()->{
			System.out.println("✅ Order Page Ready: ");
		    System.out.println("Product: " + productFuture.join());
		    System.out.println("Price: " + priceFuture.join());
		    System.out.println("Payment: " + paymentFuture.join());
		});
	}
}
