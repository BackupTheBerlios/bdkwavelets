/*
 * Created on 11.05.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.datentypen;

import java.io.*;
import de.tools.*;
/**
 * @author Uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public final class Code_Block {
	static int uwe = 0;
	/*
	 * Die einzelnen Bit-Ebenen
	 */	
	private Bit_ebene[] bit_ebene;
	
	/*
	 * Das Vorzeichen
	 */
	public Bit_ebene vorzeichen;

	/*
	 * Der Signifikantstatus
	 */
	public Bit_ebene signifikant;
	
	/*
	 * Wird zur Kodierung der signifikanzen benötigt
	 */
	public Bit_ebene pi;
	
	/*
	 * Wird zur Kodierung der magnitude Bits benötigt
	 */
	public Bit_ebene p_pfeil;
	 
	/*
	 * Zeigt an ob die Codierung grafisch angezeigt werden soll
	 */
	public static boolean show = true;
	
	private byte[] Ergebnis; 
	 
	/*
	 * Der Type bestimmt in welchem Band der Codeblock liegt
	 * 1 -> LL
	 * 2 -> LH
	 * 3 -> HL
	 * 4 -> HH
	 */ 
	public int type;
	
	public final static int LL = 1;
	public final static int LH = 2;
	public final static int HL = 3;
	public final static int HH = 4;
	
	
	/*
	 * Anzahl der Bitebenen
	 */
	public int k;
	
	private Subband_Daten_diskret daten;
	
	private int groesse_y;
	
	private int groesse_x;
	
	private int pos_y;
	
	private int pos_x;
	
	public Code_Block(Subband_Daten_diskret daten, int groesse_x, int groesse_y, int pos_x, int pos_y, boolean show_coding, int type){
		k = daten.get_anzahl_bit();
		Code_Block.show = show_coding;
		this.groesse_x = groesse_x;
		this.groesse_y = groesse_y;
		this.pos_x = pos_x;
		this.pos_y = pos_y;
		this.daten = daten;
		this.type = type;
		
		vorzeichen 	= new Bit_ebene(daten, this, groesse_x, groesse_y, pos_x, pos_y, -2, null);
		signifikant	= new Bit_ebene(this, groesse_x, groesse_y);
		pi			= new Bit_ebene(this, groesse_x, groesse_y);
		p_pfeil		= new Bit_ebene(this, groesse_x, groesse_y);
		
		bit_ebene = new Bit_ebene[k];
		

	}
	
	public Code_Block(byte[] daten, int groesse_x, int groesse_y, int pos_x, int pos_y, boolean show_coding, int type){
		Ergebnis = daten;
		Code_Block.show = show_coding;
		this.groesse_x = groesse_x;
		this.groesse_y = groesse_y;
		this.pos_x = pos_x;
		this.pos_y = pos_y;
		this.type = type;
	}
	public boolean encode( boolean show_coding){
		int help = 0;
		MQ_Encoder mq = new MQ_Encoder();
		for(int i = k-1; i >= 0;i--){
			bit_ebene[i] = new Bit_ebene(daten,this , groesse_x, groesse_y, pos_x, pos_y, i, mq);
			if(bit_ebene[i].hat_Einsen || help > 0){
				if(help == 0){
				// Die oberste Bit ebene wird encodiert
					bit_ebene[i].encode(this, true);
					System.out.println("-- Bit-Ebene = " + i+" --");
					help = 1;
				}else{
					bit_ebene[i].encode(this, false);
					System.out.println("Bit-Ebene = " + i);
				}
				// Für den Fall das keine Bitebene gecodet wurde coden wir die letzte
				if(help == 0 && i == 0){
					bit_ebene[i].encode(this, true);
					System.out.println("Bit-Ebene = " + i);					
				}
				
				this.pi.clear();
			}
			// Für den Fall das keine Bitebene gecodet wurde coden wir die letzte
			if(help == 0 && i == 0){
				bit_ebene[i].encode(this, true);
				System.out.println("Bit-Ebene = " + i);					
			}
			this.pi.clear();			
			
		}		
		Ergebnis = mq.get_Ergbenis();
		if(Ergebnis.length == 0){
			wrirte();
		}
		return show_coding;
	}
	
	
	public boolean decode( boolean show_coding){
		int help = 0;
		MQ_Decoder mq = new MQ_Decoder(Ergebnis);
		for(int i = k-1; i >= 0;i--){
			bit_ebene[i] = new Bit_ebene(this, groesse_x, groesse_y,  i, mq);
			if(bit_ebene[i].hat_Einsen || help > 0){
				if(help == 0){
				// Die oberste Bit ebene wird encodiert
					bit_ebene[i].decode(this, true);
					System.out.println("Bit-Ebene = " + i);
					help = 1;
				}else{
					bit_ebene[i].decode(this, false);
					System.out.println("Bit-Ebene = " + i);
				}
			}
		}
		return false;	
	}
	
	private void wrirte(){
		
		FileWriter f1;
		String hello = new String();
		hello += "this.groesse_x = " +this.groesse_x +"\n";
		hello += "this.groesse_y = " +this.groesse_y +"\n";
		hello += "this.pos_y = " + this.pos_y+"\n";
		hello += "this.pos_x = " + this.pos_x+"\n";
		hello += "wert = " + this.daten.getWert(this.pos_x, this.pos_y)+"\n";
		hello += "k = " +k +"\n";
		for(int i = 0; i < this.groesse_y; i++){
			for(int t = 0; t < this.groesse_x;t++){
				if(bit_ebene[0].werte[t+ i*this.groesse_x])
					hello += 1+ " ";
				else
					hello += 0 +" ";	
			}
			hello += "\n";
		}
		
		try {
		    f1 = new FileWriter("C:\\Dokumente und Einstellungen\\uwe\\Eigene Dateien\\work\\Eclipse\\debug\\"+uwe+"hee.txt");
		   	uwe++;
		    f1.write(hello);
		    f1.close();
		 } catch (IOException e) {
		   System.out.println("Fehler beim Erstellen der Datei");
		}
	}
}
