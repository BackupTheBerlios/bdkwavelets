/*
 * Created on 31.05.2004
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
public final class Java_source_generator {

	private String source;
	
	private String package_name;
	
	private String class_name;
	
	private String wavelet_name;
	
	private String wavelet_nummer;
	
	private String FilterA_Forward;
	
	private Filter filter;
	
	private static String lf = "\n";
	
	private static String bs = "	";
	
	private String FilterA = "FilterA";
	private String FilterD = "FilterD";
	
	public Java_source_generator(Filter filter, String klassenName, String packageName){
		
		this.filter = filter;
		source = new String();
		FilterA_Forward = new String();
		
		source += "\n\npackage "+ packageName + lf + lf;
		source += 	"/* " + lf +					" * Diese Klasse ist Computergeneriert" + lf+
					" */" + lf;
		source += "public final class " +klassenName +"{ "+ lf;
		
		source += generate_symetrischt_ungerade_forward(FilterA, this.filter.get_FilterA_f(), this.filter.get_Position_z0_von_A());
		source += generate_symetrischt_ungerade_forward(FilterD, this.filter.get_FilterD_f(), this.filter.get_Position_z0_von_D());
		
		source += lf+lf+lf;
		source += generate_symetrischt_ungerade_backward(FilterA, this.filter.get_FilterAt_f(), this.filter.get_Position_z0_von_At());
		source += generate_symetrischt_ungerade_backward(FilterD, this.filter.get_FilterDt_f(), this.filter.get_Position_z0_von_Dt());

		source += "}" +lf;
		
	}

	private String generate_symetrischt_ungerade_forward(String Name, float[] faktoren, int pos_z0){
		int abstand = pos_z0;
		int abstandr = faktoren.length -1 - abstand;
		boolean first = true;
		String  sFunktion = new String();
		sFunktion += bs+"public final float "+Name+"(Vektor_float werte, int position)" +"{"+lf;
		sFunktion += bs+bs+"float sum = 0;" +lf;
		sFunktion += bs+bs+"int laenge = werte.getLaenge();"+lf;
		for(int posi = 0; posi < abstand; posi++){
			if(first){
				sFunktion	+= bs+bs+"if(position == " +posi+"){" +lf;
				first = false;
			}else{
				sFunktion	+= bs+bs+"else if(position == " +posi+"){" +lf;
			}
			for(int pos_filter = 0,  help = posi - abstand ; pos_filter < faktoren.length; pos_filter++, help++){
				sFunktion	+= bs+bs+bs+"sum += " +faktoren[pos_filter] +" * werte.get("+ abs(help) +");"+lf;  
			}
			sFunktion +=bs+bs+ "}" +lf;			
		}
		
		sFunktion	+= bs+bs+"else if(position >= " +abstand+" && position < laenge - "+ abstandr + "){" +lf;
		for(int pos_filter = 0,  help = - abstand ; pos_filter < faktoren.length; pos_filter++, help++){
			sFunktion	+= bs+bs+bs+"sum += " +faktoren[pos_filter] +" * werte.get(position +"+  help +");"+lf;  
		}	
		sFunktion +=bs+bs+ "}" +lf;
		
		for(int posi = 0; posi < abstandr; posi++){
			sFunktion	+= bs+bs+"else if(position == laenge - " + (posi + 1) +"){"+lf;
			for(int pos_filter = 0,  help =  -abstand-posi ; pos_filter < faktoren.length; pos_filter++, help++){
				sFunktion	+= bs+bs+bs+"sum += " +faktoren[pos_filter] +" * werte.get(laenge - "+  (abs(help)+1) +");"+lf;  
			}		
		}
		sFunktion +=bs+bs+ "}" +lf;
		sFunktion += bs+ "return sum;"+lf;
		sFunktion += bs+"}"+lf+lf;
		
		return sFunktion;
	}
	
	private void generate_symetrischt_gerade_forward(){
	}
	
	private void generate_symetrischt_gerade_backward(){	
	}
	
	private String generate_symetrischt_ungerade_backward(String Name, float[] faktoren, int pos_z0){
		String  sFunktion = new String();
		boolean first = true;
		int drehpunkt_offset = faktoren.length/2 - pos_z0;
		int iHelp1 = 0;
		int iStartFaktoren = 0;
		int iStartwerte = 0;
		sFunktion += bs+"public final float "+Name+"(Vektor_float werte, int position, boolean gerade)" +"{"+lf;
		sFunktion += bs+bs+"float sum = 0f;"+lf;
	

		for(int i = 0; i < faktoren.length; i++){
			if(i < pos_z0) {
				if(first){
					sFunktion += bs+bs+"if(position == "+i+"){"+lf;
					first = false;
				}else{
					sFunktion += bs+bs+"else if(position == "+i+"){"+lf;
				}
				
				iStartFaktoren =FaktorStart(i % 2 == 0,drehpunkt_offset,pos_z0);
							
				for(int it = iStartFaktoren; it < faktoren.length; it+=2 ){
					iStartwerte = i/2 + nextWert(it, drehpunkt_offset, it % 2 == 0, faktoren.length, pos_z0,i,9999);
					sFunktion += bs+bs+bs+"sum += "+faktoren[it] +" * werte.get("+iStartwerte+"){"+lf;
				}
				
				
				sFunktion += bs+bs+"}"+lf;
			}else if(i > faktoren.length-pos_z0-1){
//###############################################################################################
				
				iHelp1 = faktoren.length - i;
				sFunktion += bs+bs+"else if(position == werte.length - "+iHelp1+"){"+lf;
				sFunktion += bs+bs+bs+"if(position % 2 == 0){"+lf;
					iStartFaktoren = FaktorStart(true,drehpunkt_offset,pos_z0);			
					for(int it = iStartFaktoren; it < faktoren.length; it+=2 ){
						iStartwerte = nextWert(it, drehpunkt_offset,true, faktoren.length, pos_z0,999,iHelp1);
						sFunktion += bs+bs+bs+"sum += "+faktoren[it] +" * werte.get(position/2 + "+iStartwerte+"){"+lf;
					}					
				sFunktion += bs+bs+bs+"}else{"+lf;
					iStartFaktoren =FaktorStart(false,drehpunkt_offset,pos_z0);			
					for(int it = iStartFaktoren; it < faktoren.length; it+=2 ){
						iStartwerte = nextWert(it, drehpunkt_offset,false, faktoren.length, pos_z0,999,iHelp1);
						sFunktion += bs+bs+bs+"sum += "+faktoren[it] +" * werte.get(position/2 + "+iStartwerte+"){"+lf;
					}				
				sFunktion += bs+bs+bs+"}"+lf;
				sFunktion += bs+bs+"}"+lf;				
			}else{
//#####################################################################################################
				iHelp1 = faktoren.length - i- 1;
				sFunktion += bs+bs+"else if(position > "+(i-1)+" &&  position < werte.length - "+iHelp1+"){"+lf;
				sFunktion += bs+bs+bs+"if(position % 2 == 0){"+lf;
					iStartFaktoren = FaktorStart(true,drehpunkt_offset,pos_z0);			
					for(int it = iStartFaktoren; it < faktoren.length; it+=2 ){
						iStartwerte = nextWert(it, drehpunkt_offset,true, faktoren.length, pos_z0,999,999);
						sFunktion += bs+bs+bs+"sum += "+faktoren[it] +" * werte.get(position/2 +"+iStartwerte+"){"+lf;
					}			
				sFunktion += bs+bs+bs+"}else{"+lf;
					iStartFaktoren =FaktorStart(false,drehpunkt_offset,pos_z0);			
					for(int it = iStartFaktoren; it < faktoren.length; it+=2 ){
						iStartwerte = nextWert(it, drehpunkt_offset,false, faktoren.length, pos_z0,999,999);
						sFunktion += bs+bs+bs+"sum += "+faktoren[it] +" * werte.get(position/2 + "+iStartwerte+"){"+lf;
					}
								
				sFunktion += bs+bs+bs+"}"+lf;				
				sFunktion += bs+bs+"}"+lf;					
			}
		}
		
		
		sFunktion += bs+"}"+lf+lf;
		
		
		return sFunktion;
	}
	
	/*
	 * Hilfsfunktion 
	 */
	private int FaktorStart(boolean pos_gerda, int drehpunkt_offset, int pos_z0){
		if(pos_gerda){
			if(drehpunkt_offset == 0){
				if(pos_z0 % 2 == 0)
					return  0;
				else
					return 1;	
			}else{
				if(pos_z0 % 2 == 0)
					return  0;
				else
					return  1;							
			}
		}else{
			if(drehpunkt_offset == 0){
				if(pos_z0 % 2 == 0)
					return 	1;
				else
					return  0;	
			}else{
				if(pos_z0 % 2 == 0)
					return  1;
				else
					return  0;													
			}			
		}
	}
	
	/*
	 * Hilfsfunkton
	 */
	private int nextWert(int it, int drehpunkt_offset, boolean gerade,int Filterlänge, int pos_z0, int distanzoben, int distanzunten){
		int BACK = 0;
		int[] HelpTabelle = new int[Filterlänge];
		int Help = -pos_z0;
		for(int i = 0; i < HelpTabelle.length; i++,Help++){
			HelpTabelle[i] = Help;
		}
		
		if(gerade){
			BACK = HelpTabelle[it] / 2;
		}else{
			BACK = HelpTabelle[it] / 2;
			if(HelpTabelle[it] > 0)
				BACK = (HelpTabelle[it]+1) / 2;
		}
		
		

		return BACK;
	}
	
	
	private void gnearate_normal_forward(){
	}
	
	private void gnearate_normal_backward(){
	}
	
	public String getSource(){
		return this.source;
	}
	
	private int abs(int in){
		if(in <  0){
			return in*-1;
		}
		return in;
	}
	
	
}
