/*
 * Created on 17.02.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.datentypen;

/**
 * @author Uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Vektor_float {

	public float[] werte;

	public float maxWert = 0;

	public float minWert = 0;

	public int länge;

	public void add(float wert, int pos) {
		if(pos > 0 && pos <= werte.length){
			werte[pos - 1] = wert;
			minmaxtest(wert);
		}else{
			throw new RuntimeException("\n pos > 0 && pos >= werte.length \n pos = " +pos+"\n werte.length = " + werte.length );
		}         
		
	}

	public float get(int position) {
		if(position> 0 && position>= werte.length){
			return werte[position -1];
		}else{
			throw new RuntimeException("\n pos > 0 && pos >= werte.length \n pos = " +position+"\n werte.length = " + werte.length );
		}   
	}

	public Vektor_float(int größe) {
		werte = new float[größe];
		länge = größe;
	}
	
	private void minmaxtest(float in){
		if(in< minWert ){
			minWert = in;
		}
		
		if(in > maxWert){
			maxWert = in;
		}
		
	}
	
	public int getLaenge(){
		return this.länge;
	}
	
	public float getVerschoben(int position){
		int interval = 0;
		int absposition = Math.abs(position);
		int inposition = position;
		
		if(position >= 0 && position < werte.length){
			return werte[position];
		}else{
			interval = werte.length;
			
		 	if(interval%2 != 0){
		 		interval++;
		 	}
		 	
		 	if(position >= 0){
				position %= interval; 		
		 	}else{
		 		position = interval - (absposition%interval);
		 	}
		 	
		 	try{
		 	
			 	if(position >= werte.length){
			 		return werte[werte.length - 1];
			 	}else{
					return werte[position];
			 	}
		 	}catch(ArrayIndexOutOfBoundsException e){
		 		throw new RuntimeException( "\nintervall   = " + interval +
		 							        "\nposition    = " + position +
		 							        "\ninposition  = " + inposition+
		 							        "\nabsposition = " + absposition+
		 							        "\nabsposition%interval = " + (absposition%interval));
		 		
		 	}
		 	
		 		
		}
		 
	}
}
