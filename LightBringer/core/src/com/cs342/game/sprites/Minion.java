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

    public Minion(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        texture = new Texture("minionSprite.png");
        minionAnimation = new Animation(new TextureRegion(texture), 4 , 0.5f);
        minionShots = new Array<MinionShot>();
        timer = 0;

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

    public void shoot(int frequency, float dt, float width, float height) {


        if(timer == 0) {
            minionShots.add(new MinionShot(((int) this.getPosition().x - 20), (int) this.getPosition().y,0,10f,0));
            minionShots.add(new MinionShot(((int) this.getPosition().x - 20), (int) this.getPosition().y,-10f,10f,0));
            minionShots.add(new MinionShot(((int) this.getPosition().x - 20), (int) this.getPosition().y, 10f,10f,0));
            minionShots.add(new MinionShot(((int) this.getPosition().x - 20), (int) this.getPosition().y, 10f,0,0));
            minionShots.add(new MinionShot(((int) this.getPosition().x - 20), (int) this.getPosition().y, -10f,0,0));

            minionShots.add(new MinionShot(((int) this.getPosition().x - 20), (int) this.getPosition().y, -10f,-10f,0));
            minionShots.add(new MinionShot(((int) this.getPosition().x - 20), (int) this.getPosition().y, 0,-10f,0));
            minionShots.add(new MinionShot(((int) this.getPosition().x - 20), (int) this.getPosition().y, 10f,-10f,0));
            timer = 1;
        }
        for(int i = 0; i < minionShots.size; i++) {
            if (minionShots.get(i).getPosition().y > height || minionShots.get(i).getPosition().y < -10 || minionShots.get(i).getPosition().x > width || minionShots.get(i).getPosition().x < -10 ) {
                minionShots.get(i).getPosition().set(this.getPosition().x -20, this.getPosition().y - 20, 0);


            }
        }

        for(int i = 0; i < minionShots.size; i++) {
            minionShots.get(i).move(0, 10, 0);

            minionShots.get(i).update(dt);
        }
    }

    public Array<MinionShot> getShots() {
        return minionShots;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose() {
        texture.dispose();
    }
}
