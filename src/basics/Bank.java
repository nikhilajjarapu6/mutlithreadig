package basics;

public class Bank {
	static int balance=10000;
	public static void main(String[] args) throws InterruptedException {
		Thread user1 = new Thread(()->{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			withdraw(4000,"user1");
		});
		
		Thread user2 = new Thread(()->{
			withdraw(5000,"user2");
		});
		
		Thread user3 = new Thread(()->{
			deposit(5000,"user3");
		});
		user1.start();
		user2.start();
		user3.start();
		user3.join();
		user1.join(); //user 1 threads sleeps for 1 sec and thread 2 withdraws money
		user2.join();
	}
	private synchronized static void withdraw(int n,String name) {
		if(balance<n)
			System.out.println(name + " can't withdraw. Not enough balance.");
		else {
			System.out.println(name+" with drawing "+n+"/-");
			balance-=n;
			System.out.println("remaining balance :"+balance);	
		}	
	}
	
	private synchronized static void deposit(int n,String name) {
		if(n<0)
			System.out.println("amount cannot be deposited");
		else {
			System.out.println(name+" deposited "+n+"/-");
			balance+=n;
			System.out.println("total balance :"+balance);	
		}	
	}
}
