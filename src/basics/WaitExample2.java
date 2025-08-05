package basics;

import java.util.ArrayList;
import java.util.List;

public class WaitExample2 {
    private List<Integer> data = new ArrayList<>();

    public synchronized void waitForData() throws InterruptedException {
        System.out.println("Waiting for data...");
        Thread.sleep(1000);

        while (data.isEmpty()) {
            wait(); // Wait until notified
        }

        System.out.println("Data is ready. Printing:");
        System.out.println(data);
    }

    public synchronized void setData() {
    	System.out.println("Setting data...");
    	 try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        for (int i = 0; i < 5; i++) {
            data.add(i);
        }
        notifyAll(); // Notify waiting threads
        System.out.println("Data set successfully!");
    }

    public static void main(String[] args) {
        WaitExample2 waitExample = new WaitExample2();

        // Thread that waits for data to be set
        Thread thread1 = new Thread(() -> {
            try {
                waitExample.waitForData();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Thread that sets the data
        Thread thread2 = new Thread(() -> {
     
            waitExample.setData();
        });

        thread1.start();
        thread2.start();
    }
}
