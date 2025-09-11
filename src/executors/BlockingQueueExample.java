package executors;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BlockingQueueExample {
	private static final int POISON_PILL = -1;
	private static final Logger logger = Logger.getLogger(BlockingQueueExample.class.getName());

	public static void main(String[] args) {
		BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);
		int producerCount = 3;
		int consumerCount = 3;

		Runnable producer = () -> {
			try {
				for (int i = 0; i < 3; i++) {
					int item = i;
					queue.put(item);
					logger.info(Thread.currentThread().getName() + " produced: " + item);
					Thread.sleep(300);
				}
				queue.put(POISON_PILL);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				logger.log(Level.WARNING, Thread.currentThread().getName() + " interrupted in producer", e);
			}
		};

		Runnable consumer = () -> {
			try {
				while (true) {
					int item = queue.take();
					if (item == POISON_PILL) {
						logger.info(Thread.currentThread().getName() + " received poison pill. Exiting.");
						queue.put(POISON_PILL);
						break;
					}
					logger.info(Thread.currentThread().getName() + " consumed: " + item);
					Thread.sleep(500);
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				logger.log(Level.WARNING, Thread.currentThread().getName() + " interrupted in consumer", e);
			}
		};

		for (int i = 0; i < producerCount; i++) {
			new Thread(producer, "Producer-" + i).start();
		}

		for (int i = 0; i < consumerCount; i++) {
			new Thread(consumer, "Consumer-" + i).start();
		}
	}
}
