/*
 * Created on 24.03.2004
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
public final class Skalar_Quantisierer implements QuantisierungsInterface  {

	private int exponent;
	
	private int mantis;
	
	private float delta;
	
	private float deltahalb;

	private int anzahl_bits;
	
	private short BITMASK = 0;
	
	private static double LOG2 = Math.log(2.0);
	 
	public void  set_Werte_bereich( float maxWert) {
		float work =Math.abs(maxWert) ;		
		
		double tempDelta =( Math.abs(maxWert) / Math.pow(2.0,  (double)anzahl_bits ));
		
		exponent = (int)(Math.log(2/tempDelta)/ LOG2);
		
		mantis = (int)((tempDelta * Math.pow(2.0, (double)(exponent)) - 1.0) * 2048.0);
		
		delta = (float)Math.pow(2.0, (double)(-exponent) ) * (1.0f +  mantis/2048.0f );
		
		
		/*System.out.println(	"\nmaxWert = " +maxWert + 
							"\ndelta   = "+delta +
							"\ntempDelta = " + tempDelta+
							"\nmantis = " + mantis +
							"\nexponent = " + exponent);
		*/					
	}


	public short quantisiere(float wert) {
		
		return (short) (wert/delta) ;
	}

	public Skalar_Quantisierer(Bit_selection anzahl_Bits) {
		//this.anzahl_bits = anzahl_Bits;
		int help = 0xFFFFFFFF;
		this.anzahl_bits = anzahl_Bits.anzahlbits;
		
		BITMASK = (short)(help ^ (int)(Math.pow(2.0, (double)(anzahl_Bits.groesseDerTodzone))-1));
	}
	
	public int get_anzahl_bits(){
		return this.anzahl_bits;
	}
	
	public void set_dequantisierer(int mantis,int exponent){

		delta = (float)Math.pow(2.0, (double)(-exponent) ) * (1.0f +  mantis/2048.0f );	
		
		deltahalb = delta/2.0f;
		
		
	}
	
	public float dequantisieren(short wert, boolean sign){
		wert &= BITMASK;
		if(sign){
			return this.delta * (float)wert * (-1.0f) - deltahalb;
		}else{
			return this.delta * (float)wert + deltahalb;	
		}
	}
	
	
	public int getMantis(){
		return mantis;
	}
	
	public int getExponent(){
		return exponent;
	}

}
