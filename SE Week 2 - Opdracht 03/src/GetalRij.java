import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.*;


public class GetalRij {
	private int[] getallen;
	
	public GetalRij( int aantal, int max ){
		// Belangrijke aanname: aantal < max, anders kunnen de getallen niet uniek zijn.
		getallen = new int[aantal];
		vulArrayMetUniekeWaarden( aantal, max );
	}

	private void vulArrayMetUniekeWaarden(int aantal, int max) {
		// Vul een hulplijst met getallen 0, ..., max
		ArrayList<Integer> hulpLijst = new ArrayList<Integer>( max );
		for ( int i=0; i<max; i++){
			hulpLijst.add( i );
		}
		
		// Stop 'aantal' random waarden in getallen
		Random r = new Random();
		for ( int i=0; i<aantal; i++){
			// Het omzetten van Integer naar int gaat sinds Java 1.5 automatisch (unboxing).
			int getal = (Integer) (hulpLijst.remove( r.nextInt( hulpLijst.size())));
			getallen[i] = getal;
		}
	}
	
	public boolean zitErinA( int zoekWaarde ){
		boolean zitErIn = false;
		int i = 0;
		while(i < getallen.length){
			if(zoekWaarde == getallen[i]){
				System.out.println("Number " + zoekWaarde + " is in! (A)");
				zitErIn = true;
			}
			i++;
		}
		return zitErIn;
	}

	public boolean zitErinB( int zoekWaarde ){
		int i = 0;
		while(i < getallen.length){
			if(zoekWaarde == getallen[i]){
				System.out.println("Number " + zoekWaarde + " is in! (B)");
				return true;
			}
			i++;
		}
		return false;
	}

	public boolean zitErinC( int zoekWaarde ){
		int[] clone = getallen.clone();
		Arrays.sort(clone);
		int i = 0;
		while(i < clone.length){
			if(zoekWaarde == clone[i]){
				System.out.println("Number " + zoekWaarde + " is in! (C)");
				return true;
			}
			i++;
		}
		return false;		
	}

	public boolean zitErinD( int zoekWaarde ){
		int[] clone = getallen.clone();
		Arrays.sort(clone);
		int low = 0;
		int high = clone.length - 1;
		
		while(high >= low){
			int mid = (low + high)/2;
			if(zoekWaarde < clone[mid]){
				high = mid - 1;
			}else if(zoekWaarde == clone[mid]){
				System.out.println("Number " + zoekWaarde + " is in! (D)");
				return true;
			}else{
				low = mid+ 1;
			}
		}
		
		return false;	
	}
	
	public void sorteer(){
		Arrays.sort( getallen);
	}
	
	public void print(){
		for( int i=0; i<getallen.length; i++)
			System.out.println(getallen[i]);
	}

}
