/*
 * Created on 16.02.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.gui;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;

import de.datentypen.Matrix_float;

/**
 * @author Uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public final class ZeigeMatrix extends JFrame {


	public ZeigeMatrix(Matrix_float Matrix, String Name,int PosX) {
		super(Name);
		Container con = this.getContentPane();
		con.setLayout(new GridLayout(1,1));
		con.add(new Matrix_ScrollPanel(Matrix));
		
		this.setLocation(PosX, 300);
		this.setSize(300,300);
		this.setVisible(true);
	}

}
