/*
 * Created on 15.02.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.datentypen;

/**
 * Eine Matrix in der float werte gespeichert werden k�nnen. Mit dieser Klasse soll ein
 * einfacher zugriff auf eine Spalte oder Zeile einer Matrix erreicht werden.
 * 
 * 
 * @version 0.1
 * @author Uwe Br�nen
 */
public class Matrix_float {

	public int breite;

	public int h�he;

	private float[] werte;
	
	private float maxWert = 0.0f;
	
	private float minWert = 0.0f;
	
	private double LOG10 =  Math.log(10.0);


	/**
	 * 
	 * @param breite der neuen Matrix
	 * @param hoehe der neuen Matrix
	 */
	public Matrix_float(int breite, int hoehe) {
		if(breite > 0 || hoehe > 0){
			this.werte = new float[breite * hoehe];
			this.breite = breite;
			this.h�he = hoehe;
		}else{
			throw new RuntimeException("breite = "+ breite+ " h�he = " + hoehe);
		}
	}

	/**
	 * F�gt einen Werte in die Matrix ein. pos_Hor hat einen Wertebereich von 1 bis breit.
	 * pos_Ver hat einen Wertebereich von 1 bis h�he;
	 * 
	 * @param pos_Hor Position horizontal in der Matrix
	 * @param pos_Ver Position vertikal in der Matrix
	 * @param wert einzuf�gender Wert
	 */
	public void add(int pos_Hor, int pos_Ver, float wert) {
		if(pos_Hor <= this.breite && pos_Ver <= this.h�he && pos_Hor >= 1 && pos_Ver >= 1 ){
			pos_Hor--;
			pos_Ver--;
			
			werte[pos_Ver * this.breite + pos_Hor] = wert;
			
			minmaxtest(wert,pos_Ver * this.breite + pos_Hor);
			
		}else{
			throw new RuntimeException(	"\n 0 < pos_Hor < this.h�he      0 < "+ pos_Hor + " < " + this.h�he +
										"\n 0 < pos_Ver < this.breite    0 < "+ pos_Ver + " < " + this.breite);
		}
		
	}

	
	public Vektor_float getReihe(int Reihe) {
		if(0 < Reihe && this.h�he >= Reihe){
		
			Vektor_float BACK = new Vektor_float(this.breite);
			Reihe--;
			Reihe = Reihe * this.breite;
			
			for(int i = 0; i < this.breite; i++){
				BACK.add(werte[Reihe],i+1);
				Reihe++;
			}
			return BACK;
			
		}else{
			throw new RuntimeException(	  " 0 < Reihe <= this.breite  0 < "+ Reihe + " < " + this.breite);
		}
	}

	public Vektor_float getSpalte(int Spalte) {
		if(0 < Spalte && Spalte <= this.breite){
			Vektor_float BACK = new Vektor_float(this.h�he);
			Spalte--;
			for(int i = 0; i < this.h�he; i++){
				BACK.add(werte[Spalte],i+1);
				Spalte += this.breite;
			}
			return BACK;
			
		}else{
			throw new RuntimeException(	  " 0 < Spalte <= this.h�he --->  0 < "+ Spalte + " < " + this.breite);
		}
	}
		
	public void add(float wert, int Pos){
		werte[Pos-1] = wert;

		minmaxtest(wert,Pos-1);
		
	}

	public float get(int PosX, int PosY) {
	if(PosX <= this.breite && PosY <= this.h�he && PosX >= 1 && PosY >= 1 ){
		PosX--;
		PosY--;		
		return werte[PosY*breite +  PosX];
	}else{
		throw new RuntimeException(	  " 0 < pos_Hor < this.h�he  0 < "+ PosX + " < " + this.h�he +
									"\n 0 < pos_Ver < this.breite  0 < "+ PosY + " < " + this.breite);
	}
	}

	private void minmaxtest(float in, int i){

		if(in< minWert ){
			minWert = in;
		}
		if(in > maxWert){
			maxWert = in;
		}	
	}

	public void addReihe(int Reihe,Vektor_float vektor) {
		if(Reihe > 0 && Reihe <= h�he && vektor.l�nge == breite){
			for(int i = 0; i < vektor.l�nge; i++){
				add(i+1, Reihe, vektor.werte[i]);
			}		
		}else{
			throw new RuntimeException(	"\nReihe = " + Reihe +
										"\nh�he  = " +h�he  + 
										"\nvektor.l�nge = "+vektor.l�nge+
										"\nbreite" +breite);
		}
	}

	public void addSpalte(int Spalte, Vektor_float vektor) {
		if(Spalte > 0 && Spalte <= breite && vektor.l�nge == h�he){
			for(int i = 0; i < vektor.l�nge; i++){
				add(Spalte, i+1, vektor.werte[i]);
			}				
		}else{
			throw new RuntimeException(	"\nSpalte = " + Spalte +
										"\nh�he  = " +h�he  + 
										"\nvektor.l�nge = "+vektor.l�nge+
										"\nbreite " +breite);			
		}
	}


	/*
	 *  Die �bergeben Matrix sollte die originale Matrix sein
	 */
	public float MSE(Matrix_float in){
		float BACK = 0;
			if(this.breite == in.breite && this.h�he == in.h�he){
				for(int i = 0; i < this.werte.length; i++){
					BACK += this.quadrat(this.werte(i) - in.werte(i));
				}
				BACK /=  this.werte.length;
			}else{
				throw new RuntimeException("\nFehler in Matrix_Float.MSE(Matrix_float)");
			}
			
		return BACK;
	}
	
	/*
	 *  Die �bergeben Matrix sollte die originale Matrix sein
	 */
	public float MAD(Matrix_float in){
		float BACK = 0;
			if(this.breite == in.breite && this.h�he == in.h�he){
				for(int i = 0; i < this.werte.length; i++){
					BACK += Math.abs(this.werte(i) - in.werte(i));
				}
				BACK /=  this.werte.length;
			}else{
				throw new RuntimeException("\nFehler in Matrix_Float.MAD(Matrix_float)");
			}
			
		return BACK;
	}
	
	/*
	 *  Die �bergeben Matrix sollte die originale Matrix sein
	 */
	public float SAD(Matrix_float in){
		float BACK = 0;
			if(this.breite == in.breite && this.h�he == in.h�he){
				for(int i = 0; i < this.werte.length; i++){
					BACK += Math.abs(this.werte(i) - in.werte(i));
				}
				
			}else{
				throw new RuntimeException("\nFehler in Matrix_Float.SAD(Matrix_float)");
			}
			
		return BACK;
	}

	/*
		 *  Die �bergeben Matrix sollte die originale Matrix sein
		 */
		public float SNR(Matrix_float in){
			float BACK = 0;
			float xl = 0;
			float o2x = 0;
			float o2e = 0;
			
			
				if(this.breite == in.breite && this.h�he == in.h�he){
					
					for(int i = 0; i < this.werte.length; i++){
						xl += in.werte(i);
					}					
					xl  /=  this.werte.length;
					
					for(int i = 0; i < this.werte.length; i++){
						o2x += this.quadrat(in.werte(i) - xl);
					}							
					o2x  /=  this.werte.length;
					
					o2e = this.MSE(in);

					BACK =(float) (10 * 	Math.log((double)(o2x/o2e ) )/this.LOG10);					

				}else{
					throw new RuntimeException("\nFehler in Matrix_Float.SAD(Matrix_float)");
				}
			return BACK;
		}
	
	public float getminWert(){
		return this.minWert;
	}
	
	public float getmaxWert(){
		return this.maxWert;
	}
	
	public float werte(int Pos){
		return this.werte[Pos];
	}
	
	public int wertelength(){
		return this.werte.length;
	}
	
	public void update(Matrix_float in) {
		this.breite = in.breite;

		this.h�he = in.h�he;

		this.werte = new float[in.wertelength()];
		for(int i = 0; i < this.werte.length; i++){
			this.werte[i] = in.werte(i);
		}
	
		this.maxWert = in.getmaxWert();
	
		this.minWert = in.getminWert();
		
	}
	
	private float quadrat(float in){
		return in * in;
	}

	public Matrix_float diff(Matrix_float in){
		Matrix_float BACK ;
		if(this.breite == in.breite && this.h�he == in.h�he){
			BACK = new Matrix_float(this.breite, this.h�he);
			for(int i = 0; i < this.werte.length; i++){
				BACK.add(Math.abs(this.werte(i) - in.werte(i)),i+1);
			}
			//BACK.setmaxWert((float)5);
		}else{
			throw new RuntimeException("\nFehler in Matrix_Float.SAD(Matrix_float)");
		}		
		return BACK;
	}
	
	public void setmaxWert(float in){
		this.maxWert = in;
	}

}
