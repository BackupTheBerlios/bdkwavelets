 /*
 * Created on 08.05.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.datentypen;

import de.tools.*;

/**
 * @author Uwe
 * @version 0.0.1
 * 	
 * Eine Bit Ebene speichert einzelne bits einer Ebene aus dem Codeblock.
 * Alle Funktion zur Codierung mittels EBCOT sind hier enthalten.
 */
public final class Bit_ebene {
	
	/**
	 * Die einzelnen Bits in der Ebene werden hier gespeichert.
	 */
	public boolean[] werte;
	
	/**
	 * Die Breite der Ebene in x-Richtung, identisch mit der Breite
	 * des CodeBlocks.
	 */
	private int breite_x;
	
	private int höhe_y;

	private Code_Block code_block;
	 
	private MQ_Encoder mq;
	
	public boolean hat_Einsen = false;
	 
	/*
	 * Erstellt eine Bit-Ebene, ebene bestimmt welche Ebene, ist ebene kleine null so wird die ebene
	 * aus den Vorzeichen Codiert.
	 */ 
	public Bit_ebene(Subband_Daten_diskret daten,Code_Block code_block, int groesse_x, int groesse_y, int pos_x, int pos_y, int ebene, MQ_Encoder mq){
		if(groesse_x + pos_x <= daten.getbreite() && groesse_y + pos_y <= daten.gethöhe()){
			
			this.mq = mq;
			this.breite_x = groesse_x;
			this.höhe_y = groesse_y;
			this.code_block = code_block;
			werte = new boolean[this.breite_x * this.höhe_y];
			
			boolean help = false;
			// Wenn ebene groesser oder gleich null ist so
			// wird der Wert codiert ist ebene kleiner null so wird das
			// Vorzeichen Codiert.
			if(ebene >= 0){
				for(int x = 0; x < breite_x; x++){
					for(int y = 0; y < höhe_y; y++){
						help = is_bit_set(daten.getWert(pos_x + x, pos_y + y),ebene );
						addwert(x, y, help);
						if(help){
							hat_Einsen = true;
						}
						
					}
				}
			}else{
				for(int x = 0; x < breite_x; x++){
					for(int y = 0; y < höhe_y; y++){
						addwert(x, y, daten.getSign(pos_x + x, pos_y + y));
					}
				}						
			}
			
		}else{
			throw new RuntimeException(	"\ngroesse_x = " + groesse_x +
										"\ngroesse_y = " + groesse_y + 
										"\npos_x = " + pos_x +
										"\npos_y = " + pos_y +
										"\ndaten.getbreite() = " + daten.getbreite() +
										"\ndaten.gethöhe() = " +daten.gethöhe());
		}
	}
	
	
	
	/*
	 * Erstellt eine leere Bit-Ebene
	 */
	public Bit_ebene(Code_Block code_Block, int groesse_x, int groesse_y){
		this.code_block = code_Block;
		this.breite_x = groesse_x;
		this.höhe_y = groesse_y;
		werte = new boolean[breite_x * höhe_y];		


	}
	
	public Bit_ebene(Code_Block code_block, int groesse_x, int groesse_y,  int ebene, MQ_Decoder mq){
	}
	
	/*
	 * Gibt das Bit zurück, dass an der angegebenen Stellen sein soll.
	 */
	public boolean get_Bit(int pos_x, int pos_y){
		return werte[pos_y * this.breite_x + pos_x];
	}
	
	/*
	 * Die Breite_x der Bit-Ebene
	 */
	public int getBreite(){
		return this.breite_x;
	}
	
	/*
	 * Die Höhe_y wird zurückgegeben.
	 */
	public int getHöhe(){
		return this.höhe_y;
	}
	
	
	/*
	 * Die Funktion teste ob in dem wert ein bit an der mit ebene speziefizierten ebene 
	 * gesetzt ist.
	 */
	private boolean is_bit_set(short wert, int ebene){
		int eins = 1;
		if(((int)wert & (eins<< ebene)) != 0){
			return true;
		}else{
			return false;
		}	
	}

	/*
	 * Die Funktion kopiert der Wert an die Stelle, die durch x und y angegeben wird.
	 */
	public void addwert(int pos_x, int pos_y, boolean wert){
		werte[pos_y * breite_x + pos_x] = wert;
	}
	
	private int runnn = 0;
	
	public Code_Block encode(Code_Block code_block, boolean oberste){
		
		runnn++;
		if(this.höhe_y == 1 && this.breite_x == 1){
			System.out.println("Du Dei");
		}
		this.code_block = code_block;
		if(!oberste){
			this.encode_significance();
			this.encode_magnitude();
		}
		this.encode_cleanup();
		
		
		return this.code_block; 
	}
	


	private int signi(int pos_x, int pos_y){
		int k_v = 0;
		int k_h = 0;
		int k_d = 0;
		
		int k_sig = 0;
		
		//Berechne k_v
		if(ist_bit_signifikant(pos_x, pos_y - 1)){
			k_v++;
		}
		if(ist_bit_signifikant(pos_x, pos_y + 1)){
			k_v++;
		}
		
		//Berechne k_h
		if(ist_bit_signifikant(pos_x - 1, pos_y)){
			k_h++;
		}
		if(ist_bit_signifikant(pos_x + 1, pos_y)){
			k_h++;
		}

		//Berechne k_d
		if(ist_bit_signifikant(pos_x + 1, pos_y + 1)){
			k_d++;
		}
		if(ist_bit_signifikant(pos_x + 1, pos_y - 1)){
			k_d++;
		}		
		if(ist_bit_signifikant(pos_x - 1, pos_y + 1)){
			k_d++;
		}
		if(ist_bit_signifikant(pos_x - 1, pos_y - 1)){
			k_d++;
		}		
		
		if(this.code_block.type == Code_Block.LL || this.code_block.type == Code_Block.LH){
			if(k_h == 2){
				k_sig = 8;
			}else if(k_h == 1 && k_v >= 1){
				k_sig = 7;
			}else if(k_h == 1 && k_v == 0 && k_d >= 1){
				k_sig = 6;
			}else if(k_h == 1 && k_v == 0 && k_d == 0){
				k_sig = 5;
			}else if(k_h == 0 && k_v == 2 ){
				k_sig = 4;
			}else if(k_h == 0 && k_v == 1 ){
				k_sig = 3;
			}else if(k_h == 0 && k_v == 0 && k_d >=2 ){
				k_sig = 2;
			}else if(k_h == 0 && k_v == 0 && k_d ==1 ){
				k_sig = 1;
			}else if(k_h == 0 && k_v == 0 && k_d ==0 ){
				k_sig = 0;
			}		
		}else if(this.code_block.type == Code_Block.HL){
			if(k_v == 2){
				k_sig = 8;
			}else if(k_h >= 1 && k_v == 1){
				k_sig = 7;
			}else if(k_h == 0 && k_v == 1 && k_d >= 1){
				k_sig = 6;
			}else if(k_h == 0 && k_v == 1 && k_d == 0){
				k_sig = 5;
			}else if(k_h == 2 && k_v == 0 ){
				k_sig = 4;
			}else if(k_h == 1 && k_v == 0 ){
				k_sig = 3;
			}else if(k_h == 0 && k_v == 0 && k_d >= 2){
				k_sig = 2;
			}else if(k_h == 0 && k_v == 0 && k_d == 1){
				k_sig = 1;
			}else if(k_h == 0 && k_v == 0 && k_d == 0){
				k_sig = 0;
			}
		}else{
			int k_temp = k_h + k_v;
			
			if(k_d >= 3){
				k_sig = 8;
			}else if(k_d == 2 && k_temp >= 1){
				k_sig = 7;
			}else if(k_d == 2 && k_temp >= 0){
				k_sig = 6;
			}else if(k_d == 1 && k_temp >= 2){
				k_sig = 5;
			}else if(k_d == 1 && k_temp == 1){
				k_sig = 4;
			}else if(k_d == 1 && k_temp == 0){
				k_sig = 3;
			}else if(k_d == 0 && k_temp >= 2){
				k_sig = 2;
			}else if(k_d == 0 && k_temp == 1){
				k_sig = 1;
			}else if(k_d == 0 && k_temp == 0){
				k_sig = 0;
			}
			
		}
		
		
		
		return k_sig;
	}
	
	/*
	 * Prüft ob das Angegebene Bit den Status significant hat. S
	 * Sollte die Position ausserhalb der Bit-Ebene liegen  ist der Status immer
	 * insignifikant.
	 */
	public boolean ist_bit_signifikant(int pos_x, int pos_y){
		if(pos_x < 0 || pos_y < 0){
			return false;
		}else if(pos_x >= code_block.signifikant.getBreite() || pos_y>= code_block.signifikant.getHöhe()){
			return false;
		}else{
			return this.code_block.signifikant.get_Bit(pos_x, pos_y);
		}
		
	}

	private boolean getWert(int pos_x, int pos_y){
		try{
			return werte[pos_y * this.breite_x + pos_x];
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println(	"\nthis.breite_x = " + this.breite_x +
								"\nthis.höhe_y   = " + this.höhe_y +
								"\npos_x         = " + pos_x+
								"\npos_y         = " + pos_y		);
			return false;
		}
	}
	
	/*
	 * Wandelt ein int Wert in einen boolean um.
	 */
	private boolean int_to_boolean(int i){
		if(i == 0){
			return false;
		}else{
			return true;
		}
	}
	
	
	
	/*
	 * Zur Codierung des Vorzeichens. Seite 358 
	 */
	public int get_X_v(int pos_x, int pos_y){
		int temp_x_v = 0;
		if(this.code_block.signifikant.ist_bit_signifikant(pos_x, pos_y + 1) ){
		// Test ob der Wert singnifikant ist. Für den Fall das der Wert
		// nicht mehr im Code_Block liegt ist der Wert insignifikant
			if(this.code_block.vorzeichen.getWert(pos_x , pos_y+1)){
			// Wert ist signifikant. Wert hat ein Vorzeichen.
				temp_x_v--;
			}else{
				temp_x_v++;
			}
			
		}
		
		if(this.code_block.signifikant.ist_bit_signifikant(pos_x , pos_y -1 ) ){
		// Test ob der Wert singnifikant ist. Für den Fall das der Wert
		// nicht mehr im Code_Block liegt ist der Wert insignifikant
			if(this.code_block.vorzeichen.getWert(pos_x , pos_y - 1)){
			// Wert ist signifikant. Wert hat ein Vorzeichen.
				temp_x_v--;
			}else{
				temp_x_v++;
			}
		}
		
		if(temp_x_v == 0){
			return 0;
		}else{
			if(temp_x_v < 0){
				return -1;
			}else{
				return 1;
			}
		}

	}

	/*
	 *beschreibung auf Seite 358
	 */
	public int get_X_h(int pos_x, int pos_y){
		int temp_x_h = 0;
		try{
		if(this.code_block.signifikant.ist_bit_signifikant(pos_x+ 1, pos_y ) ){
		// Test ob der Wert singnifikant ist. Für den Fall das der Wert
		// nicht mehr im Code_Block liegt ist der Wert insignifikant
			if(this.code_block.vorzeichen.getWert(pos_x+ 1, pos_y)){
			// Wert ist signifikant. Wert hat ein Vorzeichen.
				temp_x_h--;
			}else{
				temp_x_h++;
			}
		}
		}catch(NullPointerException e){
			if(this.code_block == null){
				System.out.println("this.code_block == null");	
			}
			if(this.code_block.signifikant == null){
				System.out.println("this.code_block.signifikant == null");	
			}
		}
		
		if(this.code_block.signifikant.ist_bit_signifikant(pos_x - 1, pos_y ) ){
		// Test ob der Wert singnifikant ist. Für den Fall das der Wert
		// nicht mehr im Code_Block liegt ist der Wert insignifikant
			if(this.code_block.vorzeichen.getWert(pos_x - 1, pos_y)){
			// Wert ist signifikant. Wert hat ein Vorzeichen.
				temp_x_h--;
			}else{
				temp_x_h++;
			}
		}
		
		if(temp_x_h == 0){
			return 0;
		}else{
			if(temp_x_h < 0){
				return -1;
			}else{
				return 1;
			}
		}

	}
	
	private void encode_Sign(boolean symbol, int x_h, int x_v){
		int k_sign = 0;
		int x_flip = 0;
		// Aus Tabelle 8.2 x_flip und k_sign berechnen.
		
		if(x_h == -1){
			if(x_v == -1){
				k_sign = 14;
				x_flip = -1;	
			}else if(x_v == 0){
				k_sign = 12;
				x_flip = -1;				
			}else{
				k_sign = 12;
				x_flip = -1;					
			}
			
		}else if(x_h == 0){
			if(x_v == -1){
				k_sign = 11;
				x_flip = -1;						
			}else if(x_v == 0){
				k_sign = 10;
				x_flip = 1;				
			}else{
				k_sign = 11;
				x_flip = 1;					
			}	
					
		}else{
			if(x_v == -1){
				k_sign = 12;
				x_flip = 1;						
			}else if(x_v == 0){
				k_sign = 13;
				x_flip = 1;					
			}else{
				k_sign = 14;
				x_flip = 1;					
			}			
		}
		
		if(symbol){
			if(x_flip == -1){
				mq.encode(false, k_sign);
			}else{
				mq.encode(true, k_sign);
			}
		}else{
			if(x_flip == 1){
				mq.encode(false, k_sign);
			}else{
				mq.encode(true, k_sign);
			}
			
		}
		
		
	}


	/*
	 * Beschreibung auf Seite 491. Encode_Pass0
	 */	
	private void encode_significance(){
		int aktuelle_pos_x = 0;
		int aktuelle_pos_y = 0;
		int topPunkt = 0;
		boolean lauf = true;
		
		do{
			
			
			if(!this.code_block.signifikant.get_Bit(aktuelle_pos_x, aktuelle_pos_y) && signi(aktuelle_pos_x, aktuelle_pos_y) > 0){
			// Aktuelle Position ist insignifikant und hat signifikante Nachbarn
				mq.encode(get_Bit(aktuelle_pos_x, aktuelle_pos_y), signi(aktuelle_pos_x, aktuelle_pos_y));
				if(get_Bit(aktuelle_pos_x, aktuelle_pos_y)){
				//Die aktuelle Position hat eine 1 und wechselt von
				//insignifikant zu signifikant und das Vorzeichen wird kodiert.
					this.code_block.signifikant.addwert(aktuelle_pos_x, aktuelle_pos_y, true);
					this.encode_Sign(	this.code_block.vorzeichen.get_Bit(aktuelle_pos_x, aktuelle_pos_y),
										this.code_block.vorzeichen.get_X_h(aktuelle_pos_x, aktuelle_pos_y), 
										this.code_block.vorzeichen.get_X_v(aktuelle_pos_x, aktuelle_pos_y));

				}
				this.code_block.pi.addwert(aktuelle_pos_x, aktuelle_pos_y, true);

			}else{
				this.code_block.pi.addwert(aktuelle_pos_x, aktuelle_pos_y, false);			
			}
			
			
			
			/***************************************************************
			 *  ZUM DURCHLLAUFEN UND POSITIONSBESTIMMUNG
			 ****************************************************************/
			aktuelle_pos_y++;
			if(aktuelle_pos_y % 4 == 0 || aktuelle_pos_y > this.höhe_y -1){
			//Das Ende einer vierer Spalte ist erreicht oder das ende des Codeblocks
				aktuelle_pos_x++;
				aktuelle_pos_y = topPunkt;
				
				if(aktuelle_pos_x > this.breite_x - 1){
				// Das Ende einer Zeile ist erreicht 
					topPunkt += 4;
					if(topPunkt > this.höhe_y -1){
					//Keine weiteren Zeilen sind zu bearbeiten
						lauf = false;
					}else{
						aktuelle_pos_x = 0;
						aktuelle_pos_y = topPunkt;
					}
				}
			}
			
		}while(lauf);
	}
	
	/*
	 * Beschreibung auf Seite 491. Encode_Pass1
	 */
	private void encode_magnitude(){
		int aktuelle_pos_x = 0;
		int aktuelle_pos_y = 0;
		int topPunkt = 0;
		boolean lauf = true;
		
		int k_mag = 0;
		
		
		do{
			

			if(this.code_block.signifikant.get_Bit(aktuelle_pos_x, aktuelle_pos_y)&& this.code_block.pi.get_Bit(aktuelle_pos_x, aktuelle_pos_y) ){
				
				// Finde k_mag
				if(this.code_block.p_pfeil.get_Bit(aktuelle_pos_x, aktuelle_pos_y)){
					k_mag = 17;
				}else{
					if(this.signi(aktuelle_pos_x, aktuelle_pos_y) > 0){
						k_mag = 16;
					}else{
						k_mag = 15;
					}
				}
				
				mq.encode(this.get_Bit(aktuelle_pos_x, aktuelle_pos_y), k_mag);
				this.code_block.p_pfeil.addwert(aktuelle_pos_x, aktuelle_pos_y,  this.code_block.signifikant.get_Bit(aktuelle_pos_x, aktuelle_pos_y));
				
			}
			
			
			
			/***************************************************************
			 *  ZUM DURCHLLAUFEN UND POSITIONSBESTIMMUNG
			 ****************************************************************/			
			aktuelle_pos_y++;
			if(aktuelle_pos_y % 4 == 0 || aktuelle_pos_y > this.höhe_y -1){
			//Das Ende einer vierer Spalte ist erreicht oder das ende des Codeblocks
				aktuelle_pos_x++;
				aktuelle_pos_y = topPunkt;
				
				if(aktuelle_pos_x > this.breite_x - 1){
				// Das Ende einer Zeile ist erreicht 
					topPunkt += 4;
					if(topPunkt > this.höhe_y -1){
					//Keine weiteren Zeilen sind zu bearbeiten
						lauf = false;
					}else{
						aktuelle_pos_x = 0;
						aktuelle_pos_y = topPunkt;
					}
				}
			}
			
		}while(lauf);		
	}

	/*
	 * Beschreibung auf Seite 492. Encode_Pass2
	 */
	private void encode_cleanup(){
		int aktuelle_pos_x = 0;
		int aktuelle_pos_y = 0;
		int topPunkt = 0;
		boolean lauf = true;
				
		int r = -1;	
		do{
			if(aktuelle_pos_y % 4 == 0 && aktuelle_pos_y < höhe_y - 4){
			// aktuelle_pos_y befindet sich an einer neuen Spalten mit mit mindestens noch vier Zeilen.
				r = -1;
				if(signi(aktuelle_pos_x, aktuelle_pos_y) == 0 && signi(aktuelle_pos_x, aktuelle_pos_y + 1) == 0 && signi(aktuelle_pos_x, aktuelle_pos_y + 2) == 0 && signi(aktuelle_pos_x, aktuelle_pos_y +3) == 0 ){
				// Die vollgenden vier positionen haben  keine signifikanten Nachbarn
					for(r = 0; r < 4 && !getWert(aktuelle_pos_x, aktuelle_pos_y + r); r++){
						;
					}
					
					if(r == 4){
					// Die vollgenden vier positionen sind insignifikant
						mq.encode(false, MQ_Encoder.k_run);
					}else{
					// Nicht alle vier Werte sind insignifikant	
						mq.encode(true, MQ_Encoder.k_run);
						mq.encode(int_to_boolean(r/2), MQ_Encoder.k_uni);
						mq.encode(int_to_boolean(r%2), MQ_Encoder.k_uni);
					}
				}
			}
			if(0 == signi(aktuelle_pos_x, aktuelle_pos_y) && !this.code_block.pi.get_Bit(aktuelle_pos_x, aktuelle_pos_y)){
				if(r >= 0){
					r--;
				}else{
					mq.encode(getWert(aktuelle_pos_x, aktuelle_pos_y), signi(aktuelle_pos_x, aktuelle_pos_y));
				}
				
				if(getWert(aktuelle_pos_x, aktuelle_pos_y)){
				//Der Status der aktuellen Position wechselt von insignifikant zu signifikant.
					this.code_block.signifikant.addwert(aktuelle_pos_x, aktuelle_pos_y, true);
					this.encode_Sign(	this.code_block.vorzeichen.get_Bit(aktuelle_pos_x, aktuelle_pos_y),
										this.code_block.vorzeichen.get_X_h(aktuelle_pos_x, aktuelle_pos_y), 
										this.code_block.vorzeichen.get_X_v(aktuelle_pos_x, aktuelle_pos_y));
				}		
			}	
			
			
			aktuelle_pos_y++;
			
		
			if(aktuelle_pos_y % 4 == 0 || aktuelle_pos_y > this.höhe_y -1){
				aktuelle_pos_y = topPunkt;
				aktuelle_pos_x++;
				if(aktuelle_pos_x > this.breite_x -1){
					topPunkt += 4;
					aktuelle_pos_y = topPunkt;
					aktuelle_pos_x = 0;
				}
				if( aktuelle_pos_y > this.höhe_y -1){
					lauf = false;
				}
				
			}
			
		}while(lauf);
	}
	
	public boolean decode(Code_Block codeblock, boolean show){
	
		return show;	
	}
	
	public void clear(){
		for(int h = 0; h < this.werte.length; h++){
			this.werte[h] = false;
		}
	}
}
