package com.neonpolis.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.neonpolis.game.Neonpolis;

/**
 * Created by Lauri on 20.4.2017.
 */

public class InfoScreen implements Screen {

    private Neonpolis game;
    private SpriteBatch batch;
    private Texture textureInfo;

    public InfoScreen(Neonpolis game) {
        this.game = game;

        batch = new SpriteBatch();
        textureInfo = new Texture("info.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(textureInfo, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        // playscreen starts when screen is touched.
        if(Gdx.input.justTouched())
            game.setScreen(new PlayScreen(game));
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
