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

	public Subband_Daten(int breite, int höhe, int SubbandPos) {
		super(breite,höhe);
	}
	
	public void add(Matrix_float in){
		if(in.breite == this.breite && in.höhe == this.höhe){
			for(int i = 0; i < in.wertelength(); i++){
				this.add(in.werte(i), i + 1);
			}
		}else{
			throw new RuntimeException(	"\nin.breite == this.breite && in.höhe == this.höhe" +
									"\nin.breite 		= " + in.breite +
									"\nin.höhe   		= " + in.höhe +
									"\nthis.breite 	= " + this.breite +
									"\nthis.höhe		= "	+ this.höhe	);
		}
	}
	
	public Subband_Daten(Subband_Daten_diskret daten, QuantisierungsInterface dequantisierer){
		super(daten.breite, daten.höhe);
		
		dequantisierer.set_dequantisierer(daten.mantisse, daten.exponent);
		
		int temp = daten.breite * daten.höhe;
		
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
