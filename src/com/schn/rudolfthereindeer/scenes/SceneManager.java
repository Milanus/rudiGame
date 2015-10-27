package com.schn.rudolfthereindeer.scenes;

public class SceneManager {
	
	private static SceneManager INSTANCE;
	
	public static SceneManager getInstance () {
		 if (INSTANCE==null) {
			 INSTANCE = new SceneManager();
		 }
		 return INSTANCE;
	}

}
