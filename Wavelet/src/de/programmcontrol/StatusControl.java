/*
 * Created on 14.02.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.programmcontrol;

import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import de.gui.AppletStart;
/**
 * @author Uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class StatusControl implements ActionListener {


	
	protected AppletStart gui;

	
	private StatusDaten statusDaten;


	private void bild_laden(){
		if(!this.statusDaten.Status_ist_aktive){
			this.statusDaten.Status_ist_aktive = true;
			this.statusDaten.Aktueller_Status = new Bild_laden();
			this.statusDaten = this.statusDaten.Aktueller_Status.start_bearbeitung(this.statusDaten);
		}
	}

	private void RGB_to_YCbCr(){
		if(!this.statusDaten.Status_ist_aktive){
			this.statusDaten.Status_ist_aktive = true;
			this.statusDaten =  this.statusDaten.Aktueller_Status.start_bearbeitung(this.statusDaten);
		}
	}

	private void YCbCr_to_RGB(){
		if(!this.statusDaten.Status_ist_aktive){
			this.statusDaten.Status_ist_aktive = true;
			this.statusDaten =  this.statusDaten.Aktueller_Status.start_bearbeitung(this.statusDaten);
		}
	}
	
	private void komponete_teilen(){
		if(!this.statusDaten.Status_ist_aktive){
			this.statusDaten.Status_ist_aktive = true;
			this.statusDaten =  this.statusDaten.Aktueller_Status.start_bearbeitung(this.statusDaten);
		}	
	}
	
	private void wavelet_transformation(){
		if(!this.statusDaten.Status_ist_aktive){
			this.statusDaten.Status_ist_aktive = true;
			this.statusDaten =  this.statusDaten.Aktueller_Status.start_bearbeitung(this.statusDaten);
		}	
	}
	
	private void wavelet_inverse_transformation(){
		if(!this.statusDaten.Status_ist_aktive){
			this.statusDaten.Status_ist_aktive = true;
			this.statusDaten =  this.statusDaten.Aktueller_Status.start_bearbeitung(this.statusDaten);
		}	
	}	
	
	private void komponete_zusammenfügen(){
		if(!this.statusDaten.Status_ist_aktive){
			this.statusDaten.Status_ist_aktive = true;
			this.statusDaten =  this.statusDaten.Aktueller_Status.start_bearbeitung(this.statusDaten);
		}	
	}
	
	private void quantisierne(){
		if(!this.statusDaten.Status_ist_aktive){
			this.statusDaten.Status_ist_aktive = true;
			this.statusDaten =  this.statusDaten.Aktueller_Status.start_bearbeitung(this.statusDaten);
		}
	}	
	
	private void inversquantisierne(){
		if(!this.statusDaten.Status_ist_aktive){
			this.statusDaten.Status_ist_aktive = true;
			this.statusDaten =  this.statusDaten.Aktueller_Status.start_bearbeitung(this.statusDaten);
		}
	}
	
	private void encode_EBCOT_MQ(){
		if(!this.statusDaten.Status_ist_aktive){
			this.statusDaten.Status_ist_aktive = true;
			this.statusDaten =  this.statusDaten.Aktueller_Status.start_bearbeitung(this.statusDaten);
		}
	}

	public void actionPerformed(ActionEvent event){
		String cmd = event.getActionCommand();
		
		if(cmd.equals(gui.A_BILDLADEN)){
			bild_laden();
		}else if(cmd.equals(gui.A_FARBEN_RGB_YCBCR)){
			RGB_to_YCbCr();
		}else if(cmd.equals(gui.A_FARBEN_YCBCR_RGB)){
			YCbCr_to_RGB();
		}else if(cmd.equals(gui.A_KOMPONETETEILEN)){
			komponete_teilen();
		}else if(cmd.equals(gui.A_WAVELETTRANSFORMIEREN)){
			wavelet_transformation();
		}else if(cmd.equals(gui.A_INVERSWAVELETTRANSFORMIEREN)){
			wavelet_inverse_transformation();
		}else if(cmd.equals(gui.A_KOMPONETEZUSAMMENFÜGEN)){
			komponete_zusammenfügen();
		}else if(cmd.equals(gui.A_QUANTISIEREN)){
			quantisierne();
		}else if(cmd.equals(gui.A_INVERSQUANTIESIEREN)){
			inversquantisierne();
		}else if(cmd.equals(gui.A_EncodenEBCOD_MQ)){
			encode_EBCOT_MQ();
		}
		
	}
	
	public StatusControl(AppletStart gui) {
		this.gui = gui;
		this.statusDaten = new StatusDaten();
		this.statusDaten.codeBase = this.gui.getCodeBase(); 
		this.statusDaten.Status_ist_aktive = false;
		this.statusDaten.StatusConrolle = this;
		this.statusDaten.Aktueller_Status = null;
		this.statusDaten.gui = gui;

	}

	public static void errorAusgabe(String in) {
		JOptionPane ErrorPane = new JOptionPane();
		//ErrorPane.showMessageDialog(null , in, "Error", JOptionPane.WARNING_MESSAGE);
	}

}
