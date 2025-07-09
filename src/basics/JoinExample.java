package basics;

public class JoinExample {
	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(()->{
			System.out.println("thread 1 started...");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("thread 1 finished");
		});
		
		Thread thread2 = new Thread(()->{
			System.out.println("thread 2 started...");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("thread 2 finished");
		});
		
		thread.start();
		thread2.start();
		
		
		thread2.join();
		thread.join(); //main thread waits till threads completes
		System.out.println("from main thread");
		
	}
}
