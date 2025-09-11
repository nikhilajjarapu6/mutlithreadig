package executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OrderExecutorService {
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(2);
		
		for(int i=1;i<=5;i++) {
			int orderId=i;
			pool.submit(()->{
				System.out.println("order "+orderId+" picked up by "+Thread.currentThread().getName());
				int deliveryTime = 1000 + (int)(Math.random() * 2000); // random 1-3 sec
                try {
					Thread.sleep(deliveryTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                System.out.println("order "+orderId+" confirmed by "+Thread.currentThread().getName());
			});
		}
		pool.shutdown();
	}
}	
