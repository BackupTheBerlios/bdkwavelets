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
public final class Forward_Daublet_P5 extends Forward_wavelet_45 {
	public static String name = "Daublet P5";
	
	public static int nr = 5;
	
	static float[] filterA = {	0.1601023980f,
								0.6038292698f,
								0.7243085287f,
								0.1384281462f,
								-0.2422948877f,
								-0.0322448693f,
								0.0775714938f,
								-0.0062414902f,
								-0.0125807520f,
								0.0033357253f};
	
	static float[] filterD = {	0.0033357253f,
								0.0125807520f,
								-0.0062414902f,
								-0.0775714938f,
								-0.0322448693f,
								0.2422948877f,
								0.1384281462f,
								-0.7243085287f,
								0.6038292698f,
								-0.1601023980f};


	public Forward_Daublet_P5(){
		super(filterA,0, filterD, 8, name, nr);
	}
}
