/*
 * Created on 16.02.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.gui;

import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import de.datentypen.Matrix_float;
import de.tools.Farbtools;
/**
 * @author Uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public final class Matrix_ScrollPanel extends JPanel implements Runnable {
	
	private boolean show = true;
	
	private BufferedImage img;
	
	private boolean arbeitet = true;

	private Matrix_float Matrix;
	
	
	public Matrix_ScrollPanel(){
		this.setLayout(new GridLayout(1,1));
	}

	public Matrix_ScrollPanel(Matrix_float Matrix) {
		int[] dummy = new int[Matrix.wertelength()];
		img = new BufferedImage(Matrix.breite, Matrix.höhe , BufferedImage.TYPE_INT_RGB);
		float faktor = 0.0f;
		float wertr = 255f;
		
		/*
		if(Matrix.getminWert() < 0 && Matrix.getmaxWert() < 0){
			faktor = 255.0f/(Math.abs(Matrix.getminWert()) - Math.abs(Matrix.getmaxWert()));
		}else if(Matrix.getminWert() >= 0 && Matrix.getmaxWert() >= 0){
			faktor = 255.0f/(Matrix.getmaxWert() - Matrix.getminWert() );
		}else if(Matrix.getminWert() < 0 && Matrix.getmaxWert() >= 0){
			faktor = 255.0f/(Matrix.getmaxWert() + Math.abs(Matrix.getminWert()) );
		}
		*/
		faktor = Math.abs(Matrix.getmaxWert() - Matrix.getminWert());
		if(faktor != 0)
			faktor = 255 / faktor;
		
		for(int i = 0; i < dummy.length; i++){
			wertr = (Matrix.werte(i) - Matrix.getminWert()) * faktor ;
			if(wertr >= 0 && wertr <= 255.2 ){
				dummy[i] = Farbtools.getGrau(wertr  );
			}else{
				throw new RuntimeException(	"\nwertr = " + wertr+
											"\n(Matrix.werte(i) - Matrix.getminWert()) = " +(Matrix.werte(i) - Matrix.getminWert())+
											"\nfaktor = " + faktor);
			}
		}
	
		
		img.setRGB(0 ,0 , Matrix.breite, Matrix.höhe,dummy, 0, Matrix.breite);
		
		this.setLayout(new GridLayout(1,1));
		this.add(new Bild_ScrollPanel(img));
		
	}

	public synchronized void add(Matrix_float Matrix) {
		int[] dummy = new int[Matrix.wertelength()];
		img = new BufferedImage(Matrix.breite, Matrix.höhe , BufferedImage.TYPE_INT_RGB);
		float faktor = 0;
			
		if(Matrix.getminWert() < 0 && Matrix.getmaxWert() < 0){
			faktor = 255.0f/(Math.abs(Matrix.getminWert()) - Math.abs(Matrix.getmaxWert()));
		}else if(Matrix.getminWert() >= 0 && Matrix.getmaxWert() >= 0){
			faktor = 255.0f/(Matrix.getmaxWert() - Matrix.getminWert() );
		}else if(Matrix.getminWert() < 0 && Matrix.getmaxWert() >= 0){
			faktor = 255.0f/(Matrix.getmaxWert() + Math.abs(Matrix.getminWert()) );
		}
			
		for(int i = 0; i < dummy.length; i++){
			dummy[i] = Farbtools.getGrau((Matrix.werte(i) - Matrix.getminWert()) * faktor  );
		}
		
			
		img.setRGB(0 ,0 , Matrix.breite, Matrix.höhe,dummy, 0, Matrix.breite);
		/*	
		this.setLayout(new GridLayout(1,1));
		this.add(new Bild_ScrollPanel(img));
		this.setVisible(true);
		this.validate();
		this.repaint();
		*/
		
			
	}
	
	public void update(Matrix_float Matrix) {
		if(show){
			/*
			while(true){
				if(this.getArbeit()){
					break;
				}
				try{
					this.wait();
				}catch(InterruptedException e){
					System.out.println(e.toString());
				}catch(IllegalMonitorStateException e){
					System.out.println(e.toString());
				}
			}
				
				*/
			this.Matrix = Matrix;
			arbeitet = true;
			//Thread t = new Thread(this);
			run();
		}
	}
	
	public void run(){
		int[] dummy = new int[Matrix.wertelength()];
		img = new BufferedImage(Matrix.breite, Matrix.höhe , BufferedImage.TYPE_INT_RGB);
		float faktor = 0;
			/*
		if(Matrix.getminWert() < 0 && Matrix.getmaxWert() < 0){
			faktor = 255.0f/(Math.abs(Matrix.getminWert()) - Math.abs(Matrix.getmaxWert()));
		}else if(Matrix.getminWert() >= 0 && Matrix.getmaxWert() >= 0){
			faktor = 255.0f/(Matrix.getmaxWert() - Matrix.getminWert() );
		}else if(Matrix.getminWert() < 0 && Matrix.getmaxWert() >= 0){
			faktor = 255.0f/(Matrix.getmaxWert() + Math.abs(Matrix.getminWert()) );
		}
			*/
		float diff = Math.abs(Matrix.getmaxWert() - Matrix.getminWert());
		if(diff != 0){
			faktor = 255f / diff;
		}else{
			faktor = 1;
		}
			
		for(int i = 0; i < dummy.length; i++){
			try{
				dummy[i] = Farbtools.getGrau((Matrix.werte(i) - Matrix.getminWert()) * faktor  );
			}catch(RuntimeException e){
				for(int k = 0; i < 10; k++){
					System.out.println("\ni = " + k + "  Matrix.werte(i) = " +Matrix.werte(k)+ " faktor = " +faktor );
				}
				throw new RuntimeException(	e.toString() + 
											"\nMatrix.minWert  	= " + Matrix.getminWert() +
											"\nMatrix.maxWert 	= " + Matrix.getmaxWert() +	
											"\nMatrix.werte[i] 	= " + Matrix.werte(i) +
											"\ni				= " + i +			
											"\nfaktor 			= " + faktor);
			}
		}
		
			
		img.setRGB(0 ,0 , Matrix.breite, Matrix.höhe,dummy, 0, Matrix.breite);
		
		this.removeAll();
		
		//this.setLayout(new GridLayout(1,1));
		this.add(new Bild_ScrollPanel(img));
		//this.setVisible(true);
		this.validate();
		this.repaint();			
		freeArbeit();
	}
	
	private synchronized boolean getArbeit(){
		if(this.arbeitet){
			arbeitet = false;
			return true;
		}
		return false;
	}
	
	private synchronized void freeArbeit(){
		arbeitet = true;
		try{
			this.notifyAll();
		}catch(IllegalMonitorStateException e){
			System.out.println(e.toString());
		}
	}
	
	public void setShow(){
		if(show){
			show = false;
		}else{
			show = true;
		}
	}
}
