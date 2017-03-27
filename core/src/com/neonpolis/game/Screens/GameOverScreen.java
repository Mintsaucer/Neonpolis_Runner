package com.neonpolis.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.neonpolis.game.Neonpolis;

import static com.neonpolis.game.Screens.MenuScreen.backgroundTexture;

/**
 * Created by Lauri on 27.3.2017.
 */

public class GameOverScreen implements Screen {


    private Neonpolis game;
    private SpriteBatch batch;
    private Stage stage;

    private Texture textureGameOver;

    private Table table;
    private OrthographicCamera camera;
    ScreenViewport viewport;

    Label gameOver;

    public GameOverScreen(Neonpolis game) {
        this.game = game;

        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new ScreenViewport(camera);
        viewport.apply();

        textureGameOver = new Texture("background.png");

        camera.position.set(camera.viewportHeight, camera.viewportHeight, 0);
        camera.update();

        stage = new Stage(viewport, batch);

        Gdx.input.setInputProcessor(stage);

        table = new Table();
        gameOver = new Label(("GAME OVER!"), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        gameOver.setFontScale(4);
        table.add(gameOver).center();
        table.setSize(306,423);

        table.setFillParent(true);
        stage.addActor(table);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(textureGameOver, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        // Menu screen starts when screen is touched.
        if(Gdx.input.justTouched())
            game.setScreen(new MenuScreen(game));
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
        batch.dispose();
    }
}
