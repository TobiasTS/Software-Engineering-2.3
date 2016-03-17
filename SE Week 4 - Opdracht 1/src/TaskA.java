public class TaskA implements Runnable{

	private int number;

	public TaskA(int number){
		this.number = number;
	}
	
	@Override
	public void run() {
		synchronized(this){
			for(int i = 0; i<2; i++){
				System.out.println(number);
			}
		}
	}
}
