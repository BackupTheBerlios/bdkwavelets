/*
 * Created on 16.02.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.datentypen;

/**
 * Zur Speicherung einer Kachel und deren Subbandknoten. 
 * 
 * @author Uwe Brünen
 * @version 0.1 
 */
public final class Kachel extends Matrix_float {

	public int Nr;

	public Subband_Knoten subband;


	private Komponenten_Kacheln[] kachel;

	public Kachel(int breite, int höhe, int Nr) {
		super(breite, höhe);
		this.Nr = Nr;
	}
	






}
