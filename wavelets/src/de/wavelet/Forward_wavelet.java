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
 * Interface was die eigentliche Wavelet-Transformation enthält und als Basis für andere 
 * Wavelet-Filter dinnen soll.
 * Um in der GUI den richtigen Namen anzuzeigen muss die Funktion "public String toString()"
 * überschrieben werden. Der Name ist dann der Rückgabewert.
 * 
 * @author Uwe Brünen
 * @version 0.1
 */
public interface Forward_wavelet {

	/**
	 * Dies ist der Tiefpass. Es wird immer ein Wert zurückgegben. Der Ergebnisvektor hat
	 * die gleiche länge wie der Eingangsvektor, für den Ergebnissvektor muss noch ein 
	 * extra Schritt unternommen werden um ihn zu verdünnen.
	 * 
	 * @param werte Eine Zeile oder eine Spalte des Bildes
	 * @param position Die Position des Rückgabewertes im Ergebnisvektor
	 * @return
	 */
	float FilterA(Vektor_float werte, int position);

	/**
	 * Dies ist der Hochpass. Es wird immer ein Wert zurückgegben. Der Ergebnisvektor hat
	 * die gleiche länge wie der Eingangsvektor, für den Ergebnissvektor muss noch ein 
	 * extra Schritt unternommen werden um ihn zu verdünnen.
	 * 
	 * @param werte Eine Zeile oder eine Spalte des Bildes
	 * @param position Die Position des Rückgabewertes im Ergebnisvektor
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
	 * Hier kann die minimale länges des Eingangsvektor abgefragt werden.
	 * 
	 * @return
	 */
	
	int getMinAnzahlWerte();

}
