/*
 * Created on 14.02.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.programmcontrol;

import de.datentypen.*;
/**
 * @author Uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Rueck_Quantisierer implements ProgrammStatus {

	/**
	 * Daten-Pointer wird hier abgelegt.
	 */
	private StatusDaten Daten;	




	public StatusDaten start_bearbeitung(StatusDaten Daten) {
		this.Daten = Daten;
		int i = 0;
		this.Daten.Komponete[i].dequantisiere(new Skalar_Quantisierer(this.Daten.LuminanzQuantBits));
		i++;
		for(; i < Daten.Komponete.length; i++){
			this.Daten.Komponete[i].dequantisiere(new Skalar_Quantisierer(this.Daten.ChrominanzQuantBits));
		}		
		
		this.Daten.Komponenten_Rückwärts = this.Daten.Komponete;
		
		beenden();
		return this.Daten;
	}

	/* (non-Javadoc)
	 * @see de.programmcontrol.ProgrammStatus#getStatusName()
	 */
	public String getStatusName() {

		return null;
	}

	private void beenden(){
		
		Daten.Aktueller_Status = new Rueckwaerts_Wavelettransformieren();
		Daten.StatusConrolle.gui.bInversQuantisieren.setEnabled(false);
		Daten.StatusConrolle.gui.bInverseWavelettransformation.setEnabled(true);
		Daten.Status_ist_aktive = false;		
	}
}
