package com.neonpolis.game.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.neonpolis.game.Screens.PlayScreen;


import javax.swing.plaf.nimbus.State;
import javax.swing.text.html.ImageView;
import javax.xml.soap.Text;

/**
 * Created by nikom on 23.3.2017.
 */

public class Hud implements Disposable{
    public Stage stage;
    public Viewport hudViewport;
    private TextureRegion myTextureRegion;
    private TextureRegionDrawable hudUI;
    private ImageButton pauseBtn;
    private Table table;

    public OrthographicCamera hudCam;

    private static Texture hudUIPicture;

    //Integers for gold and diamonds
    public Integer gold = 0;
    private Integer diamonds = 0;

    public Label goldLabel;
    private Label diamondLabel;
    private Label characterLabel;

    public Hud(SpriteBatch sb) {
        hudCam = new OrthographicCamera();
        hudViewport = new FitViewport(1920,1080, hudCam);
        stage = new Stage(hudViewport, sb);

        hudUIPicture = new Texture("HuD.png");
        myTextureRegion = new TextureRegion(hudUIPicture);
        hudUI = new TextureRegionDrawable(myTextureRegion);
        pauseBtn = new ImageButton(hudUI);

        //Table for HUDs graphic UI
        table = new Table();
        table.add(pauseBtn).padTop(10);
        table.setPosition(1, 637);
        table.setSize(656, 423);

        //Wrapper and setting position for text objects (health bar text, name, scores, diamonds)
        goldLabel = new Label(String.format("%04d", gold), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Container goldWrapper = new Container(goldLabel);
        goldWrapper.setTransform(true);
        goldWrapper.setOrigin(goldWrapper.getPrefWidth() / 2, goldWrapper.getPrefHeight() / 2);
        goldWrapper.setRotation(0);
        goldWrapper.setScale(4f);
        goldWrapper.setPosition(470, 860);

        diamondLabel = new Label(String.format("%04d", diamonds), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Container diamWrapper = new Container(diamondLabel);
        diamWrapper.setTransform(true);
        diamWrapper.setOrigin(diamWrapper.getPrefWidth() / 2, diamWrapper.getPrefHeight() / 2);
        diamWrapper.setRotation(0);
        diamWrapper.setScale(4f);
        diamWrapper.setPosition(497, 775);

        characterLabel = new Label("Vivica", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Container charWrapper = new Container(characterLabel);
        charWrapper.setTransform(true);
        charWrapper.setOrigin(charWrapper.getPrefWidth() / 2, charWrapper.getPrefHeight() / 2);
        charWrapper.setRotation(0);
        charWrapper.setScale(3f);
        charWrapper.setPosition(340, 1068);

        stage.addActor(table);
        stage.addActor(goldWrapper);
        stage.addActor(diamWrapper);
        stage.addActor(charWrapper);
    }

    public void updateHud() {
        gold += 5;
        goldLabel.setText(gold.toString());
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
