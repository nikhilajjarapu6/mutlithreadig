package threadpool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CompletableFutureExample {
	public static void main(String[] args) {
		 ExecutorService pool = Executors.newFixedThreadPool(2);
		 
		 CompletableFuture<Student> future = CompletableFuture.supplyAsync(()->{
			 System.out.println("Registering Student.....:"+Thread.currentThread().getName());
			 try {
	                Thread.sleep(2000); // Simulate delay
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            return new Student(1, "Rock", 20);
		 }, pool);
		 System.out.println(pool);
		 
		 future.thenAccept(student->{
			 System.out.println("getting student detalies...."+Thread.currentThread().getName());
			 try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			 System.out.println("student name:"+student.getName()+"\nstudent marks :"+student.getMarks());
		 });
		 System.out.println(pool);
		 
		 pool.shutdown();
		 System.out.println("current pool status: "+pool);
		 try {
			pool.awaitTermination(5,TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 System.out.println("current pool status: "+pool);
	}
}
