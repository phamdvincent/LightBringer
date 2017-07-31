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

/**
 * Class to manage the losing screen.
 */
public class LoseScreen extends State {

    private Texture background;

    private BitmapFont font;

    /**
     * Constructor to create the losing screen.
     *
     * @param gsm GameStateManager Variable used to control the state stack.
     */
    public  LoseScreen(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(true);
        background = new Texture("background.png");

        font = new BitmapFont(true);
        font.setColor(Color.WHITE);
        font.getData().scale(10);

    }

    /**
     * Method to handle any input by the user.
     */
    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()) {
            gsm.set(new Play(gsm));
        }
    }

    /**
     * Method to update frames.
     *
     * @param dt float Number to indicate updates.
     */
    @Override
    public void update(float dt) {
        handleInput();
    }

    /**
     * Method that draws the objects used in the class
     *
     * @param sb SpriteBatch Variable used to control drawing.
     */
    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        sb.draw(background, 0, 0, cam.viewportWidth, cam.viewportHeight);
        font.draw(sb, "You lost!", cam.viewportWidth/4, cam.viewportHeight/2);

        sb.end();
    }

    /**
     * Method to dispose any assets used in this class
     */
    @Override
    public void dispose() {
        font.dispose();
        background.dispose();
    }
}
