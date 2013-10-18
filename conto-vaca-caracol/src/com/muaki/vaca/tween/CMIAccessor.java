package com.muaki.vaca.tween;

import aurelienribon.tweenengine.TweenAccessor;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.muaki.vaca.screens.ContoModelInstance;

public class CMIAccessor implements TweenAccessor<ContoModelInstance> {
	public static final int ROTTRANS=0;
	Vector3 vector = new Vector3();


	@Override
	public int getValues(ContoModelInstance target, int tweenType, float[] returnValues) {
		switch(tweenType){
		case ROTTRANS:
			returnValues[0]=target.transform.getTranslation(vector).y;
			returnValues[1]=target.transform.getTranslation(vector).z;
			returnValues[2]=target.deltaRx;
			
			return 3;
		default:
			assert false;
			return -1;
		}
		
		

	
	}

	@Override
	public void setValues(ContoModelInstance target, int tweenType, float[] newValues) {
	switch(tweenType){
	case ROTTRANS:
		target.transform.translate(0, newValues[0], newValues[1]);
		target.transform.rotate(Vector3.X, newValues[2]);
		break;
		default:
			assert false;
		
	
		
		}
		
		

	}

}
