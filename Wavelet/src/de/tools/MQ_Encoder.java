/*
 * Created on 12.05.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.tools;

import java.io.*;
import de.datentypen.*;
/**
 * @author Uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public final class MQ_Encoder {
	
	private FileWriter testFile;
	
	private String pathDir = "C:\\Dokumente und Einstellungen\\uwe\\Eigene Dateien\\work\\Eclipse\\";
	
	private int change_counter = 0;
	
	private int change_A = 0;
	
	public final static int k_run = 9;
	public final static int k_uni = 18;
	
	/* Wahrscheinlichkeit für das nächste Symbol */
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

	private final int BITMASK_C_carry = 134217728;
	
	private final int BITMASK_C_msbs =  267386880;
	
	private final int BITMASK_C_clear_carry = 134217727;
	
	private final int BITMASK_C_clear_msbs = 1048575;
	
	private final int BITMASK_C_partial = 133693440;
	
	private final int BITMASK_C_clear_partial = 134742015;
	
	private int A;
	
	private int C;
	
	private int t_;
	
	private int T_;
	
	private int L;
	
	private int Sum_k;
	
	private boolean s;
	
	private int p_;
	
	private int[] context_states_sum;
	
	private boolean[] context_states_s;
	
	private Byte_Buffer B_;

	public MQ_Encoder(){
		reset();
		init();
		B_ = new Byte_Buffer();
		
		init_write(pathDir+"First2.txt");
	}

	public void init(){
		A = 0x8000;
		C = 0;
		t_ = 12;
		T_ = 0;
		L = -1;		
	}
	
	public void reset(){
		context_states_sum = new int[init_context_statex_sum.length];
		context_states_s = new boolean[init_context_statex_sum.length];
		for(int i = 0; i < init_context_statex_sum.length; i++){
			context_states_sum[i] = init_context_statex_sum[i];
			context_states_s[i] = false;
		}	
	}

	
	public void encodeu(boolean symbol, int kontext){
		s  = 	context_states_s[kontext];
		p_ = qe[context_states_sum[kontext]];
		
		A -= p_;	
		if(symbol == context_states_s[kontext]){ //coding MPS
			if(A >= 32768){ // no renormlization and hence conditional exchange
				C += p_;
			}else{
				if(A < p_){
					A = p_;
				}else{
					C += p_;
				}
				context_states_sum[kontext] = nMPS[context_states_sum[kontext]];
				do{
					if(A <= 0){
						throw new RuntimeException("A = "+ A);
					}
					A *= 2;
					C *= 2;
					t_--;
					if(t_ == 0){
						Transfer_Byte();
					}
				}while(A < 32768);
			}
				
		}else{ // coding LPS
			if(A < p_){
				C += p_;
			}else{
				A = p_; 
			}
			if(context_states_s[kontext] == X_s[context_states_sum[kontext]]){
				context_states_s[kontext] = false;
			}else{
				context_states_s[kontext] = true;
			}
			context_states_sum[kontext] = nLPS[context_states_sum[kontext]];
			do{
				if(A <= 0){
					throw new RuntimeException("A = "+ A);
				}
				A *= 2;
				C *= 2;
				t_--;
				if(t_ == 0){
					Transfer_Byte();
				}
			}while(A < 32768);			
		}
		this.write(symbol, kontext);
	}
	
	
	public void encode(boolean symbol, int kontext){
		s  = 	context_states_s[kontext];
		p_ = qe[context_states_sum[kontext]];
	
		boolean temp_s = s;
		A = A - p_; change_A++;
		
		if(A < p_){
			if(s){
				s = false;
			}else{
				s = true;
			}
		}
		
		if(symbol == s){
			C = C + p_;
		}else{
			A = p_; change_A++;
		}
		
		if(A < 32768){
			if(symbol == context_states_s[kontext]){
				context_states_sum[kontext] = nMPS[context_states_sum[kontext]];
			}else{
				
				if( context_states_s[kontext] == X_s[context_states_sum[kontext]]){
					context_states_s[kontext] = false;
				}else{
					context_states_s[kontext] = true;
				}
				
				context_states_sum[kontext] = nLPS[context_states_sum[kontext]];
			}
		}
		
		while(A < 32768){
			if(A <= 0){
				throw new RuntimeException(	"\nA = "+A +
											"\nSymbol = " +symbol +
											"\nKontext = " + kontext+
											"\ns = " +s+
											"\np_ = " +p_);
			}
			
			A = A * 2;
			C = C * 2;
			t_--;
			if(t_ == 0){
				Transfer_Byte();
			}
		}	
		s = temp_s;
		this.write(symbol, kontext);
		
	}
	
	
	private void Transfer_Byte(){
		int iHelp = 0;
		change_counter++;
		if(T_ == 255){
			Put_Byte();
			iHelp = C & BITMASK_C_msbs;
			 
			T_ = iHelp >> 20;
			C &= BITMASK_C_clear_msbs;
			t_= 7;
			
		}else{
			iHelp = (C & BITMASK_C_carry) >> 27;
			T_ += iHelp;
			C &= BITMASK_C_clear_carry;
			Put_Byte();
			if(T_ == 255){
				iHelp = C & BITMASK_C_msbs;
				T_ = iHelp >> 20;
				C &= BITMASK_C_clear_msbs;
				t_= 7;				
			}else{
				iHelp = C & BITMASK_C_partial;
				T_ = iHelp >> 19;
				C &= BITMASK_C_clear_partial;
				t_= 8;				
			}
		}	
	}
	
	private void Put_Byte(){
		if(L >= 0){
			this.B_.addElement(T_);
		}
		L++;
	}	
	
	public byte[] get_Ergbenis(){
		close_write();
		easy_Termination();
		return this.B_.duplicat();
	}
	
	private void easy_Termination(){
		int n_bits = 12 - t_;
		C <<= t_;
		
		while(n_bits > 0){
			Transfer_Byte();
			n_bits -= t_;
			C <<= t_;
		}
		Transfer_Byte();
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
			
			
			testFile.write("\n\n<<<<<<<<<++++++++++++++++++++++++>>>>>>>>>>");
			if(symbol)	
				testFile.write("\n"+ sym +" 1");
			else
				testFile.write("\n"+ sym +" 0");
			
			testFile.write("\n"+kon + kontext);
			testFile.write("\n"+int_to_binär(A_d, 15, 0, A)  +" || "+ A);
			testFile.write("\n"+int_to_binär(C_d, 27, 0, C)  +" || "+ C);
			testFile.write("\n"+int_to_binär(T_d ,12, 0, T_) +" || "+ T_);
			testFile.write("\n"+t_d + t_ + "  "+change_counter);
			testFile.write("\n"+p_d+ p_);
			testFile.write("\n"+s_d+ s);
			
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


