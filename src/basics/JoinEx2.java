package basics;

import java.util.Scanner;

public class JoinEx2 {
	public static void main(String[] args) throws InterruptedException {
		Scanner r=new Scanner(System.in);
		System.out.println("enter first number");
		int a=r.nextInt();
		System.out.println("enter second number");
		int b=r.nextInt();
		JoinEx2 join = new JoinEx2();
		
		
		Thread add = new Thread(()->{
			System.out.println("addition started thread");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			join.addition(a,b);
		});
		
		Thread sub = new Thread(()->{
			System.out.println("substract thread started");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			join.substract(a,b);
		});
		
		//threads are started
		add.start();
		sub.start();
		
		add.join();
		
		

		
		
	}

	private void substract(int a, int b) {
		System.out.println("substraction of numbers "+(a-b));
	}

	private void addition(int a, int b) {
		System.out.println("addition of numbers "+(a+b));
	}
}


