/*
 * Created on 14.02.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.programmcontrol;

import de.programmcontrol.wavelet.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
/**
 * @author Uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Vorwaerts_Wavelettranformieren implements ProgrammStatus, WindowListener {

	/**
	 * Kontrolliert die Wavelet-Transformatiorn
	 */
	private Forward_Wavelet_Control Controller;
	
	/**
	 * Daten-Pointer wird hier abgelegt.
	 */
	private StatusDaten Daten;
	
	
	/* (non-Javadoc)
	 * @see de.programmcontrol.ProgrammStatus#start_bearbeitung(de.programmcontrol.StatusDaten)
	 */
	public StatusDaten start_bearbeitung(StatusDaten Daten) {
		this.Daten = Daten;
		Controller = new Forward_Wavelet_Control(Daten , this, this);
		return this.Daten;
	}

	/* (non-Javadoc)
	 * @see de.programmcontrol.ProgrammStatus#getStatusName()
	 */
	public String getStatusName() {
		return null;
	}
	
	public void transformation_abgeschlossen(){
		Daten.Aktueller_Status = new Quantisierer();
		Daten.StatusConrolle.gui.bWavelettransformation.setEnabled(false);
		Daten.StatusConrolle.gui.bQuantisieren.setEnabled(true);
		Daten.Status_ist_aktive = false;		
	}
/*
	public void transformation_abgeschlossen(){
			Daten.Aktueller_Status = new Rueckwaerts_Wavelettransformieren();
			Daten.StatusConrolle.gui.bWavelettransformation.setEnabled(false);
			Daten.StatusConrolle.gui.bInverseWavelettransformation.setEnabled(true);
			Daten.Status_ist_aktive = false;		
		}
*/


	private void abbruch(){
		Daten.Status_ist_aktive = false;
	}
	
		
	public void 	windowActivated(WindowEvent e){
	}
			
	public void 	windowClosed(WindowEvent e){

		System.out.println("windowClosed");
	}
	
	public void 	windowClosing(WindowEvent e){
		abbruch();
		System.out.println("windowClosing");
	}
	
	public void 	windowDeactivated(WindowEvent e){
	}
	
	public void 	windowDeiconified(WindowEvent e){
	}
	
	public void 	windowIconified(WindowEvent e){
	}
	
	
	public void 	windowOpened(WindowEvent e){
	}	
	
}
