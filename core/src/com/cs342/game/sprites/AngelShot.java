package com.cs342.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Vincent on 7/17/17.
 */

public class AngelShot {

    private static final int TEXTURE_WIDTH = 10;
    private static final int TEXTURE_HEIGHT = 10;

    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Texture texture;
    private Animation angelShotAnimation;

    public AngelShot(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        texture = new Texture("angelShot.png");
        angelShotAnimation = new Animation(new TextureRegion(texture), 4 , 0.5f);

        bounds = new Rectangle(x, y, TEXTURE_WIDTH, TEXTURE_HEIGHT);

    }

    public void update(float dt) {
        angelShotAnimation.update(dt);
        velocity.scl(dt);
        bounds.setPosition(position.x, position.y);
    }

    public void move(float x, float y, float z) {
        position.add(x,y,z);

    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return angelShotAnimation.getFrame();
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose() {
        texture.dispose();
    }
}
