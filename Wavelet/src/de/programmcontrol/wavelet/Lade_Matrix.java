/*
 * Created on 17.02.2004
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
public class Lade_Matrix implements Status {

	/**
	 * Nr des Status
	 */
	public static final int Nr = 5; 
	
	/**
	 * Enthält die Nummer der folgenden Statuse
	 */
	public static final int[] folgeNr = {LadeKachel.Nr, Bearbeite_Zeilen.Nr};

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
		if(variablen.Ebene == 0 ){
			/*
			 * wir sind das erste mal hier
			 */
			variablen.aktuelle_Matrix 			= variablen.Komponente[variablen.KomponenteNr - 1].kachel[variablen.KachelNr - 1];
			variablen.aktueller_Subbandknoten 	= new Subband_Knoten(variablen.AnzahlEbenen, variablen.wavelet.getMinAnzahlWerte(), variablen.wavelet.getMinAnzahlWerte(), variablen.aktuelle_Matrix.breite, variablen.aktuelle_Matrix.höhe );
			variablen.Komponente[variablen.KomponenteNr - 1].kachel[variablen.KachelNr - 1].subband = variablen.aktueller_Subbandknoten;
			
			if(variablen.aktueller_Subbandknoten.letzter){
				variablen.temp_LL = variablen.aktueller_Subbandknoten.Daten[0];
			}
			
			variablen.gui.updateEingangsBild(variablen.aktuelle_Matrix);
			variablen.status = new Bearbeite_Zeilen();
			variablen.gui.updateStatus("Neue Ebene geladen  Ebene = " + variablen.Ebene );
			variablen.Ebene++;
			
		}else{
		/*
		 * Alle weiteren Ebenen laden.
		 */
			if(!variablen.aktueller_Subbandknoten.letzter){
				/*
				 * Wenn wir nicht im letzten Knoten stecken
				 */
				variablen.aktueller_Subbandknoten = variablen.aktueller_Subbandknoten.nächster_Knoten;
				variablen.aktuelle_Matrix = variablen.temp_LL;
				
				variablen.gui.updateEingangsBild(variablen.aktuelle_Matrix);
				variablen.status = new Bearbeite_Zeilen();
				variablen.gui.updateStatus("Neue Ebene geladen  Ebene = " + variablen.Ebene );
				variablen.Ebene++;
			}else{
				/*
				 * Wenn wir  im letzten Knoten stecken
				 */
				variablen.gui.updateStatus("Die letzte Ebene ist erreicht");
				variablen.aktueller_Subbandknoten.Daten[0].add(variablen.temp_LL);
				variablen.status = new LadeKachel();
			}
			
			
		}

		return variablen;

	}

}
