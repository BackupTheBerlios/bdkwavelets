/*
 * Created on 14.02.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.gui;

import java.applet.AppletContext;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;


import javax.swing.JApplet;
import javax.swing.JButton;

import de.programmcontrol.StatusControl;

/**
 * @author Uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AppletStart extends JApplet {

	private StatusControl contorller;
	
	private Container contentPane;	
	
	private AppletContext appletcontext;
	
	private String[] browsers = {"Netscape", "Microsoft Internet Explorer"};
	
	//+++++++++++++++++++++++++++++++++++++
	// Button Variablen
	//++++++++++++++++++++++++++++++++++++++

	public JButton bLade_Bild;

	public JButton bRGB_YCbCr;
	
	public JButton bKomponeten_teilen;
	
	public JButton bWavelettransformation;
	
	public JButton bQuantisieren;
	
	public JButton bInversQuantisieren;

	public JButton bInverseWavelettransformation;
	
	public JButton bKomponente_zusammenfügen;
	
	public JButton bFarben_YCbCr_RGB;
	
	public JButton bBild_speichern;
	
	public JButton bEncodenEBCOD_MQ;
	
	public JButton bDecodenEBCOD_MQ;
	
	//+++++++++++++++++++++++++++++++++++++++++++++
	// Definition der ActionCommandos
	// ++++++++++++++++++++++++++++++++++++++++++++
	
	public final String A_BILDLADEN = "1";
	
	public final String A_FARBEN_RGB_YCBCR = "2";
	
	public final String A_BILDSPEICHERN = "3";
	
	public final String A_FARBEN_YCBCR_RGB ="4";
	
	public final String A_QUANTISIEREN = "5";
	
	public final String A_INVERSQUANTIESIEREN = "6";
	
	public final String A_WAVELETTRANSFORMIEREN = "7";
	
	public final String A_INVERSWAVELETTRANSFORMIEREN = "8";
	
	public final String A_KOMPONETETEILEN = "9";
	
	public final String A_KOMPONETEZUSAMMENFÜGEN = "10";
	 
	public final String A_EncodenEBCOD_MQ = "11";
	
	public final String A_DecodenEBCOD_MQ = "12";
	 
	//+++++++++++++++++++++++++++++++++++++++++++++
	// Definition der Button Namen
	// ++++++++++++++++++++++++++++++++++++++++++++
	
	public final String N_BILDLADEN = "Bild laden";
	
	public final String N_FARBEN_RGB_YCBCR = "Farben RGB in YCbCr";
	
	public final String N_BILDSPEICHERN = "Bild speichern";
	
	public final String N_FARBEN_YCBCR_RGB ="Farben YCbCr in RGB";
	
	public final String N_QUANTISIEREN = "Quantisieren";
	
	public final String N_INVERSQUANTIESIEREN = "Inverses Quantisieren";
	
	public final String N_WAVELETTRANSFORMIEREN = "Forward Wavelet-Transformation";
	
	public final String N_INVERSWAVELETTRANSFORMIEREN = "Inverse Wavelet-Transformation";
	
	public final String N_KOMPONETETEILEN = "Bild teilen";
	
	public final String N_KOMPONETEZUSAMMENFÜGEN = "Bild zusammenfügen";
	
	public final String N_ENCODE_EBCOD_MQ = "Encode EBCOD und MQ-Coder";
	
	public final String N_DECODE_EBCOD_MQ = "Decode EBCOD und MQ-Coder";
	

	public void init(){
		

		
		contentPane = getContentPane();
		contorller = new StatusControl(this);
		contentPane.add(creatContainer());
		contentPane.repaint();
			
	}	

	private Container creatContainer() {
		Dimension grösse = new Dimension(250, 35);
		//+++++++++++++++++++++++++++++++++++++++++++++
		// Button einrichten
		// ++++++++++++++++++++++++++++++++++++++++++++
		
		this.bLade_Bild = new JButton(this.N_BILDLADEN);
		this.bLade_Bild.setActionCommand(this.A_BILDLADEN);
		this.bLade_Bild.setEnabled(true);
		this.bLade_Bild.addActionListener(contorller);
		this.bLade_Bild.setPreferredSize(grösse);
		
		this.bBild_speichern = new JButton(this.N_BILDSPEICHERN);
		this.bBild_speichern.setActionCommand(this.A_BILDSPEICHERN);
		this.bBild_speichern.setEnabled(false);
		this.bBild_speichern.addActionListener(contorller);
		this.bBild_speichern.setPreferredSize(grösse);
		
		this.bRGB_YCbCr = new JButton(this.N_FARBEN_RGB_YCBCR);
		this.bRGB_YCbCr.setActionCommand(this.A_FARBEN_RGB_YCBCR);
		this.bRGB_YCbCr.setEnabled(false);
		this.bRGB_YCbCr.addActionListener(contorller);
		this.bRGB_YCbCr.setPreferredSize(grösse);
				
		this.bFarben_YCbCr_RGB = new JButton(this.N_FARBEN_YCBCR_RGB);
		this.bFarben_YCbCr_RGB.setActionCommand(this.A_FARBEN_YCBCR_RGB);
		this.bFarben_YCbCr_RGB.setEnabled(false);
		this.bFarben_YCbCr_RGB.addActionListener(contorller);
		this.bFarben_YCbCr_RGB.setPreferredSize(grösse);
			  	
		this.bKomponeten_teilen = new JButton(this.N_KOMPONETETEILEN);
		this.bKomponeten_teilen.setActionCommand(this.A_KOMPONETETEILEN);
		this.bKomponeten_teilen.setEnabled(false);
		this.bKomponeten_teilen.addActionListener(contorller);
		this.bKomponeten_teilen.setPreferredSize(grösse);
		
		this.bKomponente_zusammenfügen = new JButton(this.N_KOMPONETEZUSAMMENFÜGEN);
		this.bKomponente_zusammenfügen.setActionCommand(this.A_KOMPONETEZUSAMMENFÜGEN);
		this.bKomponente_zusammenfügen.setEnabled(false);
		this.bKomponente_zusammenfügen.addActionListener(contorller);
		this.bKomponente_zusammenfügen.setPreferredSize(grösse);
				
		this.bWavelettransformation = new JButton(this.N_WAVELETTRANSFORMIEREN);
		this.bWavelettransformation.setActionCommand(this.A_WAVELETTRANSFORMIEREN);
		this.bWavelettransformation.setEnabled(false);
		this.bWavelettransformation.addActionListener(contorller);
		this.bWavelettransformation.setPreferredSize(grösse);
				
		this.bInverseWavelettransformation = new JButton(this.N_INVERSWAVELETTRANSFORMIEREN);
		this.bInverseWavelettransformation.setActionCommand(this.A_INVERSWAVELETTRANSFORMIEREN);
		this.bInverseWavelettransformation.setEnabled(false);
		this.bInverseWavelettransformation.addActionListener(contorller);
		this.bInverseWavelettransformation.setPreferredSize(grösse);
				
		this.bQuantisieren = new JButton(this.N_QUANTISIEREN);
		this.bQuantisieren.setActionCommand(this.A_QUANTISIEREN);
		this.bQuantisieren.setEnabled(false);
		this.bQuantisieren.addActionListener(contorller);
		this.bQuantisieren.setPreferredSize(grösse);
				
		this.bInversQuantisieren = new JButton(this.N_INVERSQUANTIESIEREN);
		this.bInversQuantisieren.setActionCommand(this.A_INVERSQUANTIESIEREN);
		this.bInversQuantisieren.setEnabled(false);
		this.bInversQuantisieren.addActionListener(contorller);
		this.bInversQuantisieren.setPreferredSize(grösse);
	
	/*
		this.bEncodenEBCOD_MQ = new JButton(this.N_ENCODE_EBCOD_MQ);
		this.bEncodenEBCOD_MQ.setActionCommand(this.A_EncodenEBCOD_MQ);
		this.bEncodenEBCOD_MQ.setEnabled(false);
		this.bEncodenEBCOD_MQ.addActionListener(contorller);
		this.bEncodenEBCOD_MQ.setPreferredSize(grösse);	
	
		this.bDecodenEBCOD_MQ = new JButton(this.N_DECODE_EBCOD_MQ);
		this.bDecodenEBCOD_MQ.setActionCommand(this.A_DecodenEBCOD_MQ);
		this.bDecodenEBCOD_MQ.setEnabled(false);
		this.bDecodenEBCOD_MQ.addActionListener(contorller);
		this.bDecodenEBCOD_MQ.setPreferredSize(grösse);			
	*/			
		//+++++++++++++++++++++++++++++++++++++++++++++
		// Container zusammenbauen
		// ++++++++++++++++++++++++++++++++++++++++++++
		
		Container Forward = new Container();
		Forward.setLayout(new FlowLayout());
		
		Forward.add(this.bLade_Bild);
		Forward.add(this.bRGB_YCbCr);
		Forward.add(this.bKomponeten_teilen);
		Forward.add(this.bWavelettransformation);
		Forward.add(this.bQuantisieren);
		//Forward.add(this.bEncodenEBCOD_MQ);
		
		Container Invers = new Container();
		Invers.setLayout(new FlowLayout());
		
		Invers.add(this.bBild_speichern);
		Invers.add(this.bFarben_YCbCr_RGB);
		Invers.add(this.bKomponente_zusammenfügen);
		Invers.add(this.bInverseWavelettransformation);
		Invers.add(this.bInversQuantisieren);
		//Invers.add(this.bDecodenEBCOD_MQ);
		
		
		Container BACK = new Container();
		BACK.setLayout(new GridLayout(1,2));
		BACK.add(Forward);
		BACK.add(Invers);
		
		return BACK;
	}
	

}
