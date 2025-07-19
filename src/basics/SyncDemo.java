package basics;

public class SyncDemo {
	static int count=0;
	private synchronized static void increment() {
		count++;
	}
	
	public  static void main(String[] args) throws InterruptedException {
		
		Thread t1=new Thread(()->{
			for(int i=0;i<5;i++) {
				increment();
			}
		});
		Thread t2=new Thread(()->{
			for(int i=0;i<5;i++) {
				increment();
			}
		});
		t1.start();
		t2.start();
		t1.join();  //first t1 will be executed
		t2.join();
		System.out.println(count); 
	}

	
}
