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
public final class Invers_Daublet_P6 extends Invers_wavelet_45 {
	
	static float[] filterD = {	-0.1115407433f,
								0.4946238904f,
								-0.7511339072f,
								0.3152503525f,
								0.2262646951f,
								-0.1297668668f,
								-0.0975016053f,
								0.0275228657f,
								0.0315820394f,
								0.0005538422f,
								-0.0047772575f,
								-0.0010773011f};
	
	static float[] filterA = {	-0.0010773011f,
								0.0047772575f,
								0.0005538422f,
								-0.0315820394f,
								0.0275228657f,
								0.0975016053f,
								-0.1297668668f,
								-0.2262646951f,
								0.3152503525f,
								0.7511339072f,
								0.4946238904f,
								0.1115407433f};

	public Invers_Daublet_P6(){
		super(filterA,11,filterD,1,Forward_Daublet_P6.nr,Forward_Daublet_P6.name);
	}
}
