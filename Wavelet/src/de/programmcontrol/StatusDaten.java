/*
 * Created on 14.02.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.programmcontrol;

import java.awt.image.BufferedImage;
import de.datentypen.Komponenten_Kacheln;
import de.datentypen.Matrix_float;
import de.datentypen.Bit_selection;
import java.net.URL;
import de.gui.AppletStart;
/**
 * @author Uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class StatusDaten {

	boolean Status_ist_aktive;

	StatusControl StatusConrolle;

	ProgrammStatus Aktueller_Status;

	public BufferedImage EingangsBild;

	public Matrix_float Y;

	public Matrix_float Cb;

	public Matrix_float Cr;

	public Matrix_float Ausgangs_Y;

	public Matrix_float Ausgangs_Cb;

	public Matrix_float Ausgangs_Cr;

	public BufferedImage AusgangsBild;

	public Komponenten_Kacheln[] Komponete;

	public URL codeBase;

	public Komponenten_Kacheln[] Komponenten_Rückwärts;

	public Matrix_float Y_Ausgang;
	
	public Matrix_float Cb_Ausgang;
	
	public Matrix_float Cr_Ausgang;

	public AppletStart gui;
	
	public Bit_selection LuminanzQuantBits;
	
	public Bit_selection ChrominanzQuantBits;
	
	public int WaveletNr = 0;

}
