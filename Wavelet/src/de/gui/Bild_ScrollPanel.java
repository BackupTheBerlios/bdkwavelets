/*
 * Created on 16.02.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.gui;

import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
/**
 * @author Uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public final class Bild_ScrollPanel extends JPanel {

	private Bild_Panel bild_panel;

	public Bild_ScrollPanel(BufferedImage Bild) {
		bild_panel = new Bild_Panel(Bild);
		JScrollPane  scroll = new JScrollPane(bild_panel);
		setLayout(new GridLayout(1,1));
		add(scroll);
		scroll.repaint();
		this.repaint();
	}

}
