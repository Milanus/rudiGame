package com.schn.rudolfthereindeer.scenes;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.schn.rudolfthereindeer.GameActiviry;
import com.schn.rudolfthereindeer.ResourcesManager;

import android.app.Activity;
import android.view.Display;

public class MenuScene extends Scene {
	private GameActiviry activity;
	private VertexBufferObjectManager vbom;
	private Camera camera;
	private Engine engine;
	private ITextureRegion backround;
	
	public MenuScene(GameActiviry activity, VertexBufferObjectManager vbom,Camera camera,Engine engine) {
		this.activity = activity;
		this.vbom = vbom;
		this.camera = camera;
		this.engine = engine;
		loadMenuResources();
	}
	
	public void createBackRound () {
		Scene myScene = new Scene ();		
		Sprite mySprite = new Sprite(camera.getWidth()/2, camera.getHeight()/2, backround, vbom);
		SpriteBackground back = new SpriteBackground(mySprite);
		myScene.setBackground(back);	

	}
	private void loadMenuResources () {
		ResourcesManager.getInstance().create(activity, engine, camera, vbom);
		ResourcesManager.getInstance().loadMenuFromAssets();
		backround = ResourcesManager.getInstance().backround;
	}
	private void unloadMenuResources () {
		backround = null;
	}

}
