package executors;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingExample {
	public static void main(String[] args) {
		 BlockingQueue<User> queue = new LinkedBlockingQueue<>(2);
		 
		 Thread t = new Thread(()->{
			 try {
				queue.put(new User("rock",1));
				System.out.println("user created "+queue.peek());
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 });
		 
		 Thread t2 = new Thread(()->{
			 	try {
					User user = queue.take();
					System.out.println("new user "+user);
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 });
		
		 t.start();
		 t2.start();
		
	}
}

class User {
    String name;
    int id;

    User(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String toString() {
        return "User{id=" + id + ", name='" + name + "'}";
    }
}
