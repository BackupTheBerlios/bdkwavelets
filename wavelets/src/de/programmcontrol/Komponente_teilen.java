/*
 * Created on 14.02.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.programmcontrol;

import de.datentypen.*;
import de.gui.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
/**
 * @author Uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Komponente_teilen implements ProgrammStatus , ActionListener, WindowListener{
	
	private StatusDaten Daten;
	
	private Komponenten_Kacheln aufteilung[] = new Komponenten_Kacheln[3];
	
	private Kacheln_Editor editor;
	
	private ZeigeMatrix zpo;

	/* (non-Javadoc)
	 * @see de.programmcontrol.ProgrammStatus#start_bearbeitung(de.programmcontrol.StatusDaten)
	 */
	public StatusDaten start_bearbeitung(StatusDaten Daten) {
		this.Daten = Daten;
		aufteilung[0] = new Komponenten_Kacheln(this.Daten.Y);
		aufteilung[0].berechneFelder();		
		editor = new Kacheln_Editor(aufteilung[0], this, this);
		return this.Daten;
	}

	/* (non-Javadoc)
	 * @see de.programmcontrol.ProgrammStatus#getStatusName()
	 */
	public String getStatusName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void actionPerformed(ActionEvent e){
		String cmd = e.getActionCommand();
		System.out.println(cmd);
		if(cmd.equals("Ok")){
			ok();
		}
		
	}
	
	private void ok(){
		editor.setVisible(false);
		
		aufteilung[1] =  new Komponenten_Kacheln(aufteilung[0]);
		aufteilung[2] =  new Komponenten_Kacheln(aufteilung[0]);

		aufteilung[0].kachel = new Kachel[(aufteilung[0].SizeX.length * aufteilung[0].SizeY.length)];
		aufteilung[1].kachel = new Kachel[(aufteilung[0].SizeX.length * aufteilung[0].SizeY.length)];
		aufteilung[2].kachel = new Kachel[(aufteilung[0].SizeX.length * aufteilung[0].SizeY.length)];
		

		
		int startx = 0;
		int starty = 0;
		int stopx = 0;
		int stopy = 0;
		Kachel temp;
		int nr = 0;
		
		for(int y = 0; y < aufteilung[0].SizeY.length; y++){
			stopy += aufteilung[0].SizeY[y];
			for(int x = 0; x < aufteilung[0].SizeX.length; x++){
				stopx +=  aufteilung[0].SizeX[x];
				temp = new Kachel(aufteilung[0].SizeX[x],aufteilung[0].SizeY[y],nr);

				// Komponete Y +++++++++++++++++++++++++++++++++
				temp = new Kachel(aufteilung[0].SizeX[x],aufteilung[0].SizeY[y],nr);
				for(int y1 = starty; y1 < stopy; y1++){
					for(int x1 = startx; x1 < stopx; x1++){
						temp.add( x1+1-startx,y1+1-starty, this.Daten.Y.get(x1 +1,  y1 + 1));
					}
				}
				aufteilung[0].kachel[nr] = temp;
				
//				Komponete Cb +++++++++++++++++++++++++++++++++
				temp = new Kachel(aufteilung[0].SizeX[x],aufteilung[0].SizeY[y],nr);
				for(int y1 = starty; y1 < stopy; y1++){
					for(int x1 = startx; x1 < stopx; x1++){
						temp.add( x1+1-startx,y1+1-starty, this.Daten.Cb.get(x1 +1,  y1 + 1));
					}
				}	
				aufteilung[1].kachel[nr] = temp;
								
//				Komponete Y +++++++++++++++++++++++++++++++++
				temp = new Kachel(aufteilung[0].SizeX[x],aufteilung[0].SizeY[y],nr);
				for(int y1 = starty; y1 < stopy; y1++){
						for(int x1 = startx; x1 < stopx; x1++){
							temp.add( x1+1-startx,y1+1-starty, this.Daten.Cr.get(x1 +1,  y1 + 1));
						}
					}
				aufteilung[2].kachel[nr] = temp;						
				
				startx += aufteilung[0].SizeX[x];
				nr++;
				
			}
			startx = 0;
			stopx = 0;
			starty += aufteilung[0].SizeY[y];
		}
		for(int i = 0; i  < aufteilung[2].kachel.length; i++ ){
			zpo = new ZeigeMatrix(aufteilung[2].kachel[i],"Kachel Nr. "+ aufteilung[2].kachel[i].Nr ,i*40);
		}
		
		Daten.Komponete = aufteilung;
		Daten.Aktueller_Status = new Vorwaerts_Wavelettranformieren();
		Daten.StatusConrolle.gui.bKomponeten_teilen.setEnabled(false);
		Daten.StatusConrolle.gui.bWavelettransformation.setEnabled(true);
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
