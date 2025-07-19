package basics;

public class Warehouse {
	private static int stock = 100;
	public static void main(String[] args) throws InterruptedException {
		Thread thread1 = new Thread(() -> {
			System.out.println("User 1 attempting to order 50 items");
			buy("User1", 50);
		});

		Thread thread2 = new Thread(() -> {
			System.out.println("User 2 attempting to order 150 items");
			buy("User2", 150);
		});
		
		Thread thread3 = new Thread(() -> {
			System.out.println("User 3 attempting to order 30 items");
			buy("User3", 30);
		});

		thread1.start();
		thread2.start();
		thread3.start();
		thread1.join();
		thread2.join();
		thread3.join();

		System.out.println("Final stock left: " + stock);
	}

	private static void buy(String user, int cart) {
		synchronized (Warehouse.class) { //when access static fields among  threads .class is preferred
			//if accessing non static fields this is preferred
			System.out.println(user + " is checking stock...");
			if (cart > stock) {
				System.out.println(user + ": Requested " + cart + " items -> Not enough stock! (Available: " + stock + ")");
			} else {
				System.out.println(user + ": Order placed for " + cart + " items.");
				stock -= cart;
				System.out.println("Updated stock after " + user + "'s order: " + stock);
			}
		}
	}
}
