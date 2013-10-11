package com.muaki.vaca.screens;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class ContoModelInstance extends ModelInstance{
	boolean libroaberto = false;
	int deltaRx;
	int deltaY;
	int deltaZ;
	boolean visible;

	

	public ContoModelInstance(Model model, Array<String> rootNodeIds) {
		super(model, rootNodeIds);
	}

	public ContoModelInstance(Model model, float x, float y, float z) {
		super(model, x, y, z);
	}

	public ContoModelInstance(Model model, Matrix4 transform,
			Array<String> rootNodeIds) {
		super(model, transform, rootNodeIds);
	}

	public ContoModelInstance(Model model, Matrix4 transform, String nodeId,
			boolean recursive, boolean parentTransform, boolean mergeTransform) {
		super(model, transform, nodeId, recursive, parentTransform, mergeTransform);
	}

	public ContoModelInstance(Model model, Matrix4 transform, String nodeId,
			boolean parentTransform, boolean mergeTransform) {
		super(model, transform, nodeId, parentTransform, mergeTransform);
	}

	public ContoModelInstance(Model model, Matrix4 transform, String nodeId,
			boolean mergeTransform) {
		super(model, transform, nodeId, mergeTransform);
	}

	public ContoModelInstance(Model model, Matrix4 transform,
			String... rootNodeIds) {
		super(model, transform, rootNodeIds);
	}

	public ContoModelInstance(Model model, Matrix4 transform) {
		super(model, transform);
	}

	public ContoModelInstance(Model model, String nodeId, boolean recursive,
			boolean parentTransform, boolean mergeTransform) {
		super(model, nodeId, recursive, parentTransform, mergeTransform);
	}

	public ContoModelInstance(Model model, String nodeId,
			boolean parentTransform, boolean mergeTransform) {
		super(model, nodeId, parentTransform, mergeTransform);
	}

	public ContoModelInstance(Model model, String nodeId, boolean mergeTransform) {
		super(model, nodeId, mergeTransform);
	}

	public ContoModelInstance(Model model, String... rootNodeIds) {
		super(model, rootNodeIds);
	}

	public ContoModelInstance(Model model, Vector3 position) {
		super(model, position);
	}

	public ContoModelInstance(Model model) {
		super(model);
	}

	public ContoModelInstance(ModelInstance copyFrom, Matrix4 transform) {
		super(copyFrom, transform);
	}

	public ContoModelInstance(ModelInstance copyFrom) {
		super(copyFrom);
	}

	
	
public boolean AbrirLibro(){
	
	libroaberto = true;
	
	return libroaberto;
	
}


public boolean PecharLibro(){
	
	libroaberto = false;
	
	return libroaberto;
	
}

public void AsignamosValores(int num){

	
	switch ( num){
	case 0:
		 deltaRx=0;
		 deltaY=0;
		 deltaZ=0;
		 visible=true;
		
		break;
	case 1:
		 deltaRx=1;
		 deltaY=1;
		 deltaZ=1;
		 visible=true;
		
		break;
		
	case 2:
		 deltaRx=2;
		 deltaY=2;
		 deltaZ=2;
		 visible=true;
		
		
		break;
		
	case 3:
		 deltaRx=3;
		 deltaY=3;
		 deltaZ=3;
		 visible=true;
		
		break;
		
	case 4:
		 deltaRx=4;
		 deltaY=4;
		 deltaZ=4;
		 visible=true;
		
		break;
		
	case 5:
		 deltaRx=5;
		 deltaY=5;
		 deltaZ=5;
		 visible=true;
		
		break;
		
	
	
	}
		
}


}
