
package de.wavelet;

import de.datentypen.Vektor_float;

public final class Invers_HaarWavelet implements Invers_wavelet {

	private final float WURZEL2 = (float) Math.sqrt(2.0); 

	public float FilterD(Vektor_float werte, int position, boolean gerade) {
		if(position % 2 == 0){
			position /= 2;
			return werte.werte[position] / WURZEL2;
		}else{
			position /= 2;
			return -werte.werte[position] / WURZEL2;			
		}
	}

	public float FilterA(Vektor_float werte, int position, boolean gerade) {
		if(position % 2 == 0){
			position /= 2;
			return werte.werte[position] / WURZEL2;
		}else{
			position /= 2;
			return werte.werte[position] / WURZEL2;			
		}
	}

	public int getNummer() {
		return 1;
	}

	public String getName() {
		return "Haar Wavelet";
	}
	
	public String toString(){
		return getName();
	}
}
