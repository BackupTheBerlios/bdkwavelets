/*
 * Created on 14.02.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.programmcontrol;

import de.tools.Farbtools;
import de.gui.*;
/**
 * @author Uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Farben_YCbCr_to_RGB implements ProgrammStatus {

	private StatusDaten Daten;

	/* (non-Javadoc)
	 * @see de.programmcontrol.ProgrammStatus#start_bearbeitung(de.programmcontrol.StatusDaten)
	 */
	public StatusDaten start_bearbeitung(StatusDaten Daten) {
		this.Daten = Daten;
		this.Daten = Farbtools.YCbCrtoRGB(this.Daten);
	
		if(this.Daten.AusgangsBild != null){
			ZeigeBild Z = new ZeigeBild(this.Daten.AusgangsBild, "AusgangsBild");
		}else{
			System.out.println("Kein Bild");
		}
		
		
		this.Daten.Aktueller_Status = new BildSpeichern();
		this.Daten.StatusConrolle.gui.bFarben_YCbCr_RGB.setEnabled(false);
		this.Daten.StatusConrolle.gui.bBild_speichern.setEnabled(true);
		this.Daten.Status_ist_aktive = false;
		
		return this.Daten;
	}

	/* (non-Javadoc)
	 * @see de.programmcontrol.ProgrammStatus#getStatusName()
	 */
	public String getStatusName() {
		// TODO Auto-generated method stub
		return null;
	}

}
