/*
 * Created on 16.02.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.gui;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.datentypen.Komponenten_Kacheln;

/**
 * @author Uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public final class JPanel_Aufteilung_Werte_View extends JPanel implements ChangeListener {

	private int XYTsizmin = 64;

	private Komponenten_Kacheln Kom;
	
	private JSpinner XSiz;
	
	private JSpinner YSiz;
	
	private JSpinner XTSiz;
	
	private JSpinner YTSiz;
	
	private JSpinner XTOSiz;
	
	private JSpinner YTOSiz;

	private JPanel_Aufteilung_View DatenView;

	private JButton ok;
	
	private ActionListener al;

	public JPanel_Aufteilung_Werte_View(Komponenten_Kacheln Kom, JPanel_Aufteilung_View DatenView, ActionListener al) {
		this.al = al;
		this.DatenView = DatenView;
		this.Kom = Kom;
		buildGUI();
	}
	
	public void verändere_Xsiz(){
		int newXsiz = ((Number)XSiz.getValue()).intValue();	
		this.Kom.Xsiz_Referenzgitter = newXsiz;
		this.Kom.XOsiz_Offset_zum_Bild = newXsiz - this.Kom.XBsiz_Bildgröße;
		this.Kom.numXtiles_anzahl_Kacheln_X = Komponenten_Kacheln.Runde_hoch(((float)this.Kom.Xsiz_Referenzgitter - (float)this.Kom.XTOsiz_Kachoffset) / (float)this.Kom.XTsiz_Kachelgrösse);
		((SpinnerNumberModel)(this.XTOSiz.getModel())).setMaximum(new Integer(this.Kom.XOsiz_Offset_zum_Bild ));
		((SpinnerNumberModel)(this.XSiz.getModel())).setMaximum(new Integer(this.Kom.XTOsiz_Kachoffset + this.Kom.XTsiz_Kachelgrösse + this.Kom.XBsiz_Bildgröße));
		((SpinnerNumberModel)(this.XTSiz.getModel())).setMinimum(new Integer( (this.Kom.XOsiz_Offset_zum_Bild - this.Kom.XTOsiz_Kachoffset + 4) > XYTsizmin ?(this.Kom.XOsiz_Offset_zum_Bild - this.Kom.XTOsiz_Kachoffset + 4) : XYTsizmin  ));
		this.Kom.berechneFelder();
		this.DatenView.repaint();	
	}
	
	public void verändere_Ysiz(){
		int newYsiz = ((Number)YSiz.getValue()).intValue();	
		this.Kom.Ysiz_Referenzgitter = newYsiz;
		this.Kom.YOsiz_Offset_zum_Bild = newYsiz - this.Kom.YBsiz_Bildgröße;
		this.Kom.numYtiles_anzahl_Kacheln_Y = Komponenten_Kacheln.Runde_hoch(((float)this.Kom.Ysiz_Referenzgitter - (float)this.Kom.YTOsiz_Kachoffset) / (float)this.Kom.YTsiz_Kachelgrösse);
		((SpinnerNumberModel)(this.YTOSiz.getModel())).setMaximum(new Integer(this.Kom.YOsiz_Offset_zum_Bild ));
		((SpinnerNumberModel)(this.YSiz.getModel())).setMaximum(new Integer(this.Kom.YTOsiz_Kachoffset + this.Kom.YTsiz_Kachelgrösse + this.Kom.YBsiz_Bildgröße));
		((SpinnerNumberModel)(this.YTSiz.getModel())).setMinimum(new Integer((this.Kom.YOsiz_Offset_zum_Bild - this.Kom.YTOsiz_Kachoffset + 4) > XYTsizmin ?(this.Kom.YOsiz_Offset_zum_Bild - this.Kom.YTOsiz_Kachoffset + 4) : XYTsizmin  ));
		this.Kom.berechneFelder();
		this.DatenView.repaint();
	}
	
	public void verändere_XTsiz(){
		int newXTsiz = ((Number)XTSiz.getValue()).intValue();	
		this.Kom.XTsiz_Kachelgrösse = newXTsiz;
		this.Kom.numXtiles_anzahl_Kacheln_X = Komponenten_Kacheln.Runde_hoch(((float)this.Kom.Xsiz_Referenzgitter - (float)this.Kom.XTOsiz_Kachoffset) / (float)newXTsiz);
		((SpinnerNumberModel)(this.XTSiz.getModel())).setMinimum(new Integer( (this.Kom.XOsiz_Offset_zum_Bild - this.Kom.XTOsiz_Kachoffset + 4) > XYTsizmin ?(this.Kom.XOsiz_Offset_zum_Bild - this.Kom.XTOsiz_Kachoffset + 4) : XYTsizmin  ));
		this.Kom.berechneFelder();
		this.DatenView.repaint();
	}
	
	public void verändere_YTsiz(){
		int newYTsiz = ((Number)YTSiz.getValue()).intValue();	
		this.Kom.YTsiz_Kachelgrösse = newYTsiz;
		this.Kom.numYtiles_anzahl_Kacheln_Y = Komponenten_Kacheln.Runde_hoch(((float)this.Kom.Ysiz_Referenzgitter - (float)this.Kom.YTOsiz_Kachoffset) / (float)newYTsiz);
		((SpinnerNumberModel)(this.YTSiz.getModel())).setMinimum(new Integer((this.Kom.YOsiz_Offset_zum_Bild - this.Kom.YTOsiz_Kachoffset + 4) > XYTsizmin ?(this.Kom.YOsiz_Offset_zum_Bild - this.Kom.YTOsiz_Kachoffset + 4) : XYTsizmin  ));
		this.Kom.berechneFelder();
		this.DatenView.repaint();
	}

	public void verändere_XTOsiz(){
		int newXTOsiz = ((Number)XTOSiz.getValue()).intValue();
		this.Kom.XTOsiz_Kachoffset = newXTOsiz;	
		this.Kom.numXtiles_anzahl_Kacheln_X = Komponenten_Kacheln.Runde_hoch(((float)this.Kom.Xsiz_Referenzgitter - (float)this.Kom.XTOsiz_Kachoffset) / (float)this.Kom.XTsiz_Kachelgrösse);
		((SpinnerNumberModel)(this.XTOSiz.getModel())).setMaximum(new Integer(this.Kom.XOsiz_Offset_zum_Bild ));
		((SpinnerNumberModel)(this.XSiz.getModel())).setMinimum(new Integer(newXTOsiz + this.Kom.XBsiz_Bildgröße ));
		this.Kom.berechneFelder();
		this.DatenView.repaint();
	}
	
	public void verändere_YTOsiz(){
		int newYTOsiz = ((Number)YTOSiz.getValue()).intValue();
		this.Kom.YTOsiz_Kachoffset = newYTOsiz;	
		this.Kom.numYtiles_anzahl_Kacheln_Y = Komponenten_Kacheln.Runde_hoch(((float)this.Kom.Ysiz_Referenzgitter - (float)this.Kom.YTOsiz_Kachoffset) / (float)this.Kom.YTsiz_Kachelgrösse);
		((SpinnerNumberModel)(this.YTOSiz.getModel())).setMaximum(new Integer(this.Kom.YOsiz_Offset_zum_Bild ));
		((SpinnerNumberModel)(this.YSiz.getModel())).setMinimum(new Integer(newYTOsiz + this.Kom.YBsiz_Bildgröße ));
		this.Kom.berechneFelder();
		this.DatenView.repaint();
	}	
	
	public void stateChanged(ChangeEvent e){
		JSpinner in = (JSpinner)e.getSource();
		String cmd = in.getName();
		
		if(cmd.equals("Xsiz")){
			verändere_Xsiz();
		}else if(cmd.equals("Ysiz")){
			verändere_Ysiz();
		}else if(cmd.equals("XTsiz")){
			verändere_XTsiz();
		}else if(cmd.equals("YTsiz")){
			verändere_YTsiz();
		}else if(cmd.equals("XTOsiz")){
			verändere_XTOsiz();
		}else if(cmd.equals("YTOsiz")){
			verändere_YTOsiz();
		}
	
	
	
	
	
	}
	
	private void update(){
		
	}
	
	private void buildGUI(){
		JPanel in;
		
		this.setLayout(new GridLayout(7,2));
		
//		++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		
		String Name = "Xsiz";
		
		in = new JPanel();
		in.add(new JLabel(Name));
		this.add(in);
		
		in = new JPanel();
		XSiz = new JSpinner(new SpinnerNumberModel(this.Kom.Xsiz_Referenzgitter, this.Kom.XBsiz_Bildgröße, 3000, 5));
		XSiz.addChangeListener(this);
		XSiz.setName(Name);
		in.add(XSiz);
		this.add(in);
		
//		++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		
		Name = "Ysiz";
		
		in = new JPanel();
		in.add(new JLabel(Name));
		this.add(in);
		
		in = new JPanel();
		YSiz = new JSpinner(new SpinnerNumberModel(this.Kom.Ysiz_Referenzgitter, this.Kom.YBsiz_Bildgröße, 3000, 5));
		YSiz.addChangeListener(this);
		YSiz.setName(Name);
		in.add(YSiz);
		this.add(in);

//		++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		
		Name = "XTsiz";
		
		in = new JPanel();
		in.add(new JLabel(Name));
		this.add(in);
		
		in = new JPanel();
		XTSiz = new JSpinner(new SpinnerNumberModel(this.Kom.XTsiz_Kachelgrösse , XYTsizmin, 3000, 5));
		XTSiz.addChangeListener(this);
		XTSiz.setName(Name);
		in.add(XTSiz);
		this.add(in);
		
//		++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		
		Name = "YTsiz";
		
		in = new JPanel();
		in.add(new JLabel(Name));
		this.add(in);
		
		in = new JPanel();
		YTSiz = new JSpinner(new SpinnerNumberModel(this.Kom.YTsiz_Kachelgrösse , XYTsizmin, 3000, 5));
		YTSiz.addChangeListener(this);
		YTSiz.setName(Name);
		in.add(YTSiz);
		this.add(in);

//		++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		
		Name = "XTOsiz";
		
		in = new JPanel();
		in.add(new JLabel(Name));
		this.add(in);
		
		in = new JPanel();
		XTOSiz = new JSpinner(new SpinnerNumberModel(this.Kom.XTOsiz_Kachoffset, this.Kom.XOsiz_Offset_zum_Bild, 0, 5));
		XTOSiz.addChangeListener(this);
		XTOSiz.setName(Name);
		in.add(XTOSiz);
		this.add(in);
		
//		++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		
		Name = "YTOsiz";
		
		in = new JPanel();
		in.add(new JLabel(Name));
		this.add(in);
		
		in = new JPanel();
		YTOSiz = new JSpinner(new SpinnerNumberModel(this.Kom.XTOsiz_Kachoffset, this.Kom.YOsiz_Offset_zum_Bild, 0, 5));
		YTOSiz.addChangeListener(this);
		YTOSiz.setName(Name);
		in.add(YTOSiz);
		this.add(in);
//		++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		
		ok = new JButton("Ok");
		ok.setActionCommand("Ok");
		ok.addActionListener(al);		
		this.add(ok);
	}
	

}

