package basics;

public class DeadlockExample {
	 public static Marks marks = new Marks(200);
	 public static Student student = new Student(1, "rock", 23, false, marks);
	 
	 public static void main(String[] args) {
		Thread t1 = new Thread(()->{
			synchronized (student) {
				System.out.println("thread 1 accessing student object");
				
//				Sleeps for 100ms — during this time, Thread 2 starts
				try { Thread.sleep(100); } catch (InterruptedException e) {}
				System.out.println("thread 1 trying to lock/access marks object");
				synchronized (marks) {
					//cannot access marks object because thread 2 locked 
					System.out.println("thread 1 accessed marks object");
					marks.setTotal(marks.getTotal()+10); //updating marks object in thread 1
				}
				  
			}
		});
		
		Thread t2 = new Thread(()->{
//			try { Thread.sleep(1000); } catch (InterruptedException e) {}
			synchronized (marks) {
				System.out.println("thread 2 accessing marks object");
				try { Thread.sleep(1000); } catch (InterruptedException e) {}
				System.out.println("thread 2 trying to lock/access student object");
				synchronized (student) {
					System.out.println("thread 2 accessed student object");
					//student object needs to send details here
					//but it was locked in thread 1
					System.out.println("student detailes "+student.getName()+":"+student.getMarks().getTotal());
				}	  
			}
		});
		
		t1.start();
		t2.start();
	}
}
