/*
 * Created on 24.02.2004
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
public class Bearbeite_Spalten_Links implements Status {

	/**
	 * Nr des Status
	 */
	public static final int Nr = 7; 
	
	/**
	 * Enthält die Nummer der folgenden Statuse
	 */
	public static final int[] folgeNr = {Bearbeite_Spalten_Rechts.Nr};

	/**
	 * Aktuelle Zeile
	 */
	private int Zeile = 0;
	
	/**
	 * Aktuelle Spalte
	 */
	private int Spalte = 0;
	
	/**
	 * Zu bearbeitende Spalte
	 */
	private Vektor_float Vektor_Spalte_in;
	
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
	 * interne Statusnummer
	 */
	private final int ersterlauf = 0;
	
	/**
	 * interne Statusnummer
	 */
	private final int ladeSpalte = 1;
	
	/**
	 * interne Statusnummer
	 */
	private final int bearbeiteTiefpass = 2;
	
	/**
	 * interne Statusnummer
	 */
	private final int bearbeiteHochpass = 3;
	
	/**
	 * interne Statusnummer
	 */
	private final int verdünnen = 4;
	
	/**
	 * In der Variable wird der aktuelle Status gespeichert
	 */
	private int interner_status = 0;
	
	/**
	 * Eingangs Matrix
	 */
	private Matrix_float input;
	
	/**
	 * Ausgangs tieffrequenter Anteil
	 */
	private Matrix_float tieffrequent;
	
	/**
	 * Ausgangs hochrequenter Anteil
	 */
	private Matrix_float hochfrequent;
	
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
		switch(interner_status)
		{
			case ersterlauf:
			variablen.gui.updateStatus("Bearbeite Spalte links  <ersterlauf>");
			input = variablen.temp1Matrix;
			tieffrequent = variablen.temp_LL = new Matrix_float(variablen.aktueller_Subbandknoten.Daten[1].breite, variablen.aktueller_Subbandknoten.Daten[1].höhe);
			hochfrequent = variablen.aktueller_Subbandknoten.Daten[1];
			interner_status = ladeSpalte;
			
			Ergebniss_Hochpass = new Vektor_float(variablen.aktuelle_Matrix.höhe);
			Ergebniss_Tiefpass = new Vektor_float(variablen.aktuelle_Matrix.höhe);

			variablen.status = this;
			break;
			
			case ladeSpalte:
			variablen.gui.updateStatus("Bearbeite Spalte links  <ladeSpalte>");
			Vektor_Spalte_in = input.getSpalte(Spalte + 1);
			variablen.gui.updateDaten1(Vektor_Spalte_in);		
			interner_status = bearbeiteTiefpass;
			break;
			
			case bearbeiteTiefpass:
			variablen.gui.updateStatus("Bearbeite Spalte links  <bearbeiteTiefpass>");
			if(Zeile < Ergebniss_Tiefpass.länge){
				Ergebniss_Tiefpass.add(variablen.wavelet.FilterA(Vektor_Spalte_in, Zeile) , Zeile+1);
				variablen.gui.updateDaten2(Ergebniss_Tiefpass);
				Zeile++;
			}else{
				interner_status = bearbeiteHochpass;
				Zeile = 0;				
			}
			break;
			
			case bearbeiteHochpass:
			variablen.gui.updateStatus("Bearbeite Spalte links  <bearbeiteHochpass>");
			if(Zeile < Ergebniss_Hochpass.länge){
				Ergebniss_Hochpass.add(variablen.wavelet.FilterD(Vektor_Spalte_in, Zeile) , Zeile+1);
				variablen.gui.updateDaten3(Ergebniss_Hochpass);
				Zeile++;
			}else{
				interner_status = verdünnen;
				Zeile = 0;				
			}
			break;
			
			case verdünnen:
			variablen.gui.updateStatus("Bearbeite Spalte links  <verdünnen>");
			Verdünnter_Tiefpass = verdünne(Ergebniss_Tiefpass);
			Verdünnter_Hochpass = verdünne(Ergebniss_Hochpass);
			
			variablen.gui.updateDaten3(Verdünnter_Hochpass);
			variablen.gui.updateDaten2(Verdünnter_Tiefpass);
			
			tieffrequent.addSpalte(Spalte + 1, Verdünnter_Tiefpass);
			hochfrequent.addSpalte(Spalte + 1, Verdünnter_Hochpass);
			
			variablen.gui.updateObenLinksBild(tieffrequent);
			variablen.gui.updateUntenLinks(hochfrequent);
			
			Ergebniss_Hochpass = new Vektor_float(variablen.aktuelle_Matrix.höhe);
			Ergebniss_Tiefpass = new Vektor_float(variablen.aktuelle_Matrix.höhe);
			
			Spalte++;
			interner_status = ladeSpalte;
			
			if(Spalte >= input.breite ){
				variablen.status = new Bearbeite_Spalten_Rechts();
			}
					
			
			break;
			
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
