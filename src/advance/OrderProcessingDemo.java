package advance;

public class OrderProcessingDemo {
		private String order;
		private boolean hasOrder=false;
		
		public synchronized void producer(String newOrder) throws InterruptedException {
			while(hasOrder) {
				wait();
			}
			order = newOrder;
			System.out.println("[Producer] Created: " + order);
			hasOrder = true;
			notify();
		}
		
		public synchronized void consumer(int orderToConsume) throws InterruptedException {
			
			for(int i=0;i<orderToConsume;i++) {
				while(!hasOrder) {
					wait();
				}
				 System.out.println("[Consumer] Delivered: " + order);
		         hasOrder = false;
		         notify();
			}
			
		}
		
	public static void main(String[] args) {
		OrderProcessingDemo demo = new OrderProcessingDemo();
		int count=3;
		int threadNo=1;
		Thread thread = new Thread(()->{
			try {
				demo.producer("biryani order "+ threadNo);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		Thread thread2 = new Thread(()->{
			try {
				demo.consumer(count);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});	
		
		//three threads for producing three orders
//		for(int i=0;i<count;i++) {
//			int threadNo=i;
//			Thread thread = new Thread(()->{
//				try {
//					demo.producer("biryani order produced by "+ threadNo);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			});
//			thread.start();
//		}
		
		thread.start();
		thread2.start();
		
	}
}
