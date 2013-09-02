package com.muaki.vaca.caracol;

public class Preferencias {
	
	
public static final int DURACIONPXS = 21;


public static final String Vs = Messages.getString("vs"); //$NON-NLS-1$


public  final int LANG_EN = 0;
public  final int LANG_SP = 1;
public final int LANG_GL = 2;

public int CURRLANG = LANG_EN ;

public static  int CURRPAX=0;

public boolean SOUNDPLAY = true;
public boolean MUSICPLAY = true;
public boolean VOICEPLAY = true;



public String TITLE= getTitle();


 public final   String getTitle(){
	 
	 switch(CURRLANG){

	 	case LANG_EN:
	 		TITLE = Messages.getString("Preferencias.1"); //$NON-NLS-1$
	 		break;
	 		 	 	
	 	case LANG_SP:
	 		TITLE = Messages.getString("Preferencias.2"); //$NON-NLS-1$
	 		break;
	 		 
	 	
	 	case LANG_GL:
	 		TITLE = Messages.getString("Preferencias.3"); //$NON-NLS-1$
	 		break;
	 		 

	 }
	return TITLE;

	
}




}
