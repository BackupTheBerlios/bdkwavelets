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

	public Subband_Knoten nächster_Knoten;

	public Subband_Daten[] Daten = new Subband_Daten[4];
	
	public Subband_Daten_diskret[] DatenQuant = new Subband_Daten_diskret[4];

	public boolean letzter;
	
	public int aktuellebreite;
	
	public int aktuellehöhe;
	
	private int mantis;
	
	private int exponent;
	
	/**
	 * Konstruiert einen ganzen Subbandbaum 
	 * 
	 * @param anzahlSubbands Die maximale anzahl an Ebenen
	 * @param kleinstebreite 	Wenn diese breite von LL erreicht wird werden keine weiteren Ebenen erstellt
	 * @param kleinstehöhe		Wenn diese höhe von LL erreicht wird werden keine weiteren Ebenen erstellt
	 * @param aktuellebreite	Die breite der vorheriegen LL-Ebene  
	 * @param aktuellehöhe		Die höhe der vorheriegen LL-Ebene
	 */
	
	public Subband_Knoten(
		int anzahlSubbands,
		int kleinstebreite,
		int kleinstehöhe,
		int aktuellebreite,
		int aktuellehöhe) {
		
			
			
			this.aktuellebreite = aktuellebreite;
			this.aktuellehöhe 	= aktuellehöhe;
		
			int breite = teile(aktuellebreite);
			int höhe = teile(aktuellehöhe);
			anzahlSubbands--;
				
			Daten[1] = new Subband_Daten(breite, höhe,  1);
			Daten[2] = new Subband_Daten(breite, höhe,  2);
			Daten[3] = new Subband_Daten(breite, höhe,  3);		
			
			/*
			 * Abbruch
			 */
			if(anzahlSubbands == 0 || breite <= kleinstebreite || höhe <= kleinstehöhe){
		
				Daten[0] = new Subband_Daten(breite, höhe,  0);
				nächster_Knoten = null;
				letzter = true;
			}else{
			/* 
			 * Noch eine Ebene
			 */
				Daten[0] = null;
				nächster_Knoten = new Subband_Knoten(anzahlSubbands, kleinstebreite , kleinstehöhe, breite, höhe);
				letzter = false;
			}
	}
	
	
	/**
	 * Konstruiert einen ganzen Subbandbaum 
	 * 
	 * @param anzahlSubbands Die maximale anzahl an Ebenen
	 * @param aktuellebreite	Die breite der vorheriegen LL-Ebene  
	 * @param aktuellehöhe		Die höhe der vorheriegen LL-Ebene
	 */
	public Subband_Knoten(
		int anzahlSubbands,
		int aktuellebreite,
		int aktuellehöhe) {
		
			this.aktuellebreite = aktuellebreite;
			this.aktuellehöhe 	= aktuellehöhe;
		
			int breite = teile(aktuellebreite);
			int höhe = teile(aktuellehöhe);
			anzahlSubbands--;
				
			DatenQuant[1] = new Subband_Daten_diskret(breite, höhe,  1);
			DatenQuant[2] = new Subband_Daten_diskret(breite, höhe,  2);
			DatenQuant[3] = new Subband_Daten_diskret(breite, höhe,  3);		
			
			/*
			 * Abbruch
			 */
			if(anzahlSubbands == 0){
		
				DatenQuant[0] = new Subband_Daten_diskret(breite, höhe,  0);
				nächster_Knoten = null;
				letzter = true;
			}else{
			/* 
			 * Noch eine Ebene
			 */
			 DatenQuant[0] = null;
				nächster_Knoten = new Subband_Knoten(anzahlSubbands, breite, höhe);
				letzter = false;
			}
	}

	/**
	 * Teil eine Zahl und rundet immer aufwärts
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
	public int anzahl_Subbänder() {
		
		if(this.letzter){
			return 1;
		}else{
		
			return 1 + nächster_Knoten.anzahl_Subbänder();
		}
	}

	public void quantisieren(QuantisierungsInterface quantisierer){

		
		if(letzter){
			
			//quantisierer.set_Werte_bereich(finde_kleinsten(Daten[0].get_absMaxWert(), Daten[1].get_absMaxWert(), Daten[2].get_absMaxWert(), Daten[3].get_absMaxWert()));
			quantisierer.set_Werte_bereich(finde_grössten(Daten[0].get_absMaxWert(), Daten[1].get_absMaxWert(), Daten[2].get_absMaxWert(), Daten[3].get_absMaxWert()));
		
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
			quantisierer.set_Werte_bereich(finde_grössten(Daten[1].get_absMaxWert(), Daten[2].get_absMaxWert(), Daten[3].get_absMaxWert()));

			mantis = quantisierer.getMantis();
			exponent = quantisierer.getExponent();

			quantisierer.set_Werte_bereich(Daten[1].get_absMaxWert());
			DatenQuant[1] = new Subband_Daten_diskret(Daten[1], quantisierer ,Subband_Daten_diskret.HL);
		
			quantisierer.set_Werte_bereich(Daten[2].get_absMaxWert());
			DatenQuant[2] = new Subband_Daten_diskret(Daten[2], quantisierer ,Subband_Daten_diskret.LH);
		
			quantisierer.set_Werte_bereich(Daten[3].get_absMaxWert());
			DatenQuant[3] = new Subband_Daten_diskret(Daten[3], quantisierer ,Subband_Daten_diskret.HH);
			nächster_Knoten.quantisieren(quantisierer);
		}

	}
	
	
	public boolean encode_EBCOT_MQ(boolean show){
		show = DatenQuant[1].encocde_EBCOTuMQ(show);
		show = DatenQuant[2].encocde_EBCOTuMQ(show);
		show = DatenQuant[3].encocde_EBCOTuMQ(show);
		if(letzter){
			show = DatenQuant[0].encocde_EBCOTuMQ(show);	
		}else{
			show = nächster_Knoten.encode_EBCOT_MQ(show);
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
			nächster_Knoten.dequantisieren(quantisierer);
		}
	
	}
	
	private float finde_grössten(float in1, float in2, float in3){
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
	
	private float finde_grössten(float in1, float in2, float in3, float in4){
		float temp = finde_grössten(in1, in2,  in3);
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
