/*
 * Created on 14.02.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.datentypen;

/**
 * @author Uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Matrix_short {

	private short[] werte;
	
	/*
	 *  true 	= -
	 *  false 	= +
	 */
	private boolean[] vorzeichen;

	public int breite;

	public int h�he;

	public void addwert(short wert,boolean vorzeichen, int pos){
		werte[pos] =(short) Math.abs(wert);
		this.vorzeichen[pos] = vorzeichen;
	}


	public Matrix_short(int h�he, int breite) {
		if(h�he > 0 && breite > 0){
			werte = new short[breite * h�he];
			vorzeichen = new boolean[breite * h�he];
			this.breite = breite;
			this.h�he = h�he;
		}else{
			throw new RuntimeException("breite = "+ breite+ " h�he = " + h�he);
		}
	}


	public int getbreite(){
		return breite;
	}
	
	public int geth�he(){
		return h�he;
	}

	public int size(){
		return werte.length;
	}

	public short getWert(int pos){
		return werte[pos];
	}
	
	public boolean getSign(int pos){
		return vorzeichen[pos];
	}
	
	public boolean getSign(int pos_x, int pos_y){
		return vorzeichen[pos_y * this.breite + pos_x];
	}

	
	protected boolean sign(short in){
		if(in < 0){
			return true;
		}else{
			return false;
		}	
	}
	
	protected boolean sign(float in){
		if(in < 0){
			return true;
		}else{
			return false;
		}	
	}


	public short getWert(int pos_x, int pos_y){
		if(pos_y >=0 && pos_y < h�he && pos_x >= 0 && pos_x< breite){
			return this.werte[pos_y * breite + pos_x];
		}else{
			throw new RuntimeException(	"\npos_y = " + pos_y +
										"\npos_x = " + pos_x +
										"\nh�he  = " + h�he +
										"\nbreite= " +breite );
		}
	}
	
}
