/*
 * Created on 17.02.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.programmcontrol.wavelet;

import de.programmcontrol.Vorwaerts_Wavelettranformieren;
import de.programmcontrol.StatusDaten;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.awt.event.WindowListener;

import de.gui.wavelet.*;
import de.wavelet.*;

/**
 * @author Uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Forward_Wavelet_Control implements ActionListener, Runnable {
		
	/**
	 *  true wenn bis zum nächsten status gelaufen werden soll.
	 */	
	private boolean nächsterStatus = false;	
	
	/**
	 * true wenn nur einmal ein Status durchlaufen werden soll.
	 */
	private boolean einlauf = false;
	
	/**
	 * true wenn alles abgearbeitet werden soll.
	 */
	private boolean fertig = false;
	
	
	/**
	 * Der Thread in dem die sache läuft.
	 */
	Thread faden;
		
	private int aktuelle_Kachel = 0;
	
	private int aktuelle_Komponente = 0;

	private StatusDaten Daten;
	
	private Vorwaerts_Wavelettranformieren vater_controller;

	private Variablen variablen;
	
	private Status status;
	
	
	/**
	 * Alle bekannten Waveletverfahren
	 */
	private Forward_wavelet[] bekannteWavelets = { 	new Forward_NeunSieben(), 
											new Forward_HaarWavelet(), 
											new Forward_waveletHaar2(), 
											new Forward_Daublet_P4(), new Forward_Symmlet_P4(),
											new Forward_Daublet_P5(), new Forward_Symmlet_P5(),
											new Forward_Daublet_P6(), new Forward_Symmlet_P6()};


	//===================================================================================
	// Begin der Funktionen
	//======================================================================================

	public Forward_Wavelet_Control(
		StatusDaten Daten,
		Vorwaerts_Wavelettranformieren vater_controller,
		WindowListener wl) {
			
			this.Daten = Daten;
			this.vater_controller = vater_controller;
			
			
			this.variablen = new Variablen();
			this.variablen.Komponente = this.Daten.Komponete;
			this.variablen.gui = new Wavelet_Fenster(this, Daten.codeBase);
			this.variablen.gui.addWindowListener(wl);
			variablen.wavelet = wähleWavelet();
			this.variablen.minSubbandgöße = variablen.wavelet.getMinAnzahlWerte();
			
			variablen.status = new Lade_Komponente();
			
			 
			
	}
	
	public void run(){
		int[] erreichbareNr = variablen.status.nextStatusNr();
		int aktuellerNr = variablen.status.dieseStatusNr();
		boolean stop = true;
		
		while(stop){	
			/*
			 * variablen.status.bearbeiteStatus ist der Aufruf zur Bearbeitung
			 */
			variablen = variablen.status.bearbeiteStatus(variablen);
			
			/*
			 * Hier ein kompletter Status abgearbeitet.
			 */
			if(nächsterStatus){
				for(int i = 0; i < erreichbareNr.length; i++){
					if(erreichbareNr[i] == variablen.status.dieseStatusNr()){
						nächsterStatus = false;
						stop = false;
					}
				}
			}
			
			/*
			 * Es wird nur einmal ein Status durchlaufen
			 */
			if(einlauf){
				einlauf = false;
				stop = false;
			}
			
			/*
			 * Bis alle Komponenten abgearbeiten sind.
			 */
			if(fertig){
				if(variablen.status.dieseStatusNr() == Fertig.Nr ){
					stop = false;
					variablen.gui.beenden.setEnabled(true);
				}
			}

		}
	}

	public void actionPerformed(ActionEvent e){
		String cmd = e.getActionCommand();
		if(faden == null  || !faden.isAlive() ){
				
			
			
			if(cmd.equals(variablen.gui.A_DURCHLAUFE_STATUS_EINMAL)){
				duchlaufe_einmal_Status();
			}else if(cmd.equals(variablen.gui.A_BEENDE_STATUS)){
				beende_status();
			}else if(cmd.equals(variablen.gui.A_BIS_FERTIG)){
				funcfertig();
			}else if(cmd.equals(variablen.gui.A_BEENDEN)){
				beenden();
			}
		}
		
		if(cmd.equals(variablen.gui.A_ANZEIGE)){
			variablen.gui.setShow();
		}
	}
	
	private void duchlaufe_einmal_Status(){
		einlauf = true;
		run();
	}
	
	private void beende_status(){
		nächsterStatus = true;
		faden = new Thread(this);
		faden.start();
	}
	
	private void funcfertig(){
		nächsterStatus = false;
		einlauf = false;
		
		fertig = true;
		
		faden = new Thread(this);
		faden.start();
	}
	
	/**
	 * schliesst das Fenster und kehrt in die Programmcontrol zurück.
	 *
	 */
	private void beenden(){
		this.variablen.gui.setVisible(false);
		this.vater_controller.transformation_abgeschlossen();
	}
	
	
	
	
	
	private Forward_wavelet wähleWavelet(){
		Forward_wavelet BACK;
		BACK = (Forward_wavelet)JOptionPane.showInputDialog(	this.variablen.gui,
											"Wählen Sie den Wavelet-Transformer",
											"Wavelet",
											JOptionPane.QUESTION_MESSAGE,
											null,
											bekannteWavelets,
											bekannteWavelets[this.Daten.WaveletNr]);	
		this.variablen.gui.updateTitle(BACK.toString());
		this.Daten.WaveletNr = BACK.getNummer();
		
		return BACK;
	}
}
