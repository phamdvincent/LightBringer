package com.cs342.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;


public class AngelShot {

    private static final int TEXTURE_WIDTH = 10;
    private static final int TEXTURE_HEIGHT = 10;

    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Texture texture;
    private Animation angelShotAnimation;
    private boolean hit;

    public AngelShot(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        texture = new Texture("angelShot.png");
        angelShotAnimation = new Animation(new TextureRegion(texture), 4 , 0.5f);
        hit = false;

        bounds = new Rectangle(x, y, 0, 0);

    }

    public void update(float dt) {
        angelShotAnimation.update(dt);
        velocity.scl(dt);
        bounds.setPosition(position.x, position.y);
    }

    public void move(float x, float y, float z) {
        position.add(x,y,z);

    }

    public void setHit(boolean x) {
        hit = x;
    }

    public boolean hit() {
        return hit;
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

    public void setBounds(float width, float height) {
        bounds.setSize(width, height);
    }

    public boolean collides(Rectangle object) {
        return object.overlaps(this.getBounds());
    }

    public void dispose() {
        texture.dispose();
    }
}
