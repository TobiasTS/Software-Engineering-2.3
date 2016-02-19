
public class SnelheidOefening {

	/**
	 * @param args
	 */
	public static void main( String[] args){
		GetalRij gr = new GetalRij( 100000, 200000);
		//Begin time is now (time())
		long time = tijd();
		gr.zitErinA(50000);
		//calculate end-time = now - begin time 
		long a = tijd() - time;
		System.out.println("time for a = " + a + " ms");
		time = tijd();
		gr.zitErinB(50000);
		long b = tijd() - time;
		System.out.println("time for b = " + b + " ms");
	}

	// Hulpmethode voor tijdsbepaling
	private static long tijd(){
		return System.currentTimeMillis();
	}

}
