/*
 * Created on 15.02.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * @author Uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public final class Bild_Panel extends JPanel {

	private BufferedImage Bild;

	public Bild_Panel(BufferedImage Bild) {
		this.Bild = Bild;
		this.setPreferredSize(new Dimension(this.Bild.getWidth(), this.Bild.getHeight()));
		this.setMinimumSize(new Dimension(this.Bild.getWidth(), this.Bild.getHeight()));
		this.setSize(new Dimension(this.Bild.getWidth(), this.Bild.getHeight()));
		//System.out.println("Breite = " + this.Bild.getWidth() + " Höhe =  " + this.Bild.getHeight());
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		if(this.Bild != null){
			g.drawImage(this.Bild,1,1, this);
		}
	}
	public synchronized void update(Graphics g) {
		paint(g);
	}



}
