/*
 * Created on 11.10.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.gui;

import java.applet.Applet;
import java.util.StringTokenizer;

/**
 * @author uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public final class javacheck extends Applet {

	private int v1 = 0;
	private int v2 = 0;

	public void init(){
		StringTokenizer str = new StringTokenizer(System.getProperty("java.version"), ".");

		v1 = Integer.parseInt(str.nextToken());
		v2 = Integer.parseInt(str.nextToken());		

	}
	
	public int getV1(){
		return v1;
	}
	
	
	public int getV2(){
		return v2;
	}
	
}
