/*
 * Created on 23.02.2004
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
public class Bearbeite_Zeilen implements Status {

	/**
	 * Nr des Status
	 */
	public static final int Nr = 6; 

	/**
	 * Enthält die Nummer der folgenden Statuse
	 */
	public static final int[] folgeNr = {Bearbeite_Spalten_Links.Nr};

	/**
	 * Aktuelle Zeile
	 */
	private int Zeile = 0;
	
	/**
	 * Aktuelle Spalte
	 */
	private int Spalte = 0;
	
	/**
	 * Zu bearbeitende Zeile
	 */
	private Vektor_float Vektor_Zeile_in;
	
	/**
	 * Ergebnisse des Tiefpasses
	 */
	private Vektor_float Ergebniss_Tiefpass;
	
	/**
	 * Ergebniss des Hochpasses
	 */
	private Vektor_float Ergebniss_Hochpass;
	
	/**
	 * Ergebniss nach dem Verdünnen des Tiefpasses
	 */
	private Vektor_float Verdünnter_Tiefpass;
	
	/**
	 * Ergebniss nach dem Verdünnen des Hochpasses
	 */
	private Vektor_float Verdünnter_Hochpass;
	
	/**
	 * Beim erstellen gleich true nach dem ersten aufruf von bearbeiteStatus() gleich false
	 */
	private boolean ersterlauf = true;
	
	/**
	 * Immer true wenn eine neue Zeile geladen werden muss.
	 */
	private boolean ladeZeile = true;
	
	/**
	 * Immer true wenn der Tiefpass bearbeite wird
	 */
	private boolean bearbeiteTiefpass = false;
	
	/**
	 * Immer true wenn der Hochpass bearbeite wird
	 */
	private boolean bearbeiteHochpass = false;
	
	/**
	 * Immer true wenn die Ergebnissvektor verdünnt werden.
	 */
	private boolean verdünnen = false;
	

	//===================================================================================
	// Begin der Funktionen
	//======================================================================================	

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
		
		if(ersterlauf){
			variablen.gui.updateStatus("Bearbeite Zeilen");
			variablen.temp1Matrix = new Matrix_float(variablen.aktueller_Subbandknoten.Daten[1].breite, variablen.aktuelle_Matrix.höhe);
			variablen.temp2Matrix = new Matrix_float(variablen.aktueller_Subbandknoten.Daten[1].breite, variablen.aktuelle_Matrix.höhe);
			ersterlauf = false;
			
			Ergebniss_Hochpass = new Vektor_float(variablen.aktuelle_Matrix.breite);
			Ergebniss_Tiefpass = new Vektor_float(variablen.aktuelle_Matrix.breite);
			
			variablen.letzteXgroesse = variablen.aktuelle_Matrix.breite/2;
		}
		
		if(ladeZeile){
			ladeZeile = false;
			bearbeiteTiefpass = true;
			Vektor_Zeile_in = variablen.aktuelle_Matrix.getReihe(Zeile + 1);
			variablen.gui.updateDaten1(Vektor_Zeile_in);
			variablen.gui.updateStatus("Zeile geladen " + Zeile);
			
			
			variablen.status = this;
			return variablen;			
		}
		
		if(verdünnen){
			Verdünnter_Tiefpass = verdünne(Ergebniss_Tiefpass);
			Verdünnter_Hochpass = verdünne(Ergebniss_Hochpass);
			
			variablen.gui.updateDaten3(Verdünnter_Hochpass);
			variablen.gui.updateDaten2(Verdünnter_Tiefpass);
			
			variablen.temp1Matrix.addReihe(Zeile + 1, Verdünnter_Tiefpass);
			variablen.temp2Matrix.addReihe(Zeile + 1, Verdünnter_Hochpass);
			
			variablen.gui.updateLinkeBild(variablen.temp1Matrix);
			variablen.gui.updateRechteBild(variablen.temp2Matrix);
			
			Ergebniss_Hochpass = new Vektor_float(variablen.aktuelle_Matrix.breite);
			Ergebniss_Tiefpass = new Vektor_float(variablen.aktuelle_Matrix.breite);
			
			Zeile++;
			verdünnen = false;
			ladeZeile = true;
			
			if(Zeile >= variablen.aktuelle_Matrix.höhe ){
				variablen.status = new Bearbeite_Spalten_Links();
			}
			 
			
		}
		
		if(bearbeiteHochpass){
			if(Spalte < Ergebniss_Hochpass.länge){
				Ergebniss_Hochpass.werte[Spalte] = variablen.wavelet.FilterD(Vektor_Zeile_in, Spalte);
				variablen.gui.updateDaten3(Ergebniss_Hochpass);
				Spalte++;
			}else{
				bearbeiteHochpass = false;
				bearbeiteTiefpass = false;
				verdünnen = true;
				Spalte = 0;
			}			
		}

		if(bearbeiteTiefpass){
			if(Spalte < Ergebniss_Tiefpass.länge){
				Ergebniss_Tiefpass.werte[Spalte] = variablen.wavelet.FilterA(Vektor_Zeile_in, Spalte);
				variablen.gui.updateDaten2(Ergebniss_Tiefpass);
				Spalte++;
			}else{
				bearbeiteHochpass = true;
				bearbeiteTiefpass = false;
				Spalte = 0;
			}
		}

		return variablen;
	}

	private Vektor_float verdünne(Vektor_float in){
		
		Vektor_float BACK;
		if(in.länge % 2 == 0){
			BACK = new Vektor_float(in.länge / 2);
		}else{
			BACK = new Vektor_float((in.länge + 1) / 2);
		}
		
		for(int i = 0; i < BACK.länge ; i++){
			BACK.werte[i] = in.werte[i*2];
		}
		
		return BACK;
	}

}
