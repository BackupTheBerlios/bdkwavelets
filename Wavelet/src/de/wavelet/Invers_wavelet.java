/*
 * Created on 17.02.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.wavelet;

import de.datentypen.Vektor_float;

/**
 * 
 *
 * Interface was die eigentliche Wavelet-Transformation enth�lt und als Basis f�r andere 
 * Wavelet-Filter dinnen soll.
 * Um in der GUI den richtigen Namen anzuzeigen muss die Funktion "public String toString()"
 * �berschrieben werden. Der Name ist dann der R�ckgabewert.
 * 
 * @author Uwe Br�nen
 * @version 0.1
 */
public interface Invers_wavelet {
	
	/**
	 * Die Position bestimmt die Position im Ergebnisvektor, deswegen kann der Eingangsvektor kleiner
	 * sein als die Position. 
	 * 
	 * @param werte Eine Zeile oder eine Spalte des Bildes
	 * @param position Die Position des R�ckgabewertes im Ergebnisvektor
	 * @param gerade Wenn der Ergebnisvektor gerade ist so ist diese Variablen == true
	 * @return
	 */
	float FilterA(Vektor_float werte, int position, boolean gerade);

	/**
	 * Die Position bestimmt die Position im Ergebnisvektor, deswegen kann der Eingangsvektor kleiner
	 * sein als die Position.
	 * 
	 * @param werte Eine Zeile oder eine Spalte des Bildes
	 * @param position Die Position des R�ckgabewertes im Ergebnisvektor
	 * @param gerade Wenn der Ergebnisvektor gerade ist so ist diese Variablen == true
	 * @return
	 */
	float FilterD(Vektor_float werte, int position, boolean gerade);

	/**
	 * Um das Wavelet zu kennzeichnen, zur Speicherung in einer Datei.
	 * @return
	 */
	int getNummer();


	/**
	 * Besser ist toString() 
	 * @return
	 
	String getName();
	*/

}
