package de.wavelet;

import de.datentypen.Vektor_float;

public final class Forward_HaarWavelet implements Forward_wavelet {

	private final float WURZEL2 = (float) Math.sqrt(2.0); 

	public float FilterA(Vektor_float werte, int position) {
		if(position < werte.werte.length - 1){
			return (werte.werte[position] + werte.werte[position + 1])/WURZEL2;
		}else{
			return (werte.werte[position] + 0)/WURZEL2;
		}

	}

	public float FilterD(Vektor_float werte, int position) {
		
		if(position < werte.werte.length - 1){
			return (werte.werte[position] - werte.werte[position + 1])/WURZEL2;
		}else{
			return (werte.werte[position] - 0)/WURZEL2;
		}
	}

	public int getNummer() {
		return 1;
	}

	public String getName(){
		return "Haar Wavelet";
	}

	public String toString(){
		return getName();
	}

	public int getMinAnzahlWerte() {
		return 10;
	}
}
