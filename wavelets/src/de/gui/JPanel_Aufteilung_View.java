/*
 * Created on 16.02.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import de.datentypen.Komponenten_Kacheln;

/**
 * @author Uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public final class JPanel_Aufteilung_View extends JPanel {

	private Komponenten_Kacheln Kompo;
	
	private int ObenLinks = 30;
	
	private int s;

	public JPanel_Aufteilung_View(Komponenten_Kacheln komponete) {
		this.Kompo = komponete;
		this.setPreferredSize(new Dimension(800,600));
		if( 700/Kompo.Xsiz_Referenzgitter < 600/Kompo.Ysiz_Referenzgitter){
			s = 600/Kompo.Ysiz_Referenzgitter;
		}else{
			s = 700/Kompo.Xsiz_Referenzgitter;
		}
		if(s == 0){
			s = 1;
		}
		
	}
	


	public void paint(Graphics g) {
		super.paint(g);
		if( 700/Kompo.Xsiz_Referenzgitter < 600/Kompo.Ysiz_Referenzgitter){
			s = 600/Kompo.Ysiz_Referenzgitter;
		}else{
			s = 700/Kompo.Xsiz_Referenzgitter;
		}
		if(s == 0){
			s = 1;
		}
		
		//Bild zeichnen
		
		g.setColor(Color.yellow);
		g.fillRect(ObenLinks +this.Kompo.XOsiz_Offset_zum_Bild*s	,ObenLinks + this.Kompo.YOsiz_Offset_zum_Bild * s,this.Kompo.XBsiz_Bildgröße *s , this.Kompo.YBsiz_Bildgröße*s);
		
		//Umrisse Grid
		g.setColor(Color.BLACK);
		g.drawLine(ObenLinks	,ObenLinks, ObenLinks + this.Kompo.Xsiz_Referenzgitter*s , ObenLinks 	);
		g.drawLine(ObenLinks 	,ObenLinks+ this.Kompo.Ysiz_Referenzgitter*s, ObenLinks + this.Kompo.Xsiz_Referenzgitter*s, ObenLinks + this.Kompo.Ysiz_Referenzgitter*s	);

		g.drawLine(ObenLinks ,ObenLinks, ObenLinks , ObenLinks	+ this.Kompo.Ysiz_Referenzgitter*s);
		g.drawLine(ObenLinks + this.Kompo.Xsiz_Referenzgitter*s,ObenLinks, ObenLinks + this.Kompo.Xsiz_Referenzgitter*s, ObenLinks	+ this.Kompo.Ysiz_Referenzgitter*s);

		//Kacheln
		
		g.setColor(Color.RED);
		for(int y = 0; y < this.Kompo.numYtiles_anzahl_Kacheln_Y; y++){
			for(int x = 0; x < this.Kompo.numXtiles_anzahl_Kacheln_X; x++){
				
				int x1 = this.ObenLinks + this.Kompo.XTOsiz_Kachoffset*s + this.Kompo.XTsiz_Kachelgrösse*x*s;
				int y1 = this.ObenLinks + this.Kompo.YTOsiz_Kachoffset*s + this.Kompo.YTsiz_Kachelgrösse*y*s;
				
				int x2 = this.ObenLinks + this.Kompo.XTOsiz_Kachoffset*s + this.Kompo.XTsiz_Kachelgrösse*x*s + this.Kompo.XTsiz_Kachelgrösse*s;
				int y2 = this.ObenLinks + this.Kompo.YTOsiz_Kachoffset*s + this.Kompo.YTsiz_Kachelgrösse*y*s;
				
				int x3 = this.ObenLinks + this.Kompo.XTOsiz_Kachoffset*s + this.Kompo.XTsiz_Kachelgrösse*x*s ;
				int y3 = this.ObenLinks + this.Kompo.YTOsiz_Kachoffset*s + this.Kompo.YTsiz_Kachelgrösse*y*s + this.Kompo.YTsiz_Kachelgrösse*s;					
						
				int x4 = this.ObenLinks + this.Kompo.XTOsiz_Kachoffset*s + this.Kompo.XTsiz_Kachelgrösse*x*s + this.Kompo.XTsiz_Kachelgrösse*s;
				int y4 = this.ObenLinks + this.Kompo.YTOsiz_Kachoffset*s + this.Kompo.YTsiz_Kachelgrösse*y*s + this.Kompo.YTsiz_Kachelgrösse*s;
	
				
				
				g.drawLine(x1,y1,x2,y2);			
				g.drawLine(x3,y3,x4,y4);
				g.drawLine(x1,y1,x3,y3);
				g.drawLine(x2,y2,x4,y4);
				
			
			}
		}
		
		// Beschriftung
		
		for(int x = 0; x < this.Kompo.SizeX.length; x++){
			for(int y = 0; y < this.Kompo.SizeY.length; y++){
				g.drawString(this.Kompo.SizeX[x]+"/"+this.Kompo.SizeY[y] , (this.Kompo.XTOsiz_Kachoffset + 25 + x * this.Kompo.XTsiz_Kachelgrösse)* s, (this.Kompo.YTOsiz_Kachoffset + 25 + y * this.Kompo.YTsiz_Kachelgrösse)* s  );
			}	
		}

	}



}
