/*
 * Created on 30.05.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.wavelet.generator;

/**
 * @author uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public final class Filter {
	
	
	/*
	 * Die Faktoren für den Filter A  als z-Tranformierte.
	 * Die Position von z^0 wird durch pos_z0_A angegeben.
	 */
	private double[] filterfaktoren_A;
	
	private int pos_z0_A;
	
	/*
	 * Die Faktoren für den Filter At als z-Tranformierte.
	 * Die Position von z^0 wird durch pos_z0_At angegeben.
	 */
	private double[] filterfaktoren_At;
	
	private int pos_z0_At;
	
	/*
	 * Die Faktoren für den Filter D  als z-Tranformierte.
	 * Die Position von z^0 wird durch pos_z0_D angegeben.
	 */
	private double[] filterfaktoren_D;
	
	private int pos_z0_D;
	
	/*
	 * Die Faktoren für den Filter Dt  als z-Tranformierte.
	 * Die Position von z^0 wird durch pos_z0_Dt angegeben.
	 */
	private double[] filterfaktoren_Dt;
	
	private int pos_z0_Dt;
	
	private int L = 1;
	
	public Filter(double[] A, int pos_A, double[] D , int pos_D){
		filterfaktoren_A = new double[A.length];
		System.arraycopy(A, 0,filterfaktoren_A,0 ,A.length );
		pos_z0_A = pos_A;
		
		filterfaktoren_D = new double[D.length];
		System.arraycopy(D, 0, filterfaktoren_D, 0, D.length);
		pos_z0_D = pos_D;
		
		filterfaktoren_Dt = new double[A.length];
		System.arraycopy(A, 0,filterfaktoren_Dt,0 ,A.length );
		pos_z0_Dt = pos_A + L;	
		
		for(int i = 0; i < filterfaktoren_Dt.length; i++){
			if(i != pos_A){
				filterfaktoren_Dt[i] *= -1.0;	
			}
			filterfaktoren_Dt[i] *= -1.0;
		}
		
		filterfaktoren_At = new double[D.length];
		System.arraycopy(D, 0, filterfaktoren_At, 0, D.length);
		pos_z0_At = pos_D + L;
		
		for(int i = 0; i < filterfaktoren_At.length; i++){
			if(i != pos_D){
				filterfaktoren_At[i] *= -1.0;
			}
		}
	}
	
	
	public Filter(int pos_A, double[] A, int pos_At, double[] At){
		filterfaktoren_A = new double[A.length];
		System.arraycopy(A, 0,filterfaktoren_A,0 ,A.length );
		pos_z0_A = pos_A;
		
		filterfaktoren_Dt = new double[A.length];
		System.arraycopy(A, 0,filterfaktoren_Dt,0 ,A.length );
		pos_z0_Dt = pos_A + L;	
		
		for(int i = 0; i < filterfaktoren_Dt.length; i++){
			if(pos_A % 2 == 0){
				if(i % 2 == 1)
					filterfaktoren_Dt[i] *= -1.0;	
			}else{
				if(i % 2 == 0)
					filterfaktoren_Dt[i] *= -1.0;				
			}
			filterfaktoren_Dt[i] *= -1.0;
		}
		
		//++++++++++++++++++++++++++++++++++++++++++++++++
		filterfaktoren_At = new double[At.length];
		System.arraycopy(At, 0,filterfaktoren_At,0 ,At.length );
		pos_z0_At = pos_At;
		
		filterfaktoren_D = new double[At.length];
		System.arraycopy(At, 0, filterfaktoren_D, 0, At.length);
		pos_z0_D = pos_At - L;
		
		for(int i = 0; i < filterfaktoren_D.length; i++ ){
			if(pos_z0_D % 2 == 0){
				if(i % 2 == 1)
					filterfaktoren_D[i] *= -1.0;				
			}else{
				if(i % 2 == 0)
					filterfaktoren_D[i] *= -1.0;				
			}
		}
				
	}
	
	public double[] get_FilterA(){
		double[] BACK = new double[filterfaktoren_A.length];
		System.arraycopy(filterfaktoren_A, 0, BACK, 0, filterfaktoren_A.length);
		return BACK;
	}
	
	public float[] get_FilterA_f(){
		float[] BACK = new float[filterfaktoren_A.length];
		for(int i = 0; i < BACK.length; i++){
			BACK[i] = (float)filterfaktoren_A[i];
		}
		return BACK;
	}
	
	public int get_Position_z0_von_A(){
		return this.pos_z0_A;
	}
	
	public double[] get_FilterAt(){
		double[] BACK = new double[filterfaktoren_At.length];
		System.arraycopy(filterfaktoren_At, 0, BACK, 0, filterfaktoren_At.length);
		return BACK;
	}
	
	public float[] get_FilterAt_f(){
		float[] BACK = new float[filterfaktoren_At.length];
		for(int i = 0; i < BACK.length; i++){
			BACK[i] = (float)filterfaktoren_At[i];
		}		
		return BACK;
	}
	public int get_Position_z0_von_At(){
		return this.pos_z0_At;
	}
	
	public double[] get_FilterD(){
		double[] BACK = new double[filterfaktoren_D.length];
		System.arraycopy(filterfaktoren_D, 0, BACK, 0, filterfaktoren_D.length);
		return BACK;
	}
	
	public float[] get_FilterDt_f(){
		float[] BACK = new float[filterfaktoren_Dt.length];
		for(int i = 0; i < BACK.length; i++){
			BACK[i] = (float)filterfaktoren_Dt[i];
		}		
		return BACK;
	}
	
	public float[] get_FilterD_f(){
		float[] BACK = new float[filterfaktoren_D.length];
		for(int i = 0; i < BACK.length; i++){
			BACK[i] = (float)filterfaktoren_D[i];
		}
		return BACK;
	}
	
	public int get_Position_z0_von_D(){
		return this.pos_z0_D;
	}

	public double[] get_FilterDt(){
		double[] BACK = new double[filterfaktoren_Dt.length];
		System.arraycopy(filterfaktoren_Dt, 0, BACK, 0, filterfaktoren_Dt.length);
		return BACK;
	}
	
	public int get_Position_z0_von_Dt(){
		return this.pos_z0_Dt;
	}
}
