package com.neonpolis.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.neonpolis.game.Screens.PlayScreen;

public class Neonpolis extends Game {

	// Virtual resolution if need that with viewports
	public static final int V_WIDTH = 200;
	public static final int V_HEIGHT = 200;
	// Pixel per meter
	public static final float PPM = 100;


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
		setScreen(new PlayScreen(this));
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
