
public class TaskB implements Runnable{
	
	public int nextInt;
	public int number;
	
	public TaskB(int number){
		this.number = number;
	}
	
	@Override
	public synchronized void run() {
		while(number < nextInt){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		for(int i = 0; i<2; i++){
			System.out.println(number);
		}
		nextInt = nextInt - 1;
		notifyAll();	
	}
}
