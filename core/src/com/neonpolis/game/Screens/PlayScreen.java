package com.neonpolis.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.neonpolis.game.Neonpolis;
import com.neonpolis.game.Stages.GameStage;

/**
 * Created by nikom on 20.3.2017.
 */

public class PlayScreen implements Screen {

    private GameStage stage;
    private Neonpolis game;

    public PlayScreen(Neonpolis game){
        stage = new GameStage();
        this.game = game;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        //Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //update stage
        stage.draw();
        stage.act(delta);
    }

    public void handleInput() {
    }

    public void update (float dt) {

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
