/*
 * Created on 05.08.2004
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
public final class Forward_waveletHaar2 extends Forward_wavelet_45 {
	
	
	
	static float[] filterD = {1/((float) Math.sqrt(2.0)), -1/((float) Math.sqrt(2.0))};
	
	static float[] filterA = {1/((float) Math.sqrt(2.0)), 1/((float) Math.sqrt(2.0))};

	public Forward_waveletHaar2(){
		super(filterA,0, filterD, 0, "Haar2", 2);
	}
}
