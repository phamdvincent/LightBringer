package com.cs342.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.cs342.game.LightBringer;

public class Menu extends State {

    private Texture background;
    private BitmapFont font;
    public  Menu(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(true);
        background = new Texture("background.png");

        font = new BitmapFont(true);
        font.setColor(Color.WHITE);
        font.getData().scale(10);

    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()) {
            gsm.set(new Play(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        sb.draw(background, 0, 0, cam.viewportWidth, cam.viewportHeight);
        font.draw(sb, "LightBringer\n Click to Play", 0, cam.viewportHeight/3);

        sb.end();
    }

    @Override
    public void dispose() {
        font.dispose();
        background.dispose();
    }
}
