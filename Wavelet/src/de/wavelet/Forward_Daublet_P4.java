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
public final class Forward_Daublet_P4 extends Forward_wavelet_45 {
	
	public static String name = "Daublet P4";
	
	public static int nr = 3;
	
	static float[] filterA = {0.2303778133f, 0.7148465707f , 0.6308807677f, -0.0279837692f, -0.1870348118f, 0.0308413819f, 0.0328830117f, -0.0105974018f};
	
	static float[] filterD = {-0.0105974018f, -0.0328830117f, 0.0308413819f, 0.1870348118f, -0.0279837692f, -0.6308807677f, 0.7148465707f, -0.2303778133f};


	public Forward_Daublet_P4(){
		super(filterA,0, filterD, 6, name, nr);
	}
}
