/*
 * Created on 23.03.2004
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
public final class Subband_Daten_diskret extends Matrix_short {
	
	
	public int exponent;
	
	public int mantisse;
	
	private int anzahl_bit = 0;
	
	private int teilungsbreite_x = 32;
	
	private int teilungsbreite_y = 32;
	
	private Code_Block[] code_Bloecke;
	
	public final static int LL = 1;
	public final static int LH = 2;
	public final static int HL = 3;
	public final static int HH = 4;
	
	private int type;
	
	public Subband_Daten_diskret(int höhe, int breite, int type){
		super(höhe, breite);
		
		this.type = type;
		
	}
	
	public Subband_Daten_diskret(int höhe, int breite, int type, Code_Block[] code_Bloecke){
		super(höhe, breite);
		
		this.code_Bloecke = code_Bloecke;
		this.type = type;
		
	}	
	
	
	
	
	public Subband_Daten_diskret(Subband_Daten daten, QuantisierungsInterface  quantisierer, int type){
		super(daten.höhe, daten.breite);
		this.mantisse = quantisierer.getMantis();
		this.exponent = quantisierer.getExponent();
		
		this.type = type;	
		for(int i = 0; i < daten.wertelength(); i++){
			this.addwert( quantisierer.quantisiere(Math.abs(daten.werte(i))), this.sign(daten.werte(i)) , i);
		}
		
		this.anzahl_bit  = quantisierer.get_anzahl_bits();
			
	}
	
	public int get_anzahl_bit(){
		return anzahl_bit;
	}
	
	
	/*
	 * Startet die Kodierung des Subbands in Code-Blöcke.
	 */
	public boolean encocde_EBCOTuMQ(boolean show){
	
		
		int teile_x = this.breite / teilungsbreite_x;
		int teile_x_rest = teilungsbreite_x;
		if(this.breite % teilungsbreite_x != 0){	
			teile_x_rest = this.breite - teile_x * teilungsbreite_x;
			teile_x++;
		}
		
		int teile_y = this.höhe / teilungsbreite_y;
		int teile_y_rest = teilungsbreite_y;
		if(this.höhe % teilungsbreite_y != 0){
			
			teile_y_rest = this.höhe - teile_y * teilungsbreite_y;
			teile_y++;
		}
		
		code_Bloecke = new Code_Block[teile_x * teile_y];

		for(int y = 0; y < teile_y; y++){
			for(int x = 0; x < teile_x; x++){
				if(teile_y - 1 ==  y ){
				// Wir befinden uns am unteren Rand des Subbandes
					if(teile_x - 1 == x){
					//Wir befinden uns am rechten Rand des Subbandes
						code_Bloecke[y* teile_x + x] = new Code_Block(this, teile_x_rest, teile_y_rest, x * teilungsbreite_x, y * teilungsbreite_y, show, type);
						code_Bloecke[y* teile_x + x].encode(show);
						show = Code_Block.show;	
					}else{
						
						code_Bloecke[y* teile_x + x] = new Code_Block(this, teilungsbreite_x, teile_y_rest, x * teilungsbreite_x, y * teilungsbreite_y, show, type);
						code_Bloecke[y* teile_x + x].encode(show);
						show = Code_Block.show;
					}
					
				}else if(teile_x - 1 == x){
				//Wir befinden uns am rechten Rand des Subbandes	
					code_Bloecke[y* teile_x + x] = new Code_Block(this, teile_x_rest, teilungsbreite_y, x * teilungsbreite_x, y * teilungsbreite_y, show, type);
					code_Bloecke[y* teile_x + x].encode(show);
					show = Code_Block.show;
				}else{

					code_Bloecke[y* teile_x + x] = new Code_Block(this, teilungsbreite_x, teilungsbreite_y, x * teilungsbreite_x, y * teilungsbreite_y, show, type);
					code_Bloecke[y* teile_x + x].encode(show);
					show = Code_Block.show;
				}	
			}
		}
		return show;	
	}

}
