
public class SnelheidOefening {

	/**
	 * @param args
	 */
	public static void main( String[] args){
		GetalRij gr = new GetalRij( 300000, 600000);
		System.out.println("Begonnen met algoritmen.");
		//Begin time is now (time())
		long time = tijd();
		gr.zitErinA(5000);
		//calculate end-time = now - begin time 
		long end = tijd() - time;
		System.out.println("time for a = " + end + " ms");
		time = tijd();
		gr.zitErinB(5000);
		end = tijd() - time;
		System.out.println("time for b = " + end + " ms");
		time = tijd();
		gr.zitErinC(5000);
		end = tijd() - time;
		System.out.println("time for C = " + end + " ms");
		time = tijd();
		gr.zitErinD(5000);
		end = tijd() - time;
		System.out.println("time for D = " + end + " ms");
	}

	// Hulpmethode voor tijdsbepaling
	private static long tijd(){
		return System.currentTimeMillis();
	}

}
