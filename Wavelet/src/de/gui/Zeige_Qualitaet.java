/*
 * Created on 03.08.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import java.awt.*;
/**
 * @author uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public final class Zeige_Qualitaet extends JFrame {

	public Zeige_Qualitaet(	float MSE_Y,
											float MAD_Y,
											float SAD_Y,
											float SNR_Y,
											float MSE_Cb,
											float MAD_Cb,
											float SAD_Cb,
											float SNR_Cb,
											float MSE_Cr,
											float MAD_Cr,
											float SAD_Cr,
											float SNR_Cr						
											){
		super("Qualität der Transformation");
		setLocation(100, 100);
		setSize(300, 200);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridLayout(1,3, 4,4));
		contentPane.add(createPanel(MSE_Y, MAD_Y, SAD_Y, SNR_Y, "Y Komponente"));
		contentPane.add(createPanel(MSE_Cr, MAD_Cr, SAD_Cr, SNR_Cr, "Cr Komponente"));
		contentPane.add(createPanel(MSE_Cb, MAD_Cb, SAD_Cb, SNR_Cb, "Cb Komponente"));
		
		setVisible(true);
		
	}
	
	private JPanel createPanel(	float MSE,
													float MAD,
													float SAD,
													float SNR,
													String name){
														JPanel BACK = new JPanel();
														BACK.setLayout(new GridLayout(4,2, 4,4));
														BACK.setBorder(new TitledBorder(name));

														BACK.add(new JLabel("MSE"));
														BACK.add(new JLabel(""+MSE));

														BACK.add(new JLabel("MAD"));
														BACK.add(new JLabel(""+MAD));
														
														BACK.add(new JLabel("SAD"));
														BACK.add(new JLabel(""+SAD));
														
														BACK.add(new JLabel("SNR"));
														BACK.add(new JLabel(""+SNR));
														
														
														return BACK;
													}
	
}
