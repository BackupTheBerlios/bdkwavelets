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
public class Farben_RGB_to_YCbCr implements ProgrammStatus {

	private StatusDaten Daten;

	private final String StatusName = "Farben_RGB_to_YCbCr";

	/* (non-Javadoc)
	 * @see de.programmcontrol.ProgrammStatus#start_bearbeitung(de.programmcontrol.StatusDaten)
	 */
	public StatusDaten start_bearbeitung(StatusDaten Daten) {
		this.Daten = Daten;
		this.Daten = Farbtools.RGBtoYCbCr(this.Daten);
		ZeigeMatrix a =new ZeigeMatrix(this.Daten.Y, "Y Matrix",0);
		ZeigeMatrix b =new ZeigeMatrix(this.Daten.Cb, "Cb Matrix",250);
		ZeigeMatrix c =new ZeigeMatrix(this.Daten.Cr, "Cr Matrix",500);
	
		Daten.Aktueller_Status = new Komponente_teilen();
		Daten.StatusConrolle.gui.bRGB_YCbCr.setEnabled(false);
		Daten.StatusConrolle.gui.bKomponeten_teilen.setEnabled(true);
		Daten.Status_ist_aktive = false;		
		
		return this.Daten;
	}

	/* (non-Javadoc)
	 * @see de.programmcontrol.ProgrammStatus#getStatusName()
	 */
	public String getStatusName() {
		
		return StatusName;
	}

}
