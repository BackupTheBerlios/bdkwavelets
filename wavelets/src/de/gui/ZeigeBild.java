/*
 * Created on 14.02.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.gui;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
/**
 * @author Uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ZeigeBild extends JFrame {

	private Bild_Panel BildPane;

	private Container container;
	

	public ZeigeBild(BufferedImage Bild, String Name) {
		super(Name);
		
		this.container = this.getContentPane();
		this.container.setLayout(new GridLayout(1,1));
		this.container.add(new Bild_ScrollPanel(Bild));
		this.setLocation(500, 300);
		this.setSize(300,300);
		this.setVisible(true);
		

	}
	
	

}
