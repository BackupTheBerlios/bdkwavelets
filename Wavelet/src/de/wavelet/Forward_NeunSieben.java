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
public final class Forward_NeunSieben implements Forward_wavelet {


	/**
	 * Filternummer zum wiedererkennen
	 */
	private final int Nr = 0;
	
	/**
	 * Name des Filters
	 */
	private String Name = "7/9 Filter";

	/**
	 * Filterkoeffizienten für Filter A (Tiefpass)
	 */
	private float[] koe_A = {	 1.115087052456994f ,
								-0.5912717631142470f  ,
								-0.05754352622849957f ,
								 0.09127176311424948f  };

	/**
	 * Filterkoeffizienten für Filter D (Hochpass)
	 */
	private float[] koe_D = {		 0.6029490182363579f,
									 0.2668641184428723f,
									-0.07822326652898785f,
									-0.01686411844287495f,
									 0.02674975741080976f};
	


	/* (non-Javadoc)
	 * @see de.wavelet.forward_wavelet#FilterA(de.datentypen.Vektor_float, int)
	 */
	public float FilterD(Vektor_float werte, int position) {
		if(position >= 0 && position < werte.länge){
			
			if(werte.länge % 2 == 0){
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
	 * && werte.länge >= getMinAnzahlWerte()*2
	 */
	public float FilterA(Vektor_float werte, int position) {
		if(position >= 0 && position < werte.länge ){
			
			if(werte.länge % 2 == 0){
				return FilterD_gerade(werte, position);		
			}else{
				return FilterD_ungerade(werte, position);
			}
			
		}else{
			throw new RuntimeException(	"\nposition    = " + position +
										"\nwerte.länge = " + werte.länge);
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
	 * Überschreiben der Funktion, damit in der Auswahl etwas Sinnvolles steht.
	 */
	public String toString(){
		return getName();
	}

	/*
	 * 
	 */
	private float FilterA_gerade(Vektor_float werte, int position){		
		float BACK = 0.0f;
		
		if(position == 0){
			BACK = 	werte.werte[3] * koe_A[3] +
					werte.werte[2] * koe_A[2] +
					werte.werte[1] * koe_A[1] +		
					werte.werte[position    ] * koe_A[0] +
					werte.werte[position + 1] * koe_A[1] +
					werte.werte[position + 2] * koe_A[2] +
					werte.werte[position + 3] * koe_A[3];
			
		}else if(position == 1){
			BACK = 	werte.werte[2] * koe_A[3] +
					werte.werte[1] * koe_A[2] +
					werte.werte[position - 1] * koe_A[1] +		
					werte.werte[position    ] * koe_A[0] +
					werte.werte[position + 1] * koe_A[1] +
					werte.werte[position + 2] * koe_A[2] +
					werte.werte[position + 3] * koe_A[3];
	
		}else if(position == 2){
			BACK = 	werte.werte[1] * koe_A[3] +
					werte.werte[position - 2] * koe_A[2] +
					werte.werte[position - 1] * koe_A[1] +		
					werte.werte[position    ] * koe_A[0] +
					werte.werte[position + 1] * koe_A[1] +
					werte.werte[position + 2] * koe_A[2] +
					werte.werte[position + 3] * koe_A[3];
	
		}else if(position >= 3 && position < werte.länge  - 3){
			BACK = 	werte.werte[position - 3] * koe_A[3] +
					werte.werte[position - 2] * koe_A[2] +
					werte.werte[position - 1] * koe_A[1] +		
					werte.werte[position    ] * koe_A[0] +
					werte.werte[position + 1] * koe_A[1] +
					werte.werte[position + 2] * koe_A[2] +
					werte.werte[position + 3] * koe_A[3];
	
		}else if(position == werte.länge  - 3){
			BACK = 	werte.werte[position - 3] * koe_A[3] +
					werte.werte[position - 2] * koe_A[2] +
					werte.werte[position - 1] * koe_A[1] +		
					werte.werte[position    ] * koe_A[0] +
					werte.werte[position + 1] * koe_A[1] +
					werte.werte[position + 2] * koe_A[2] +
					werte.werte[position + 1] * koe_A[3];			
		
		}else if(position == werte.länge  - 2){
			BACK = 	werte.werte[position - 3] * koe_A[3] +
					werte.werte[position - 2] * koe_A[2] +
					werte.werte[position - 1] * koe_A[1] +		
					werte.werte[position    ] * koe_A[0] +
					werte.werte[position + 1] * koe_A[1] +
					werte.werte[position    ] * koe_A[2] +
					werte.werte[position - 1] * koe_A[3];			
		
		}else if(position == werte.länge  - 1){
			BACK = 	werte.werte[position - 3] * koe_A[3] +
					werte.werte[position - 2] * koe_A[2] +
					werte.werte[position - 1] * koe_A[1] +		
					werte.werte[position    ] * koe_A[0] +
					werte.werte[position - 1] * koe_A[1] +
					werte.werte[position - 2] * koe_A[2] +
					werte.werte[position - 3] * koe_A[3];			
			
		}
		
		return BACK;
	}
	
	/*
	 * 
	 */
	private float FilterA_ungerade(Vektor_float werte, int position){
		
		float BACK = 0.0f;
		
		if(position == 0){
			BACK = 	werte.werte[3] * koe_A[3] +
					werte.werte[2] * koe_A[2] +
					werte.werte[1] * koe_A[1] +		
					werte.werte[position    ] * koe_A[0] +
					werte.werte[position + 1] * koe_A[1] +
					werte.werte[position + 2] * koe_A[2] +
					werte.werte[position + 3] * koe_A[3];
			
		}else if(position == 1){
			BACK = 	werte.werte[2] * koe_A[3] +
					werte.werte[1] * koe_A[2] +
					werte.werte[position - 1] * koe_A[1] +		
					werte.werte[position    ] * koe_A[0] +
					werte.werte[position + 1] * koe_A[1] +
					werte.werte[position + 2] * koe_A[2] +
					werte.werte[position + 3] * koe_A[3];
	
		}else if(position == 2){
			BACK = 	werte.werte[1] * koe_A[3] +
					werte.werte[position - 2] * koe_A[2] +
					werte.werte[position - 1] * koe_A[1] +		
					werte.werte[position    ] * koe_A[0] +
					werte.werte[position + 1] * koe_A[1] +
					werte.werte[position + 2] * koe_A[2] +
					werte.werte[position + 3] * koe_A[3];
	
		}else if(position >= 3 && position < werte.länge  - 3){
			BACK = 	werte.werte[position - 3] * koe_A[3] +
					werte.werte[position - 2] * koe_A[2] +
					werte.werte[position - 1] * koe_A[1] +		
					werte.werte[position    ] * koe_A[0] +
					werte.werte[position + 1] * koe_A[1] +
					werte.werte[position + 2] * koe_A[2] +
					werte.werte[position + 3] * koe_A[3];
	
		}else if(position == werte.länge  - 3){
			BACK = 	werte.werte[position - 3] * koe_A[3] +
					werte.werte[position - 2] * koe_A[2] +
					werte.werte[position - 1] * koe_A[1] +		
					werte.werte[position    ] * koe_A[0] +
					werte.werte[position + 1] * koe_A[1] +
					werte.werte[position + 2] * koe_A[2] +
					werte.werte[position + 1] * koe_A[3];			
		
		}else if(position == werte.länge  - 2){
			BACK = 	werte.werte[position - 3] * koe_A[3] +
					werte.werte[position - 2] * koe_A[2] +
					werte.werte[position - 1] * koe_A[1] +		
					werte.werte[position    ] * koe_A[0] +
					werte.werte[position + 1] * koe_A[1] +
					werte.werte[position    ] * koe_A[2] +
					werte.werte[position - 1] * koe_A[3];			
		
		}else if(position == werte.länge  - 1){
			BACK = 	werte.werte[position - 3] * koe_A[3] +
					werte.werte[position - 2] * koe_A[2] +
					werte.werte[position - 1] * koe_A[1] +		
					werte.werte[position    ] * koe_A[0] +
					werte.werte[position - 1] * koe_A[1] +
					werte.werte[position - 2] * koe_A[2] +
					werte.werte[position - 3] * koe_A[3];			
			
		}
		
		return BACK;
	}
	
	/*
	 * 
	 */
	private float FilterD_gerade(Vektor_float werte, int position){		
		float BACK = 0.0f;

		if(position == 0){
			BACK = 	werte.werte[3] * koe_D[4] +
					werte.werte[2] * koe_D[3] +
					werte.werte[1] * koe_D[2] +
					werte.werte[position    ] * koe_D[1] +		
					werte.werte[position + 1] * koe_D[0] +
					werte.werte[position + 2] * koe_D[1] +
					werte.werte[position + 3] * koe_D[2] +
					werte.werte[position + 4] * koe_D[3] +
					werte.werte[position + 5] * koe_D[4];
			
		}else if(position == 1){
			BACK = 	werte.werte[2] * koe_D[4] +
					werte.werte[1] * koe_D[3] +
					werte.werte[position - 1] * koe_D[2] +
					werte.werte[position    ] * koe_D[1] +		
					werte.werte[position + 1] * koe_D[0] +
					werte.werte[position + 2] * koe_D[1] +
					werte.werte[position + 3] * koe_D[2] +
					werte.werte[position + 4] * koe_D[3] +
					werte.werte[position + 5] * koe_D[4];
			
		}else if(position == 2){
			BACK = 	werte.werte[1] * koe_D[4] +
					werte.werte[position - 2] * koe_D[3] +
					werte.werte[position - 1] * koe_D[2] +
					werte.werte[position ] * koe_D[1] +		
					werte.werte[position + 1] * koe_D[0] +
					werte.werte[position + 2] * koe_D[1] +
					werte.werte[position + 3] * koe_D[2] +
					werte.werte[position + 4] * koe_D[3] +
					werte.werte[position + 5] * koe_D[4];
							
		}else if(position >= 3 && position < werte.länge - 5){
			BACK = 	werte.werte[position - 3] * koe_D[4] +
					werte.werte[position - 2] * koe_D[3] +
					werte.werte[position - 1] * koe_D[2] +
					werte.werte[position 	] * koe_D[1] +		
					werte.werte[position + 1] * koe_D[0] +
					werte.werte[position + 2] * koe_D[1] +
					werte.werte[position + 3] * koe_D[2] +
					werte.werte[position + 4] * koe_D[3] +
					werte.werte[position + 5] * koe_D[4];
					
		}else if(position == werte.länge - 5){
			BACK = 	werte.werte[position - 3] * koe_D[4] +
					werte.werte[position - 2] * koe_D[3] +
					werte.werte[position - 1] * koe_D[2] +
					werte.werte[position 	] * koe_D[1] +		
					werte.werte[position + 1] * koe_D[0] +
					werte.werte[position + 2] * koe_D[1] +
					werte.werte[position + 3] * koe_D[2] +
					werte.werte[position + 4] * koe_D[3] +
					werte.werte[position + 3] * koe_D[4];
						
		}else if(position == werte.länge - 4){
			BACK = 	werte.werte[position - 3] * koe_D[4] +
					werte.werte[position - 2] * koe_D[3] +
					werte.werte[position - 1] * koe_D[2] +
					werte.werte[position 	] * koe_D[1] +		
					werte.werte[position + 1] * koe_D[0] +
					werte.werte[position + 2] * koe_D[1] +
					werte.werte[position + 3] * koe_D[2] +
					werte.werte[position + 2] * koe_D[3] +
					werte.werte[position + 1] * koe_D[4];
							
		}else if(position == werte.länge - 3){
			BACK = 	werte.werte[position - 3] * koe_D[4] +
					werte.werte[position - 2] * koe_D[3] +
					werte.werte[position - 1] * koe_D[2] +
					werte.werte[position 	] * koe_D[1] +		
					werte.werte[position + 1] * koe_D[0] +
					werte.werte[position + 2] * koe_D[1] +
					werte.werte[position + 1] * koe_D[2] +
					werte.werte[position    ] * koe_D[3] +
					werte.werte[position - 1] * koe_D[4];
							
		}else if(position == werte.länge - 2){
			BACK = 	werte.werte[position - 3] * koe_D[4] +
					werte.werte[position - 2] * koe_D[3] +
					werte.werte[position - 1] * koe_D[2] +
					werte.werte[position 	] * koe_D[1] +		
					werte.werte[position + 1] * koe_D[0] +
					werte.werte[position    ] * koe_D[1] +
					werte.werte[position - 1] * koe_D[2] +
					werte.werte[position - 2] * koe_D[3] +
					werte.werte[position - 3] * koe_D[4];
							
		}else if(position == werte.länge - 1){
			BACK = 	werte.werte[position - 3] * koe_D[4] +
					werte.werte[position - 2] * koe_D[3] +
					werte.werte[position - 1] * koe_D[2] +
					werte.werte[position 	] * koe_D[1] +		
					werte.werte[position - 1] * koe_D[0] +
					werte.werte[position - 2] * koe_D[1] +
					werte.werte[position - 3] * koe_D[2] +
					werte.werte[position - 4] * koe_D[3] +
					werte.werte[position - 5] * koe_D[4];
								
		}
		return BACK;
	}
	
	/*
	 * 
	 */
	private float FilterD_ungerade(Vektor_float werte, int position){		
		float BACK = 0.0f;

		if(position == 0){
			BACK = 	werte.werte[3] * koe_D[4] +
					werte.werte[2] * koe_D[3] +
					werte.werte[1] * koe_D[2] +
					werte.werte[position    ] * koe_D[1] +		
					werte.werte[position + 1] * koe_D[0] +
					werte.werte[position + 2] * koe_D[1] +
					werte.werte[position + 3] * koe_D[2] +
					werte.werte[position + 4] * koe_D[3] +
					werte.werte[position + 5] * koe_D[4];
			
		}else if(position == 1){
			BACK = 	werte.werte[2] * koe_D[4] +
					werte.werte[1] * koe_D[3] +
					werte.werte[position - 1] * koe_D[2] +
					werte.werte[position    ] * koe_D[1] +		
					werte.werte[position + 1] * koe_D[0] +
					werte.werte[position + 2] * koe_D[1] +
					werte.werte[position + 3] * koe_D[2] +
					werte.werte[position + 4] * koe_D[3] +
					werte.werte[position + 5] * koe_D[4];
			
		}else if(position == 2){
			BACK = 	werte.werte[1] * koe_D[4] +
					werte.werte[position - 2] * koe_D[3] +
					werte.werte[position - 1] * koe_D[2] +
					werte.werte[position ] * koe_D[1] +		
					werte.werte[position + 1] * koe_D[0] +
					werte.werte[position + 2] * koe_D[1] +
					werte.werte[position + 3] * koe_D[2] +
					werte.werte[position + 4] * koe_D[3] +
					werte.werte[position + 5] * koe_D[4];
							
		}else if(position >= 3 && position < werte.länge - 5){
			BACK = 	werte.werte[position - 3] * koe_D[4] +
					werte.werte[position - 2] * koe_D[3] +
					werte.werte[position - 1] * koe_D[2] +
					werte.werte[position 	] * koe_D[1] +		
					werte.werte[position + 1] * koe_D[0] +
					werte.werte[position + 2] * koe_D[1] +
					werte.werte[position + 3] * koe_D[2] +
					werte.werte[position + 4] * koe_D[3] +
					werte.werte[position + 5] * koe_D[4];
					
		}else if(position == werte.länge - 5){
			BACK = 	werte.werte[position - 3] * koe_D[4] +
					werte.werte[position - 2] * koe_D[3] +
					werte.werte[position - 1] * koe_D[2] +
					werte.werte[position 	] * koe_D[1] +		
					werte.werte[position + 1] * koe_D[0] +
					werte.werte[position + 2] * koe_D[1] +
					werte.werte[position + 3] * koe_D[2] +
					werte.werte[position + 4] * koe_D[3] +
					werte.werte[position + 3] * koe_D[4];
						
		}else if(position == werte.länge - 4){
			BACK = 	werte.werte[position - 3] * koe_D[4] +
					werte.werte[position - 2] * koe_D[3] +
					werte.werte[position - 1] * koe_D[2] +
					werte.werte[position 	] * koe_D[1] +		
					werte.werte[position + 1] * koe_D[0] +
					werte.werte[position + 2] * koe_D[1] +
					werte.werte[position + 3] * koe_D[2] +
					werte.werte[position + 2] * koe_D[3] +
					werte.werte[position + 1] * koe_D[4];
							
		}else if(position == werte.länge - 3){
			BACK = 	werte.werte[position - 3] * koe_D[4] +
					werte.werte[position - 2] * koe_D[3] +
					werte.werte[position - 1] * koe_D[2] +
					werte.werte[position 	] * koe_D[1] +		
					werte.werte[position + 1] * koe_D[0] +
					werte.werte[position + 2] * koe_D[1] +
					werte.werte[position + 1] * koe_D[2] +
					werte.werte[position    ] * koe_D[3] +
					werte.werte[position - 1] * koe_D[4];
							
		}else if(position == werte.länge - 2){
			BACK = 	werte.werte[position - 3] * koe_D[4] +
					werte.werte[position - 2] * koe_D[3] +
					werte.werte[position - 1] * koe_D[2] +
					werte.werte[position 	] * koe_D[1] +		
					werte.werte[position + 1] * koe_D[0] +
					werte.werte[position    ] * koe_D[1] +
					werte.werte[position - 1] * koe_D[2] +
					werte.werte[position - 2] * koe_D[3] +
					werte.werte[position - 3] * koe_D[4];
							
		}else if(position == werte.länge - 1){
			BACK = 	werte.werte[position - 3] * koe_D[4] +
					werte.werte[position - 2] * koe_D[3] +
					werte.werte[position - 1] * koe_D[2] +
					werte.werte[position 	] * koe_D[1] +		
					werte.werte[position - 1] * koe_D[0] +
					werte.werte[position - 2] * koe_D[1] +
					werte.werte[position - 3] * koe_D[2] +
					werte.werte[position - 4] * koe_D[3] +
					werte.werte[position - 5] * koe_D[4];
								
		}
		return BACK;
	}	
	
	public int getMinAnzahlWerte() {
		return 23;
	}

}