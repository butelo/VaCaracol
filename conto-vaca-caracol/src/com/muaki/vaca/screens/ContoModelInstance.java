package com.muaki.vaca.screens;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class ContoModelInstance extends ModelInstance{

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

	
	








}
