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
public class LadeKachel implements Status {
	
	
	/**
	 * Nr des Status
	 */
	public static final int Nr = 4; 

	/**
	 * Enth�lt die Nummer der folgenden Statuse
	 */
	public static final int[] folgeNr = {Lade_Komponente.Nr, Lade_Matrix.Nr};

	/**
	 *  Gibt einen Array zur�ck in dem die Statusnummern aller von diesem Status erreichbaren Statuse gespeichert ist
	 */
	public int[] nextStatusNr() {
		return folgeNr;
	}

	/**
	 * Gibt die Nummer diese Status zur�ck.
	 */
	public int dieseStatusNr() {
		return Nr;
	}

	/* (non-Javadoc)
	 * @see de.programmcontrol.wavelet.Status#bearbeiteStatus(de.programmcontrol.wavelet.Variablen)
	 */
	public Variablen bearbeiteStatus(Variablen variablen) {

		if(variablen.Komponente[variablen.KomponenteNr - 1].kachel.length > variablen.KachelNr){
		/*
		 * Wenn noch kacheln da sind lade die n�chste.
		 */
		 		 	
			variablen.KachelNr++;
			variablen.Ebene = 0;
			variablen.gui.updateStatus("Kachel geladen");
			variablen.status = new Lade_Matrix();
			
		}else{
		/*
		 * Wenn keine Kacheln in der Komponente sind lade n�chste Komponente
		 */
		 	variablen.gui.updateStatus("Keine Kacheln mehr in der aktuellen Komponente");
			variablen.status = new Lade_Komponente();
		}
		
		return variablen;
	}

}
