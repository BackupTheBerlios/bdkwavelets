/*
 * Created on 06.08.2004
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
public class Invers_wavelet_45 implements Invers_wavelet {

	private float[] FilterA_gerade;
	
	private float[] FilterA_ungerade;
	
	private float[] FilterD_gerade;
	
	private float[] FilterD_ungerade;
	
	private String name;
	
	private int nr;
	
	private int filterlänge = 23;
	
	private int filterIndex0 = 11;
	
	public Invers_wavelet_45(	float[] filterA, 
								int index0A, 
								float[] filterD, 
								int index0D, 
								int nummer, 
								String name){
		nr =nummer;
		this.name = name;
		int i, t;
		FilterA_gerade = new float[filterlänge];
		FilterA_ungerade = new float[filterlänge];
		FilterD_gerade = new float[filterlänge];
		FilterD_ungerade = new float[filterlänge];
	
		/*
		 * Konstruiere FilterA~ gerade
		 */
		for(i = filterIndex0, t = index0A; i < filterlänge; i++){
			FilterA_gerade[i] = 0;
			if(t < filterA.length && t >= 0){
				FilterA_gerade[i] = filterA[t];
				t += 2;
			}
		}

		for(i = filterIndex0-1, t = index0A-2; i >= 0; i--){
			FilterA_gerade[i] = 0;
			if(t < filterA.length && t >= 0){
				FilterA_gerade[i] = filterA[t];
				t -= 2;
			}
		}
		/*
		 * Konstruiere FilterA~ ungerade
		 */
		 for(i = filterIndex0, t = index0A-1; i < filterlänge; i++){
			 FilterA_ungerade[i] = 0;
			 if(t < filterA.length && t >= 0){
				 FilterA_ungerade[i] = filterA[t];
				 t += 2;
			 }
		 }

		 for(i = filterIndex0-1, t = index0A-3; i >= 0; i--){
			 FilterA_ungerade[i] = 0;
			 if(t < filterA.length && t >= 0){
				 FilterA_ungerade[i] = filterA[t];
				 t -= 2;
			 }
		 }
		 
		 /*
		  * Konstruiere FilterD~ gerade
		  */
		 for(i = filterIndex0, t = index0D; i < filterlänge; i++){
			 FilterD_gerade[i] = 0;
			 if(t < filterD.length && t >= 0){
				 FilterD_gerade[i] = filterD[t];
				 t += 2;
			 }
		 }

		 for(i = filterIndex0-1, t = index0D-2; i >= 0; i--){
			 FilterD_gerade[i] = 0;
			 if(t < filterD.length && t >= 0){
				 FilterD_gerade[i] = filterD[t];
				 t -= 2;
			 }
		 }
		 /*
		  * Konstruiere FilterD~ ungerade
		  */
		  for(i = filterIndex0, t = index0D-1; i < filterlänge; i++){
			  FilterD_ungerade[i] = 0;
			  if(t < filterD.length && t >= 0){
				  FilterD_ungerade[i] = filterD[t];
				  t += 2;
			  }
		  }

		  for(i = filterIndex0-1, t = index0D-3; i >= 0; i--){
			  FilterD_ungerade[i] = 0;
			  if(t < filterD.length && t >= 0){
				  FilterD_ungerade[i] = filterD[t];
				  t -= 2;
			  }
		  }			
		  
		  //printFilter();									
									
	}
								

	/* (non-Javadoc)
	 * @see de.wavelet.Invers_wavelet#FilterA(de.datentypen.Vektor_float, int, boolean)
	 */
	public float FilterA(Vektor_float werte, int position, boolean gerade) {
		float BACK = 0;
		int start = 0;
		if(position %2 == 0){
			start = position/2 - filterIndex0;
			for(int i = 0;i < FilterA_gerade.length;i++,start++){
				if(FilterA_gerade[i] != 0){
					BACK += FilterA_gerade[i] * werte.getVerschoben(start);
				}
			}
		}else{
			start = (position-1)/2 - filterIndex0;
			for(int i = 0;i < FilterA_ungerade.length ; i++,start++){
				if(FilterA_ungerade[i] != 0){
					BACK += FilterA_ungerade[i] * werte.getVerschoben(start);
				}
			}
		}

		return BACK;
	}

	/* (non-Javadoc)
	 * @see de.wavelet.Invers_wavelet#FilterD(de.datentypen.Vektor_float, int, boolean)
	 */
	public float FilterD(Vektor_float werte, int position, boolean gerade) {
		float BACK = 0;
		int start = 0;
		if(position %2 == 0){
			start = position/2 - filterIndex0;
			for(int i = 0;i < FilterD_gerade.length;i++,start++){
				if(FilterD_gerade[i] != 0){
					BACK += FilterD_gerade[i] * werte.getVerschoben(start);
				}
			}
		}else{
			start = (position-1)/2 - filterIndex0;
			for(int i = 0;i < FilterD_ungerade.length ; i++,start++){
				if(FilterD_ungerade[i] != 0){
					BACK += FilterD_ungerade[i] * werte.getVerschoben(start);
				}
			}
		}

		return BACK;
	}

	/* (non-Javadoc)
	 * @see de.wavelet.Invers_wavelet#getNummer()
	 */
	public int getNummer() {
		return nr;
	}

	/* (non-Javadoc)
	 * @see de.wavelet.Invers_wavelet#getName()
	 */
	public String getName() {
		return this.name;
	}
	
	public String toString() {
		return this.name;
	}
	
	public void printFilter(){
		for(int i = 0; i < this.FilterA_gerade.length; i++){
			
			System.out.println("\n "+ i+" | "+ this.FilterA_gerade[i] +" | "+ this.FilterA_ungerade[i]+" | "+ this.FilterD_gerade[i]+" | "+ this.FilterD_ungerade[i]);
		}
	}

}
