/*
 * Created on 21.02.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.wavelet;

import de.datentypen.Vektor_float;

/**
 * @author Uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public final class Invers_NeunSieben implements Invers_wavelet {

	/**
	 * Filterkoeffizienten für Filter D (Tiefpass)
	 */
	private float[] koe_D = {	 1.115087052456994f ,
								 0.5912717631142470f  ,
								-0.05754352622849957f ,
								-0.09127176311424948f  };

	/**
	 * Filterkoeffizienten für Filter A (Hochpass)
	 */
	private float[] koe_A = {		 0.6029490182363579f,
									-0.2668641184428723f,
									-0.07822326652898785f,
									 0.01686411844287495f,
									 0.02674975741080976f};

	/**
	 * Filternummer zum wiedererkennen
	 */
	private final int Nr = 0;
	
	/**
	 * Name des Wavelets
	 */
	private String Name = "7/9 Filter";




	/* (non-Javadoc)
	 * @see de.wavelet.forward_wavelet#FilterA(de.datentypen.Vektor_float, int)
	 */
	public float FilterD(Vektor_float werte, int position, boolean gerade) {
		if(position >= 0 && position < werte.länge *2+1){
			
			if(gerade){
				return FilterA_gerade(werte, position);		
			}else{
				return FilterA_ungerade(werte, position);
			}
			
		}else{
			throw new RuntimeException(	"\nposition    = " + position +
										"\nwerte.länge = " + werte.länge);
		}
	}

	/* (non-Javadoc)
	 * @see de.wavelet.forward_wavelet#FilterD(de.datentypen.Vektor_float, int)
	 */
	public float FilterA(Vektor_float werte, int position, boolean gerade) {
		if(position >= 0 && position < werte.länge*2+1){
			
			if(gerade){
				return FilterD_gerade(werte, position);		
			}else{
				return FilterD_ungerade(werte, position);
			}
			
		}else{
			throw new RuntimeException(	"\nposition    	= " + position +
										"\nwerte.länge 	= " + werte.länge +
										"\ngerade 		= " + gerade);
		}
	}

	/* (non-Javadoc)
	 * @see de.wavelet.forward_wavelet#getNummer()
	 */
	public int getNummer() {
		return Nr;
	}

	/* (non-Javadoc)
	 * @see de.wavelet.forward_wavelet#getName()
	 */
	public String getName() {
		return this.Name;
	}

	/**
	 * Diese Funktion wird aufgerufen wenn der Ergebnissvektor eine gerade länge hat.
	 * In der Funktion wird nochmal unterschieden ob die position gerade oder ungerade ist.
	 * 
	 * @param werte 
	 * @param position
	 * @return
	 */
	private float FilterA_gerade(Vektor_float werte, int position){
		float BACK = 0.0f;
		/*
		 * Hier werden die gerade positionen behandelt.
		 */
		if(position % 2 == 0){
			if(position == 0){
				BACK = 	koe_A[4] * werte.werte[position/2 + 2] +
						koe_A[2] * werte.werte[position/2 + 1] +
						koe_A[0] * werte.werte[position/2 ] +
						koe_A[2] * werte.werte[position/2 + 1] +
						koe_A[4] * werte.werte[position/2 + 2] ;
			}else if(position == 2){
				BACK = 	koe_A[4] * werte.werte[position/2 ] +
						koe_A[2] * werte.werte[position/2 - 1] +
						koe_A[0] * werte.werte[position/2 ] +
						koe_A[2] * werte.werte[position/2 + 1] +
						koe_A[4] * werte.werte[position/2 + 2] ;
			}else if(position >= 4 && position < 2 * werte.länge - 4 ){
				BACK = 	koe_A[4] * werte.werte[position/2 - 2] +
						koe_A[2] * werte.werte[position/2 - 1] +
						koe_A[0] * werte.werte[position/2 ] +
						koe_A[2] * werte.werte[position/2 + 1] +
						koe_A[4] * werte.werte[position/2 + 2] ;
			}else if( position == 2 * werte.länge - 4 ){
				BACK = 	koe_A[4] * werte.werte[position/2 - 2] +
						koe_A[2] * werte.werte[position/2 - 1] +
						koe_A[0] * werte.werte[position/2 ] +
						koe_A[2] * werte.werte[position/2 + 1] +
						koe_A[4] * werte.werte[position/2 + 1] ;
			}else if( position == 2 * werte.länge - 2 ){
				BACK = 	koe_A[4] * werte.werte[position/2 - 2] +
						koe_A[2] * werte.werte[position/2 - 1] +
						koe_A[0] * werte.werte[position/2 ] +
						koe_A[2] * werte.werte[position/2 ] +
						koe_A[4] * werte.werte[position/2 - 1] ;
			}
			
		/*
		 * Hier werden die ungerade positionen behandelt.
		 */	
		}else{
			if(position == 1){
				BACK = 	koe_A[3] * werte.werte[position/2 + 1] +
						koe_A[1] * werte.werte[position/2 ] +
						koe_A[1] * werte.werte[position/2 + 1] +
						koe_A[3] * werte.werte[position/2 + 2];
			}else if(position >= 3 && position < werte.länge * 2 - 3 ){
				BACK = 	koe_A[3] * werte.werte[position/2 - 1] +
						koe_A[1] * werte.werte[position/2 ] +
						koe_A[1] * werte.werte[position/2 + 1] +
						koe_A[3] * werte.werte[position/2 + 2];
			}else if( position ==  werte.länge*2 - 3 ){
				BACK = 	koe_A[3] * werte.werte[position/2 - 1] +
						koe_A[1] * werte.werte[position/2 ] +
						koe_A[1] * werte.werte[position/2 + 1] +
						koe_A[3] * werte.werte[position/2 + 1];
			}else if( position ==  werte.länge*2 - 1 ){
				BACK = 	koe_A[3] * werte.werte[position/2 - 1] +
						koe_A[1] * werte.werte[position/2    ] +
						koe_A[1] * werte.werte[position/2    ] +
						koe_A[3] * werte.werte[position/2 - 1];
			}
		}
		return BACK;
	}
	
	
	
	
	
	
	private float FilterA_ungerade(Vektor_float werte, int position){
		float BACK = 0.0f;
		/*
		 * Hier werden die gerade positionen behandelt.
		 */
		if(position % 2 == 0){
			if(position == 0){
				BACK = 	koe_A[4] * werte.werte[position/2 + 2] +
						koe_A[2] * werte.werte[position/2 + 1] +
						koe_A[0] * werte.werte[position/2 ] +
						koe_A[2] * werte.werte[position/2 + 1] +
						koe_A[4] * werte.werte[position/2 + 2] ;
			}else if(position == 2){
				BACK = 	koe_A[4] * werte.werte[position/2 ] +
						koe_A[2] * werte.werte[position/2 - 1] +
						koe_A[0] * werte.werte[position/2 ] +
						koe_A[2] * werte.werte[position/2 + 1] +
						koe_A[4] * werte.werte[position/2 + 2] ;
			}else if(position >= 4 && position < 2 * werte.länge - 4 ){
				BACK = 	koe_A[4] * werte.werte[position/2 - 2] +
						koe_A[2] * werte.werte[position/2 - 1] +
						koe_A[0] * werte.werte[position/2 ] +
						koe_A[2] * werte.werte[position/2 + 1] +
						koe_A[4] * werte.werte[position/2 + 2] ;
			}else if( position == 2 * werte.länge - 4 ){
				BACK = 	koe_A[4] * werte.werte[position/2 - 2] +
						koe_A[2] * werte.werte[position/2 - 1] +
						koe_A[0] * werte.werte[position/2 ] +
						koe_A[2] * werte.werte[position/2 + 1] +
						koe_A[4] * werte.werte[position/2 ] ;
			}else if( position == 2 * werte.länge - 2 ){
				BACK = 	koe_A[4] * werte.werte[position/2 - 2] +
						koe_A[2] * werte.werte[position/2 - 1] +
						koe_A[0] * werte.werte[position/2 ] +
						koe_A[2] * werte.werte[position/2 - 1] +
						koe_A[4] * werte.werte[position/2 - 2] ;
			}
			
		/*
		 * Hier werden die ungerade positionen behandelt.
		 */	
		}else{
			if(position == 1){
				BACK = 	koe_A[3] * werte.werte[position/2 + 1] +
						koe_A[1] * werte.werte[position/2 ] +
						koe_A[1] * werte.werte[position/2 + 1] +
						koe_A[3] * werte.werte[position/2 + 2];
			}else if(position >= 3 && position < werte.länge*2 - 3 ){
				BACK = 	koe_A[3] * werte.werte[position/2 - 1] +
						koe_A[1] * werte.werte[position/2 ] +
						koe_A[1] * werte.werte[position/2 + 1] +
						koe_A[3] * werte.werte[position/2 + 2];
			}else if( position ==  werte.länge*2 - 3 ){
				BACK = 	koe_A[3] * werte.werte[position/2 - 1] +
						koe_A[1] * werte.werte[position/2 ] +
						koe_A[1] * werte.werte[position/2 + 1] +
						koe_A[3] * werte.werte[position/2 ];
			}
		}
		return BACK;
	}
	
	private float FilterD_gerade(Vektor_float werte, int position){
		float BACK = 0.0f;
			if(position % 2 == 0){
				if(position == 0){
					BACK = 	koe_D[3] * werte.werte[1] +
							koe_D[1] * werte.werte[0] +   
							koe_D[1] * werte.werte[0] +
							koe_D[3] * werte.werte[1];
				}else if(position == 2){
					BACK = 	koe_D[3] * werte.werte[0] +
							koe_D[1] * werte.werte[0] +   
							koe_D[1] * werte.werte[1] +
							koe_D[3] * werte.werte[2];
				}else if(position >= 4 && position < werte.länge * 2 - 3  ){
					BACK = 	koe_D[3] * werte.werte[position/2 - 2] +
							koe_D[1] * werte.werte[position/2 - 1] +   
							koe_D[1] * werte.werte[position/2	 ] +
							koe_D[3] * werte.werte[position/2  +1];
				}else if(position == werte.länge * 2 - 2  ){
					BACK = 	koe_D[3] * werte.werte[position/2 - 2] +
							koe_D[1] * werte.werte[position/2 - 1] +   
							koe_D[1] * werte.werte[position/2	 ] +
							koe_D[3] * werte.werte[position/2 - 1];
				}
			}else{
				if(position == 1){
					BACK = 	koe_D[2] * werte.werte[position/2 	 ] +
							koe_D[0] * werte.werte[position/2  	 ] +
							koe_D[2] * werte.werte[position/2 + 1];		
					
				}else if(position >= 3 && position <= werte.länge*2 - 3 ){
					BACK = 	koe_D[2] * werte.werte[position/2 -	1] +
							koe_D[0] * werte.werte[position/2  	 ] +
							koe_D[2] * werte.werte[position/2 + 1];		
						
				}else if(position == werte.länge*2 - 1 ){
					BACK = 	koe_D[2] * werte.werte[position/2 -	1] +
							koe_D[0] * werte.werte[position/2  	 ] +
							koe_D[2] * werte.werte[position/2 - 1];		
					
				}
			}
		return BACK;
	}
	
	private float FilterD_ungerade(Vektor_float werte, int position){
		float BACK = 0.0f;
			if(position % 2 == 0){
				if(position == 0){
					BACK = 	koe_D[3] * werte.werte[1] +
							koe_D[1] * werte.werte[0] +   
							koe_D[1] * werte.werte[0] +
							koe_D[3] * werte.werte[1];
				}else if(position == 2){
					BACK = 	koe_D[3] * werte.werte[0] +
							koe_D[1] * werte.werte[0] +   
							koe_D[1] * werte.werte[1] +
							koe_D[3] * werte.werte[2];
				}else if(position >= 4 && position <= werte.länge * 2 - 4  ){
					BACK = 	koe_D[3] * werte.werte[position/2 - 2] +
							koe_D[1] * werte.werte[position/2 - 1] +   
							koe_D[1] * werte.werte[position/2	 ] +
							koe_D[3] * werte.werte[position/2 + 1];
				}else if(position == werte.länge * 2 - 2  ){
					BACK = 	koe_D[3] * werte.werte[position/2 - 2] +
							koe_D[1] * werte.werte[position/2 - 1] +   
							koe_D[1] * werte.werte[position/2	 ] +
							koe_D[3] * werte.werte[position/2 - 2];
				}
			}else{
				if(position == 1){
					BACK = 	koe_D[2] * werte.werte[position/2 	 ] +
							koe_D[0] * werte.werte[position/2  	 ] +
							koe_D[2] * werte.werte[position/2 + 1];		
					
				}else if(position >= 3 && position <= werte.länge * 2  - 3 ){
					BACK = 	koe_D[2] * werte.werte[position/2 -	1] +
							koe_D[0] * werte.werte[position/2  	 ] +
							koe_D[2] * werte.werte[position/2 + 1];		
						
				}
			}
		return BACK;
	}
	
	public String toString(){
		return this.Name;
	}
}
