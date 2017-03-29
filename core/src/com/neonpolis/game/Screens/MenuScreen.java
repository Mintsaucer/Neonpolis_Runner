package com.neonpolis.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.neonpolis.game.Neonpolis;


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
        batch = new SpriteBatch();
        stage = new Stage();

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

        backgroundTexture = new Texture("background.png");

        playTexture = new Texture("playgame.png");
        //loadTexture = new Texture("loadgame.png");
        //settingTexture = new Texture("setting.png");
        //soundSettingTexture = new Texture("soundsetting.png");

        myTextureRegion = new TextureRegion(playTexture);
        myTexRegionDrawablePlay = new TextureRegionDrawable(myTextureRegion);

        //myTextureRegion = new TextureRegion(loadTexture);
        //myTexRegionDrawableLoad = new TextureRegionDrawable(myTextureRegion);

        //myTextureRegion = new TextureRegion(settingTexture);
        //myTexRegionDrawableSetting = new TextureRegionDrawable(myTextureRegion);

        //myTextureRegion = new TextureRegion(soundSettingTexture);
        //myTexRegionDrawableSoundSetting = new TextureRegionDrawable(myTextureRegion);

        button = new ImageButton(myTexRegionDrawablePlay);
        //button2 = new ImageButton(myTexRegionDrawableLoad);
        //button3 = new ImageButton(myTexRegionDrawableSetting);
        //button4 = new ImageButton(myTexRegionDrawableSoundSetting);

        table = new Table();
        table.add(button).center();
        //table.add(button2);
        //table.row().pad(30);
        //table.add(button3);
        //table.add(button4);
        table.setSize(1100,1000);
        table.setFillParent(true);
        stage.addActor(table); //Add the button to the stage

        // menu button listeners
        button.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new PlayScreen(game));
            }
        });
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
        batch.dispose();

    }
}
