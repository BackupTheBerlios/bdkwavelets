/*
 * Created on 23.02.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.programmcontrol.wavelet;

/**
 * @author Uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Lade_Komponente implements Status {
	
	/**
	 * Nr des Status
	 */
	public static final int Nr = 3; 

	/**
	 * Enthält die Nummer der folgenden Statuse
	 */
	public static final int[] folgeNr = {LadeKachel.Nr, Fertig.Nr};

	/**
	 *  Gibt einen Array zurück in dem die Statusnummern aller von diesem Status erreichbaren Statuse gespeichert ist
	 */
	public int[] nextStatusNr() {
		return folgeNr;
	}

	/* (non-Javadoc)
	 * @see de.programmcontrol.wavelet.Status#dieseStatusNr()
	 */
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
		if(variablen.Komponente.length  > variablen.KomponenteNr ){
			variablen.KomponenteNr++;
			variablen.KachelNr = 0;
			variablen.status = new LadeKachel();
			variablen.gui.updateStatus("Komponenten geladen");
		}else{
			variablen.gui.updateStatus("Alle Komponenten abgearbeitet");
			variablen.status = new Fertig();
		}
		
		return variablen;
	}

}
