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
public final class Forward_Daublet_P6 extends Forward_wavelet_45{
	public static String name = "Daublet P6";
	
	public static int nr = 7;
	
	static float[] filterA = {	0.1115407433f,
								0.4946238904f,
								0.7511339072f,
								0.3152503525f,
								-0.2262646951f,
								-0.1297668668f,
								0.0975016053f,
								0.0275228657f,
								-0.0315820394f,
								0.0005538422f,
								0.0047772575f,
								-0.0010773011f};
	
	static float[] filterD = {	-0.0010773011f,
								-0.0047772575f,
								0.0005538422f,
								0.0315820394f,
								0.0275228657f,
								-0.0975016053f,
								-0.1297668668f,
								0.2262646951f,
								0.3152503525f,
								-0.7511339072f,
								0.4946238904f,
								-0.1115407433f};


	public Forward_Daublet_P6(){
		super(filterA,0, filterD, 10, name, nr);
	}
}
