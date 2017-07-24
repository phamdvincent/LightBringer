package com.cs342.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;



public class Angel {

    private static final int MOVEMENT = 100;
    private static final int TEXTURE_WIDTH = 25;
    private static final int TEXTURE_HEIGHT = 20;

    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Texture texture;
    private Animation angelAnimation;
    private Array<AngelShot> angelShots;
    private int timer;

    public Angel(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        texture = new Texture("AngelSprite.png");
        angelAnimation = new Animation(new TextureRegion(texture), 4 , 0.5f);
        angelShots = new Array<AngelShot>();
        timer = 0;
        bounds = new Rectangle(x, y, TEXTURE_WIDTH, TEXTURE_HEIGHT);

    }

    public void update(float dt) {
        angelAnimation.update(dt);
        velocity.scl(dt);
        bounds.setPosition(position.x, position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public void move(int x, int y) {
        position.set(x,y,0);

    }

    public TextureRegion getTexture() {
        return angelAnimation.getFrame();
    }

    public void shoot(float x, float y, float z, int frequency, float dt) {
        timer++;
        if(timer > frequency) {
            angelShots.add(new AngelShot(((int) this.getPosition().x - 20), (int) this.getPosition().y));
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

    public Array<AngelShot> getShots() {
        return angelShots;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose() {
        texture.dispose();
    }
}
