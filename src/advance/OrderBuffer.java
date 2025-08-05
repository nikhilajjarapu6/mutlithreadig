package advance;

import java.util.LinkedList;
import java.util.Queue;

//cleanest way to maintain wait and notify 
class OrderBuffer {
    private final Queue<String> buffer = new LinkedList<>();
    private final int capacity = 3;

    public synchronized void produce(String order) throws InterruptedException {
        while (buffer.size() == capacity) {
            wait();
        }
        buffer.add(order);
        System.out.println("[Producer] Produced: " + order);
        notifyAll(); // use notifyAll when multiple threads are possible
    }

    public synchronized String consume() throws InterruptedException {
        while (buffer.isEmpty()) {
            wait();
        }
        String order = buffer.poll();
        System.out.println("[Consumer] Consumed: " + order);
        notifyAll();
        return order;
    }
}

class Producer implements Runnable {
    private final OrderBuffer buffer;

    public Producer(OrderBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int i = 1;
        while (i <= 5) {
            try {
                buffer.produce("Biryani Order #" + i);
                Thread.sleep(500);
                i++;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class Consumer implements Runnable {
    private final OrderBuffer buffer;

    public Consumer(OrderBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int i = 1;
        while (i <= 5) {
            try {
                buffer.consume();
                Thread.sleep(1000);
                i++;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

 class OrderSystem {
    public static void main(String[] args) {
        OrderBuffer buffer = new OrderBuffer();

        Thread producerThread = new Thread(new Producer(buffer));
        Thread consumerThread = new Thread(new Consumer(buffer));

        producerThread.start();
        consumerThread.start();
    }
}

