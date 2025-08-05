package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadExecutor {
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(2);
		
		Runnable task1=()->{
			System.out.println("task 1 started by "+Thread.currentThread().getName());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("task 1 finished by "+Thread.currentThread().getName());
		};
		Runnable task2=()->{
			System.out.println("task 2 started by "+Thread.currentThread().getName());
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("task 2 finished by "+Thread.currentThread().getName());
		};
		
		pool.submit(task1);
		pool.submit(task2);
		
		pool.shutdown();
		
		try {
			pool.awaitTermination(10, java.util.concurrent.TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("main thread completed all tasks");
	}
}
