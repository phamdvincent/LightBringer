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
    private int timer;
    private int distNum;
    private int health;


    public Boss(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        texture = new Texture("bossSprite.png");
        bossAnimation = new Animation(new TextureRegion(texture), 3 , 0.5f);
        bossShots = new Array<BossShot>();
        timer = 0;
        distNum = 0;
        health = 1000;

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

    public void shoot(float x, float y, float z, int frequency, float dt) {
        timer++;
        if(timer > frequency) {
            bossShots.add(new BossShot(((int) this.getPosition().x - 20), (int) this.getPosition().y, 0, 10, 0));
            bossShots.add(new BossShot(((int) this.getPosition().x - 20), (int) this.getPosition().y,-20f,10f,0));
            bossShots.add(new BossShot(((int) this.getPosition().x - 20), (int) this.getPosition().y, 20f,10f,0));
            bossShots.add(new BossShot(((int) this.getPosition().x - 20), (int) this.getPosition().y, 10f,0,0));
            bossShots.add(new BossShot(((int) this.getPosition().x - 20), (int) this.getPosition().y, -10f,0,0));
            timer = 0;
        }
        for(int i = 0; i < bossShots.size; i++) {
            if(bossShots.get(i).getPosition().y > 900) {
                bossShots.get(i).dispose();
                bossShots.removeIndex(i);

            }

            bossShots.get(i).move();



            bossShots.get(i).update(dt);
        }
    }

    public void shoot2(int frequency, float dt, float width, float height) {


        if(timer == 0) {

            bossShots.add(new BossShot(((int) this.getPosition().x - 40), (int) this.getPosition().y,0,10f,0));
            bossShots.add(new BossShot(((int) this.getPosition().x - 40), (int) this.getPosition().y,-10f,10f,0));
            bossShots.add(new BossShot(((int) this.getPosition().x - 40), (int) this.getPosition().y, 10f,10f,0));
            bossShots.add(new BossShot(((int) this.getPosition().x - 40), (int) this.getPosition().y, 10f,0,0));
            bossShots.add(new BossShot(((int) this.getPosition().x - 40), (int) this.getPosition().y, -10f,0,0));
            bossShots.add(new BossShot(((int) this.getPosition().x - 40), (int) this.getPosition().y, -10f,-10f,0));
            bossShots.add(new BossShot(((int) this.getPosition().x - 40), (int) this.getPosition().y, 0,-10f,0));
            bossShots.add(new BossShot(((int) this.getPosition().x - 40), (int) this.getPosition().y, 10f,-10f,0));


            bossShots.add(new BossShot(((int) this.getPosition().x - 40), (int) this.getPosition().y,0,10f,0));
            bossShots.add(new BossShot(((int) this.getPosition().x - 40), (int) this.getPosition().y,-10f,10f,0));
            bossShots.add(new BossShot(((int) this.getPosition().x - 40), (int) this.getPosition().y, 10f,10f,0));
            bossShots.add(new BossShot(((int) this.getPosition().x - 40), (int) this.getPosition().y, 10f,0,0));
            bossShots.add(new BossShot(((int) this.getPosition().x - 40), (int) this.getPosition().y, -10f,0,0));
            bossShots.add(new BossShot(((int) this.getPosition().x - 40), (int) this.getPosition().y, -10f,-10f,0));
            bossShots.add(new BossShot(((int) this.getPosition().x - 40), (int) this.getPosition().y, 0,-10f,0));
            bossShots.add(new BossShot(((int) this.getPosition().x - 40), (int) this.getPosition().y, 10f,-10f,0));
            timer = 1;

        }
        if(distNum > 100)   {
        for(int i = 0; i < bossShots.size/2; i++) {

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

    public Array<BossShot> getShots() {
        return bossShots;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose() {
        texture.dispose();
    }
}
