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
public class Subband_Daten extends Matrix_float {

	public Subband_Daten(int breite, int h�he, int SubbandPos) {
		super(breite,h�he);
	}
	
	public void add(Matrix_float in){
		if(in.breite == this.breite && in.h�he == this.h�he){
			for(int i = 0; i < in.wertelength(); i++){
				this.add(in.werte(i), i + 1);
			}
		}else{
			throw new RuntimeException(	"\nin.breite == this.breite && in.h�he == this.h�he" +
									"\nin.breite 		= " + in.breite +
									"\nin.h�he   		= " + in.h�he +
									"\nthis.breite 	= " + this.breite +
									"\nthis.h�he		= "	+ this.h�he	);
		}
	}
	
	public Subband_Daten(Subband_Daten_diskret daten, QuantisierungsInterface dequantisierer){
		super(daten.breite, daten.h�he);
		
		dequantisierer.set_dequantisierer(daten.mantisse, daten.exponent);
		
		int temp = daten.breite * daten.h�he;
		
		for(int i = 0; i < temp; i++){
			this.add(dequantisierer.dequantisieren(daten.getWert(i),daten.getSign(i)), i+1);
		}	
	}

	public float get_absMaxWert(){
		if(Math.abs(this.getmaxWert()) > Math.abs(this.getminWert())){
			return Math.abs(this.getmaxWert());
		}else{
			return Math.abs(this.getminWert());
		}
	}
}
