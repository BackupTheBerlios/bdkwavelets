/*
 * Created on 30.05.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.wavelet.generator;


import java.io.*;
/**
 * @author uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public final class Start_Filtergenerator {

	// hs
	double[] A = {-0.0645,  -0.0407, 0.4181, 0.7885, 0.4181, -0.0407, -0.0645};
	//double[] At ={0.707106781, 0.707106781};
	
	// h
	double[] At = { 0.0378, -0.0238, -0.1106, 0.3774, 0.8527, 0.3774, -0.1106, -0.0238, 0.0378};
	//double[] A = {0.70710678, 0.707106781};
	
	int pos_At = 4;
	
	int pos_A = 3;

	String Path = "C:\\Dokumente und Einstellungen\\uwe\\Eigene Dateien\\work\\Eclipse\\TestOrdner";


	public static void main(String[] args) {
		new Start_Filtergenerator();
	}
	
	public Start_Filtergenerator(){
		Filter t = new Filter(pos_A, A, pos_At, At);
		this.writeFile(t);
		
		//System.out.println("Filter A  = "  +printFilter(t.get_FilterA() ,t.get_Position_z0_von_A() ));
		//System.out.println("Filter D  = "  +printFilter(t.get_FilterD() ,t.get_Position_z0_von_D() ));
		//System.out.println("Filter At  = " +printFilter(t.get_FilterAt(),t.get_Position_z0_von_At()));
		//System.out.println("Filter Dt  = " +printFilter(t.get_FilterDt(),t.get_Position_z0_von_Dt()));		


		//System.out.println("Filter A  = "  +printFilter(t.get_FilterA() ,t.get_Position_z0_von_A() , "C", 394));
		//System.out.println("Filter D  = "  +printFilter(t.get_FilterD() ,t.get_Position_z0_von_D() , "C", 394));
		//System.out.println("Filter At  = " +printFilter(t.get_FilterAt(),t.get_Position_z0_von_At(), "H", 394));
		//System.out.println("Filter Dt  = " +printFilter(t.get_FilterDt(),t.get_Position_z0_von_Dt(), "I", 394));		
	}
	
	private String printFilter(double[] f, int pos){
		String Back = new String();
		int z_hoch = -pos;
		boolean first = true;
		
		for(int i = 0; i < f.length; i++){
			if(z_hoch == 0){
				if(first){
					Back += f[i];
					first = false;
				}else{
					Back += " + "+ f[i];
				}
				z_hoch++;
			}else{
					if(first){
						Back += f[i]+"z^"+z_hoch;
						first = false;
					}else{
						Back += " + "+ f[i]+"z^"+z_hoch;
					}
					z_hoch++;				
			}
		}
		return Back;
	}

	private String printFilter(double[] f, int pos, String ff, int nr){
		String Back = new String();
		int z_hoch = -pos;
		boolean first = true;
		
		for(int i = 0; i < f.length; i++){

					if(first){
						Back += f[i] + "*" + ff + (nr + z_hoch);
						first = false;
					}else{
						if(f[i] < 0)
							Back += 	   f[i]+"*" +  ff + (nr + z_hoch);
						else
							Back += " + "+ f[i]+"*" +  ff + (nr + z_hoch);
					}
				z_hoch++;				
			
		}
		Back = Back.replace('.', ',');
		return Back;
	}
	
	
	private void writeFile(Filter t){
		String KlassenName = "TestWavelet";
		String PackageName = "de.wavelet;";
		Java_source_generator source = new Java_source_generator(t,KlassenName,PackageName);

		try {
			DataOutputStream out = new DataOutputStream(new FileOutputStream(this.Path+"\\"+KlassenName+".java"));
		       
		    out.writeUTF(source.getSource());
		       
		       out.close();
		     } catch (Exception e) {
		       System.err.println(e.toString());
		       System.exit(1);
		     }		
		
	}
}
