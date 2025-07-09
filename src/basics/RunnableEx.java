package basics;

public class RunnableEx implements Runnable {

    @Override
    public void run() {
        // Print which thread is running
        System.out.println("Running in thread: " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {

        // Create thread 1
        Thread thread1 = new Thread(new RunnableEx(), "Worker-1");
        thread1.setPriority(4);

        // Create thread 2
        Thread thread2 = new Thread(new RunnableEx(), "Worker-2");

        // Create thread 3 using lambda
        Thread thread3 = new Thread(() -> {
            System.out.println("Running in thread: " + Thread.currentThread().getName());
        }, "Lambda-Thread");
        thread3.setPriority(8);

        // Start threads
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
