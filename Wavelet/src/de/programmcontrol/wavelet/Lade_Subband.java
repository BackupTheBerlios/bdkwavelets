/*
 * Created on 28.02.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.programmcontrol.wavelet;

import de.datentypen.*;
/**
 * @author Uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Lade_Subband implements Status {

	public static final int Nr = 5;

	/**
	 * Enthält die Nummer der folgenden Statuse
	 */
	public static final int[] folgeNr = {Bearbeite_Spalten_Links2.Nr, Lade_Kachel2.Nr};
	
	//=========================================================================
	// Funktionen
	//========================================================================

	/**
	 *  Gibt einen Array zurück in dem die Statusnummern aller von diesem Status erreichbaren Statuse gespeichert ist
	 */
	public int[] nextStatusNr() {
		return folgeNr;
	}
	
	
	/**
	 * Gibt die Nummer diese Status zurück.
	 */
	public int dieseStatusNr() {
		return Nr;
	}

	/* (non-Javadoc)
	 * @see de.programmcontrol.wavelet.Status#bearbeiteStatus(de.programmcontrol.wavelet.Variablen)
	 */
	public Variablen bearbeiteStatus(Variablen variablen) {
		int iWork = 1;
	
	
		if(variablen.Ebene > 0){
		
			variablen.aktueller_Subbandknoten = variablen.Komponente[variablen.KomponenteNr - 1].kachel[variablen.KachelNr -1].subband;
		  	/*
		  	 * Laden der zu bearbeitendenen Ebene
		  	 */
		  	while(!(variablen.Ebene == iWork)){
				variablen.aktueller_Subbandknoten = variablen.aktueller_Subbandknoten.nächster_Knoten;
				iWork++;
		  	}
			variablen.Ebene--;
		  	
			/*
			 * Wenn wir die letzte Ebene im Baum erreicht haben laden wir es daraus
			 */
			 if(variablen.aktueller_Subbandknoten.letzter){
			   variablen.temp_LL = variablen.aktueller_Subbandknoten.Daten[0];
			 }else{
			   variablen.temp_LL = variablen.aktuelle_Matrix;
			 }
		  	
		  	
		  	/*
		  	 *  Anzeigen der Subbänder
		  	 */
		  	 variablen.gui.updateObenRechts(variablen.aktueller_Subbandknoten.Daten[1]);
		  	 variablen.gui.updateUntenRechts(variablen.aktueller_Subbandknoten.Daten[3]);
		  	 variablen.gui.updateUntenLinks(variablen.aktueller_Subbandknoten.Daten[2]);
		  	 variablen.gui.updateObenLinksBild(variablen.temp_LL);
	
		  	 /*
		  	  * resaviere Speicher 
		  	  */
		  	 
			variablen.temp1Matrix = new Matrix_float(variablen.aktueller_Subbandknoten.Daten[1].breite, variablen.aktueller_Subbandknoten.aktuellehöhe);
			variablen.temp2Matrix = new Matrix_float(variablen.aktueller_Subbandknoten.Daten[1].breite, variablen.aktueller_Subbandknoten.aktuellehöhe);
		  	 
		  	variablen.aktuelle_Matrix = new Matrix_float(variablen.aktueller_Subbandknoten.aktuellebreite, variablen.aktueller_Subbandknoten.aktuellehöhe);
	
	
			variablen.status = new Bearbeite_Spalten_Links2();
			return variablen;
		}else{
			
			variablen.Komponente[variablen.KomponenteNr - 1].kachel[variablen.KachelNr -1].update(variablen.temp_LL); 
			variablen.status = new Lade_Kachel2(); 
			return variablen;
		}
		
	}

	

}
