package com.neonpolis.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.neonpolis.game.Neonpolis;

import java.awt.Color;

/**
 * Created by Lauri on 22/03/2017.
 */

public class MenuScreen implements Screen {

    private Neonpolis game;
    private SpriteBatch batch;
    private Stage stage;

    private TextureRegion myTextureRegion;
    private TextureRegionDrawable myTexRegionDrawablePlay, myTexRegionDrawableLoad, myTexRegionDrawableSetting, myTexRegionDrawableSoundSetting;
    private ImageButton button, button2, button3, button4;
    private Table table;

    public static Texture backgroundTexture, playTexture, loadTexture, settingTexture, soundSettingTexture;

    public MenuScreen (final Neonpolis game) {
        this.game = game;

        stage = new Stage();
        batch = new SpriteBatch();

        backgroundTexture = new Texture("background.png");

        playTexture = new Texture("newgame.png");
        loadTexture = new Texture("loadgame.png");
        settingTexture = new Texture("setting.png");
        soundSettingTexture = new Texture("soundsetting.png");

        myTextureRegion = new TextureRegion(playTexture);
        myTexRegionDrawablePlay = new TextureRegionDrawable(myTextureRegion);

        myTextureRegion = new TextureRegion(loadTexture);
        myTexRegionDrawableLoad = new TextureRegionDrawable(myTextureRegion);

        myTextureRegion = new TextureRegion(settingTexture);
        myTexRegionDrawableSetting = new TextureRegionDrawable(myTextureRegion);

        myTextureRegion = new TextureRegion(soundSettingTexture);
        myTexRegionDrawableSoundSetting = new TextureRegionDrawable(myTextureRegion);

        button = new ImageButton(myTexRegionDrawablePlay);
        button2 = new ImageButton(myTexRegionDrawableLoad);
        button3 = new ImageButton(myTexRegionDrawableSetting);
        button4 = new ImageButton(myTexRegionDrawableSoundSetting);

        table = new Table();
        table.add(button).top();
        table.add(button2);
        table.row();
        table.add(button3);
        table.add(button4);

        table.setFillParent(true);

        stage.addActor(table); //Add the button to the stage
        Gdx.input.setInputProcessor(stage);

        // menu button listeners
        button.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen( new PlayScreen(game));
            }
        });
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
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
