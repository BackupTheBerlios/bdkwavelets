/*
 * Created on 23.02.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.wavelet;

import de.datentypen.*;
/**
 * @author Uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class testpro {

	static Forward_wavelet forward = new Forward_NeunSieben();
	static Invers_wavelet  invers = new Invers_NeunSieben();

	public static void main(String[] args) {
		testungerade();
	}


	private static void testgerade(){
		Vektor_float testvektor  = new Vektor_float(10);
		Vektor_float lowvektor  = new Vektor_float(10);
		Vektor_float lowvektord�n  = new Vektor_float(5);
		Vektor_float hightvektor  = new Vektor_float(10);
		Vektor_float hightvektord�n  = new Vektor_float(5);
		Vektor_float ergebnissvektor1 = new Vektor_float(10);
		Vektor_float ergebnissvektor2 = new Vektor_float(10);
		Vektor_float ergebnissvektor = new Vektor_float(10);


		testvektor.werte[0] = 4.0f;
		testvektor.werte[1] = 6.0f;
		testvektor.werte[2] = 6.0f;
		testvektor.werte[3] = 7.0f;
		testvektor.werte[4] = 8.0f;
		testvektor.werte[5] = 9.0f;
		testvektor.werte[6] = 1.0f;
		testvektor.werte[7] = 2.0f;
		testvektor.werte[8] = 3.0f;
		testvektor.werte[9] = 4.0f;
		
		float work;
		
		print("Eingangsvektor",testvektor);
		/*
		 * berrechnen der lowfilter zahlen
		 */
		for(int i = 0; i < testvektor.l�nge ; i++){
			lowvektor.add(forward.FilterA(testvektor,i),i+1);
		}
		print("Lowpassvektor", lowvektor);

		/*
		 * berrechnen der hightfilter zahlen
		 */
		for(int i = 0; i < testvektor.l�nge ; i++){
			hightvektor.add(forward.FilterD(testvektor,i),i+1);
		}
		print("Higthpassvektor", hightvektor);	
		
		/*
		 * Verd�nne die Vektoron
		 */
		for(int i = 0; i < lowvektord�n.l�nge; i++){
			lowvektord�n.werte[i] = lowvektor.werte[i * 2];
			hightvektord�n.werte[i] = hightvektor.werte[i * 2];
		}
		print("Verd�nnt Low",lowvektord�n );
		print("Verd�nnt higth", hightvektord�n);

		for(int i = 0; i < ergebnissvektor1.l�nge; i++ ){
			work = invers.FilterA(lowvektord�n,i,true);
			ergebnissvektor1.add(work, i+1);
		}
		print("ergebnissvektor1", ergebnissvektor1);
		
		for(int i = 0; i < ergebnissvektor2.l�nge; i++ ){
			work = invers.FilterD(hightvektord�n,i,true);
			ergebnissvektor2.add(work, i+1);
		}
		print("ergebnissvektor2", ergebnissvektor2);
		
		for(int i = 0; i < ergebnissvektor.l�nge; i++){
			ergebnissvektor.werte[i] = ergebnissvektor1.werte[i] + ergebnissvektor2.werte[i];
		}
		print("ergebnissvektor" , ergebnissvektor);
			
	}

	private static void testungerade(){
		int l�nge = 13;
		Vektor_float testvektor  = new Vektor_float(l�nge);
		Vektor_float lowvektor  = new Vektor_float(l�nge);
		Vektor_float lowvektord�n  = new Vektor_float(l�nge/2+1);
		Vektor_float hightvektor  = new Vektor_float(l�nge);
		Vektor_float hightvektord�n  = new Vektor_float(l�nge/2+1);
		Vektor_float ergebnissvektor1 = new Vektor_float(l�nge);
		Vektor_float ergebnissvektor2 = new Vektor_float(l�nge);
		Vektor_float ergebnissvektor = new Vektor_float(l�nge);


		testvektor.werte[0] 	= 4.0f;
		testvektor.werte[1] 	= 6.0f;
		testvektor.werte[2] 	= 6.0f;
		testvektor.werte[3] 	= 7.0f;
		testvektor.werte[4] 	= 8.0f;
		testvektor.werte[5] 	= 9.0f;
		testvektor.werte[6] 	= 1.0f;
		testvektor.werte[7] 	= 2.0f;
		testvektor.werte[8] 	= 22.0f;
		testvektor.werte[9] 	= 65.0f;
		testvektor.werte[10] 	= 8.0f;
		testvektor.werte[11] 	= 67.0f;
		testvektor.werte[12] 	= 12.0f;
		
		
		
		float work;
		
		print("Eingangsvektor",testvektor);
		/*
		 * berrechnen der lowfilter zahlen
		 */
		for(int i = 0; i < testvektor.l�nge ; i++){
			lowvektor.add(forward.FilterA(testvektor,i),i+1);
		}
		print("Lowpassvektor", lowvektor);

		/*
		 * berrechnen der hightfilter zahlen
		 */
		for(int i = 0; i < testvektor.l�nge ; i++){
			hightvektor.add(forward.FilterD(testvektor,i),i+1);
		}
		print("Higthpassvektor", hightvektor);	
		
		/*
		 * Verd�nne die Vektoron
		 */
		for(int i = 0; i < lowvektord�n.l�nge; i++){
			lowvektord�n.werte[i] = lowvektor.werte[i * 2];
			hightvektord�n.werte[i] = hightvektor.werte[i * 2];
		}
		print("Verd�nnt Low",lowvektord�n );
		print("Verd�nnt higth", hightvektord�n);

		for(int i = 0; i < ergebnissvektor1.l�nge; i++ ){
			work = invers.FilterA(lowvektord�n,i,false);
			ergebnissvektor1.add(work, i+1);
		}
		print("ergebnissvektor1", ergebnissvektor1);
		
		for(int i = 0; i < ergebnissvektor2.l�nge; i++ ){
			work = invers.FilterD(hightvektord�n,i,false);
			ergebnissvektor2.add(work, i+1);
		}
		print("ergebnissvektor2", ergebnissvektor2);
		
		for(int i = 0; i < ergebnissvektor.l�nge; i++){
			ergebnissvektor.werte[i] = ergebnissvektor1.werte[i] + ergebnissvektor2.werte[i];
		}
		print("ergebnissvektor" , ergebnissvektor);
			
	}


	private static void  print(String info,Vektor_float in){
	
		System.out.println(info);
		for(int i = 0; i < in.l�nge; i++){
			System.out.print(" "+ in.werte[i]);
		}
		System.out.print("\n");
	}
}
