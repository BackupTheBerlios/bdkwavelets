/*
 * Created on 06.08.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.wavelet;

/**
 * @author uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public final class Forward_Symmlet_P4 extends Forward_wavelet_45 {
	
	public static String name = "Symmlet P4";
	
	public static int nr = 4;
	
	static float[] filterA = {	0.0322231058f,
								-0.0126039673f,
								-0.0992195425f,
								0.2978577954f,
								0.8037387520f,
								0.4976186676f,
								-0.0296355276f,
								-0.0757657147f };
	
	static float[] filterD = {	-0.0757657147f,
								0.0296355276f,
								0.4976186676f,
								-0.8037387520f,
								0.2978577954f,
								0.0992195425f,
								-0.0126039673f,
								-0.0322231058f};


	public Forward_Symmlet_P4(){
		super(filterA,0, filterD, 6, name, nr);
	}
}
