/*
 * Created on 17.02.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.gui.wavelet;

import java.net.URL;


import java.awt.Dimension;
import java.net.MalformedURLException;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import java.awt.GridLayout;
import de.datentypen.*;
import de.gui.*;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
/**
 * @author Uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Wavelet_Fenster extends JFrame {
	
	private JLabel statusString;
	
	private DatenView[] datenview = new DatenView[4];
	
	private Matrix_ScrollPanel[] bildview = new Matrix_ScrollPanel[7];
	
	private ActionListener al;
	
	
	private URL urlBase;
	
	
	private boolean show = true;
	/**
	 * ActionCommand des ersten Buttons
	 */
	public final String A_DURCHLAUFE_STATUS_EINMAL = "A_DURCHLAUFE_STATUS_EINMAL";

	/**
	 * ActionCommand des zweiten Buttons
	 */
	public final String A_BEENDE_STATUS = "A_BEENDE_STATUS";

	/**
	 * ActionCommand des dreitten Buttons
	 */
	public final String A_BIS_FERTIG = "bis_fertig";
	
	/**
	 * ActionCommand des Beenden Buttons
	 */
	public final String A_BEENDEN = "beenden";	
	
	/**
	 * ActionCommand des Schritt anzeigen Buttons
	 */	
	public final String A_ANZEIGE ="anzeigen";
	/**
	 * Knopf zum schlieﬂen des fensters
	 */
	public JButton beenden;
	
	public JButton anzeige;

	public  void updateEingangsBild(Matrix_float in){
		//bildview[0].setVisible(false);
		if(show)
			bildview[0].update(in);
	
		//bildview[0].repaint();
	}

	public  void updateLinkeBild(Matrix_float in){
		//bildview[1].setVisible(false);
		if(show)
			bildview[1].update(in);
		//bildview[1].repaint();		
	}

	public  void updateRechteBild(Matrix_float in){
		//bildview[2].setVisible(false);
		if(show)
			bildview[2].update(in);
		//bildview[2].repaint();		
	}

	public  void updateObenLinksBild(Matrix_float in){
		//bildview[3].setVisible(false);
		if(show)
			bildview[3].update(in);
		//bildview[3].repaint();
	}

	public  void updateUntenLinks(Matrix_float in){
		//bildview[5].setVisible(false);
		if(show)
			bildview[5].update(in);
		//bildview[5].repaint();
		
	}

	public  void updateObenRechts(Matrix_float in){
		//bildview[4].setVisible(false);
		if(show)
			bildview[4].update(in);
		//bildview[4].repaint();		
	}

	public  void updateUntenRechts(Matrix_float in){
		//bildview[6].setVisible(false);
		if(show)
			bildview[6].update(in);
		//bildview[6].repaint();		
	}

	public  void updateDaten1(Vektor_float in){
		if(show)
			datenview[0].updateDaten(in);
	}

	public  void updateDaten2(Vektor_float in){
		if(show)
			datenview[1].updateDaten(in);
	}
	
	public  void updateDaten3(Vektor_float in){
		if(show)
			datenview[2].updateDaten(in);
	}
	
	public  void updateDaten4(Vektor_float in){
		if(show)
			datenview[4].updateDaten(in);
	}

	private JPanel createDatenView() {
		JPanel BACK = new JPanel();
		BACK.setLayout(new GridLayout(3,1,4, 12));
	
		BACK.add(new JScrollPane(datenview[0] = new  DatenView()));
		BACK.add(new JScrollPane(datenview[1] = new  DatenView()));
		BACK.add(new JScrollPane(datenview[2] = new  DatenView()));
		//BACK.add(new JScrollPane(datenview[3] = new  DatenView()));
		
		BACK.setPreferredSize(new Dimension(900, 400));
		
		return BACK;
	}

	private JPanel createButtonView() {
		
		int x = 1000;
		int y = 39;

		JPanel BACK = new JPanel();

		BACK.setSize(new Dimension(x, y));
		BACK.setMaximumSize(new Dimension(x, y));
		BACK.setMinimumSize(new Dimension(x, y));
		BACK.setPreferredSize(new Dimension(x, y));
		BACK.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

		try {
			x = 32;
			y = 32;
			
			JButton onStatus =new JButton(new ImageIcon(new URL(urlBase, "Icons/NormalStatus.jpg")));
			onStatus.setRolloverIcon(new ImageIcon(new URL(urlBase, "Icons/SelectStatus.jpg")));
			onStatus.setPressedIcon(new ImageIcon(new URL(urlBase, "Icons/PressedStatus.jpg")));
			
			JButton end = new JButton(new ImageIcon(new URL(urlBase, "Icons/NormalEnde.jpg")));
			end.setRolloverIcon(new ImageIcon(new URL(urlBase, "Icons/SelectEnde.jpg")));
			end.setPressedIcon(new ImageIcon(new URL(urlBase, "Icons/PressedEnde.jpg")));		
		
			JButton onStepp =new JButton(new ImageIcon(new URL(urlBase, "Icons/NormalSchritt.jpg")));
			//JButton onStepp =new JButton(new ImageIcon(new URL(urlBase, "Icons/NormalSchritt.jpg")));
			onStepp.setRolloverIcon(new ImageIcon(new URL(urlBase, "Icons/SelectSchritt.jpg")));
			onStepp.setPressedIcon(new ImageIcon(new URL(urlBase, "Icons/PressedSchritt.jpg")));
		
			System.out.println(urlBase.toString());
		
			beenden = new JButton("Beenden");
			anzeige = new JButton("Schritte anzeigen");
					
			onStepp.setSize(new Dimension(x, y));
			onStepp.setMaximumSize(new Dimension(x, y));
			onStepp.setMinimumSize(new Dimension(x, y));
			onStepp.setPreferredSize(new Dimension(x, y));

			onStatus.setSize(new Dimension(x, y));
			onStatus.setMaximumSize(new Dimension(x, y));
			onStatus.setMinimumSize(new Dimension(x, y));
			onStatus.setPreferredSize(new Dimension(x, y));

			end.setSize(new Dimension(x, y));
			end.setMaximumSize(new Dimension(x, y));
			end.setMinimumSize(new Dimension(x, y));
			end.setPreferredSize(new Dimension(x, y));
			
			x = 130;			
			beenden.setSize(new Dimension(x, y));
			beenden.setMaximumSize(new Dimension(x, y));
			beenden.setMinimumSize(new Dimension(x, y));
			beenden.setPreferredSize(new Dimension(x, y));
			beenden.setEnabled(false);

			x = 160;
			anzeige.setSize(new Dimension(x, y));
			anzeige.setMaximumSize(new Dimension(x, y));
			anzeige.setMinimumSize(new Dimension(x, y));
			anzeige.setPreferredSize(new Dimension(x, y));
			anzeige.setEnabled(true);
			
			onStepp.addActionListener(al);
			onStepp.setActionCommand(A_DURCHLAUFE_STATUS_EINMAL);

			onStatus.addActionListener(al);
			onStatus.setActionCommand(A_BEENDE_STATUS);

			end.addActionListener(al);
			end.setActionCommand(A_BIS_FERTIG);
			
			beenden.addActionListener(al);
			beenden.setActionCommand(A_BEENDEN);

			anzeige.addActionListener(al);
			anzeige.setActionCommand(A_ANZEIGE);
			
			BACK.add(onStepp);
			BACK.add(onStatus);
			BACK.add(end);
			BACK.add(anzeige);
			BACK.add(beenden);
			
			
		} catch (MalformedURLException e) {
		System.out.println(e.toString());
		}

		return BACK;
	}

	private JPanel createBildView() {
		JPanel BACK = new JPanel();
		BACK.setLayout(new GridLayout(1,3,4, 4));
		
		JPanel links = new JPanel();
		links.setLayout(new GridLayout(1,1));
		links.add(bildview[0] = new Matrix_ScrollPanel());
		
		JPanel mitte = new JPanel();
		mitte.setLayout(new GridLayout(1,2));
		mitte.add(bildview[1] = new Matrix_ScrollPanel());
		mitte.add(bildview[2] = new Matrix_ScrollPanel());
	
		JPanel rechts = new JPanel();
		rechts.setLayout(new GridLayout(2,2));
		rechts.add(bildview[3] = new Matrix_ScrollPanel());
		rechts.add(bildview[4] = new Matrix_ScrollPanel());	
		rechts.add(bildview[5] = new Matrix_ScrollPanel());	
		rechts.add(bildview[6] = new Matrix_ScrollPanel());	
		
		BACK.add(links);
		BACK.add(mitte);
		BACK.add(rechts);
		
		BACK.setPreferredSize(new Dimension(900, 270));
		
		return BACK;
	}

	public Wavelet_Fenster(ActionListener al, URL codeBase) {
		this.al = al;
		this.urlBase = codeBase;
		
		
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add("North",this.createButtonView());
		JPanel panelCenter = new JPanel();
		panelCenter.setLayout(new GridLayout(2,1));
		panelCenter.add(this.createBildView());
		panelCenter.add(this.createDatenView());
		this.getContentPane().add("Center",panelCenter);
		this.getContentPane().add("South",this.creatStatusPanel());
		this.setSize(1000, 700);
		this.setVisible(true);
		
	}

	private JPanel creatStatusPanel() {
		JPanel BACK = new JPanel();
		BACK.setLayout(new GridLayout(1,1));
		BACK.setPreferredSize(new Dimension(1000,25));
		statusString = new JLabel("Test");
		BACK.add(statusString);
		BACK.setBorder(new BevelBorder(BevelBorder.LOWERED) );
		
		return BACK;
	}
	public void updateStatus(String in) {
		statusString.setText(in);
	}
	
	public void updateTitle(String name){
		setTitle(name);
	}
	
	public void setShow(){
		if(show){
			show = false;
		}else{
			show = true;
		}
	}
}
