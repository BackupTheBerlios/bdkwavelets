/*
 * Created on 17.08.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.datentypen;

/**
 * @author uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Bit_selection{
	int anzahlbits;
	int groesseDerTodzone;
	String info;
		
	public Bit_selection(int anzahl, int todzone){
		this.anzahlbits = anzahl;
		this.groesseDerTodzone = todzone;
		this.info = anzahl + " Bit Aufteilung " + todzone + " Bit LSB" ;
	}
		
	public String toString(){
		return info;
	}
}
