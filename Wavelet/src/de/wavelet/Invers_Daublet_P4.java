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
public final class Invers_Daublet_P4 extends Invers_wavelet_45 {
	
	static float[] filterD = {	-0.2303778133f,
								0.7148465707f,
								-0.6308807677f,
								-0.0279837692f,
								0.1870348118f,
								0.0308413819f,
								-0.0328830117f,
								-0.0105974018f };
	
	static float[] filterA = {	-0.0105974018f,
								0.0328830117f,
								0.0308413819f,
								-0.1870348118f,
								-0.0279837692f,
								0.6308807677f,
								0.7148465707f,
								0.2303778133f};

	public Invers_Daublet_P4(){
		super(filterA,7,filterD,1,Forward_Daublet_P4.nr,Forward_Daublet_P4.name);
	}
}
