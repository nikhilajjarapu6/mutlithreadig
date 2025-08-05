package threadpool;

import java.util.concurrent.*;

public class FutureExample {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(1);
        Callable<String> callableTask = () -> {
            System.out.println("task started by " + Thread.currentThread().getName());
            Thread.sleep(2000); // Simulate time-consuming task
            return "task completed";
        };

        Callable<String> userFetch=()->{
        	System.out.println("connecting to database.......");
        	Thread.sleep(2000);
        	System.out.println("getting data.....");
        	Thread.sleep(2000);
        	System.out.println("found user...");
        	return "The Dwayne Rock Johnson";
        };
        Future<String> futureTask = pool.submit(callableTask);
        Future<String> future = pool.submit(userFetch);
//        System.out.println("Future object returned immediately: " + futureTask);
        
   

        try {
            // This will block until the task is finished
            String result = futureTask.get(); 
            System.out.println("Result after task completion: " + result);
            
            String string = future.get();
            System.out.println("fetched user from data base :"+string);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

      
//        System.out.println("Future object completion of task: " + futureTask);
        pool.shutdown();
        
    }
}
