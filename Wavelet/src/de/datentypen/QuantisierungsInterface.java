/*
 * Created on 23.03.2004
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
public interface QuantisierungsInterface {


	void set_Werte_bereich( float maxWert);

	short quantisiere(float wert);

	float dequantisieren(short wert, boolean sign);

	void set_dequantisierer(int mantis,int exponent);
	
	int getMantis();
	
	int getExponent();
	
	/*
	 * Anzahl der Bits für eine Komponente
	 */
	int get_anzahl_bits();

}
