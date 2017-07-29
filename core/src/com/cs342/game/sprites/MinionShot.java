package com.cs342.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;


public class MinionShot {

    private static final int TEXTURE_WIDTH = 10;
    private static final int TEXTURE_HEIGHT = 10;

    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Texture texture;
    private float pathX;
    private float pathY;
    private float pathZ;
    private boolean hit;

    public MinionShot(int x, int y, float pathX, float pathY, float pathZ) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(pathX, pathY, pathZ);
        this.pathX = pathX;
        this.pathY = pathY;
        this.pathZ = pathZ;
        texture = new Texture("fire.png");

        bounds = new Rectangle(x, y, TEXTURE_WIDTH, TEXTURE_HEIGHT);

    }

    public void update(float dt) {
        velocity.scl(dt);
        bounds.setPosition(position.x, position.y);
    }

    public void move() {
        position.add(pathX, pathY, pathZ);

    }

    public Vector3 getPosition() {
        return position;
    }

    public void setHit(boolean x) {
        hit = x;
    }

    public boolean hit() {
        return hit;
    }

    public Texture getTexture() {return texture;}

    public void setBounds(float width, float height) {
        bounds.setSize(width, height);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public boolean collides(Rectangle object) {
        return object.overlaps(this.getBounds());
    }

    public void dispose() {
        texture.dispose();
    }
}
