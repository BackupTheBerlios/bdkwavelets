/*
 * Created on 29.02.2004
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
public class Bearbeite_Spalten_Rechts2 implements Status {

	public static final int Nr = 7;

	/**
	 * Enthält die Nummer der folgenden Statuse
	 */
	public static final int[] folgeNr = {Bearbeite_Zeilen2.Nr};

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
	private final int bearbeiten = 1;
	
	/**
	 * interne Statusnummer
	 */
	private final int ladeVekoren = 2;
	
	/**
	 * interne Statusnummer
	 */
	private final int update = 3;
	
	
	/**
	 * In der Variable wird der aktuelle Status gespeichert
	 */
	private int interner_status = 0;
	
	/**
	 * Eingangs Matrix
	 */
	private Matrix_float input1;
	
	/**
	 * Eingangs Matrix
	 */
	private Matrix_float input2;
	

	
	/**
	 * 
	 */
	private Variablen variablen;
	
	
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
		this.variablen = variablen;
		
		switch(interner_status){
			case ersterlauf:
				ersterlauf();
				break;
			case bearbeiten:
				bearbeiten();
				break;
			case ladeVekoren:
				ladeVekoren();
				break;
			case update:
				update();
				break;
			
		}
		
		return this.variablen;
	}
	
	private void ersterlauf(){
		variablen.gui.updateStatus("Bearbeite Spalte links  <ersterlauf>");
		input1 = variablen.aktueller_Subbandknoten.Daten[2];
		input2 = variablen.aktueller_Subbandknoten.Daten[3];
		
		interner_status 	= ladeVekoren;
		variablen.status = this;		
	}
	
	private void ladeVekoren(){
		variablen.gui.updateStatus("Bearbeite Spalte links  <ladeVekoren>");
		Verdünnter_Tiefpass = input1.getSpalte(this.Spalte + 1);
		Verdünnter_Hochpass = input2.getSpalte(this.Spalte + 1);
		
		Ergebniss_Tiefpass = new Vektor_float(variablen.temp1Matrix.höhe);
		Ergebniss_Hochpass = new Vektor_float(variablen.temp2Matrix.höhe);
		
		Vektor_Spalte_in = new Vektor_float(variablen.temp2Matrix.höhe);
		
		interner_status 	= bearbeiten; 
		variablen.status 	= this;
			
	}
	
	private void bearbeiten(){
		//variablen.gui.updateStatus("Bearbeite Spalte links  <bearbeiten>");
		boolean gerade;
		if(Ergebniss_Tiefpass.werte.length % 2 == 0){
			gerade = true;
		}else{
			gerade = false;
		}
		
		Ergebniss_Tiefpass.add(variablen.invers_wavelet.FilterA(Verdünnter_Tiefpass, Zeile,gerade),Zeile+1);
		Ergebniss_Hochpass.add(variablen.invers_wavelet.FilterD(Verdünnter_Hochpass, Zeile,gerade),Zeile+1);
		
		Vektor_Spalte_in.werte[Zeile] = Ergebniss_Tiefpass.werte[Zeile] + Ergebniss_Hochpass.werte[Zeile];
		
		variablen.gui.updateDaten1(Vektor_Spalte_in);
		variablen.gui.updateDaten2(Ergebniss_Tiefpass);
		variablen.gui.updateDaten3(Ergebniss_Hochpass);
		
		Zeile++;
		//variablen.gui.updateStatus("Bearbeite Spalte links  <bearbeiten> " + Vektor_Spalte_in.werte.length+ " Zeile " +Zeile);
		if(Zeile < Vektor_Spalte_in.werte.length ){
			interner_status = bearbeiten;
		}else{
			interner_status = update;
		}		
	}
	
	public void update(){
		variablen.gui.updateStatus("Bearbeite Spalte links  <update>");
		variablen.temp2Matrix.addSpalte(this.Spalte+1, Vektor_Spalte_in);
		variablen.gui.updateRechteBild(variablen.temp2Matrix);
		Spalte++;
		Zeile = 0;
		
		if(Spalte < input1.breite){
			interner_status = ladeVekoren;
		}else{
			variablen.gui.updateStatus("Bearbeite Spalte links  <verlassen>");
			variablen.status = new Bearbeite_Zeilen2();
		}
	}
}
