package com.cs342.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

/**
 * Class that creates the angel character and its characteristics
 * like its animaion, shooting, and health.
 */

public class Angel {

    private static final int TEXTURE_WIDTH = 25;
    private static final int TEXTURE_HEIGHT = 20;

    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Texture texture;
    private Animation angelAnimation;
    private Array<AngelShot> angelShots;
    private int timer;
    private boolean isDead;
    private int health;

    private float rectWidth;
    private float rectHeight;

    /**
     * Constructor for creating an angel object on at point x,y
     *
     * @param x int Will create an angel object at a specified x position.
     * @param y int Will create an angel object at a specified y position.
     */
    public Angel(float x, float y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        texture = new Texture("AngelSprite.png");
        angelAnimation = new Animation(new TextureRegion(texture), 4 , 0.5f);
        angelShots = new Array<AngelShot>();
        timer = 0;

        bounds = new Rectangle(x, y, 0, 0);
        isDead = false;
        health = 100;

    }

    /**
     * Method to update every frame.
     *
     * @param dt float Number to specify an update of a frame.
     */
    public void update(float dt) {
        angelAnimation.update(dt);
        velocity.scl(dt);
        bounds.setPosition(position.x, position.y);
    }

    /**
     * Method to return a Vector3 position of the object.
     *
     * @return Vector3 Returns position of this object.
     */
    public Vector3 getPosition() {
        return position;
    }

    /**
     * Method to move the object.
     *
     * @param x int Move the object at a specified x position.
     * @param y int Move the object at a specified y position.
     */
    public void move(int x, int y) {
        position.set(x,y,0);

    }

    /**
     * Method to return the texture animation of this object
     *
     * @return TextureRegion Returns the animation object of this object.
     */
    public TextureRegion getTexture() {
        return angelAnimation.getFrame();
    }

    /**
     * Method to control the shooting that this object will do.
     *
     * @param x float Bullets will move along this x variable.
     * @param y float Bullets will move along this y variable.
     * @param z float Bullets will move along this z variable.
     * @param frequency int The delay time that each bullet spawns.
     * @param dt float Shows the update of time.
     */
    public void shoot(float x, float y, float z, int frequency, float dt) {
        timer++;
        if(timer > frequency) {
            angelShots.add(new AngelShot(((int)( this.getPosition().x + this.getBounds().width/2)), (int) (this.getPosition().y + this.getBounds().height/2)));
            timer = 0;
        }
        for(int i = 0; i < angelShots.size; i++) {
            if(angelShots.get(i).getPosition().y < -200) {
                angelShots.get(i).dispose();
                angelShots.removeIndex(i);

            }

            angelShots.get(i).move(x, y, z);

            angelShots.get(i).update(dt);
        }
    }

    /**
     * Returns the bullet array for this object.
     *
     * @return Array<AngelShot> Returns an array of angelShots.
     */
    public Array<AngelShot> getShots() {
        return angelShots;
    }

    /**
     * Method to adjust the bounds that will be used for collision detection.
     *
     * @param width float Changes the bound's width.
     * @param height float Changes the bound's height.
     */
    public void setBounds(float width, float height) {
        bounds.setSize(width, height);
    }

    /**
     * Method to return the bounds of this object
     *
     * @return Rectangle Returns info for the rectangle bounds of this object.
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * Method to set the death status of this object.
     *
     * @param x boolean Boolean to tell whether the object is dead
     */
    public void setStatus(boolean x) {
        isDead = x;
    }

    /**
     * Method to return the death status of this object.
     *
     * @return boolean Returns death status.
     */

    public boolean getStatus() {
        return isDead;
    }

    /**
     * Method to detect collision with another object's bounds.
     *
     * @param object Rectangle Takes in the bounds of another object.
     * @return boolean Returns a boolean whether this bounds overlaps with another object.
     */
    public boolean collides(Rectangle object) {
        return object.overlaps(this.getBounds());
    }

    /**
     * Method to subtract health from this object.
     *
     * @param amount int Amount of health to be subtracted.
     */
    public void loseHealth(int amount) {
        health -= amount;
    }

    /**
     * Method to return this object's health
     * @return int Returns health.
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Method to dispose any assets used by this object.
     */
    public void dispose() {
        texture.dispose();
    }
}
