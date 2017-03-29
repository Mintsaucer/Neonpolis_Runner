package com.neonpolis.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.neonpolis.game.Screens.PlayScreen;
import com.neonpolis.game.Screens.SplashScreen;

public class Neonpolis extends Game {
/*
	// Virtual resolution if need that with viewports
	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT = 200;
	// Pixel per meter
	public static final float PPM = 100;*/

	// Manage all music, sounds
	public static AssetManager manager;

	// All textures go to batch
	public SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		manager = new AssetManager();
		// manager.load(" tänne_musiikkia.ogg ", Music.class);
		// manager.finishLoading();

		// Splash screen starts first
		setScreen(new SplashScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		super.dispose();
		manager.dispose();
		batch.dispose();
	}
}
