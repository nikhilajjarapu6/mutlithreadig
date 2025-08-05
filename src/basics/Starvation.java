package basics;

public class Starvation {
	static final Object lock1 = new Object();
    static final Object lock2 = new Object();

    public static void main(String[] args) {
        Thread waitingThread = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("WaitingThread: Waiting on lock1...");
                try {
                    lock1.wait(); // waiting on lock1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("WaitingThread: Resumed!");
            }
        });

        Thread notifyingThread = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("NotifyingThread: Notifying on lock2...");
                lock2.notify(); // notifying on lock2
                System.out.println("NotifyingThread: Sent notify");
            }
        });

        waitingThread.start();
        try { Thread.sleep(500); } catch (InterruptedException e) {}
        notifyingThread.start();
    }
}
