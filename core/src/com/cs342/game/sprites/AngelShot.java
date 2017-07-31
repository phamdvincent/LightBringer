package com.cs342.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Class to create the bullets that will be used by the angel.
 */
public class AngelShot {

    private static final int TEXTURE_WIDTH = 10;
    private static final int TEXTURE_HEIGHT = 10;

    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Texture texture;
    private Animation angelShotAnimation;
    private boolean hit;

    /**
     * Constructor for creating a bullet at location x,y.
     *
     * @param x int Create at specified x location.
     * @param y int Create at specified y location.
     */
    public AngelShot(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        texture = new Texture("angelShot.png");
        angelShotAnimation = new Animation(new TextureRegion(texture), 4 , 0.5f);
        hit = false;

        bounds = new Rectangle(x, y, 0, 0);

    }

    /**
     * Method to update every frame.
     *
     * @param dt float Number to indicate an update in frames.
     */
    public void update(float dt) {
        angelShotAnimation.update(dt);
        velocity.scl(dt);
        bounds.setPosition(position.x, position.y);
    }

    /**
     * Method to move the bullet's location.
     *
     * @param x float Move along x variable.
     * @param y float Move along y variable.
     * @param z float Move along z variable.
     */
    public void move(float x, float y, float z) {
        position.add(x,y,z);

    }

    /**
     * Method to set the status of this bullet hitting an object.
     *
     * @param x boolean Set the status of the bullet hitting something.
     */
    public void setHit(boolean x) {
        hit = x;
    }

    /**
     * Method to return whether this bullet hit something.
     *
     * @return boolean Returns whether this bullet has already hit
     */
    public boolean hit() {
        return hit;
    }

    /**
     * Method to get this object's position.
     *
     * @return Vector3 Returns position of this object.
     */
    public Vector3 getPosition() {
        return position;
    }

    /**
     * Method to return the animation texture of this object.
     *
     * @return TextureRegion Returns texture animation of the bullet object.
     */
    public TextureRegion getTexture() {
        return angelShotAnimation.getFrame();
    }

    /**
     * Method to return the bounds for this object.
     *
     * @return Rectangle Returns the rectangle bounds of this object.
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * Method to adjust the width and height of the bounds.
     *
     * @param width float Adjust the width of the bounds.
     * @param height float Adjust the height of the bounds.
     */
    public void setBounds(float width, float height) {
        bounds.setSize(width, height);
    }

    /**
     * Methof to detect collision with other objects.
     *
     * @param object Rectangle Takes in the bounds of an object.
     *
     * @return boolean Returns whether this object is overlapping with another.
     */
    public boolean collides(Rectangle object) {
        return object.overlaps(this.getBounds());
    }

    /**
     * Method to dispose of assets used in this object.
     */
    public void dispose() {
        texture.dispose();
    }
}
