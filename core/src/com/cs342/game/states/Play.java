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

    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isTouched())
        {
            angel.move(Gdx.input.getX(),Gdx.input.getY());
        }

        if(Gdx.input.isKeyJustPressed(X))
            angel = null;
    }

    @Override
    public void update(float dt) {
        handleInput();
        background.move(0, 20f, 0);
        if(background.getPosition().y >= cam.viewportHeight) {
            background.getPosition().set(0, -cam.viewportHeight, 0);
        }
        background2.move(0, 20f, 0);
        if(background2.getPosition().y >= cam.viewportHeight) {
            background2.getPosition().set(0, -cam.viewportHeight, 0);
        }
        angel.update(dt);

        moveMyBoss();

        moveMyMinion();

        minion.update(dt);
        boss.update(dt);
        angel.shoot(0, -20f, 0, 15, dt);
        minion.shoot(70, dt, cam.viewportWidth,cam.viewportHeight);

        //boss.shoot(0, 10f, 0, 20, dt);
        boss.shoot(70, dt, cam.viewportWidth,cam.viewportHeight);

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
        sb.draw(angel.getTexture(), angel.getPosition().x, angel.getPosition().y, 10.0f, 10.0f, cam.viewportWidth / 20, cam.viewportHeight/25,3.0f, 3.0f, 180.0f);
        sb.draw(minion.getTexture(), minion.getPosition().x, minion.getPosition().y, 10.0f, 10.0f, cam.viewportWidth / 20, cam.viewportHeight / 25, 3.0f, 3.0f, 180.0f);
//        sb.draw(boss.getTexture(), boss.getPosition().x, boss.getPosition().y, 10.0f, 10.0f, cam.viewportWidth / 20, cam.viewportHeight / 25, 3.0f, 3.0f, 180.0f);

        for(AngelShot s : angel.getShots() ) {
            sb.draw(s.getTexture(), s.getPosition().x, s.getPosition().y, 10.0f, 10.0f, cam.viewportWidth / 40, cam.viewportHeight / 40, 3.0f, 3.0f, 180.0f);
        }
//        for(BossShot s : boss.getShots() ) {
//            sb.draw(s.getTexture(), s.getPosition().x, s.getPosition().y, cam.viewportWidth / 20, cam.viewportHeight / 20);
//        }
        for(MinionShot s : minion.getShots() ) {
            sb.draw(s.getTexture(), s.getPosition().x, s.getPosition().y, cam.viewportWidth / 20, cam.viewportHeight / 20);
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
