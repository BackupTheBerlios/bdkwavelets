/*
 * Created on 18.05.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.programmcontrol;

/**
 * @author uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Decoder_EBCOT_MQ implements ProgrammStatus {

	private StatusDaten Daten;
	
	/* (non-Javadoc)
	 * @see de.programmcontrol.ProgrammStatus#start_bearbeitung(de.programmcontrol.StatusDaten)
	 */
	public StatusDaten start_bearbeitung(StatusDaten Daten) {
		this.Daten = Daten;
		
		run();
		
		return null;
	}
	
	public void run(){
		
	}

	private StatusDaten beenden(StatusDaten Daten){
		Daten.Aktueller_Status = new Rueck_Quantisierer();
		Daten.StatusConrolle.gui.bDecodenEBCOD_MQ.setEnabled(false);
		Daten.StatusConrolle.gui.bInversQuantisieren.setEnabled(true);
		Daten.Status_ist_aktive = false;	
		return Daten;
	}




	/* (non-Javadoc)
	 * @see de.programmcontrol.ProgrammStatus#getStatusName()
	 */
	public String getStatusName() {
		// TODO Auto-generated method stub
		return null;
	}


}
