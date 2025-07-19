package basics;

import java.util.ArrayList;
import java.util.List;

public class Methods {
	static List<Integer> list=new ArrayList<Integer>();
	static List<Integer> list2=new ArrayList<Integer>();
	public static void main(String[] args) throws InterruptedException {
		
		//thread one produce the data
		Thread thread1 = new Thread(()->{
			System.out.println("thread started");
			for(int i=0;i<10;i++) {
				list.add(i);
			}
			System.out.println("added numbers into list");
			System.out.println(list);
		});
		
		System.out.println(list);
		
		//thread two depends on produced data of list 1
		Thread thread2 = new Thread(() -> {
			for(Integer i:list) {
				list2.add(i);
			}
		});
		System.out.println(list2);
		
	
		thread1.start();
		thread1.join();
		
		
		
//		thread2.start();
//		thread2.join();
//		thread1.wait();
		
	}
}
