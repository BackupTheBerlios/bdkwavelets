/*
 * Created on 15.02.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.tools;

import de.programmcontrol.StatusDaten;
import de.datentypen.Matrix_float;
import java.awt.image.BufferedImage;



/**
 * @author Uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Farbtools {

	public static final int RED = 0x00ff0000;

	public static final int GREEN = 0x0000ff00;

	public static final int BLUE = 0x000000ff;
	
	private static float help1 = 0;
	private static float help2 = 0;
	private static float help3 = 0;



	static public StatusDaten RGBtoYCbCr(StatusDaten Daten) {
		int RGB[] = Daten.EingangsBild.getRGB(0,0, Daten.EingangsBild.getWidth(), Daten.EingangsBild.getHeight(), null,0 , Daten.EingangsBild.getWidth());
		
		Daten.Y 	= new Matrix_float(Daten.EingangsBild.getWidth(), Daten.EingangsBild.getHeight());
		Daten.Cb 	= new Matrix_float(Daten.EingangsBild.getWidth(), Daten.EingangsBild.getHeight());
		Daten.Cr 	= new Matrix_float(Daten.EingangsBild.getWidth(), Daten.EingangsBild.getHeight());
		
		float fRed, fGreen, fBlue;
		
		for(int i = 0; i < RGB.length; i++){
			
			fRed 	= getRot(RGB[i]);
			fGreen 	= getGrün(RGB[i]);
			fBlue	= getBlau(RGB[i]);
			
			help1 = (fRed *  0.299f + fGreen *  0.587f + fBlue *  0.114f);
			help2 = (fRed * -0.169f + fGreen * -0.331f + fBlue *  0.500f);
			help3 = (fRed *  0.500f + fGreen * -0.419f + fBlue * -0.081f);
			if(help2 < -555 && help2 > 555 ){
				throw new RuntimeException("wert = " + help2);
			}
			
			Daten.Y.add( help1,i+1);
			Daten.Cr.add(help2,i+1);
			Daten.Cb.add(help3,i+1);
				
		}
		return Daten;	
		
	}
	
	static public StatusDaten YCbCrtoRGB(StatusDaten Daten){
		
		int[] dummy;
		try{
			dummy = new int[Daten.Y_Ausgang.wertelength()];
		}catch(NullPointerException e){
			System.out.println(e.toString());
			if(Daten == null){
				throw new RuntimeException("Daten == null");
			}else if(Daten.Y_Ausgang == null){
				throw new RuntimeException("Daten.Y_Ausgang == null");
			}
			
			throw new RuntimeException("keine Ahnung");
		}
		
		Daten.AusgangsBild = new BufferedImage(Daten.Y_Ausgang.breite, Daten.Y_Ausgang.höhe , BufferedImage.TYPE_INT_RGB);
		
		float fY,fCb , fCr, fRed, fGreen, fBlue;
		for(int i = 0; i < dummy.length; i++){
			
			fY  = Daten.Y_Ausgang.werte(i);
			fCb = Daten.Cr_Ausgang.werte(i);
			fCr = Daten.Cb_Ausgang.werte(i);
	
			fRed   = fY - 0.000926f * fCb + 1.40168f * fCr;
			fGreen = fY - 0.343695f * fCb - 0.71416f * fCr;
			fBlue  = fY + 1.772160f * fCb + 0.00099f * fCr;
	
			fRed	= (fRed   * 256.0f) + 128.0f;
			fGreen 	= (fGreen * 256.0f) + 128.0f;
			fBlue 	= (fBlue  * 256.0f) + 128.0f;
	
			if (fGreen < 0) {
				fGreen = 0;
			} else if (fGreen > 255) {
				fGreen = 255;
			}
	
			if (fBlue < 0) {
				fBlue = 0;
			} else if (fBlue > 255) {
				fBlue = 255;
			}
	
			if (fRed < 0) {
				fRed = 0;
			} else if (fRed > 255) {
				fRed = 255;
			}
	
			dummy[i] |= ((((int) fRed) << 16) & RED);
			dummy[i] |= ((((int) fGreen) << 8) & GREEN);
			dummy[i] |= ((((int) fBlue)) & BLUE);	
		}	
		
		Daten.AusgangsBild.setRGB(0 ,0 , Daten.Y_Ausgang.breite, Daten.Y_Ausgang.höhe,dummy, 0, Daten.Y_Ausgang.breite);

		return Daten;
	}

	static private float getRot(int RGB){
		float BACK = (float) ((RGB & RED) >> 16);
		BACK = (BACK - 128.0f)/ 256.0f;
		return BACK ; 
	}
	
	static private float getGrün(int RGB){
		float BACK =(float)((RGB & GREEN) >> 8);
		BACK = (BACK - 128.0f)/ 256.0f;
		return BACK  ;
	}
	
	static private float getBlau(int RGB){
		float BACK =(float)((RGB & BLUE));
		BACK = (BACK - 128.0f)/ 256.0f;
		return BACK  ;
	}
	
	static public int getGrau(float wert){
		if(wert >= 0 && wert <= 255.2 ){
			int BACK = 0;
			int iwert = (int) wert;
			BACK |= iwert;
			BACK |= (iwert << 8);
			BACK |= (iwert << 16);
			
			return BACK;
		}else{
			throw new RuntimeException("wert = " + wert);
		}
	}

}
