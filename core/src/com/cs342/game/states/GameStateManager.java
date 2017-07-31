package com.cs342.game.states;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Class to manage the game states.
 */
public class GameStateManager {
    private Stack<State> states;

    /**
     * Constructor to create the game State stack
     */
    public GameStateManager() {
        states = new Stack<State>();
    }

    /**
     * Method to push a game state onto the stack.
     *
     * @param state State State to be pushed onto the stack.
     */
    public void push(State state) {
        states.push(state);
    }

    /**
     * Method to pop a state from the stack.
     */
    public void pop() {
        states.pop().dispose();
    }

    /**
     * Method to set a the stack to a state.
     *
     * @param state State State to be set on the stack.
     */
    public void set(State state) {
        states.pop().dispose();
        states.push(state);
    }

    /**
     * Method to update frames.
     */
    public void update(float dt) {
        states.peek().update(dt);
    }

    /**
     * Method to render the objects on the stack.
     *
     * @param sb SpriteBatch Variable to allow drawing.
     */
    public void render(SpriteBatch sb) {
        states.peek().render(sb);
    }
}
