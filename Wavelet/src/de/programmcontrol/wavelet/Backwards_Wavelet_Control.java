/*
 * Created on 28.02.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.programmcontrol.wavelet;

import de.programmcontrol.*;
import de.programmcontrol.StatusDaten;
import de.wavelet.*;
import de.wavelet.Invers_NeunSieben;
import de.gui.wavelet.Wavelet_Fenster;
import java.awt.event.WindowListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

/**
 * @author Uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public final class Backwards_Wavelet_Control implements ActionListener, Runnable {

	/**
	 * Daten aus Programmcontrol.
	 */
	private StatusDaten Daten;

	/**
	 * 
	 */
	private Rueckwaerts_Wavelettransformieren vater_controller;

	/**
	 * 
	 */
	private boolean nächsterStatus = false;
	
	/**
	 * 
	 */
	private boolean fertig = false;

	/**
	 * Um Daten von Status zu Status austauschen zu können
	 */
	private Variablen variablen;
	
	/**
	 * 
	 */
	private Thread faden;
	
	/**
	 * 
	 */
	private boolean einlauf = false;
	
	/**
	 * Alle bekannte Waveletverfahren zur Rücktransformation
	 */
	private	Invers_wavelet[] bekannteWavelets = { 	new Invers_NeunSieben(), 
													new Invers_HaarWavelet(), 
													new Invers_HaarWavelet2(), 
													new Invers_Daublet_P4(), new Invers_Symmlet_P4(),
													new Invers_Daublet_P5(), new Invers_Symmlet_P5(),
													new Invers_Daublet_P6(), new Invers_Symmlet_P6()};

	
	//=========================================================================
	// Funktionen
	//========================================================================
	

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		boolean stop = true;
		int[] erreichbareNr = variablen.status.nextStatusNr();
		
		while(stop){
			this.variablen = this.variablen.status.bearbeiteStatus(variablen);
		
			if(this.einlauf){
				stop = false;
			}
			
			if(nächsterStatus){
				for(int i = 0; i < erreichbareNr.length; i++){
					if(erreichbareNr[i] == variablen.status.dieseStatusNr()){
						nächsterStatus = false;
						stop = false;
						
					}
				}
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
		
		einlauf = false;

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
		fertig = true;
		faden = new Thread(this);
		faden.start();	
	}
	
	private void beenden(){
		variablen.gui.setVisible(false);
		this.vater_controller.transformation_abgeschlossen();
				
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
			System.out.println("Here I am na na nanana");
			this.variablen.gui.setShow();
		}
	}

	/**
	 * 
	 * @param Daten enthält den Baum an Subbändern
	 * @param Vater_Control 
	 */
	public Backwards_Wavelet_Control(
					StatusDaten Daten,
					Rueckwaerts_Wavelettransformieren Vater_Control,
					WindowListener wl) {
		
		this.Daten = Daten;
		this.vater_controller = Vater_Control;
		this.variablen = new Variablen();
		
		this.variablen.status = new Lade_Komponente2();
		this.variablen.Komponente = Daten.Komponenten_Rückwärts;
		this.variablen.gui = new Wavelet_Fenster(this, this.Daten.codeBase );
		this.variablen.gui.addWindowListener(wl);
		this.variablen.invers_wavelet = wähleWavelet();

	}

	private Invers_wavelet wähleWavelet(){

		return (Invers_wavelet) JOptionPane.showInputDialog(	this.variablen.gui,
																"Wählen Sie den Wavelet-Transformer",
																"Wavelet",
																JOptionPane.QUESTION_MESSAGE,
																null,
																bekannteWavelets,
																bekannteWavelets[this.Daten.WaveletNr]);	
	}

}
