/*
 * Created on 15.02.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.datentypen;

/**
 * Eine Matrix in der float werte gespeichert werden können. Mit dieser Klasse soll ein
 * einfacher zugriff auf eine Spalte oder Zeile einer Matrix erreicht werden.
 * 
 * 
 * @version 0.1
 * @author Uwe Brünen
 */
public class Matrix_float {

	public int breite;

	public int höhe;

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
			this.höhe = hoehe;
		}else{
			throw new RuntimeException("breite = "+ breite+ " höhe = " + hoehe);
		}
	}

	/**
	 * Fügt einen Werte in die Matrix ein. pos_Hor hat einen Wertebereich von 1 bis breit.
	 * pos_Ver hat einen Wertebereich von 1 bis höhe;
	 * 
	 * @param pos_Hor Position horizontal in der Matrix
	 * @param pos_Ver Position vertikal in der Matrix
	 * @param wert einzufügender Wert
	 */
	public void add(int pos_Hor, int pos_Ver, float wert) {
		if(pos_Hor <= this.breite && pos_Ver <= this.höhe && pos_Hor >= 1 && pos_Ver >= 1 ){
			pos_Hor--;
			pos_Ver--;
			
			werte[pos_Ver * this.breite + pos_Hor] = wert;
			
			minmaxtest(wert,pos_Ver * this.breite + pos_Hor);
			
		}else{
			throw new RuntimeException(	"\n 0 < pos_Hor < this.höhe      0 < "+ pos_Hor + " < " + this.höhe +
										"\n 0 < pos_Ver < this.breite    0 < "+ pos_Ver + " < " + this.breite);
		}
		
	}

	
	public Vektor_float getReihe(int Reihe) {
		if(0 < Reihe && this.höhe >= Reihe){
		
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
			Vektor_float BACK = new Vektor_float(this.höhe);
			Spalte--;
			for(int i = 0; i < this.höhe; i++){
				BACK.add(werte[Spalte],i+1);
				Spalte += this.breite;
			}
			return BACK;
			
		}else{
			throw new RuntimeException(	  " 0 < Spalte <= this.höhe --->  0 < "+ Spalte + " < " + this.breite);
		}
	}
		
	public void add(float wert, int Pos){
		werte[Pos-1] = wert;

		minmaxtest(wert,Pos-1);
		
	}

	public float get(int PosX, int PosY) {
	if(PosX <= this.breite && PosY <= this.höhe && PosX >= 1 && PosY >= 1 ){
		PosX--;
		PosY--;		
		return werte[PosY*breite +  PosX];
	}else{
		throw new RuntimeException(	  " 0 < pos_Hor < this.höhe  0 < "+ PosX + " < " + this.höhe +
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
		if(Reihe > 0 && Reihe <= höhe && vektor.länge == breite){
			for(int i = 0; i < vektor.länge; i++){
				add(i+1, Reihe, vektor.werte[i]);
			}		
		}else{
			throw new RuntimeException(	"\nReihe = " + Reihe +
										"\nhöhe  = " +höhe  + 
										"\nvektor.länge = "+vektor.länge+
										"\nbreite" +breite);
		}
	}

	public void addSpalte(int Spalte, Vektor_float vektor) {
		if(Spalte > 0 && Spalte <= breite && vektor.länge == höhe){
			for(int i = 0; i < vektor.länge; i++){
				add(Spalte, i+1, vektor.werte[i]);
			}				
		}else{
			throw new RuntimeException(	"\nSpalte = " + Spalte +
										"\nhöhe  = " +höhe  + 
										"\nvektor.länge = "+vektor.länge+
										"\nbreite " +breite);			
		}
	}


	/*
	 *  Die übergeben Matrix sollte die originale Matrix sein
	 */
	public float MSE(Matrix_float in){
		float BACK = 0;
			if(this.breite == in.breite && this.höhe == in.höhe){
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
	 *  Die übergeben Matrix sollte die originale Matrix sein
	 */
	public float MAD(Matrix_float in){
		float BACK = 0;
			if(this.breite == in.breite && this.höhe == in.höhe){
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
	 *  Die übergeben Matrix sollte die originale Matrix sein
	 */
	public float SAD(Matrix_float in){
		float BACK = 0;
			if(this.breite == in.breite && this.höhe == in.höhe){
				for(int i = 0; i < this.werte.length; i++){
					BACK += Math.abs(this.werte(i) - in.werte(i));
				}
				
			}else{
				throw new RuntimeException("\nFehler in Matrix_Float.SAD(Matrix_float)");
			}
			
		return BACK;
	}

	/*
		 *  Die übergeben Matrix sollte die originale Matrix sein
		 */
		public float SNR(Matrix_float in){
			float BACK = 0;
			float xl = 0;
			float o2x = 0;
			float o2e = 0;
			
			
				if(this.breite == in.breite && this.höhe == in.höhe){
					
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

		this.höhe = in.höhe;

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
		if(this.breite == in.breite && this.höhe == in.höhe){
			BACK = new Matrix_float(this.breite, this.höhe);
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
