/*
 * Created on 22.06.2004
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
public final class WriteFile {
	
	private DataOutputStream output;
	private static String testPath = "C:\\TestBilder\\myFile.jp2";
	
	//JPEG2000 Signatur Box
	private int SignaturBox_Size 	= 12;
	private int SignaturBox 		= 0x6A502020;
	private int SignaturBox_Inhalt 	= 0x0D0A870A;  
	
	//JPEG2000 FileTypeBox
	private int FileTypeBox_Size 	= 0x14; 
	private int FileTypeBox			= 0x66747970;
	private int FileTypeBox_Br		= 0x6A703220;		
	private int FileTypeBox_MV		= 0;
	private int FileTypeBox_CL1		= 0x6A703220;
	
	//JPEG2000 JP2Header
	
	private int JP2Header_Size 		= 45;
	private int JP2Header			= 0x6A703268;
	
		private int ImageHeader_Size		= 22;
		private int ImageHeader				= 0x69686472;
		private int ImageHeader_W			= 256;
		private int ImageHeader_H			= 256;
		private short ImageHeader_C			= 3;	//Anzahl der Komponenten
		private byte  ImageHeader_B			= 8;	//Bit pro Komponente
		private byte  ImageHeader_CT 		= 7;	//Immer 7
		private byte  ImageHeader_UC		= 0;
		private byte  ImageHeader_IP		= 0;
		
		private int ColorSpec_Size			= 15;
		private int ColorSpec				= 0x636F6C72;
		private byte ColorSpec_M			= 1;
		private byte ColorSpec_P			= 0;
		private byte ColorSpec_A			= 0;
		private int ColorSpec_ECS			= 16;
		
	// Contignous Code_Stream
	
	private int CodeStream_Size				= 0xEfffffff;
	private int CodeStream					= 0x6A703263;
	
	public WriteFile(String path){
		try{
			output = new DataOutputStream(new FileOutputStream(path, false));
		}catch(FileNotFoundException e){
			;
		}catch(IOException e){
			;
		}
		
		write_Signaturebox();
		write_FileTypeBox();
		write_JP2Header();
		write_CodeStream();
		close();
		
		
	}
	
	private void write_CodeStream(){
		write(CodeStream_Size);
		write(CodeStream);
	}
	
	private void write_Signaturebox(){
		write(SignaturBox_Size);
		write(SignaturBox);
		write(SignaturBox_Inhalt);
	}
	
	private void write_FileTypeBox(){
		write(FileTypeBox_Size); 
		write(FileTypeBox);
		write(FileTypeBox_Br);		
		write(FileTypeBox_MV);
		write(FileTypeBox_CL1);		
	}
	
	private void write_JP2Header(){
		write(JP2Header_Size );
		write(JP2Header	);	
		writeImageHeader();
		writeColorSpec();	
	}
	
	private void writeImageHeader(){
		write(ImageHeader_Size);
		write(ImageHeader);
		write(ImageHeader_W	);
		write(ImageHeader_H	);
		write(ImageHeader_C	);	//Anzahl der Komponenten
		write(ImageHeader_B	);	//Bit pro Komponente
		write(ImageHeader_CT );	//Immer 7
		write(ImageHeader_UC );
		write(ImageHeader_IP );		
	}
	
	private void writeColorSpec(){
		write(ColorSpec_Size);
		write(ColorSpec);
		write(ColorSpec_M);
		write(ColorSpec_P);
		write(ColorSpec_A);
		write(ColorSpec_ECS);		
	}
	
	public void close(){
		try{
			output.close();
		}catch(IOException e){
			
		}
	}
	
	private void write(int daten){
		try{
			output.writeInt(daten);
		}catch(IOException e){
		}
	}
	
	private void write(byte daten){
		try{
			output.writeByte((int)daten);
		}catch(IOException e){
		}
	}
	
	private void write(short daten){
		try{
			output.writeShort((int)daten);
		}catch(IOException e){
		}
	}

	public static void main(String[] argc){
		new WriteFile(testPath);
	}
}
