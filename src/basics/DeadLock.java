package basics;

public class DeadLock {
	final static Object lock1 =new Object();
	final static Object lock2 =new Object();
	
	public static void main(String[] args) {
		Thread thread = new Thread(()->{
			System.out.println("thread1 is trying to lock lock1");
			
			//here thread 1 is trying to lock access lock 1
			synchronized (lock1) {
				System.out.println("thread 1 locked lock1");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("thread 1 trying to access lock2");
				synchronized (lock2) {
					//it was waiting for lock 2 but it was locked by thread 2 below
					System.out.println("thread 1 locked lock2");
				}
				
				System.out.println("thread 1 released lock1 lock 2");
			}
		});

		Thread thread2 = new Thread(() -> {
			System.out.println("thread2 is trying to lock lock2");

			synchronized (lock2) {
				System.out.println("thread 2 locked lock2");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("thread 2 trying to access lock1");
				synchronized (lock1) {
					//thread2  looking for lock 1 but it was locked by thread 1
					System.out.println("thread 2 locked lock1");
				}

				System.out.println("thread 2 released lock1 lock 2");
			}

		});
		
		thread.start();
		thread2.start();
	}
}
