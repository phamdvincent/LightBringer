package com.cs342.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Class to control the boss's bullets
 */
public class BossShot {

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
     * Constructor to create the boss's bullets.
     *
     * @param x int Create at point x.
     * @param y int Create at point y.
     * @param pathX float Move along x axis.
     * @param pathY float Move along y axis.
     * @param pathZ float Move along z axis.
     */
    public BossShot(int x, int y, float pathX, float pathY, float pathZ) {
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
     * @param dt float Takes in the number to indicate update in frames.
     */
    public void update(float dt) {
        velocity.scl(dt);
        bounds.setPosition(position.x, position.y);
    }

    /**
     * Method to control the movement of this object.
     */
    public void move() {
        position.add(pathX, pathY, pathZ);

    }

    /**
     * Method to return the position of this object.
     *
     * @return Vector3 Returns the Vector position of the object.
     */
    public Vector3 getPosition() {
        return position;
    }

    /**
     * Method to change the hit status of each bullet.
     *
     * @param x boolean Value of the status of the bullet.
     */
    public void setHit(boolean x) {
        hit = x;
    }

    /**
     * Method to return the hit status of the bullet.
     *
     * @return boolean Returns whether this bullet has hit yet.
     */
    public boolean hit() {
        return hit;
    }

    /**
     * Method to return the texture of this object.
     *
     * @return Texture Returns the image texture of this object.
     */
    public Texture getTexture() {return texture;}

    /**
     * Method to adjust the bounds of each instance of this object.
     *
     * @param width float Change the width of the bounds.
     * @param height float Change the height of the bounds.
     */
    public void setBounds(float width, float height) {
        bounds.setSize(width, height);
    }

    /**
     * Method to detect collision with another object.
     *
     * @param object Rectangle Takes in the bounds of another object.
     * @return boolean Returns whether this objects overlaps with another.
     */
    public boolean collides(Rectangle object) {
        return object.overlaps(this.getBounds());
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
     * Method to dispose the assets used in this object.
     */
    public void dispose() {
        texture.dispose();
    }
}
