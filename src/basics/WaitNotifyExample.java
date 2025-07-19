package basics;

public class WaitNotifyExample {
//	public static Marks marks=new Marks(200);
	public static Student student=new Student(1, "rock", 23,false,null);
	
	public static void main(String[] args) {
		
		//thread 1 is setting marks but it should wait for another complete marks
		Thread t1 = new Thread(()->{
			System.out.println("Thread 1 trying to access student");
			synchronized (student) {
				System.out.println("Thread 1 accessd student object");
				try {Thread.sleep(2000);} catch (InterruptedException e) { e.printStackTrace();}
				while(student.getMarks()==null) {
					System.out.println("student waiting for marks");
					try {
						student.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				 System.out.println("Thread 1: Got marks: " + student.getMarks().getTotal());
			
			}
		});
		
		//thread 2 set marks and notify thread 1 mark is completed
		 Thread t2 = new Thread(() -> {
	            try {
	                Thread.sleep(3000); // simulate delay
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }

	            synchronized (student) {
	                System.out.println("Thread 2: Setting marks...");
	                student.setMarks(new Marks(200));
	                student.notify(); // notify waiting thread
	                System.out.println("Thread 2: Notified thread 1");
	            }
	        });
		 
		 t1.start();
		 t2.start();
		
		
	}

}
