/*
 * Created on 14.02.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.programmcontrol;

import de.programmcontrol.wavelet.Backwards_Wavelet_Control;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
/**
 * @author Uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Rueckwaerts_Wavelettransformieren implements ProgrammStatus, WindowListener {

	private Backwards_Wavelet_Control Controll;
	
	private StatusDaten Daten;

	/* (non-Javadoc)
	 * @see de.programmcontrol.ProgrammStatus#start_bearbeitung(de.programmcontrol.StatusDaten)
	 */
	public StatusDaten start_bearbeitung(StatusDaten Daten) {
		this.Daten = Daten;
		this.Daten.Komponenten_Rückwärts = this.Daten.Komponete;
		Controll = new Backwards_Wavelet_Control(this.Daten, this, this);
		
		return this.Daten;
	}

	/* (non-Javadoc)
	 * @see de.programmcontrol.ProgrammStatus#getStatusName()
	 */
	public String getStatusName() {
		// TODO Auto-generated method stub
		return null;
	}

	public void transformation_abgeschlossen(){
		Daten.Aktueller_Status = new Komponente_zusammensetzen();
		Daten.StatusConrolle.gui.bInverseWavelettransformation.setEnabled(false);
		Daten.StatusConrolle.gui.bKomponente_zusammenfügen.setEnabled(true);
		Daten.Status_ist_aktive = false;
	}
	
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
