package basics;

public class Demo {
	int count = 0; // shared (heap)

    public void increment() {
        count++;
    }

    public static void main(String[] args) {
        Demo demo = new Demo(); // heap

        Thread t1 = new Thread(() -> {
            demo.increment();  // stack for t1 calls increment(), modifies heap
            System.out.println(demo.count);
        });

        Thread t2 = new Thread(() -> {
            demo.increment();  // stack for t2 calls increment(), modifies heap
        });

        t1.start();
        t2.start();
        System.out.println(demo.count);
    }
   
}
