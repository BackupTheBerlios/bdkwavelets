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
public final class Invers_HaarWavelet2 extends Invers_wavelet_45 {

	static float[] filterD = {-1/((float) Math.sqrt(2.0)), 1/((float) Math.sqrt(2.0))};
	
	static float[] filterA = {1/((float) Math.sqrt(2.0)), 1/((float) Math.sqrt(2.0))};

	public Invers_HaarWavelet2(){
		super(filterA,1,filterD,1,2,"Haar2");
	}

}
