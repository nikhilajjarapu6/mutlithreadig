package basics;

public class MonitorLock {
	public static void main(String[] args) throws InterruptedException {
		Printer printer=new Printer();
		
//		for(int i=0;i<5;i++) {
//			int threadId = i;
//			Thread thread=new Thread(()->{
//				printer.print("user "+threadId);
//			});
//			thread.start();
//			thread.join();
//		}
		Thread thread=new Thread(()->{
			printer.print("user one");
		});
		
		
		Thread thread2=new Thread(()->{
			printer.print("user two");
		});
		
		
		Thread thread3=new Thread(()->{
			printer.print("user three");
		});
		
		thread.start();
		thread2.start();
		thread3.start();
		thread.join();
		thread2.join();
		thread3.join();
		
		System.out.println("now other thread can access printer");
		
	}
}

class Printer{
	public void print(String user) {
//		System.out.println(user+" is waiting for printn");
		synchronized (this) {
			 System.out.println(user + " is waiting for printer...");
			System.out.println(user+" got the lock and printing");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				
			}
			System.out.println(user+" finshed and released the lock");
		}
	}
}
