/*
 * Created on 28.02.2004
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
public class Lade_Kachel2 implements Status {

	
	
	/**
	 * Nr des Status
	 */
	public static final int Nr = 4; 

	/**
	 * Enthält die Nummer der folgenden Statuse
	 */
	public static final int[] folgeNr = {Lade_Komponente.Nr, Lade_Matrix.Nr};

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

		if(variablen.Komponente[variablen.KomponenteNr - 1].kachel.length > variablen.KachelNr){
		/*
		 * Wenn noch kacheln da sind lade die nächste.
		 */
		 		 	
			variablen.KachelNr++;
			//variablen.Ebene = variablen.Komponente[variablen.KomponenteNr - 1].kachel[variablen.KachelNr - 1].subband.anzahl_Subbänder();
			variablen.Ebene = variablen.Komponente[variablen.KomponenteNr - 1].kachel[variablen.KachelNr -1].subband.anzahl_Subbänder();
			variablen.gui.updateStatus("Kachel geladen <info> " + variablen.Ebene);
			variablen.status = new Lade_Subband();
			
		}else{
		/*
		 * Wenn keine Kacheln in der Komponente sind lade nächste Komponente
		 */
		 	
			variablen.gui.updateStatus("Keine Kacheln mehr in der aktuellen Komponente");
			variablen.status = new Lade_Komponente2();
		}
		
		return variablen;
	}
}
