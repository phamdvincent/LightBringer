package com.cs342.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;


/**
 * Class to create minions and its characteristics.
 */
public class Minion {

    private static final int TEXTURE_WIDTH = 25;
    private static final int TEXTURE_HEIGHT = 20;

    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Texture texture;
    private Animation minionAnimation;
    private Array<MinionShot> minionShots;
    private int timer;
    private int distNum;
    private boolean isDead;
    private int health;
    private Sound explosion;

    /**
     * Constructor to create a minion object.
     *
     * @param x float Create object at point x.
     * @param y float Create object at point y.
     */
    public Minion(float x, float y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        texture = new Texture("minionSprite.png");
        minionAnimation = new Animation(new TextureRegion(texture), 4 , 0.5f);
        minionShots = new Array<MinionShot>();
        timer = 0;
        distNum = 0;
        isDead = false;
        health = 50;
        explosion = Gdx.audio.newSound(Gdx.files.internal("explosion.mp3"));
        bounds = new Rectangle(x, y, TEXTURE_WIDTH, TEXTURE_HEIGHT);

    }

    /**
     * Method to update every frame.
     *
     * @param dt float Number to indicate update of frame.
     */
    public void update(float dt) {
        minionAnimation.update(dt);
        velocity.scl(dt);
        bounds.setPosition(position.x, position.y);
    }

    /**
     * Method to return the position of this object.
     *
     * @return Vector3 Return Vector position of this object.
     */
    public Vector3 getPosition() {
        return position;
    }

    /**
     * Method to return the texture animation of this object.
     *
     * @return TetureRegion Returns the animation texture of this object.
     */
    public TextureRegion getTexture() {
        return minionAnimation.getFrame();
    }

    /**
     * Method to control the shooting of this object.
     *
     * @param x float Moves along x axis.
     * @param y float Moves along y axis.
     * @param dt float Moves along z axis.
     */
    public void shoot(float x, float y, float dt) {


        if(timer == 0) {
            explosion.play(0.4f);
            minionShots.add(new MinionShot(((int) this.getPosition().x - 40), (int) this.getPosition().y, 0, y,0));
            minionShots.add(new MinionShot(((int) this.getPosition().x - 40), (int) this.getPosition().y,-x, y,0));
            minionShots.add(new MinionShot(((int) this.getPosition().x - 40), (int) this.getPosition().y, x, y,0));
            minionShots.add(new MinionShot(((int) this.getPosition().x - 40), (int) this.getPosition().y, x,0,0));
            minionShots.add(new MinionShot(((int) this.getPosition().x - 40), (int) this.getPosition().y, -x,0,0));
            minionShots.add(new MinionShot(((int) this.getPosition().x - 40), (int) this.getPosition().y, -x,-y,0));
            minionShots.add(new MinionShot(((int) this.getPosition().x - 40), (int) this.getPosition().y, 0,-y,0));
            minionShots.add(new MinionShot(((int) this.getPosition().x - 40), (int) this.getPosition().y, x,-y,0));



            timer = 1;

        }
        if(distNum > 100)   {
            explosion.play(0.4f);
            for(int i = 0; i < minionShots.size; i++) {
                minionShots.get(i).setHit(false);
                minionShots.get(i).getPosition().set(this.getPosition().x -40, this.getPosition().y - 40, 0);
                distNum = 0;
            }
        }

        for(int i = 0; i < minionShots.size; i++) {
            minionShots.get(i).move();

            minionShots.get(i).update(dt);
        }

        distNum++;
    }

    /**
     * Method to return the array of Minion bullets.
     *
     * @return Array<MinionShot> Returns array of minionShots.
     */
    public Array<MinionShot> getShots() {
        return minionShots;
    }

    /**
     * Method to adjust the bounds of this object.
     *
     * @param width float Change the width of the bounds.
     * @param height float Change the height of the bounds.
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
     * Method to adjust the death status of this object.
     *
     * @param x boolean Boolean to change death status.
     */
    public void setStatus(boolean x) {
        isDead = x;
    }

    /**
     * Method to return the death status of this object.
     *
     * @return boolean Returns the death status of this object.
     */
    public boolean getStatus() {
        return isDead;
    }

    /**
     * Method to detect collision with another object.
     *
     * @param object Rectangle Takes in the bounds of another object.
     * @return boolean
     */
    public boolean collides(Rectangle object) {
        return object.overlaps(this.getBounds());
    }

    /**
     * Method to subtract an amount of health.
     *
     * @param amount int Amount of health to subtract.
     */
    public void loseHealth(int amount) {
        health -= amount;
    }

    /**
     * Method to return the amount of health.
     *
     * @return int Returns the current amount of health.
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Method to dispose assets used in this objects.
     */
    public void dispose() {
        explosion.dispose();
        texture.dispose();
    }
}
