/*
 * Created on 17.02.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.programmcontrol.wavelet;

import de.gui.wavelet.Wavelet_Fenster;
import de.datentypen.Subband_Knoten;
import de.datentypen.Matrix_float;
import de.datentypen.Komponenten_Kacheln;
import de.wavelet.Invers_wavelet;
import de.wavelet.Forward_wavelet;
//import de.datentypen.Subband_Daten;
/**
 * @author Uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Variablen {

	public int KomponenteNr = 0;

	public int KachelNr = 0;

	public Wavelet_Fenster gui;

	public Komponenten_Kacheln[] Komponente;

	public Matrix_float aktuelle_Matrix;

	public int minSubbandgöße;
	
	public int letzteXgroesse = 100;
	
	public int letzteYgroesse = 100;

	public String maxSubband;



	public Forward_wavelet wavelet;

	public Status status;

	public int Ebene = 0;

	public int AnzahlEbenen = 5;

	public Subband_Knoten aktueller_Subbandknoten;

	public Matrix_float temp_LL;

	public Matrix_float temp1Matrix;

	public Matrix_float temp2Matrix;

	public Invers_wavelet invers_wavelet;

}
