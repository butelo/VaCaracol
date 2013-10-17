package com.muaki.vaca.simulations;

import com.muaki.vaca.caracol.Preferencias;
//aqui levamos control das paxinas do libro
public class Control {
	
	
	public int CurrPax = Preferencias.CURRPAX;
	public int PaxsTot = Preferencias.DURACIONPXS;
	
//	os ciclos cortos son para cargar as paxs na mamoria
	public int CicloCorto(){
		return CurrPax%6;
		
	}
	
	
	
	

}
