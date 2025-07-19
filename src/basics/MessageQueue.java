package basics;

public class MessageQueue {
	public String msg=null;
	private final Object lock=new Object(); //custom lock object
	
	public void consume() {
		synchronized (lock) {
			while(msg==null) {
				try {
					lock.wait(); //if msg is null it should be wait for msg(another thread notify)
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("consumed msg "+msg);
			msg=null;  //once msg is consumed it should be null
			lock.notifyAll(); //call waiting threads
		}
	}
	
	public void produce(String newMsg){
		synchronized (lock) {
			while(msg!=null) { 
				try {
					lock.wait(); //wait for thread consume msg
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			msg=newMsg; //once consumed replaces with new msg
			System.out.println("produced msg :"+msg);
			lock.notifyAll(); //call waiting threads
		}
	}
	
	
	
}
