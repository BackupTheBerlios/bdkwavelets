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
public interface Forward_wavelet {

	/**
	 * Dies ist der Tiefpass. Es wird immer ein Wert zur�ckgegben. Der Ergebnisvektor hat
	 * die gleiche l�nge wie der Eingangsvektor, f�r den Ergebnissvektor muss noch ein 
	 * extra Schritt unternommen werden um ihn zu verd�nnen.
	 * 
	 * @param werte Eine Zeile oder eine Spalte des Bildes
	 * @param position Die Position des R�ckgabewertes im Ergebnisvektor
	 * @return
	 */
	float FilterA(Vektor_float werte, int position);

	/**
	 * Dies ist der Hochpass. Es wird immer ein Wert zur�ckgegben. Der Ergebnisvektor hat
	 * die gleiche l�nge wie der Eingangsvektor, f�r den Ergebnissvektor muss noch ein 
	 * extra Schritt unternommen werden um ihn zu verd�nnen.
	 * 
	 * @param werte Eine Zeile oder eine Spalte des Bildes
	 * @param position Die Position des R�ckgabewertes im Ergebnisvektor
	 * @return
	 */
	float FilterD(Vektor_float werte, int position);

	/**
	 * Um das Wavelet zu kennzeichnen, zur Speicherung in einer Datei.
	 * @return Nummer des Wavelets
	 */
	int getNummer();

	/**
	 * Besser ist toString() 
	 * @return
	 
	String getName();
	*/
	/**
	 * Hier kann die minimale l�nges des Eingangsvektor abgefragt werden.
	 * 
	 * @return
	 */
	
	int getMinAnzahlWerte();

}
