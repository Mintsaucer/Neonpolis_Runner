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
import com.neonpolis.game.Scenes.Hud;

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
    Hud hud;

    private Integer score = 0;

    Label gameOverLabel, scoreLabel;

    public GameOverScreen(Neonpolis game, Integer gold) {
        this.game = game;

        score = gold;
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
        gameOverLabel = new Label(("GAME OVER!"), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        gameOverLabel.setFontScale(5);
        table.add(gameOverLabel).center();
        table.row().pad(50);

        scoreLabel = new Label(String.format("%04d",score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel.setFontScale(4);
        table.setSize(306,423);
        table.add(scoreLabel).center();

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
