/*
 * Created on 14.02.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.programmcontrol;


import de.datentypen.*;
import de.gui.*;

/**
 * @author Uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Komponente_zusammensetzen implements ProgrammStatus {

	private StatusDaten Daten;

	/* (non-Javadoc)
	 * @see de.programmcontrol.ProgrammStatus#start_bearbeitung(de.programmcontrol.StatusDaten)
	 */
	public StatusDaten start_bearbeitung(StatusDaten Daten) {
		this.Daten = Daten;
		
		run();
		
		new ZeigeMatrix(this.Daten.Y_Ausgang, "Y_Komponente rekonstruiert",200);
		new ZeigeMatrix(this.Daten.Cb_Ausgang, "Cb_Komponente rekonstruiert",300);
		new ZeigeMatrix(this.Daten.Cr_Ausgang, "Cr_Komponente rekonstruiert",400);
		
		new Zeige_Qualitaet( 	this.Daten.Y_Ausgang.MSE(this.Daten.Y),
											this.Daten.Y_Ausgang.MAD(this.Daten.Y),
											this.Daten.Y_Ausgang.SAD(this.Daten.Y),
											this.Daten.Y_Ausgang.SNR(this.Daten.Y),
											this.Daten.Cb_Ausgang.MSE(this.Daten.Cb),
											this.Daten.Cb_Ausgang.MAD(this.Daten.Cb),
											this.Daten.Cb_Ausgang.SAD(this.Daten.Cb),
											this.Daten.Cb_Ausgang.SNR(this.Daten.Cb),
											this.Daten.Cr_Ausgang.MSE(this.Daten.Cr),
											this.Daten.Cr_Ausgang.MAD(this.Daten.Cr),
											this.Daten.Cr_Ausgang.SAD(this.Daten.Cr),
											this.Daten.Cr_Ausgang.SNR(this.Daten.Cr) );
		
		Matrix_float yy = this.Daten.Y_Ausgang.diff(this.Daten.Y);
		Matrix_float ycb = this.Daten.Cb_Ausgang.diff(this.Daten.Cb);
		Matrix_float ycr = this.Daten.Cr_Ausgang.diff(this.Daten.Cr);
		
		new ZeigeMatrix(yy, "Y differenz Eingang Ausgang" + yy.getmaxWert(),200);
		new ZeigeMatrix(ycb, "Cb differenz Eingang Ausgang"+ ycb.getmaxWert(),350);
		new ZeigeMatrix(ycr, "Cr differenz Eingang Ausgang"+ ycr.getmaxWert(),500);
		
		this.Daten.Aktueller_Status = new Farben_YCbCr_to_RGB();
		this.Daten.StatusConrolle.gui.bKomponente_zusammenfügen.setEnabled(false);
		this.Daten.StatusConrolle.gui.bFarben_YCbCr_RGB.setEnabled(true);
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
	
	public void run(){
		this.Daten.Y_Ausgang = zusammenfügen(Daten.Komponenten_Rückwärts[0]);
		this.Daten.Cb_Ausgang = zusammenfügen(Daten.Komponenten_Rückwärts[1]);
		this.Daten.Cr_Ausgang = zusammenfügen(Daten.Komponenten_Rückwärts[2]);
	}

	private Matrix_float zusammenfügen(Komponenten_Kacheln in){
		Matrix_float BACK = new Matrix_float(in.XBsiz_Bildgröße, in.YBsiz_Bildgröße);
		
		int KachelNr 	= 0;
		int xOffset 	= 0;
		int yOffset 	= 0;
		
		for(int y = 0 ; y < in.anzahl_Kacheln_Y(); y++){

			for(int x = 0; x < in.anzahl_Kacheln_X(); x++){
				
				for(int yk = 0; yk < in.kachel[KachelNr].höhe; yk++){
					for(int xk = 0; xk < in.kachel[KachelNr].breite; xk++){
						BACK.add(xk + xOffset+1, yk + yOffset+1, in.kachel[KachelNr].get(xk+1,yk+1) );
					}
					
				}
				xOffset += in.kachel[KachelNr].breite;
				KachelNr++;
			}
			xOffset = 0;
			yOffset += in.kachel[KachelNr-1].höhe;
		}
		
		return BACK;
	}

}
