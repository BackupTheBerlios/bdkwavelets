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
public class Encoder_EBCOT_MQ implements ProgrammStatus , Runnable{

	private StatusDaten Daten;

	/* (non-Javadoc)
	 * @see de.programmcontrol.ProgrammStatus#start_bearbeitung(de.programmcontrol.StatusDaten)
	 */
	public StatusDaten start_bearbeitung(StatusDaten Daten) {
		this.Daten = Daten;
		
		Thread lauf = new Thread(this);
		lauf.start();
		
		return Daten;
	}

	public void run(){
		for(int i = 0; i < Daten.Komponete.length; i++){
			Daten.Komponete[i].encode_EBCOT_MQ(true);
		}	
		this.beenden(Daten);	
	}

	/* (non-Javadoc)
	 * @see de.programmcontrol.ProgrammStatus#getStatusName()
	 */
	public String getStatusName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	private StatusDaten beenden(StatusDaten Daten){
		Daten.Aktueller_Status = new Decoder_EBCOT_MQ();
		Daten.StatusConrolle.gui.bEncodenEBCOD_MQ.setEnabled(false);
		Daten.StatusConrolle.gui.bDecodenEBCOD_MQ.setEnabled(true);
		Daten.Status_ist_aktive = false;	
		return Daten;
	}

}
