
public class ThreadDemoB {
	
	public static void main(String[] args){
		Runnable print1 = new TaskB(1);
		Runnable print2 = new TaskB(2);
		Runnable print3 = new TaskB(3);
		Runnable print4 = new TaskB(4);
		
		Thread thread1 = new Thread(print1);
		Thread thread2 = new Thread(print2);
		Thread thread3 = new Thread(print3);
		Thread thread4 = new Thread(print4);
		
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		
		
	}
}
