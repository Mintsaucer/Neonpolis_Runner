package com.neonpolis.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
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

    private Music music;

    private TextureRegion myTextureRegion;
    private TextureRegionDrawable myTexRegionDrawablePlay, myTexRegionDrawableInfo;
    private ImageButton button, buttonInfo;
    private Table table;

    public static Texture backgroundTexture, playTexture, infoTexture;

    public MenuScreen (final Neonpolis game) {
        this.game = game;
        batch = new SpriteBatch();
        stage = new Stage();

        Gdx.input.setCatchBackKey(false);
        Gdx.input.setInputProcessor(stage);

        music = Neonpolis.manager.get("audio/music/hubbub_music.ogg", Music.class);
        music.setLooping(true);
        music.play();
    }

    @Override
    public void show() {

        backgroundTexture = new Texture("background.png");

        playTexture = new Texture("playgame.png");
        infoTexture = new Texture("infobutton.png");

        myTextureRegion = new TextureRegion(playTexture);
        myTexRegionDrawablePlay = new TextureRegionDrawable(myTextureRegion);

        myTextureRegion = new TextureRegion(infoTexture);
        myTexRegionDrawableInfo = new TextureRegionDrawable(myTextureRegion);

        button = new ImageButton(myTexRegionDrawablePlay);
        buttonInfo = new ImageButton(myTexRegionDrawableInfo);

        table = new Table();
        table.add(buttonInfo).padLeft(900).size(100,100);
        table.row();
        table.add(button).center();


        table.setSize(1100,1000);
        table.setFillParent(true);
        stage.addActor(table); //Add the button to the stage

        // menu button listeners
        button.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new PlayScreen(game));
                music.stop();
            }
        });

        buttonInfo.addListener( new ClickListener() {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            game.setScreen(new InfoScreen(game));
            music.stop();
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
        music.dispose();
    }
}
