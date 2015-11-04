package com.schn.rudolfthereindeer;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.bitmap.BitmapTextureFormat;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class ResourcesManager {
	
	private static ResourcesManager INSTANCE = null;
	private BuildableBitmapTextureAtlas gameTextureAtlas;
	
	private BuildableBitmapTextureAtlas gameSceneTextureAtlas;
	
	public GameActiviry activity;
	public Engine engine;
	public Camera camera;
	public VertexBufferObjectManager vbom;
	
	public ITiledTextureRegion playerTextureRegion;
	public ITiledTextureRegion enemyTextureRegion;
	public ITextureRegion backround;
	public ITextureRegion cloudOne;
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
	
	//game resources
	public ITextureRegion gameBackround;
	public ITextureRegion gameCloud;
	
	
	public static ResourcesManager getInstance () {
		if (INSTANCE == null) {
			INSTANCE = new ResourcesManager();
		}
		return INSTANCE;
	}
	
	 public void create (GameActiviry activity, Engine engine,Camera camera, VertexBufferObjectManager vbom) {
		 this.activity = activity;
		 this.engine = engine;
		 this.camera = camera;
		 this.vbom = vbom;
	 }
	public void loadMenuFromAssets () {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		gameTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, BitmapTextureFormat.RGBA_8888,TextureOptions.NEAREST);
		cloudOne = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity.getAssets(), "cloud_one.png");
		backround = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity.getAssets(), "menu.png");
		snowOne = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity.getAssets(), "snow_one.png");
		snowTwo = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity.getAssets(), "snow_two.png");
		snowThree = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity.getAssets(), "snow_three.png");
		moon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity.getAssets(), "moon.png");
		rudy = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity.getAssets(), "rudy.png");
		play_btn = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity.getAssets(), "play_btn.png");
		settings_btn = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity.getAssets(), "settings_btn.png");
		share_btn = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity.getAssets(), "share_btn.png");
		back_image =  BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity.getAssets(), "settings_back.png");
		back_arrow =  BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity.getAssets(), "back_arrow.png");
		
		gameSceneTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, BitmapTextureFormat.RGBA_8888,TextureOptions.NEAREST);
		gameBackround = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameSceneTextureAtlas, activity.getAssets(), "game_back.png");
		gameCloud = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameSceneTextureAtlas, activity.getAssets(), "cloud_game.png");
		try {
			gameTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(2, 0, 2));
			gameTextureAtlas.load();
			
			gameSceneTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(2, 0, 2));
			gameSceneTextureAtlas.load();
		} catch (TextureAtlasBuilderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
