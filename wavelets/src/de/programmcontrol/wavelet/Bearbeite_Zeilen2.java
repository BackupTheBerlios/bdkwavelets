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
public class Bearbeite_Zeilen2 implements Status {

	public static final int Nr = 8;

	/**
	 * Enthält die Nummer der folgenden Statuse
	 */
	public static final int[] folgeNr = {Lade_Subband.Nr};

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
		input1 = variablen.temp1Matrix;
		input2 = variablen.temp2Matrix;
		
		interner_status 	= ladeVekoren;
		variablen.status = this;		
	}
	
	private void ladeVekoren(){
		variablen.gui.updateStatus("Bearbeite Zeile  <ladeVekoren>");
		Verdünnter_Tiefpass = input1.getReihe(this.Zeile + 1);
		Verdünnter_Hochpass = input2.getReihe(this.Zeile + 1);
		
		Ergebniss_Tiefpass = new Vektor_float(variablen.aktuelle_Matrix.breite);
		Ergebniss_Hochpass = new Vektor_float(variablen.aktuelle_Matrix.breite);
		
		
		Vektor_Spalte_in = new Vektor_float(variablen.aktuelle_Matrix.breite);
		
		interner_status 	= bearbeiten; 
		variablen.status 	= this;
			
	}
	
	private void bearbeiten(){
		//variablen.gui.updateStatus("Bearbeite Zeile <bearbeiten>");
		boolean gerade;
		if(Ergebniss_Tiefpass.werte.length % 2 == 0){
			gerade = true;
		}else{
			gerade = false;
		}
		
		Ergebniss_Tiefpass.add(variablen.invers_wavelet.FilterA(Verdünnter_Tiefpass, Spalte,gerade),Spalte+1);
		Ergebniss_Hochpass.add(variablen.invers_wavelet.FilterD(Verdünnter_Hochpass, Spalte,gerade),Spalte+1);
		
		Vektor_Spalte_in.werte[Spalte] = Ergebniss_Tiefpass.werte[Spalte] + Ergebniss_Hochpass.werte[Spalte];
		
		variablen.gui.updateDaten1(Vektor_Spalte_in);
		variablen.gui.updateDaten2(Ergebniss_Tiefpass);
		variablen.gui.updateDaten3(Ergebniss_Hochpass);
		
		Spalte++;
		//variablen.gui.updateStatus("Bearbeite Zeile  <bearbeiten> " + Vektor_Spalte_in.werte.length+ " Zeile " +Zeile);
		if(Spalte < Vektor_Spalte_in.werte.length ){
			interner_status = bearbeiten;
		}else{
			interner_status = update;
		}		
	}
	
	public void update(){
		//variablen.gui.updateStatus("Bearbeite Zeile  <update>");
		variablen.aktuelle_Matrix.addReihe(this.Zeile+1, Vektor_Spalte_in);
		variablen.gui.updateEingangsBild(variablen.aktuelle_Matrix);
		Zeile++;
		Spalte = 0;
		
		if(Zeile < input1.höhe){
			interner_status = ladeVekoren;
		}else{
			variablen.temp_LL = variablen.aktuelle_Matrix;
			variablen.gui.updateStatus("Bearbeite Zeile  <verlassen>");
			variablen.status = new Lade_Subband();
		}
	}
}
