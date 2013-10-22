package com.muaki.vaca.tween;

import aurelienribon.tweenengine.TweenAccessor;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

public class CMIAccessor implements TweenAccessor<ModelInstance> {
	public static final int ROTATION=0;



	@Override
	public int getValues(ModelInstance target, int tweenType, float[] returnValues) {
		switch(tweenType){
			
		case ROTATION:
			returnValues[0]=0;
			return 1;
		default:
			assert false;
			return -1;
		}
		
		

	
	}

	@Override
	public void setValues(ModelInstance target, int tweenType, float[] newValues) {
	switch(tweenType){

		
	case ROTATION:

		target.transform.setToRotation(Vector3.X, newValues[0]);		
break;		
	
		default:
			assert false;
		
	
		
		}
		
		

	}

}
