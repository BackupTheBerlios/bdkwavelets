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
 * @author Uwe Br�nen
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

	public int XBsiz_Bildgr��e;

	public int YBsiz_Bildgr��e;

	public int XRsiz = 1;

	public int YRsiz = 1;

	public int XTsiz_Kachelgr�sse;

	public int YTsiz_Kachelgr�sse;

	public int XTOsiz_Kachoffset;

	public int YTOsiz_Kachoffset;

	public int numXtiles_anzahl_Kacheln_X;

	public int numYtiles_anzahl_Kacheln_Y;





	//public Kachel[] kachel;

	/**
	 * Erzeugt eine neu Kommponente und legt wilk�rlich die g��en f�r
	 * die Kacheln fest
	 * 
	 * @param matrix Die Matrix repr�sentiert eine Komponente z.B. die Y-Komponente
	 */
	public Komponenten_Kacheln(Matrix_float matrix) {
		this.XBsiz_Bildgr��e = matrix.breite;
		this.YBsiz_Bildgr��e = matrix.h�he;
		
		this.Xsiz_Referenzgitter = this.XBsiz_Bildgr��e;
		this.Ysiz_Referenzgitter = this.YBsiz_Bildgr��e;
		
		this.XOsiz_Offset_zum_Bild = 0;
		this.YOsiz_Offset_zum_Bild = 0;
		
		this.XTsiz_Kachelgr�sse = 260;
		this.YTsiz_Kachelgr�sse = 260;
		
		this.XTOsiz_Kachoffset = 0;
		this.YTOsiz_Kachoffset = 0;
		
		this.numXtiles_anzahl_Kacheln_X = Runde_hoch((float)this.XBsiz_Bildgr��e / (float)this.XTsiz_Kachelgr�sse);
		this.numYtiles_anzahl_Kacheln_Y = Runde_hoch((float)this.YBsiz_Bildgr��e / (float)this.YTsiz_Kachelgr�sse);
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
			EckenX[i] = min(this.XTOsiz_Kachoffset + i * this.XTsiz_Kachelgr�sse - this.XOsiz_Offset_zum_Bild, this.XBsiz_Bildgr��e);
		}
		
		for(int i = 1; i < EckenY.length; i++){
			EckenY[i] = min(this.YTOsiz_Kachoffset + i * this.YTsiz_Kachelgr�sse - this.YOsiz_Offset_zum_Bild, this.YBsiz_Bildgr��e);
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
								int XBsiz_Bildgr��e,
								int YBsiz_Bildgr��e,
								int XRsiz,
								int YRsiz,
								int XTsiz_Kachelgr�sse,
								int YTsiz_Kachelgr�sse,
								int XTOsiz_Kachoffset,
								int YTOsiz_Kachoffset){
		this.Xsiz_Referenzgitter 	= Xsiz_Referenzgitter;
		this.Ysiz_Referenzgitter 	= Ysiz_Referenzgitter;
		this.XOsiz_Offset_zum_Bild 	= XOsiz_Offset_zum_Bild;
		this.YOsiz_Offset_zum_Bild 	= YOsiz_Offset_zum_Bild;
		this.XBsiz_Bildgr��e 		= XBsiz_Bildgr��e;
		this.YBsiz_Bildgr��e 		= YBsiz_Bildgr��e;
		this.XRsiz 					= XRsiz;
		this.YRsiz 					= YRsiz;
		this.XTsiz_Kachelgr�sse 	= XTsiz_Kachelgr�sse;
		this.YTsiz_Kachelgr�sse 	= YTsiz_Kachelgr�sse;
		this.XTOsiz_Kachoffset 		= XTOsiz_Kachoffset;
		this.YTOsiz_Kachoffset 		= YTOsiz_Kachoffset;	
				
		numXtiles_anzahl_Kacheln_X = Runde_hoch(((float)Xsiz_Referenzgitter - (float)XTOsiz_Kachoffset) / (float)XTsiz_Kachelgr�sse);							
		numYtiles_anzahl_Kacheln_Y = Runde_hoch(((float)Ysiz_Referenzgitter - (float)YTOsiz_Kachoffset) / (float)YTsiz_Kachelgr�sse);
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

		this.XBsiz_Bildgr��e = ref.XBsiz_Bildgr��e;

		this.YBsiz_Bildgr��e = ref.YBsiz_Bildgr��e;

		this.XRsiz = ref.XRsiz;

		this.YRsiz = ref.YRsiz;

		this.XTsiz_Kachelgr�sse = ref.XTsiz_Kachelgr�sse;

		this.YTsiz_Kachelgr�sse = ref.YTsiz_Kachelgr�sse;

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
