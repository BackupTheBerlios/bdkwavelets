/*
 * Created on 16.02.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.gui;

import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import de.datentypen.Komponenten_Kacheln;
/**
 * @author Uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Kacheln_Editor extends JFrame {
	
	private Container container;
	
	private JSplitPane sPane;
	
	private JPanel_Aufteilung_View aufteilungsView;

	public Kacheln_Editor(Komponenten_Kacheln Komponente, ActionListener al, WindowListener wl) {
		super("Tiles Editor");
		
		this.addWindowListener(wl);
		
		aufteilungsView = new JPanel_Aufteilung_View(Komponente);
		
		sPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		sPane.setRightComponent(new JPanel_Aufteilung_Werte_View(Komponente,aufteilungsView,al));
		sPane.setLeftComponent(aufteilungsView);
		
		
		this.getContentPane().add(sPane);
		//this.container = this.getContentPane().add(sPane);
		//this.container.setLayout(new GridLayout(1,1));
		//this.add(sPane);
		this.setSize(1000,700);
		this.setVisible(true);
	}

	private void create_Eingabe() {
	}

}
