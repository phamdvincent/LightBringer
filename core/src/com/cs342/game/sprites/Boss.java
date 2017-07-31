package com.cs342.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import java.lang.Math;

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

    public void update(float dt) {
        bossAnimation.update(dt);
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
        return bossAnimation.getFrame();
    }


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


//            bossShots.add(new BossShot(((int) this.getPosition().x - 40), (int) this.getPosition().y,0,10f,0));
//            bossShots.add(new BossShot(((int) this.getPosition().x - 40), (int) this.getPosition().y,-10f,10f,0));
//            bossShots.add(new BossShot(((int) this.getPosition().x - 40), (int) this.getPosition().y, 10f,10f,0));
//            bossShots.add(new BossShot(((int) this.getPosition().x - 40), (int) this.getPosition().y, 10f,0,0));
//            bossShots.add(new BossShot(((int) this.getPosition().x - 40), (int) this.getPosition().y, -10f,0,0));
//            bossShots.add(new BossShot(((int) this.getPosition().x - 40), (int) this.getPosition().y, -10f,-10f,0));
//            bossShots.add(new BossShot(((int) this.getPosition().x - 40), (int) this.getPosition().y, 0,-10f,0));
//            bossShots.add(new BossShot(((int) this.getPosition().x - 40), (int) this.getPosition().y, 10f,-10f,0));
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

    public void straightShoot(float x, float y, float dt) {
        if(timer2 == 0) {
            straightShots.add(new BossShot(((int) this.getPosition().x - 40), (int) this.getPosition().y, x, y,0));
//            straightShots.add(new BossShot(((int) this.getPosition().x - 40), (int) this.getPosition().y, x, y,0));
//            straightShots.add(new BossShot(((int) this.getPosition().x - 40), (int) this.getPosition().y, x, y,0));
//            straightShots.add(new BossShot(((int) this.getPosition().x - 40), (int) this.getPosition().y, x, y,0));
//            straightShots.add(new BossShot(((int) this.getPosition().x - 40), (int) this.getPosition().y, x, y,0));
//            straightShots.add(new BossShot(((int) this.getPosition().x - 40), (int) this.getPosition().y, x, y,0));
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

    public Array<BossShot> getShots() {
        return bossShots;
    }

    public Array<BossShot> getStraightShots() {
        return straightShots;
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
