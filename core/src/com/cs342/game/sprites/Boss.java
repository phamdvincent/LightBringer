package com.cs342.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import java.lang.Math;

/**
 * Class to create the boss character.
 */
public class Boss {

    private static final int TEXTURE_WIDTH = 25;
    private static final int TEXTURE_HEIGHT = 20;

    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Texture texture;
    private Animation bossAnimation;
    private Array<BossShot> bossShots;
    private Array<BossShot> straightShots;
    private int timer;
    private int timer2;
    private int distNum;
    private int distNum2;
    private int health;
    private boolean isDead;
    private Sound explosion;


    /**
     * Constructor for creating the boss at location x,y
     * @param x float Spawn boss at point x.
     * @param y float Spawn boss at point y.
     */
    public Boss(float x, float y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        texture = new Texture("bossSprite.png");
        bossAnimation = new Animation(new TextureRegion(texture), 3 , 0.5f);
        bossShots = new Array<BossShot>();
        straightShots = new Array<BossShot>();
        timer = 0;
        timer2 = 0;
        distNum = 0;
        distNum2 = 0;
        health = 500;
        isDead = false;
        explosion = Gdx.audio.newSound(Gdx.files.internal("explosion.mp3"));
        bounds = new Rectangle(x, y, TEXTURE_WIDTH, TEXTURE_HEIGHT);
    }

    /**
     * Method to update the frames.
     *
     * @param dt float Takes in the number after updating frames.
     */
    public void update(float dt) {
        bossAnimation.update(dt);
        velocity.scl(dt);
        bounds.setPosition(position.x, position.y);
    }

    /**
     * Method to return the position of this object.
     *
     * @return Vector3 Returns the position of this object.
     */
    public Vector3 getPosition() {
        return position;
    }

    public void move(int x, int y) {
        position.set(x,y,0);
        System.out.println("x:" + x + " y:" + y);
    }

    /**
     * Method to return the texture animation of this object.
     *
     * @return TextureRegion Returns a frame of the animation.
     */
    public TextureRegion getTexture() {
        return bossAnimation.getFrame();
    }


    /**
     * Method to control the shooting from this object.
     *
     * @param x float Bullets move along x variable.
     * @param y float Bullets move along y variable.
     * @param dt float Updates the frames.
     */
    public void shoot(float x, float y, float dt) {


        if(timer == 0) {
            explosion.play(0.4f);
            bossShots.add(new BossShot(((int) this.getPosition().x - 40), (int) this.getPosition().y,0, y,0));
            bossShots.add(new BossShot(((int) this.getPosition().x - 40), (int) this.getPosition().y,-x, y,0));
            bossShots.add(new BossShot(((int) this.getPosition().x - 40), (int) this.getPosition().y, x, y,0));
            bossShots.add(new BossShot(((int) this.getPosition().x - 40), (int) this.getPosition().y, x,0,0));
            bossShots.add(new BossShot(((int) this.getPosition().x - 40), (int) this.getPosition().y, -x,0,0));
            bossShots.add(new BossShot(((int) this.getPosition().x - 40), (int) this.getPosition().y, -x,-y,0));
            bossShots.add(new BossShot(((int) this.getPosition().x - 40), (int) this.getPosition().y, 0,-y,0));
            bossShots.add(new BossShot(((int) this.getPosition().x - 40), (int) this.getPosition().y, x,-y,0));
            timer = 1;

        }
        if(distNum > 100)   {
            explosion.play(0.4f);
        for(int i = 0; i < bossShots.size; i++) {
                bossShots.get(i).setHit(false);
                bossShots.get(i).getPosition().set(this.getPosition().x -40, this.getPosition().y - 40, 0);
                distNum = 0;
            }
        }

        for(int i = 0; i < bossShots.size; i++) {
            bossShots.get(i).move();

            bossShots.get(i).update(dt);
        }

        distNum++;
    }

    /**
     * Method to control a secondary shooting pattern.
     * @param x float Bullet moves along x variable.
     * @param y float Bullet moves along y variable.
     * @param dt float Updates the frames.
     */
    public void straightShoot(float x, float y, float dt) {
        if(timer2 == 0) {
            straightShots.add(new BossShot(((int) this.getPosition().x - 40), (int) this.getPosition().y, x, y,0));
            timer2 = 1;

        }

            for(int i = 0; i < straightShots.size; i++) {
                if(distNum2 > 20) {
                    straightShots.get(i).setHit(false);
                    straightShots.get(i).getPosition().set(this.getPosition().x - 40, this.getPosition().y - 40, 0);
                    distNum2 = 0;
                }
            }


        for(int i = 0; i < straightShots.size; i++) {
            straightShots.get(i).move();
            straightShots.get(i).update(dt);
        }

        distNum2++;
    }

    /**
     * Method to return the shot array of this object.
     *
     * @return Array<BossShot> Returns bossShot array.
     */
    public Array<BossShot> getShots() {
        return bossShots;
    }

    /**
     * Method to return the array of secondary shots.
     *
     * @return Array<BossShot> Returns the array of secondary shots.
     */
    public Array<BossShot> getStraightShots() {
        return straightShots;
    }

    /**
     * Method to adjust the bounds for this object.
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
     * Method to set the death status of this object.
     *
     * @param x boolean Sets the death status.
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
     * @return boolean Returns whether this object overlaps with another.
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
     * Method to return the amount of health.
     *
     * @return int Returns the current amount of health.
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Method to dispose the assets used in this object.
     */
    public void dispose() {
        explosion.dispose();
        texture.dispose();
    }
}
