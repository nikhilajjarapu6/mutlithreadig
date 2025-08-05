package advance;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueEx {
	 public static final User POISON_PILL =new User(null, 0);
	public static void main(String[] args) {
		BlockingQueue<User> queue = new ArrayBlockingQueue<>(3);
		Thread thread = new Thread(new Creater(queue));
		Thread thread2 = new Thread(new Cient(queue));
		
		thread.start();
		thread2.start();
		
		try {
			thread.join();
			thread2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("main thread ended");
	}
}

class Creater implements Runnable {
	private BlockingQueue<User> queue;

	public Creater(BlockingQueue<User> newQueue) {
		super();
		this.queue = newQueue;
	}

	@Override
	public void run() {
		for(int i=0;i<3;i++) {
		try {
			User user=new User("user"+i,i);
			queue.put(user);
			System.out.println("produced" +user);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
		try {
			queue.put(BlockingQueueEx.POISON_PILL);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

class Cient implements Runnable {
	private BlockingQueue<User> queue;

	public Cient(BlockingQueue<User> queue) {
		this.queue = queue;
	}

	/**
	 *
	 */
	@Override
	public void run() {
		while(true) {
		try {
			User take = queue.take();
			if(take==BlockingQueueEx.POISON_PILL) {
				System.out.println("poision pill recieved, consumer starting");
				break;
			}
			System.out.println("consumed " + take);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

}
