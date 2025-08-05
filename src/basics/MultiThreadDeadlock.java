package basics;

public class MultiThreadDeadlock {
	static Resource r1 = new Resource("R1");
    static Resource r2 = new Resource("R2");
    static Resource r3 = new Resource("R3");

    public static void main(String[] args) {
        
        Thread t1 = new Thread(() -> {
            synchronized (r1) {
                System.out.println("Thread 1: locked " + r1.name);
                sleep(100);
                synchronized (r2) {
                    System.out.println("Thread 1: locked " + r2.name);
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (r2) {
                System.out.println("Thread 2: locked " + r2.name);
                sleep(100);
                synchronized (r3) {
                    System.out.println("Thread 2: locked " + r3.name);
                }
            }
        });

        Thread t3 = new Thread(() -> {
            synchronized (r3) {
                System.out.println("Thread 3: locked " + r3.name);
                sleep(100);
                synchronized (r1) {
                    System.out.println("Thread 3: locked " + r1.name);
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }

    static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) {}
    }
}
