package com.cs342.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Class that will let other classes inherit methods of State.
 */
public abstract class State {

    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected GameStateManager gsm;

    /**
     * Constructor to create a State
     *
     * @param gsm GameStateManager Variable to control State stack.
     */
    protected State(GameStateManager gsm) {
        this.gsm = gsm;
        cam = new OrthographicCamera();
        mouse = new Vector3();
    }

    /**
     * Method to control user input.
     */
    protected abstract void handleInput();

    /**
     * Method to update frames.
     *
     * @param dt float Number used to indicate updates.
     */
    public abstract void update(float dt);

    /**
     * Method to control drawing of textures.
     *
     * @param sb SpriteBatch Variable to control drawing.
     */
    public abstract void render(SpriteBatch sb);

    /**
     * Method to dispose any assets used in a class.
     */
    public abstract void dispose();
}
