/*
 * Created on 07.08.2004
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
public final class Invers_Symmlet_P5 extends Invers_wavelet_45 {
	
	static float[] filterA = {	0.0273330683f,
								0.0295194909f,
								-0.0391342493f,
								0.1993975336f,
								0.7234076902f,
								0.6339789630f,
								0.0166021057f,
								-0.1753280897f,
								-0.0211018340f,
								0.0195388827f};
	
	static float[] filterD = {	-0.0195388827f,
								-0.0211018340f,
								0.1753280897f,
								0.0166021057f,
								-0.6339789630f,
								0.7234076902f,
								-0.1993975336f,
								-0.0391342493f,
								-0.0295194909f,
								0.0273330683f};

	public Invers_Symmlet_P5(){
		super(filterA,9,filterD,1,Forward_Symmlet_P5.nr,Forward_Symmlet_P5.name);
	}
}
