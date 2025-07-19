package basics;

import java.util.concurrent.atomic.AtomicInteger;

public class LifeCycleOfThread {
	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(() -> {
            try {
                System.out.println("Running...");
                Thread.sleep(5000); // TIMED_WAITING
                System.out.println("Woke up...");
            } catch (InterruptedException e) {}
        });
		System.out.println("State: " + t.getState()); // NEW
        t.start();
        System.out.println("State after start: " + t.getState()); // RUNNABLE

        Thread.sleep(10000); // Let t start running
        System.out.println("State while sleeping: " + t.getState()); // TIMED_WAITING

        t.join(); // Wait for t to finish
        System.out.println("State after join: " + t.getState()); // TERMINATED
        AtomicInteger count = new AtomicInteger(0);
	}
}
