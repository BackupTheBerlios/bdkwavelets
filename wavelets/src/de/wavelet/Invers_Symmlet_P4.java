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
public final class Invers_Symmlet_P4 extends Invers_wavelet_45 {
	
	static float[] filterD = {	-0.0322231058f,
								-0.0126039673f,
								0.0992195425f,
								0.2978577954f,
								-0.8037387520f,
								0.4976186676f,
								0.0296355276f,
								-0.0757657147f};
	
	static float[] filterA = {	-0.0757657147f,
								-0.0296355276f,
								0.4976186676f,
								0.8037387520f,
								0.2978577954f,
								-0.0992195425f,
								-0.0126039673f,
								0.0322231058f};

	public Invers_Symmlet_P4(){
		super(filterA,7,filterD,1,Forward_Symmlet_P4.nr,Forward_Symmlet_P4.name);
	}
}
