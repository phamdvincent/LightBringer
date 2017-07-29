package com.cs342.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.Input.Keys;
import com.cs342.game.LightBringer;
import com.cs342.game.sprites.Angel;
import com.cs342.game.sprites.AngelShot;
import com.cs342.game.sprites.Background;
import com.cs342.game.sprites.Boss;
import com.cs342.game.sprites.BossShot;
import com.cs342.game.sprites.Minion;
import com.cs342.game.sprites.MinionShot;

import java.lang.Math;

import static com.badlogic.gdx.Input.Keys.X;


class Play extends State{

    private static final double PI = 3.14;

    private Angel angel;
    private Boss boss;
    private Background background;
    private  Background background2;
    private Texture bg;
    private boolean bossMove;
    private Minion minion;
    private boolean minionMoveDown;
    private boolean minionMoveLeft;

    Play(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(true);
        angel = new Angel(cam.viewportWidth, cam.viewportHeight);
        boss = new Boss(50, 250);
        bg = new Texture("background.jpg");
        background = new Background(bg, 0, 0, 0);
        background2 = new Background(bg,0, -cam.viewportHeight, 0);
        bossMove = false;
        minion = new Minion(200, 40);
        minionMoveLeft = true;
        minionMoveDown = true;
        angel.setBounds(cam.viewportWidth / 10, cam.viewportHeight/13);
        boss.setBounds(cam.viewportWidth / 10, cam.viewportHeight/13);
        minion.setBounds(cam.viewportWidth / 10, cam.viewportHeight/13);

    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isTouched())
        {
            angel.move(Gdx.input.getX(),Gdx.input.getY());
        }

        if(Gdx.input.isKeyJustPressed(X)) {
            angel.setStatus(true);
            boss.setStatus(true);
            minion.setStatus(true);
        }
            //angel = null;

    }

    @Override
    public void update(float dt) {
        handleInput();
        background.move(0, 5f, 0);
        if(background.getPosition().y >= cam.viewportHeight) {
            background.getPosition().set(0, -cam.viewportHeight, 0);
        }
        background2.move(0, 5f, 0);
        if(background2.getPosition().y >= cam.viewportHeight) {
            background2.getPosition().set(0, -cam.viewportHeight, 0);
        }

        if(angel.getStatus() == false) {
            angel.update(dt);
            angel.shoot(0, -30f, 0, 5, dt);
        }

        if(boss.getStatus() == false) {
            moveMyBoss();
            boss.update(dt);
            boss.shoot(2,2,dt);
        }


        if(minion.getStatus() == false) {
            moveMyMinion();
            minion.update(dt);
            minion.shoot(2,2,dt);
        }

        System.out.println("Health: " + angel.getHealth());

    }

    public void moveMyBoss(){
        if(bossMove == false) {
            if(boss.getPosition().x > cam.viewportWidth - 50) {
                bossMove = true;
            }
            boss.getPosition().add(5f, 0, 0);
        }
        else if(bossMove == true) {
            if(boss.getPosition().x <50)
                bossMove = false;
            boss.getPosition().add(-5f, 0, 0);
        }
    }

    public void moveMyMinion(){
        if(minionMoveDown == true && minionMoveLeft == true) {
            if(minion.getPosition().x < 40) {
                minionMoveLeft = false;
            }
            if(minion.getPosition().y >= cam.viewportHeight - 20) {
                minionMoveDown = false;
            }
            minion.getPosition().add(-5, 5, 0);
        }
        if(minionMoveDown == true && minionMoveLeft == false) {
            if(minion.getPosition().x >= cam.viewportWidth - 20) {
                minionMoveLeft = true;
            }
            if(minion.getPosition().y >= cam.viewportHeight - 20) {
                minionMoveDown = false;
            }
            minion.getPosition().add(5, 5, 0);
        }
        if(minionMoveDown == false && minionMoveLeft == true){
            if(minion.getPosition().x < 40) {
                minionMoveLeft = false;
            }
            if(minion.getPosition().y <= 40) {
                minionMoveDown = true;
            }
            minion.getPosition().add(-5, -5, 0);
        }
        if(minionMoveDown == false && minionMoveLeft == false){
            if(minion.getPosition().x >= cam.viewportWidth - 20) {
                minionMoveLeft = true;
            }
            if(minion.getPosition().y <= 40) {
                minionMoveDown = true;
            }
            minion.getPosition().add(5, -5, 0);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background.getTexture(),background.getPosition().x,background.getPosition().y, cam.viewportWidth, cam.viewportHeight + 10);
        sb.draw(background2.getTexture(),background2.getPosition().x,background2.getPosition().y, cam.viewportWidth, cam.viewportHeight + 10);

        if(angel.getStatus() == false) {
            sb.draw(angel.getTexture(), angel.getPosition().x, angel.getPosition().y, 50.0f, 40.0f, cam.viewportWidth / 10, cam.viewportHeight/13,1.0f, 1.0f, 180.0f);
            for(AngelShot s : angel.getShots() ) {
                s.setBounds(cam.viewportWidth / 40, cam.viewportHeight / 40);
                sb.draw(s.getTexture(), s.getPosition().x, s.getPosition().y, 10.0f, 10.0f, cam.viewportWidth / 40, cam.viewportHeight / 40, 1.0f, 1.0f, 180.0f);
            }
        }

        if(boss.getStatus() == false) {
            sb.draw(boss.getTexture(), boss.getPosition().x, boss.getPosition().y, 10.0f, 10.0f, cam.viewportWidth / 10, cam.viewportHeight / 13, 1.0f, 1.0f, 180.0f);
            for(BossShot s : boss.getShots() ) {
                if(s.collides(angel.getBounds()) && s.hit() == false){
                    s.setHit(true);
                    angel.loseHealth(1);
                }
                else if(s.hit() == false){
                    s.setBounds(cam.viewportWidth / 20, cam.viewportHeight / 20);
                    sb.draw(s.getTexture(), s.getPosition().x, s.getPosition().y, cam.viewportWidth / 20, cam.viewportHeight / 20);
                }
            }
        }

        if(minion.getStatus() == false) {
            sb.draw(minion.getTexture(), minion.getPosition().x, minion.getPosition().y, 10.0f, 10.0f, cam.viewportWidth / 10, cam.viewportHeight / 13, 1.0f, 1.0f, 180.0f);
            for(MinionShot s : minion.getShots() ) {
                if(s.collides(angel.getBounds()) && s.hit() == false){
                    s.setHit(true);
                    angel.loseHealth(1);
                }
                else if(s.hit() == false){
                    s.setBounds(cam.viewportWidth / 20, cam.viewportHeight / 20);
                    sb.draw(s.getTexture(), s.getPosition().x, s.getPosition().y, cam.viewportWidth / 20, cam.viewportHeight / 20);
                }
            }
        }


        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        background2.dispose();
        angel.dispose();

        boss.dispose();
        System.out.println("Play disposed");
    }
}
