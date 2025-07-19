package basics;

public class LockObjectExample {
	public static void main(String[] args) {
		MessageQueue message=new MessageQueue();
		Thread thread = new Thread(()->{
			message.consume();
			message.produce("hello from thread 1");
		});
		Thread thread1 = new Thread(()->{
			message.produce("hello hello from thread 2");
//			message.consume();
		});
		
		thread.start();
		thread1.start();
	}
}
