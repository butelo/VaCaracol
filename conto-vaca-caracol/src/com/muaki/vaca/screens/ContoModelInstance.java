package com.muaki.vaca.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.muaki.vaca.tween.BookAnimation;
import com.muaki.vaca.tween.BookAnimationController;

public class ContoModelInstance extends ModelInstance{
	boolean libroaberto = false;
	public float deltaRx;
	int deltaY;
	int deltaZ;
	boolean visible;
	/** nome da paxina para debugear **/
	String pagename;
	String valoress= " ";
//	private float[] posicion;
	public BookAnimation bookanimation =new BookAnimation();

	



	public ContoModelInstance(Model model, String... rootNodeIds) {
		super(model, rootNodeIds);
	}

	
	
	
public boolean AbrirLibro(){
	
	libroaberto = true;
	
	return libroaberto;
	
}


public boolean PecharLibro(){
	
	libroaberto = false;
	
	return libroaberto;
	
}

/**
 * @param num
 * @param xyz
 */
public void PasamosPaxina(int num, Vector3 xyz){

//estes cases son os que manexan a rotacion das paxinas do libro
//o case 0 cando o libro esta recen aberto seria a paxina 6 que pasa de estar agochada na portada
//a estar agochada na contraportada, non hai animacion o cambio e inmediato
	
	switch ( num){
	case 0:
		this.transform.rotate(Vector3.X, 100);
//		modelInstance.transform.translate(0, 0, 0);
		
		xyz.x = 0;
		xyz.y = 0.044f;
		xyz.z = -0.99f;
		this.transform.setTranslation(xyz);
		deltaRx = 0;
		
		xyz = this.transform.getTranslation(xyz);
		
		valoress ="x: "+ xyz.x + " y:"+ xyz.y + " z:" + xyz.z + "rotate"+this.deltaRx ;
		
		
		 
		
		
		 Gdx.app.log("FPSLogger", "paxina "+pagename +" pasa a ser case "+num+ " "+ valoress);
	
		 valoress="";
		break;
	case 1:
//		modelInstance.transform.rotate(Vector3.X, -95);
		
//		o case 1 e a paxina que esta agochada na contraportada que sube para riba un posto e deixa o hueco para a paxina que 
//		esta agochada na portada
//		non hai rotacion, so un pouco de traslacion hacia arriba
		
		
		this.transform.translate(0, 0f, 0.06f);
		
		
		
		deltaRx = 0;

	
			xyz = this.transform.getTranslation(xyz);

			valoress ="x: "+ xyz.x + " y:"+ xyz.y + " z:" + xyz.z + "rotate"+this.deltaRx ;

		 
		 Gdx.app.log("FPSLogger", "paxina "+pagename +" pasa a ser case "+num+ " "+ valoress);
	
		
		break;
		
	case 2:
//		modelInstance.transform.rotate(Vector3.X, 0);
		
//		o case dous e a paxina que esta xusto debaixo da que esta aberta e que pasa a ser a paxina visible da parte
//		de abaixo, tampouco hai rotacion, solo traslacion hacia arriba
		
		this.transform.translate(0, 0f, 0.04f);			

		
		deltaRx = 0;


		 
			xyz = this.transform.getTranslation(xyz);

			valoress ="x: "+ xyz.x + " y:"+ xyz.y + " z:" + xyz.z + "rotate"+this.deltaRx ;


		 Gdx.app.log("FPSLogger", "paxina "+pagename +" pasa a ser case "+num+ " "+ valoress);

		 valoress="";
		break;
		
	case 3:
//		modelInstance.transform.rotate(Vector3.X, 0);
		
		
//		esta e a paxina visible da parte de abaixo que pasa a ser a paxina visible da parte de arriba
//		rota -95 graos, non hai traslacion
		
		
		
		this.transform.rotate(Vector3.X, -95).translate(0, 0f, 0);	
		
		deltaRx = -95;

		
		 
			xyz = this.transform.getTranslation(xyz);

			valoress ="x: "+ xyz.x + " y:"+ xyz.y + " z:" + xyz.z + "rotate"+this.deltaRx ;

		
		 
		 Gdx.app.log("FPSLogger", "paxina "+pagename +" pasa a ser case "+num+ " "+ valoress);
		 valoress="";
		break;
		
	case 4:
//		modelInstance.transform.rotate(Vector3.X, -95);
		this.transform.translate(0,  -0.12f, 0.05f).rotate(Vector3.X, -5);	

//		a paxina visible da parte de arriba pasa a ser a paxina de arriba de detras da visible
//		ainda esta visible e rota -5 grados para ter unha rotacion de -100, ainda se ve
		
		
		deltaRx = -100;

		 
			xyz = this.transform.getTranslation(xyz);

			valoress ="x: "+ xyz.x + " y:"+ xyz.y + " z:" + xyz.z + "rotate"+this.deltaRx ;

		 
	
		 Gdx.app.log("FPSLogger", "paxina "+pagename +" pasa a ser case "+num+ " "+ valoress);
		 valoress="";
		
		break;
		
	case 5:
//		modelInstance.transform.rotate(Vector3.X, -5);
		
//		a paxina que esta detras da visible da parte de arriba pasa a ser a paxina oculta na portada
//		non hai rotacion, a rotacion sigue sendo -100 pero hai traslacion para ocultarse na portada do libro
//		a continuacion pasamos a case 0 de novo e volta a empezar
		
		this.transform.translate(0, -0.012f, 0.048f);	
		xyz.x = 0;
		xyz.y = 0.2540f;
		xyz.z = -1.1168f;
		this.transform.setTranslation(xyz);


		deltaRx = -100;

		 
			xyz = this.transform.getTranslation(xyz);

		 
			valoress ="x: "+ xyz.x + " y:"+ xyz.y + " z:" + xyz.z + "rotate"+this.deltaRx ;

		 
		 Gdx.app.log("FPSLogger", "paxina "+pagename +" pasa a ser case "+num+ " "+ valoress);
		 valoress="";
		
		break;
		
	
	
	}
		
}



}
