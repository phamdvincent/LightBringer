package com.cs342.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;



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

    public void update(float dt) {
        minionAnimation.update(dt);
        velocity.scl(dt);
        bounds.setPosition(position.x, position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public void move(int x, int y) {
        position.set(x,y,0);
        System.out.println("x:" + x + " y:" + y);
    }

    public TextureRegion getTexture() {
        return minionAnimation.getFrame();
    }

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

    public Array<MinionShot> getShots() {
        return minionShots;
    }

    public void setBounds(float width, float height) {
        bounds.setSize(width, height);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setStatus(boolean x) {
        isDead = x;
    }

    public boolean getStatus() {
        return isDead;
    }

    public boolean collides(Rectangle object) {
        return object.overlaps(this.getBounds());
    }

    public void loseHealth(int amount) {
        health -= amount;
    }

    public int getHealth() {
        return this.health;
    }

    public void dispose() {
        explosion.dispose();
        texture.dispose();
    }
}
