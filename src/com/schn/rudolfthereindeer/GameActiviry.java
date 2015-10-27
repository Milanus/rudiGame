package com.schn.rudolfthereindeer;

import java.io.IOException;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.engine.options.resolutionpolicy.IResolutionPolicy;
import org.andengine.entity.Entity;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.particle.BatchedPseudoSpriteParticleSystem;
import org.andengine.entity.particle.BatchedSpriteParticleSystem;
import org.andengine.entity.particle.emitter.RectangleParticleEmitter;
import org.andengine.entity.particle.initializer.ExpireParticleInitializer;
import org.andengine.entity.particle.initializer.VelocityParticleInitializer;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.EntityBackground;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.UncoloredSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.bitmap.BitmapTextureFormat;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.util.adt.color.Color;

import android.util.Log;
import android.view.Display;



public class GameActiviry extends BaseGameActivity {
	
	private static final int CAMERA_WIDTH = 480;
	private static final int CAMERA_HEIGHT = 800;
	ITextureRegion backroundRegion;
	public ITextureRegion backround;
	private ITextureRegion cloud;
	public ITextureRegion snowOne;
	public ITextureRegion snowTwo;
	public ITextureRegion snowThree;
	public ITextureRegion moon;
	public ITextureRegion rudy;
	public ITextureRegion play_btn;
	public ITextureRegion settings_btn;
	public ITextureRegion share_btn;
	public ITextureRegion back_image;
	public ITextureRegion back_arrow;
	public Sprite backSpriteStngs;
	
	private Boolean settingsChanger = false;

	@Override
	public EngineOptions onCreateEngineOptions() {
		IResolutionPolicy ration = new FillResolutionPolicy();
		Camera camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		EngineOptions engine = new EngineOptions(true,ScreenOrientation.PORTRAIT_FIXED, ration, camera);
		engine.setWakeLockOptions(WakeLockOptions.SCREEN_ON);
		engine.getRenderOptions().setDithering(true);
		return engine;
	}

	@Override
	public void onCreateResources(
			OnCreateResourcesCallback pOnCreateResourcesCallback)
			throws IOException {
		ResourcesManager.getInstance().create(this, getEngine(), getEngine().getCamera(), getVertexBufferObjectManager());
		ResourcesManager.getInstance().loadMenuFromAssets();
		backround = ResourcesManager.getInstance().backround;
		cloud = ResourcesManager.getInstance().cloudOne;
		snowOne = ResourcesManager.getInstance().snowOne;
		snowTwo = ResourcesManager.getInstance().snowTwo;
		snowThree =ResourcesManager.getInstance().snowThree;
		moon = ResourcesManager.getInstance().moon;
		rudy =ResourcesManager.getInstance().rudy;
		play_btn = ResourcesManager.getInstance().play_btn;
		settings_btn = ResourcesManager.getInstance().settings_btn;
		share_btn = ResourcesManager.getInstance().share_btn;
		back_image = ResourcesManager.getInstance().back_image;
		back_arrow = ResourcesManager.getInstance().back_arrow;
		pOnCreateResourcesCallback.onCreateResourcesFinished();
		
	}

	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback)
			throws IOException {
		pOnCreateSceneCallback.onCreateSceneFinished(menuScene());
		
	}

	@Override
	public void onPopulateScene(Scene pScene,
			OnPopulateSceneCallback pOnPopulateSceneCallback)
			throws IOException {

		pOnPopulateSceneCallback.onPopulateSceneFinished();
		
	}
	private Scene menuScene () {
		final Scene myScene = new Scene ();
		
		Sprite mySprite = new Sprite(CAMERA_WIDTH/2, CAMERA_HEIGHT/2, backround, getVertexBufferObjectManager());
		SpriteBackground back = new SpriteBackground(mySprite);
		myScene.setBackground(back);
		Sprite rudySprite = new Sprite(300, 135, rudy, getVertexBufferObjectManager());
		final Sprite play_btnSprite = new Sprite(CAMERA_WIDTH/2,CAMERA_HEIGHT/2,play_btn, getVertexBufferObjectManager());
		final Sprite share_btnSprite = new Sprite(50, 50, share_btn, getVertexBufferObjectManager()) ;
		final Sprite backArrowSprite = new Sprite(50, 50, back_arrow, getVertexBufferObjectManager()) ;
		Log.d("Button", "button was pressed");
		Sprite settings_btnSprite = new Sprite(400, 700, settings_btn, getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				Log.d("AndEngine", "button was pressed");
				if (pSceneTouchEvent.isActionDown()) {
					if (!settingsChanger) {
						if (backSpriteStngs == null) {
							backSpriteStngs = new Sprite(CAMERA_WIDTH / 2,
									CAMERA_HEIGHT / 2, back_image,
									getVertexBufferObjectManager());
							myScene.attachChild(backSpriteStngs);
							myScene.detachChild(play_btnSprite);
							myScene.detachChild(share_btnSprite);
							settingsChanger = true;
							
						}
					} else {
						myScene.attachChild(play_btnSprite);
						myScene.detachChild(backSpriteStngs);
						myScene.attachChild(share_btnSprite);
						backSpriteStngs = null;
						settingsChanger = false;
					}
				}
				return true;
			}
		};
		
		 
//		myScene.attachChild(cloudSprite);
		myScene.attachChild(rudySprite);
		myScene.attachChild(moonRotation());
		myScene.attachChild(moveSnow ());
		myScene.attachChild(moveSnowTwo());
		myScene.attachChild(moveSnowThree ());
		myScene.attachChild(moveCload());
		myScene.attachChild(settings_btnSprite);
		myScene.registerTouchArea(settings_btnSprite);
		myScene.attachChild(play_btnSprite);
		myScene.attachChild(share_btnSprite);
		
		
		return myScene;
	}
	private BatchedSpriteParticleSystem moveSnow () {
		BatchedSpriteParticleSystem particleCloudSys = new BatchedSpriteParticleSystem(new RectangleParticleEmitter(192,800,500, 150),
				2, 6,200, snowOne, getVertexBufferObjectManager());
		particleCloudSys.addParticleInitializer(new VelocityParticleInitializer<UncoloredSprite>(10,0,-50,-90));
		particleCloudSys.addParticleInitializer(new ExpireParticleInitializer<UncoloredSprite>(10f));
		return particleCloudSys;
	}
	private BatchedSpriteParticleSystem moveSnowTwo () {
		BatchedSpriteParticleSystem particleCloudSys = new BatchedSpriteParticleSystem(new RectangleParticleEmitter(180,800,500, 100 ),
				4, 10,200, snowTwo, getVertexBufferObjectManager());
		particleCloudSys.addParticleInitializer(new VelocityParticleInitializer<UncoloredSprite>(0,20,-10,-50));
		particleCloudSys.addParticleInitializer(new ExpireParticleInitializer<UncoloredSprite>(20f));
		return particleCloudSys;
	}

	private BatchedSpriteParticleSystem moveSnowThree () {
		BatchedSpriteParticleSystem particleCloudSys = new BatchedSpriteParticleSystem(new RectangleParticleEmitter(180,800,500, 100 ),
				4, 10,200, snowThree, getVertexBufferObjectManager());
		particleCloudSys.addParticleInitializer(new VelocityParticleInitializer<UncoloredSprite>(-30,0,-20,-60));
		particleCloudSys.addParticleInitializer(new ExpireParticleInitializer<UncoloredSprite>(20f));
		return particleCloudSys;
	}
	private BatchedSpriteParticleSystem moveCload () {
		BatchedSpriteParticleSystem particleCloudSys = new BatchedSpriteParticleSystem(new RectangleParticleEmitter(-200,600,100, 500 ),
				1, 1,50, cloud, getVertexBufferObjectManager());
		particleCloudSys.addParticleInitializer(new VelocityParticleInitializer<UncoloredSprite>(30,-20,0,-10));
		particleCloudSys.addParticleInitializer(new ExpireParticleInitializer<UncoloredSprite>(50f));
		return particleCloudSys;
	}
	private Sprite moonRotation () {
		Sprite moonSprite = new Sprite(100, 500, moon, getVertexBufferObjectManager());
		RotationModifier rotation = new RotationModifier(20, 0, 360);
		LoopEntityModifier loop = new LoopEntityModifier(rotation);
		moonSprite.registerEntityModifier(loop);
		return moonSprite;
	}

	
}