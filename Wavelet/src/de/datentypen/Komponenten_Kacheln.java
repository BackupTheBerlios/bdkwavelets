/*
 * Created on 16.02.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.datentypen;

import java.util.Collection;
/**
 * In einer Komponente wird nur ein Wert gespeichert z.B. nur der rot Anteil im RGB Farbsystem. 
 * Hier wird die werden alle Komponeten eines Typs z.B. rot eines Bildes verwaltet. Ein Bild
 * ist in mehrere Kacheln aufgeteilt. Hier werden die Kacheln gespeichert und deren Position im 
 * Bild.
 * 
 * @author Uwe Brünen
 * @version 0.1 
 */
public class Komponenten_Kacheln {

	/**
	 * Hier werden die Kacheln der Komponente gespeichert. 
	 */
	public Kachel[] kachel;
	
	/**
	 * Zur Speicherung der Kachelbreiten in x Richtung
	 */
	public int[] SizeX;
	
	/**
	 * Zur Speicherung der Kachelbreiten in x Richtung
	 */
	public int[] SizeY;


	public int Xsiz_Referenzgitter;

	public int Ysiz_Referenzgitter;

	public int XOsiz_Offset_zum_Bild;

	public int YOsiz_Offset_zum_Bild;

	public int XBsiz_Bildgröße;

	public int YBsiz_Bildgröße;

	public int XRsiz = 1;

	public int YRsiz = 1;

	public int XTsiz_Kachelgrösse;

	public int YTsiz_Kachelgrösse;

	public int XTOsiz_Kachoffset;

	public int YTOsiz_Kachoffset;

	public int numXtiles_anzahl_Kacheln_X;

	public int numYtiles_anzahl_Kacheln_Y;





	//public Kachel[] kachel;

	/**
	 * Erzeugt eine neu Kommponente und legt wilkürlich die gößen für
	 * die Kacheln fest
	 * 
	 * @param matrix Die Matrix repräsentiert eine Komponente z.B. die Y-Komponente
	 */
	public Komponenten_Kacheln(Matrix_float matrix) {
		this.XBsiz_Bildgröße = matrix.breite;
		this.YBsiz_Bildgröße = matrix.höhe;
		
		this.Xsiz_Referenzgitter = this.XBsiz_Bildgröße;
		this.Ysiz_Referenzgitter = this.YBsiz_Bildgröße;
		
		this.XOsiz_Offset_zum_Bild = 0;
		this.YOsiz_Offset_zum_Bild = 0;
		
		this.XTsiz_Kachelgrösse = 260;
		this.YTsiz_Kachelgrösse = 260;
		
		this.XTOsiz_Kachoffset = 0;
		this.YTOsiz_Kachoffset = 0;
		
		this.numXtiles_anzahl_Kacheln_X = Runde_hoch((float)this.XBsiz_Bildgröße / (float)this.XTsiz_Kachelgrösse);
		this.numYtiles_anzahl_Kacheln_Y = Runde_hoch((float)this.YBsiz_Bildgröße / (float)this.YTsiz_Kachelgrösse);
		berechneFelder();
	}

	static public int Runde_hoch(float in){
		if( ((float)((int)in)) == in ){
			return (int) in;
		}else{
			return (int) (in + 1.f);
		}		
	}
	
	static public int Runde_runter(float in){
		return (int) in;
		
	}
	
	public void berechneFelder(){
		int[] EckenX = new int[this.numXtiles_anzahl_Kacheln_X + 1];
		int[] EckenY = new int[this.numYtiles_anzahl_Kacheln_Y + 1];
		this.SizeX = new int[this.numXtiles_anzahl_Kacheln_X];
		this.SizeY = new int[this.numYtiles_anzahl_Kacheln_Y];
		//int iWork = 0;
		
		EckenX[0] = 0;
		EckenY[0] = 0;
		for(int i = 1; i < EckenX.length; i++){
			EckenX[i] = min(this.XTOsiz_Kachoffset + i * this.XTsiz_Kachelgrösse - this.XOsiz_Offset_zum_Bild, this.XBsiz_Bildgröße);
		}
		
		for(int i = 1; i < EckenY.length; i++){
			EckenY[i] = min(this.YTOsiz_Kachoffset + i * this.YTsiz_Kachelgrösse - this.YOsiz_Offset_zum_Bild, this.YBsiz_Bildgröße);
		}
		
		for(int i = 0, iWork = 0; i < SizeX.length ; i++){
			SizeX[i] =  EckenX[i+1] - iWork;
			iWork = iWork + SizeX[i];
		}
		
		for(int i = 0, iWork = 0; i < SizeY.length ; i++){
			SizeY[i] =  EckenY[i+1] - iWork;
			iWork += SizeY[i];
		}
		
	}

	public static int min(int i1, int i2){
		return i1 < i2 ? i1 : i2 ;
	}
	
	public Komponenten_Kacheln(	int Xsiz_Referenzgitter, 
								int Ysiz_Referenzgitter,
								int XOsiz_Offset_zum_Bild,
								int YOsiz_Offset_zum_Bild,
								int XBsiz_Bildgröße,
								int YBsiz_Bildgröße,
								int XRsiz,
								int YRsiz,
								int XTsiz_Kachelgrösse,
								int YTsiz_Kachelgrösse,
								int XTOsiz_Kachoffset,
								int YTOsiz_Kachoffset){
		this.Xsiz_Referenzgitter 	= Xsiz_Referenzgitter;
		this.Ysiz_Referenzgitter 	= Ysiz_Referenzgitter;
		this.XOsiz_Offset_zum_Bild 	= XOsiz_Offset_zum_Bild;
		this.YOsiz_Offset_zum_Bild 	= YOsiz_Offset_zum_Bild;
		this.XBsiz_Bildgröße 		= XBsiz_Bildgröße;
		this.YBsiz_Bildgröße 		= YBsiz_Bildgröße;
		this.XRsiz 					= XRsiz;
		this.YRsiz 					= YRsiz;
		this.XTsiz_Kachelgrösse 	= XTsiz_Kachelgrösse;
		this.YTsiz_Kachelgrösse 	= YTsiz_Kachelgrösse;
		this.XTOsiz_Kachoffset 		= XTOsiz_Kachoffset;
		this.YTOsiz_Kachoffset 		= YTOsiz_Kachoffset;	
				
		numXtiles_anzahl_Kacheln_X = Runde_hoch(((float)Xsiz_Referenzgitter - (float)XTOsiz_Kachoffset) / (float)XTsiz_Kachelgrösse);							
		numYtiles_anzahl_Kacheln_Y = Runde_hoch(((float)Ysiz_Referenzgitter - (float)YTOsiz_Kachoffset) / (float)YTsiz_Kachelgrösse);
		berechneFelder();
		
		kachel = new Kachel[(SizeX.length * SizeY.length)];
		
		int startx = 0;
		int starty = 0;
		int stopx = 0;
		int stopy = 0;
		Kachel temp;
		int nr = 0;
		
		for(int y = 0; y < SizeY.length; y++){
			stopy += SizeY[y];
			for(int x = 0; x < SizeX.length; x++){
				stopx +=  SizeX[x];
				kachel[nr] = new Kachel(SizeX[x], SizeY[y],nr);	
				startx += SizeX[x];
				nr++;
		
			}
			startx = 0;
			stopx = 0;
			starty += SizeY[y];
		}						
						
	}
	
	public Komponenten_Kacheln(Komponenten_Kacheln ref){
		
		this.Xsiz_Referenzgitter = ref.Xsiz_Referenzgitter;

		this.Ysiz_Referenzgitter = ref.Ysiz_Referenzgitter;

		this.XOsiz_Offset_zum_Bild = ref.XOsiz_Offset_zum_Bild;

		this.YOsiz_Offset_zum_Bild = ref.YOsiz_Offset_zum_Bild;

		this.XBsiz_Bildgröße = ref.XBsiz_Bildgröße;

		this.YBsiz_Bildgröße = ref.YBsiz_Bildgröße;

		this.XRsiz = ref.XRsiz;

		this.YRsiz = ref.YRsiz;

		this.XTsiz_Kachelgrösse = ref.XTsiz_Kachelgrösse;

		this.YTsiz_Kachelgrösse = ref.YTsiz_Kachelgrösse;

		this.XTOsiz_Kachoffset = ref.XTOsiz_Kachoffset;

		this.YTOsiz_Kachoffset = ref.YTOsiz_Kachoffset;

		this.numXtiles_anzahl_Kacheln_X = ref.numXtiles_anzahl_Kacheln_X;

		this.numYtiles_anzahl_Kacheln_Y = ref.numYtiles_anzahl_Kacheln_Y;	
		
		this.SizeX = new int[ref.SizeX.length];
		for(int i = 0; i < ref.SizeX.length; i++ ){
			this.SizeX[i] = ref.SizeX[i];
		}
		
		
		this.SizeY = new int[ref.SizeY.length];
		for(int i = 0; i < ref.SizeY.length; i++ ){
			this.SizeY[i] = ref.SizeY[i];
		}
	}
	

	public int anzahl_Kacheln_X(){
		return SizeX.length;
	}
	
	public int anzahl_Kacheln_Y(){
		return SizeY.length;
	}

	public void quantisiere(QuantisierungsInterface quantisierer){
		
		for(int i = 0; i < kachel.length; i++){
			kachel[i].subband.quantisieren(quantisierer);
		}
		
	}
	
	public void dequantisiere(QuantisierungsInterface quantisierer){
		for(int i = 0; i < kachel.length; i++){
			kachel[i].subband.dequantisieren(quantisierer);
		}	
	}
	
	public boolean encode_EBCOT_MQ(boolean show){
		
		for(int i = 0; i < kachel.length; i++){
			show = kachel[i].subband.encode_EBCOT_MQ(show);
		}
		return show;		
	}



}
