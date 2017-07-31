package com.cs342.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Class to control the minion shots.
 */

public class MinionShot {

    private static final int TEXTURE_WIDTH = 10;
    private static final int TEXTURE_HEIGHT = 10;

    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Texture texture;
    private float pathX;
    private float pathY;
    private float pathZ;
    private boolean hit;

    /**
     * Constructor to create minion shots.
     *
     * @param x int Create object at point x.
     * @param y int Create object at point y.
     * @param pathX float Move along x axis.
     * @param pathY float Move along y axis.
     * @param pathZ float Move along z axis.
     */
    public MinionShot(int x, int y, float pathX, float pathY, float pathZ) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(pathX, pathY, pathZ);
        this.pathX = pathX;
        this.pathY = pathY;
        this.pathZ = pathZ;
        texture = new Texture("fire.png");

        bounds = new Rectangle(x, y, TEXTURE_WIDTH, TEXTURE_HEIGHT);

    }

    /**
     * Method to update frames.
     * @param dt float Number to indicate update of frames.
     */
    public void update(float dt) {
        velocity.scl(dt);
        bounds.setPosition(position.x, position.y);
    }

    /**
     * Method to move the bullet along the plane.
     */
    public void move() {
        position.add(pathX, pathY, pathZ);

    }

    /**
     * Method to return the position of the object.
     * @return Vector3 Returns vector position of the object.
     */
    public Vector3 getPosition() {
        return position;
    }

    /**
     * Method to change the hit status of a bullet.
     *
     * @param x boolean Set the hit status to this value.
     */
    public void setHit(boolean x) {
        hit = x;
    }

    /**
     * Method to return the hit status of this object.
     *
     * @return boolean Boolean to tell whether the bullet has hit something.
     */
    public boolean hit() {
        return hit;
    }

    /**
     * Method to return the texture of this object.
     *
     * @return Texture Returns the texture image of this object.
     */
    public Texture getTexture() {return texture;}

    /**
     * Method to adjust the bounds of this object.
     *
     * @param width float Adjusts the width.
     * @param height float Adjusts the height.
     */
    public void setBounds(float width, float height) {
        bounds.setSize(width, height);
    }

    /**
     * Method to return the bounds of this object.
     *
     * @return Rectangle Returns the rectangle bounds of this object.
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * Method to detect collision with another object.
     *
     * @param object Rectangle Takes in bounds of another object.
     *
     * @return boolean Returns whether this object overlaps with another.
     */
    public boolean collides(Rectangle object) {
        return object.overlaps(this.getBounds());
    }

    /**
     * Method to dispose any assets used in this object.
     */
    public void dispose() {
        texture.dispose();
    }
}
