/*
 * Created on 24.05.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.tools;

import java.util.*;
/**
 * @author uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class test {

	private static boolean[] in;
	
	private static boolean[] ergebnis;
	
	private static byte[] zwischenschritt;
	
	private static int anzahl = 7777;
	
	private static int[] kontexte;

	public static void main(String[] args) {
		System.out.println("Wilkommen in der Welt von Hass und Dummheit");
		init();
		int dif = 0;
		MQ_Encoder mq = new MQ_Encoder();
		for(int i = 0; i < anzahl; i++){
			mq.encode(in[i],kontexte[i]);
		}
		zwischenschritt = mq.get_Ergbenis();
		
		if(zwischenschritt == null){
			throw new RuntimeException("zwischenschritt == null");
		}		
		MQ_Decoder mq_d = new MQ_Decoder(zwischenschritt);
	
		for(int i = 0; i < anzahl; i++){
			ergebnis[i] = mq_d.decode(kontexte[i]);
		}

		
		for(int i = 0; i < anzahl; i++){
			if(ergebnis[i] != in[i]){
				dif++;
				System.out.println(""+ergebnis[i]+" != "+ in[i]+"<---- Fehler" );
			}else{
				System.out.println(""+ergebnis[i]+" != "+ in[i] );
			}
		}
		System.out.println("Anzahl der Unterschiedlich gekodeten Symbole = "+dif);
		
	}
	
	
	
	private static void init(){
		Random r = new Random();
		in = new boolean[anzahl];
		ergebnis = new boolean[anzahl];
		kontexte = new int[anzahl];
		for(int i = 0;i < anzahl; i++){
			if(Math.abs(r.nextInt()) % 2 == 1){
				in[i] = false;
			}else{
				in[i] = false;
			}
			in[anzahl % 33] = true;
			kontexte[i] = Math.abs(r.nextInt()) % 18;
			kontexte[i] = 0;
			//kontexte[i] = 0;
		}
	}
	
}
