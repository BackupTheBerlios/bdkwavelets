/*
 * Created on 24.05.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.datentypen;

/**
 * @author uwe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Byte_Buffer {

	private byte[] buffer;
	
	private int length;

	private int lengthplus = 100;
	
	private int counter;

	public Byte_Buffer(){
		length = 0;
		counter = lengthplus;
	}
	
	public void addElement(int in){
		if(counter == lengthplus){
			byte[] buffer_temp = new byte[length + counter];
			counter = 0;
			if(buffer != null){
				System.arraycopy(buffer,0, buffer_temp, 0 , buffer.length);
			}
			buffer = buffer_temp;
		}
		buffer[length] = (byte)in;
		length++;
		counter++;
	}
	
	public byte get_byte(int i){
		return buffer[i];
	}
	
	public int get_length(){
		return length;
	}
	
	
	public byte[] duplicat(){
		byte[] BACK = new byte[this.length];
		if(this.length > 0){
			System.arraycopy(this.buffer,0,BACK,0,this.length);
		}else{
			System.out.println("####### this.length = " + this.length);
		}
		this.length = 0;
		this.counter = lengthplus;
		this.buffer = null;
		
		return BACK;
	}

}
