/*
 * Created on 05.08.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.wavelet;

import de.datentypen.Vektor_float;

/**
 * @author uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Forward_wavelet_45 implements Forward_wavelet {

	private float[] filterA;
	
	private float[] filterD;
	
	private String name;
	
	private int nr;

	private void setFilterA(float[] filterA, int indexNullA){
		this.filterA = new float[45];
		int help = 22 - indexNullA;
		int t = 0;
		for(int i = 0; i < 45; i++){
			this.filterA[i] = 0;
			if(i >= help && t < filterA.length){
				this.filterA[i] = filterA[t];
				t++;
			}
		}		
	}
	
	private void setFilterD(float[] filterD, int indexNullD){
		this.filterD = new float[45];
		int help = 22 - indexNullD;
		int t = 0;
		for(int i = 0; i < 45; i++){
			this.filterD[i] = 0;
			if(i >= help && t < filterD.length){
				this.filterD[i] = filterD[t];
				t++;
			}
		}		
	}

	public Forward_wavelet_45(float[] filterA, int indexNullA, float[] filterD, int indexNullD, String Name, int Nummer){
		setFilterD(filterD,indexNullD); 
		setFilterA(filterA,indexNullA);
		
		name = Name;
		nr = Nummer;
		
	}
	/* (non-Javadoc)
	 * @see de.wavelet.Forward_wavelet#FilterA(de.datentypen.Vektor_float, int)
	 */
	public float FilterA(Vektor_float werte, int position) {
		float BACK = 0;
		int help = position - 22;
		for(int i = 0; i < filterA.length; i++){
			if(filterA[i] != 0){
				BACK += filterA[i] * werte.getVerschoben(help + i);
			}
		}
		return BACK;
	}

	/* (non-Javadoc)
	 * @see de.wavelet.Forward_wavelet#FilterD(de.datentypen.Vektor_float, int)
	 */
	public float FilterD(Vektor_float werte, int position) {
		float BACK = 0;
		int help = position - 22;
		for(int i = 0; i < filterA.length; i++){
			if(filterD[i] != 0){
				BACK += filterD[i] * werte.getVerschoben(help + i);
			}
		}
		return BACK;
	}

	/* (non-Javadoc)
	 * @see de.wavelet.Forward_wavelet#getNummer()
	 */
	public int getNummer() {
		return nr;
	}

	/* (non-Javadoc)
	 * @see de.wavelet.Forward_wavelet#getName()
	 */
	public String getName() {	
		return name;
	}

	/* (non-Javadoc)
	 * @see de.wavelet.Forward_wavelet#getMinAnzahlWerte()
	 */
	public int getMinAnzahlWerte() {
		return 23;
	}
	
	public String toString(){
		return name;
	}

}
