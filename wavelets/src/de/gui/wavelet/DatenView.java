/*
 * Created on 17.02.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.gui.wavelet;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import de.datentypen.*;

/**
 * @author Uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DatenView extends JPanel {
	
	private boolean show = true;
	
	private Vektor_float daten;


	public void paint(Graphics g) {
		super.paint(g);
		int faktor = 1;
		if (daten != null) {
			if(Math.abs(daten.maxWert) > Math.abs(daten.minWert)){
				faktor = (int) (Math.abs(daten.maxWert) / 60 );
			}else{
				faktor = (int) (Math.abs(daten.minWert) / 60 );
			}
			if(faktor == 0){
				faktor = 1;
			}			
			int value = 0;
			for (int i = 0; i < daten.länge; i++) {
				try{
					value = (int) (daten.werte[i] * 80.0f );
				}catch(ArrayIndexOutOfBoundsException e){

				}
				g.drawLine(10 + i * 2, 60 - value, 10 + i * 2, 60);
			}
			
			g.drawLine(5,60,900,60);
			
		}
	}

	public synchronized void updateDaten(Vektor_float daten) {
		if(show){
			this.daten = daten;
			getParent().repaint();
		}
	}

	/**
	 * Creates a new <code>JPanel</code> with a double buffer and a flow layout. 
	 */
	public DatenView() {
		super();
		setBackground(Color.WHITE);
		int y = 130;
		int x = 900;
		setPreferredSize(new Dimension(x, y));
		setSize(x,y);
		
	}
	
	public void update(Graphics g){
		g.setColor(this.getBackground());
		g.fillRect(0,0,this.getWidth(), this.getHeight());
		paint(g);	
	}
	
	public void setShow(){
		if(show){
			show = false;
		}else{
			show = true;
		}
	}
}
