package com.cs342.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

/**
 * Class to control the background of the game.
 */
public class Background {
    private Texture background;
    private Vector3 position;

    /**
     * Constructor to build the background.
     * @param texture Texture Image of the background
     * @param x float Create drawing at point x.
     * @param y float Create drawing at point y.
     * @param z float Create drawing at point z.
     */
    public Background(Texture texture, float x, float y, float z) {
        this.background = texture;
        position = new Vector3(x, y , z);
    }

    /**
     * Method to control the way the background moves
     *
     * @param x float Moves along x variable.
     * @param y float Moves along y variable.
     * @param z float Moves along z variable.
     */
    public void move(float x, float y, float z) {
        position.add(x, y, z);
    }

    /**
     * Method to return the position of this object.
     *
     * @return Vector3 Returns the position vector of this object.
     */
    public Vector3 getPosition() {
        return position;
    }

    /**
     * Method to return the texture image of this object.
     *
     * @return Texture Returns texture of this object.
     */
    public Texture getTexture() {
        return background;
    }

    /**
     * Method to dispose of assets used in this object.
     */
    public void dispose() {
        background.dispose();
    }


}
