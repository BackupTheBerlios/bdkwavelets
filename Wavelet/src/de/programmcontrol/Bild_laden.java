/*
 * Created on 14.02.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.programmcontrol;


//import de.gui.Bild_laden_GUI;
import de.gui.ZeigeBild;
import de.gui.ExampleFileFilter;

import javax.swing.JFileChooser;
import java.net.URL;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.net.MalformedURLException;
import java.io.IOException;
import javax.swing.JApplet;
import java.awt.MediaTracker;
import java.io.File;

/**
 * @author Uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Bild_laden extends JApplet implements ProgrammStatus {

	private final String StatusName = "Bild_laden";

	//private Bild_laden_GUI gui;
	
	private StatusDaten Daten;

	/* (non-Javadoc)
	 * @see de.programmcontrol.ProgrammStatus#start_bearbeitung(de.programmcontrol.StatusDaten)
	 */
	public StatusDaten start_bearbeitung(StatusDaten Daten) {
		this.Daten = Daten;
		

		
		JFileChooser chooser = new JFileChooser();
		ExampleFileFilter filter = new ExampleFileFilter();
		filter.addExtension("jpg");
		filter.setDescription("JPG Images");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(this.Daten.StatusConrolle.gui);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			ok(chooser.getSelectedFile());
		}else{
			Daten.Status_ist_aktive = false;
		}
		
		return this.Daten;
	}

	/* (non-Javadoc)
	 * @see de.programmcontrol.ProgrammStatus#getStatusName()
	 */
	public String getStatusName() {
		return StatusName;
	}


	private void ok(File file) {
		URL urlBild ;
		BufferedImage testBild;
		try{
			MediaTracker tracker = new MediaTracker(this);
			urlBild =   file.toURL();
			testBild = ImageIO.read(urlBild);
			tracker.addImage(testBild, 0);
			try{
				tracker.waitForAll();
			}catch(InterruptedException e){
				;
			}
			Daten.EingangsBild = testBild;
			new ZeigeBild(testBild, "Eingangs Bild");	
		}catch(MalformedURLException e){
			System.out.println("<84> "+e.toString());
			Daten.Status_ist_aktive = false;
			return;
		}catch(IOException e){			
			System.out.println("<86> " + e.toString());
			Daten.Status_ist_aktive = false;
			return ;
		}	
		
		Daten.Aktueller_Status = new Farben_RGB_to_YCbCr();
		Daten.StatusConrolle.gui.bLade_Bild.setEnabled(false);
		Daten.StatusConrolle.gui.bRGB_YCbCr.setEnabled(true);
		Daten.Status_ist_aktive = false;
			
	}


}
