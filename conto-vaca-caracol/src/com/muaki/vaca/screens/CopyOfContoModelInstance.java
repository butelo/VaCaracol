package com.muaki.vaca.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.muaki.vaca.tween.BookAnimation;
import com.muaki.vaca.tween.BookAnimationController;

public class CopyOfContoModelInstance extends ModelInstance{
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

	



	public CopyOfContoModelInstance(Model model, String... rootNodeIds) {
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
		
		this.transform.translate(0, 0f, 0.06f);
		
		
		
		deltaRx = 0;

	
			xyz = this.transform.getTranslation(xyz);

			valoress ="x: "+ xyz.x + " y:"+ xyz.y + " z:" + xyz.z + "rotate"+this.deltaRx ;

		 
		 Gdx.app.log("FPSLogger", "paxina "+pagename +" pasa a ser case "+num+ " "+ valoress);
	
		
		break;
		
	case 2:
//		modelInstance.transform.rotate(Vector3.X, 0);
		this.transform.translate(0, 0f, 0.04f);			

		
		deltaRx = 0;


		 
			xyz = this.transform.getTranslation(xyz);

			valoress ="x: "+ xyz.x + " y:"+ xyz.y + " z:" + xyz.z + "rotate"+this.deltaRx ;


		 Gdx.app.log("FPSLogger", "paxina "+pagename +" pasa a ser case "+num+ " "+ valoress);

		 valoress="";
		break;
		
	case 3:
//		modelInstance.transform.rotate(Vector3.X, 0);
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

		
		deltaRx = -100;

		 
			xyz = this.transform.getTranslation(xyz);

			valoress ="x: "+ xyz.x + " y:"+ xyz.y + " z:" + xyz.z + "rotate"+this.deltaRx ;

		 
	
		 Gdx.app.log("FPSLogger", "paxina "+pagename +" pasa a ser case "+num+ " "+ valoress);
		 valoress="";
		
		break;
		
	case 5:
//		modelInstance.transform.rotate(Vector3.X, -5);
		
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
