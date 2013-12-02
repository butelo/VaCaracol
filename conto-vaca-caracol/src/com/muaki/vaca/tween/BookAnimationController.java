package com.muaki.vaca.tween;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.muaki.vaca.screens.ContoModelInstance;

public class BookAnimationController {
	
	

	public static void abrirLibro(ContoModelInstance instance, float limit) {
	
if (instance.deltaRx>limit){
	Gdx.app.log("posicion seis ",""+ instance.deltaRx);
	float incremento;
	
	incremento = -10 * Gdx.graphics.getDeltaTime();
//		
		instance.transform.rotate(Vector3.X, incremento);
		

		instance.deltaRx += incremento;
}
		
	
			
		
	}
	
	

	

}
