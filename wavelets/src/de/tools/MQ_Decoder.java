/*
 * Created on 25.05.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.tools;

import java.io.*;
/**
 * @author uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public final class MQ_Decoder {

	private FileWriter testFile;
	
	private String pathDir = "C:\\Dokumente und Einstellungen\\uwe\\Eigene Dateien\\work\\Eclipse\\";
	
	private int C_active;
	
	/*
	 * Das Ergebnis der Encodierung steht in diesem Vektor
	 */
	private byte[] B_L;
	
	/*
	 * Die Länge des Ergenisvektor
	 */
	private int L_max;
	
	private int T_;
	
	private int L;
	
	private int C;
	
	private int A;
	
	private int t_;
	
	private int p_;
	
	private boolean s;

	final static
		int qe[]={0x5601, 0x3401, 0x1801, 0x0ac1, 0x0521, 0x0221, 0x5601,
				  0x5401, 0x4801, 0x3801, 0x3001, 0x2401, 0x1c01, 0x1601, 
				  0x5601, 0x5401, 0x5101, 0x4801, 0x3801, 0x3401, 0x3001,
				  0x2801, 0x2401, 0x2201, 0x1c01, 0x1801, 0x1601, 0x1401,
				  0x1201, 0x1101, 0x0ac1, 0x09c1, 0x08a1, 0x0521, 0x0441,
				  0x02a1, 0x0221, 0x0141, 0x0111, 0x0085, 0x0049, 0x0025,
				  0x0015, 0x0009, 0x0005, 0x0001, 0x5601 };
    
	/* Der Kontext für das nächste MPS*/
	final static
		int nMPS[]={ 1 , 2, 3, 4, 5,38, 7, 8, 9,10,11,12,13,29,15,16,17,
					 18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,
					 35,36,37,38,39,40,41,42,43,44,45,45,46 };

	/* Der Kontext für das nächste LPS */
	final static
		int nLPS[]={ 1 , 6, 9,12,29,33, 6,14,14,14,17,18,20,21,14,14,15,
					 16,17,18,19,19,20,21,22,23,24,25,26,27,28,29,30,31,
					 32,33,34,35,36,37,38,39,40,41,42,43,46 };

	/* Whether LPS and MPS should be switched */
	final static        // at indices 0, 6, and 14 we switch
		boolean[] X_s={ true, false ,false,false,false,false,true,false,false,false,false,false,false,false,true,false,false,false,false,false,false,false,false,false,
						 false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false };

	/* Buch Seite 488 */
	private final static int[] init_context_statex_sum = {	4, 0, 0, 0,
															0, 0, 0, 0, 0,
															3, 0, 0, 0, 0,
															0, 0, 0, 0, 46};
	
	private int[] context_states_sum;
	
	private boolean[] context_states_s;
	
	private final int BITMASK_C_active = 0xFFFF00;
	
	private final int BITMASK_C_invers = 0x0000FF;
	
	private final int SHIFT_C_active = 8;
	
	private final int ZWEI_HOCH_FÜNFZEHN = 32768;
	
	/*
	 * MQ-Decoder Initialization Seite 482
	 */
	public MQ_Decoder(byte[] Ergebnis){
		reset();
		B_L = Ergebnis;
		L_max = B_L.length;
		
		T_ = 0;
		L = 0;
		C = 0;
		Fill_LSB();
		C <<= t_;
		Fill_LSB();
		C <<=7;
		t_ -= 7;
		A = 0x8000;
		
		init_write(pathDir+"test_Decoder1.txt");
	}

	/*
	 * Seite 482
	 */
	private void Fill_LSB(){
		t_ = 8;
		int B_L_help = 0;
		System.out.println("L = "+L);
		if(L < L_max)
			B_L_help = ((int) B_L[L] ) & 0xFF;
		
		if(L == L_max || (T_ == 0xFF && B_L_help > 0x8F )){
			C += 0xFF;
		}else{
			if(T_ == 0xFF){
				t_ = 7;
			}
			T_ = B_L_help;
			L++;
			
			C += (T_<<(8-t_));
		}	
	}
	
	/*
	 * Buch Seite 646
	 */
	public boolean decode(int kontext){
		
		boolean x;
		p_ = qe[context_states_sum[kontext]];
		s = context_states_s[kontext];	
		x = s;
		A -= p_;
		
		C_active = get_C_active();
		
		if(get_C_active() >= p_){
			sub_C_active(p_);
			if(A < ZWEI_HOCH_FÜNFZEHN){
				if(A < p_){
					if(x){
						x = false;
					}else{
						x = true;
					}
					if(context_states_s[kontext] == X_s[context_states_sum[kontext]]){
						context_states_s[kontext] = false;
					}else{
						context_states_s[kontext] = true;
					}
					context_states_sum[kontext] = nLPS[context_states_sum[kontext]];
				}else{
					context_states_sum[kontext] = nMPS[context_states_sum[kontext]];
				}
				do{
					Renormalize_Once();
					
				}while(A < this.ZWEI_HOCH_FÜNFZEHN);
			}
		}else{
			if(A < p_){
				context_states_sum[kontext] = nMPS[context_states_sum[kontext]];
			}else{
		
				if(x){					
					x = false;
				}else{
					x = true;
				}
				if(context_states_s[kontext] == X_s[context_states_sum[kontext]]){
					context_states_s[kontext] = false;
				}else{
					context_states_s[kontext] = true;
				}
				context_states_sum[kontext] = nLPS[context_states_sum[kontext]];
				
			}
			A = p_;
			do{
				Renormalize_Once();
					
			}while(A < this.ZWEI_HOCH_FÜNFZEHN);
		}
		
		write(x, kontext);
		return x;
	}
	
	private void Renormalize_Once(){
		if(t_ == 0){
			Fill_LSB();
		}
		A *=2;
		C *=2;
		t_--;
	}
	
	private int get_C_active(){
		int C_temp;
		C_temp = C & this.BITMASK_C_active;
		C_temp >>= this.SHIFT_C_active;
		return C_temp;
	}

	/*
	 * Buch Seite 483
	 */
	 
	public boolean decodew(int kontext){
		boolean x = false;
		p_ = qe[context_states_sum[kontext]];
		s = context_states_s[kontext];
		boolean s_temp = s;
		C_active = get_C_active();
		
		A -=p_;
		if(A < p_){
			if(s){
				s = false;
			}else{
				s = true;
			}
		}
		
		if(get_C_active() < p_){
			if(s){
				x = false;
			}else{
				x = true;
			}
			A = p_;
		}else{
			x = s;
			sub_C_active(p_);
		}
		
		if(A < this.ZWEI_HOCH_FÜNFZEHN){
			if(x == context_states_s[kontext]){
				context_states_sum[kontext] = nMPS[context_states_sum[kontext]];
			}else{
				if(context_states_s[kontext] == X_s[context_states_sum[kontext]]){
					context_states_s[kontext] = false;
				}else{
					context_states_s[kontext] = true;
				}
				context_states_sum[kontext] = nLPS[context_states_sum[kontext]];
			}
			while(A < this.ZWEI_HOCH_FÜNFZEHN){
				Renormalize_Once();
			}
		}
		s = s_temp;
		write(x, kontext);
		return x;
	}

	
	private void sub_C_active(int p_help){
		int C_temp = C;
		C_temp &= this.BITMASK_C_active;
		C_temp >>= this.SHIFT_C_active;	
		C_temp -= p_help;
		C_temp <<= this.SHIFT_C_active;	
		C_temp &= this.BITMASK_C_active;
		C &= BITMASK_C_invers;
		C |= C_temp;	
	}
	
	private void reset(){
		context_states_sum = new int[init_context_statex_sum.length];
		context_states_s = new boolean[init_context_statex_sum.length];
		for(int i = 0; i < init_context_statex_sum.length; i++){
			context_states_sum[i] = init_context_statex_sum[i];
			context_states_s[i] = false;
		}	
	}
	
	private void init_write(String path){
		try {
			testFile = new FileWriter(path);
			
		}catch(IOException e) {
			System.out.println("Fehler beim Erstellen der Datei");
		}
	}
	
	private void close_write(){
		try{
			testFile.close();
		}catch(IOException e) {
			System.out.println("Fehler beim Erstellen der Datei");
		}
	}

	private void write(boolean symbol, int kontext){
		try {
			String sym = "Symbol  --> ";
			String kon = "Kontext --> ";
			String A_d = "A       --> ";
			String C_d = "C       --> ";
			String T_d = "T_      --> ";
			String t_d = "t_      --> ";
			String p_d = "p_      --> ";
			String s_d = "s       --> ";
			String C_a = "C_active--> ";
			
			testFile.write("\n\n<<<<<<<<<++++++++++++++++++++++++>>>>>>>>>>");
			if(symbol)	
				testFile.write("\n"+ sym +" 1");
			else
				testFile.write("\n"+ sym +" 0");
			
			testFile.write("\n"+kon + kontext);
			testFile.write("\n"+A_d + A);
			testFile.write("\n"+ C);
			testFile.write("\n"+T_d + T_);
			testFile.write("\n"+t_d + t_ );
			testFile.write("\n"+p_d+ p_);
			testFile.write("\n"+s_d+ s);
			testFile.write("\n"+C_a+ this.C_active);
			
		}catch(IOException e) {
			System.out.println("Fehler beim Erstellen der Datei");
		}
	}
	
	private String int_to_binär(String vorsetzen ,int start, int stop, int wert){
		String BACK = vorsetzen;
	
		for(int i = start; i >= stop; i--){
			if((wert & (1 << i)) > 0){
				BACK += " 1";
			}else{
				BACK += " 0";
			}
		}
		return BACK;
	}
}
