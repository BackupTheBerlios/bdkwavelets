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
public final class Forward_Symmlet_P6 extends Forward_wavelet_45{
	public static String name = "Symmlet P6";
	
	public static int nr = 8;
	
	static float[] filterA = {	0.0154041093f,
								0.0034907121f,
								-0.1179901113f,
								-0.0483117426f,
								0.4910559424f,
								0.7876411406f,
								0.3379294224f,
								-0.0726375233f,
								-0.0210602923f,
								0.0447249017f,
								0.0017677119f,
								-0.0078007083f};
	
	static float[] filterD = {	-0.0078007083f,
								-0.0017677119f,
								0.0447249017f,
								0.0210602923f,
								-0.0726375233f,
								-0.3379294224f,
								0.7876411406f,
								-0.4910559424f,
								-0.0483117426f,
								0.1179901113f,
								0.0034907121f,
								-0.0154041093f};


	public Forward_Symmlet_P6(){
		super(filterA,0, filterD, 10, name, nr);
	}
}
