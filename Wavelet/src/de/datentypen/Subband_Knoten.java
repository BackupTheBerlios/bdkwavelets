/*
 * Created on 17.02.2004
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
public class Subband_Knoten {

	public Subband_Knoten n�chster_Knoten;

	public Subband_Daten[] Daten = new Subband_Daten[4];
	
	public Subband_Daten_diskret[] DatenQuant = new Subband_Daten_diskret[4];

	public boolean letzter;
	
	public int aktuellebreite;
	
	public int aktuelleh�he;
	
	private int mantis;
	
	private int exponent;
	
	/**
	 * Konstruiert einen ganzen Subbandbaum 
	 * 
	 * @param anzahlSubbands Die maximale anzahl an Ebenen
	 * @param kleinstebreite 	Wenn diese breite von LL erreicht wird werden keine weiteren Ebenen erstellt
	 * @param kleinsteh�he		Wenn diese h�he von LL erreicht wird werden keine weiteren Ebenen erstellt
	 * @param aktuellebreite	Die breite der vorheriegen LL-Ebene  
	 * @param aktuelleh�he		Die h�he der vorheriegen LL-Ebene
	 */
	
	public Subband_Knoten(
		int anzahlSubbands,
		int kleinstebreite,
		int kleinsteh�he,
		int aktuellebreite,
		int aktuelleh�he) {
		
			
			
			this.aktuellebreite = aktuellebreite;
			this.aktuelleh�he 	= aktuelleh�he;
		
			int breite = teile(aktuellebreite);
			int h�he = teile(aktuelleh�he);
			anzahlSubbands--;
				
			Daten[1] = new Subband_Daten(breite, h�he,  1);
			Daten[2] = new Subband_Daten(breite, h�he,  2);
			Daten[3] = new Subband_Daten(breite, h�he,  3);		
			
			/*
			 * Abbruch
			 */
			if(anzahlSubbands == 0 || breite <= kleinstebreite || h�he <= kleinsteh�he){
		
				Daten[0] = new Subband_Daten(breite, h�he,  0);
				n�chster_Knoten = null;
				letzter = true;
			}else{
			/* 
			 * Noch eine Ebene
			 */
				Daten[0] = null;
				n�chster_Knoten = new Subband_Knoten(anzahlSubbands, kleinstebreite , kleinsteh�he, breite, h�he);
				letzter = false;
			}
	}
	
	
	/**
	 * Konstruiert einen ganzen Subbandbaum 
	 * 
	 * @param anzahlSubbands Die maximale anzahl an Ebenen
	 * @param aktuellebreite	Die breite der vorheriegen LL-Ebene  
	 * @param aktuelleh�he		Die h�he der vorheriegen LL-Ebene
	 */
	public Subband_Knoten(
		int anzahlSubbands,
		int aktuellebreite,
		int aktuelleh�he) {
		
			this.aktuellebreite = aktuellebreite;
			this.aktuelleh�he 	= aktuelleh�he;
		
			int breite = teile(aktuellebreite);
			int h�he = teile(aktuelleh�he);
			anzahlSubbands--;
				
			DatenQuant[1] = new Subband_Daten_diskret(breite, h�he,  1);
			DatenQuant[2] = new Subband_Daten_diskret(breite, h�he,  2);
			DatenQuant[3] = new Subband_Daten_diskret(breite, h�he,  3);		
			
			/*
			 * Abbruch
			 */
			if(anzahlSubbands == 0){
		
				DatenQuant[0] = new Subband_Daten_diskret(breite, h�he,  0);
				n�chster_Knoten = null;
				letzter = true;
			}else{
			/* 
			 * Noch eine Ebene
			 */
			 DatenQuant[0] = null;
				n�chster_Knoten = new Subband_Knoten(anzahlSubbands, breite, h�he);
				letzter = false;
			}
	}

	/**
	 * Teil eine Zahl und rundet immer aufw�rts
	 * @param i
	 * @return
	 */
	private int teile(int i){
		if(i % 2 == 0){
			return i / 2;
		}else{
			return (i + 1)/2;
		}
	}
	public int anzahl_Subb�nder() {
		
		if(this.letzter){
			return 1;
		}else{
		
			return 1 + n�chster_Knoten.anzahl_Subb�nder();
		}
	}

	public void quantisieren(QuantisierungsInterface quantisierer){

		
		if(letzter){
			
			//quantisierer.set_Werte_bereich(finde_kleinsten(Daten[0].get_absMaxWert(), Daten[1].get_absMaxWert(), Daten[2].get_absMaxWert(), Daten[3].get_absMaxWert()));
			quantisierer.set_Werte_bereich(finde_gr�ssten(Daten[0].get_absMaxWert(), Daten[1].get_absMaxWert(), Daten[2].get_absMaxWert(), Daten[3].get_absMaxWert()));
		
			mantis = quantisierer.getMantis();
			exponent = quantisierer.getExponent();

			quantisierer.set_Werte_bereich(Daten[0].get_absMaxWert());
			DatenQuant[0] = new Subband_Daten_diskret(Daten[0], quantisierer ,Subband_Daten_diskret.LL );
			
			quantisierer.set_Werte_bereich(Daten[1].get_absMaxWert());
			DatenQuant[1] = new Subband_Daten_diskret(Daten[1], quantisierer ,Subband_Daten_diskret.HL);
			
			quantisierer.set_Werte_bereich(Daten[2].get_absMaxWert());
			DatenQuant[2] = new Subband_Daten_diskret(Daten[2], quantisierer ,Subband_Daten_diskret.LH);
			
			quantisierer.set_Werte_bereich(Daten[3].get_absMaxWert());
			DatenQuant[3] = new Subband_Daten_diskret(Daten[3], quantisierer ,Subband_Daten_diskret.HH);
		}else{
		

			//quantisierer.set_Werte_bereich(finde_kleinsten(Daten[1].get_absMaxWert(), Daten[2].get_absMaxWert(), Daten[3].get_absMaxWert()));
			quantisierer.set_Werte_bereich(finde_gr�ssten(Daten[1].get_absMaxWert(), Daten[2].get_absMaxWert(), Daten[3].get_absMaxWert()));

			mantis = quantisierer.getMantis();
			exponent = quantisierer.getExponent();

			quantisierer.set_Werte_bereich(Daten[1].get_absMaxWert());
			DatenQuant[1] = new Subband_Daten_diskret(Daten[1], quantisierer ,Subband_Daten_diskret.HL);
		
			quantisierer.set_Werte_bereich(Daten[2].get_absMaxWert());
			DatenQuant[2] = new Subband_Daten_diskret(Daten[2], quantisierer ,Subband_Daten_diskret.LH);
		
			quantisierer.set_Werte_bereich(Daten[3].get_absMaxWert());
			DatenQuant[3] = new Subband_Daten_diskret(Daten[3], quantisierer ,Subband_Daten_diskret.HH);
			n�chster_Knoten.quantisieren(quantisierer);
		}

	}
	
	
	public boolean encode_EBCOT_MQ(boolean show){
		show = DatenQuant[1].encocde_EBCOTuMQ(show);
		show = DatenQuant[2].encocde_EBCOTuMQ(show);
		show = DatenQuant[3].encocde_EBCOTuMQ(show);
		if(letzter){
			show = DatenQuant[0].encocde_EBCOTuMQ(show);	
		}else{
			show = n�chster_Knoten.encode_EBCOT_MQ(show);
		}
		
		return show;
	}
	
	
	public void dequantisieren(QuantisierungsInterface quantisierer){
		
		quantisierer.set_dequantisierer(mantis, exponent);
		
		Daten[1] = new Subband_Daten(DatenQuant[1],  quantisierer);
		Daten[2] = new Subband_Daten(DatenQuant[2],  quantisierer);
		Daten[3] = new Subband_Daten(DatenQuant[3],  quantisierer);		
		if(letzter){
			Daten[0] = new Subband_Daten(DatenQuant[0], quantisierer);
		}else{
			n�chster_Knoten.dequantisieren(quantisierer);
		}
	
	}
	
	private float finde_gr�ssten(float in1, float in2, float in3){
		if(in1>in2){
			if(in1> in3){
				return in1;	
			}else{
				return in3;
			}
		}else{
			if(in2>in3){
				return in2;
			}else{
				return in3;
			}
		}
	}
	
	private float finde_gr�ssten(float in1, float in2, float in3, float in4){
		float temp = finde_gr�ssten(in1, in2,  in3);
		if(temp > in4){
			return temp;
		}else{
			return in4;
		}
	}
	
	private float finde_kleinsten(float in1, float in2, float in3){
		if(in1<in2){
			if(in1<in3){
				return in1;
			}else{
				return in3;
			}
		}else{
			if(in2 < in3){
				return in2;
			}else{
				return in3;
			}
		}
	}
	
	private float finde_kleinsten(float in1, float in2, float in3, float in4){
		float temp = finde_kleinsten(in1, in2, in3);
		if(temp < in4){
			return temp;
		}else{
			return in4;
		}
	}
	
}
