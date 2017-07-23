package com.cs342.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;



public class Boss {

    private static final int MOVEMENT = 100;
    private static final int TEXTURE_WIDTH = 25;
    private static final int TEXTURE_HEIGHT = 20;

    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Texture texture;
    private Animation bossAnimation;
    private Array<BossShot> bossShots;
    private int timer;

    public Boss(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        texture = new Texture("bossSprite.png");
        bossAnimation = new Animation(new TextureRegion(texture), 3 , 0.5f);
        bossShots = new Array<BossShot>();
        timer = 0;

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
            bossShots.add(new BossShot(((int) this.getPosition().x - 20), (int) this.getPosition().y));
            timer = 0;
        }
        for(int i = 0; i < bossShots.size; i++) {
            if(bossShots.get(i).getPosition().y > 900) {
                bossShots.get(i).dispose();
                bossShots.removeIndex(i);

            }

            bossShots.get(i).move(x, y, z);



            bossShots.get(i).update(dt);
        }
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
