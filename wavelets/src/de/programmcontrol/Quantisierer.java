/*
 * Created on 14.02.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.programmcontrol;

import de.datentypen.*;
import javax.swing.JOptionPane;
/**
 * @author Uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Quantisierer implements ProgrammStatus {

	StatusDaten Daten;
  
	Bit_selection[] anzahl_bits = {	new Bit_selection(7,0),
									new Bit_selection(7,1),
									new Bit_selection(7,2),
									new Bit_selection(7,3),
									new Bit_selection(9,2),
									new Bit_selection(9,3),
									new Bit_selection(9,4),
									new Bit_selection(9,5),
									new Bit_selection(11,4),
									new Bit_selection(11,5),
									new Bit_selection(11,6),
									new Bit_selection(11,7)};
									

	public StatusDaten start_bearbeitung(StatusDaten Daten) {
		
		this.Daten = Daten;
		
		int i = 0;
		this.Daten.LuminanzQuantBits = anzahl_Bits_Luminanz();
		Daten.Komponete[i].quantisiere(new Skalar_Quantisierer(this.Daten.LuminanzQuantBits));

		this.Daten.ChrominanzQuantBits = anzahl_Bits_Chrominanz();
		for(i = 1; i < Daten.Komponete.length; i++){
			Daten.Komponete[i].quantisiere(new Skalar_Quantisierer(this.Daten.ChrominanzQuantBits));
		}
		
		beenden(Daten);
		return Daten;
	}

	/* (non-Javadoc)
	 * @see de.programmcontrol.ProgrammStatus#getStatusName()
	 */
	public String getStatusName() {
		return null;
	}
	
	private void beenden(StatusDaten Daten){
		Daten.Aktueller_Status = new Rueck_Quantisierer();
		Daten.StatusConrolle.gui.bQuantisieren.setEnabled(false);
		Daten.StatusConrolle.gui.bInversQuantisieren.setEnabled(true);
		Daten.Status_ist_aktive = false;		
	}
	
	/*
	private void beenden(StatusDaten Daten){
		Daten.Aktueller_Status = new Encoder_EBCOT_MQ();
		Daten.StatusConrolle.gui.bQuantisieren.setEnabled(false);
		Daten.StatusConrolle.gui.bEncodenEBCOD_MQ.setEnabled(true);
		Daten.Status_ist_aktive = false;		
	}*/
	
	private Bit_selection anzahl_Bits_Chrominanz(){
		Bit_selection BACK;
		int counter = 0;
		
		while( ( BACK = (Bit_selection) JOptionPane.showInputDialog(Daten.gui, "Anzahl der Bits für die Chrominaz", "Auswahl", JOptionPane.QUESTION_MESSAGE, null, anzahl_bits, anzahl_bits[4] )) == null && counter < 4){
			counter++;
		}																
		return BACK;								
		
	}

	private Bit_selection anzahl_Bits_Luminanz(){
		Bit_selection BACK;
		int counter = 0;
		
		while( ( BACK = (Bit_selection) JOptionPane.showInputDialog(Daten.gui, "Anzahl der Bits für die Luminanz", "Auswahl", JOptionPane.QUESTION_MESSAGE, null, anzahl_bits, anzahl_bits[4] )) == null && counter < 4){
			counter++;
		}																
		return BACK;								
		
	}
}
