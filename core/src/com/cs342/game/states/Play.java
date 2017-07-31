package com.cs342.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
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
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;

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
    private Minion minion2;
    private Minion minion3;
    private Minion minion4;
    private boolean minionMoveDown;
    private boolean minionMoveLeft;
    private boolean minionMoveDown2;
    private boolean minionMoveLeft2;
    private boolean minionMoveDown3;
    private boolean minionMoveLeft3;
    private boolean minionMoveDown4;
    private boolean minionMoveLeft4;
    private BitmapFont font;
    private BitmapFont font2;
    private Sound lightImpact;
    private Texture dmg;

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
        minion2 = new Minion(300, 200);
        minionMoveLeft2 = true;
        minionMoveDown2 = true;
        minion3 = new Minion(800, 600);
        minionMoveLeft3 = true;
        minionMoveDown3 = true;
        minion4 = new Minion(100, 700);
        minionMoveLeft4 = true;
        minionMoveDown4 = true;
        angel.setBounds(cam.viewportWidth / 10, cam.viewportHeight/13);
        boss.setBounds(cam.viewportWidth / 10, cam.viewportHeight/13);
        minion.setBounds(cam.viewportWidth / 10, cam.viewportHeight/13);
        lightImpact = Gdx.audio.newSound(Gdx.files.internal("slash.mp3"));
        dmg = new Texture("light.png");

        font = new BitmapFont(true);
        font.setColor(Color.GREEN);
        font.getData().scale(5);

        font2 = new BitmapFont(true);
        font2.setColor(Color.RED);
        font2.getData().scale(5);

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

    }

    @Override
    public void update(float dt) {
        handleInput();
        if(angel.getHealth() < 1)
            gsm.set(new LoseScreen(gsm));

        if(boss.getHealth() < 1)
            gsm.set(new WinScreen(gsm));

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
            boss.shoot(20,20,dt);
            boss.straightShoot(0,25,dt);
        }


        if(minion.getStatus() == false) {
            moveMyMinion();
            minion.update(dt);
            minion.shoot(15,15,dt);
        }

        if(minion2.getStatus() == false && boss.getHealth() < 400) {
            moveMyMinion2();
            minion2.update(dt);
            minion2.shoot(15,15,dt);
        }

        if(minion3.getStatus() == false && boss.getHealth() < 300) {
            moveMyMinion3();
            minion3.update(dt);
            minion3.shoot(15,15,dt);
        }

        if(minion3.getStatus() == false && boss.getHealth() < 200) {
            moveMyMinion4();
            minion4.update(dt);
            minion4.shoot(15,15,dt);
        }

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




    public void moveMyMinion2(){
        if(minionMoveDown2 == true && minionMoveLeft2 == true) {
            if(minion2.getPosition().x < 40) {
                minionMoveLeft2 = false;
            }
            if(minion2.getPosition().y >= cam.viewportHeight - 20) {
                minionMoveDown2 = false;
            }
            minion2.getPosition().add(-5, 5, 0);
        }
        if(minionMoveDown2 == true && minionMoveLeft2 == false) {
            if(minion2.getPosition().x >= cam.viewportWidth - 20) {
                minionMoveLeft2 = true;
            }
            if(minion2.getPosition().y >= cam.viewportHeight - 20) {
                minionMoveDown2 = false;
            }
            minion2.getPosition().add(5, 5, 0);
        }
        if(minionMoveDown2 == false && minionMoveLeft2 == true){
            if(minion2.getPosition().x < 40) {
                minionMoveLeft2 = false;
            }
            if(minion2.getPosition().y <= 40) {
                minionMoveDown2 = true;
            }
            minion2.getPosition().add(-5, -5, 0);
        }
        if(minionMoveDown2 == false && minionMoveLeft2 == false){
            if(minion2.getPosition().x >= cam.viewportWidth - 20) {
                minionMoveLeft2 = true;
            }
            if(minion2.getPosition().y <= 40) {
                minionMoveDown2 = true;
            }
            minion2.getPosition().add(5, -5, 0);
        }
    }

    public void moveMyMinion3(){
        if(minionMoveDown3 == true && minionMoveLeft3 == true) {
            if(minion3.getPosition().x < 40) {
                minionMoveLeft3 = false;
            }
            if(minion3.getPosition().y >= cam.viewportHeight - 20) {
                minionMoveDown3 = false;
            }
            minion3.getPosition().add(-5, 5, 0);
        }
        if(minionMoveDown3 == true && minionMoveLeft3 == false) {
            if(minion3.getPosition().x >= cam.viewportWidth - 20) {
                minionMoveLeft3 = true;
            }
            if(minion3.getPosition().y >= cam.viewportHeight - 20) {
                minionMoveDown3 = false;
            }
            minion3.getPosition().add(5, 5, 0);
        }
        if(minionMoveDown3 == false && minionMoveLeft3 == true){
            if(minion3.getPosition().x < 40) {
                minionMoveLeft3 = false;
            }
            if(minion3.getPosition().y <= 40) {
                minionMoveDown3 = true;
            }
            minion3.getPosition().add(-5, -5, 0);
        }
        if(minionMoveDown3 == false && minionMoveLeft3 == false){
            if(minion3.getPosition().x >= cam.viewportWidth - 20) {
                minionMoveLeft3 = true;
            }
            if(minion3.getPosition().y <= 40) {
                minionMoveDown3 = true;
            }
            minion3.getPosition().add(5, -5, 0);
        }
    }



    public void moveMyMinion4(){
        if(minionMoveDown4 == true && minionMoveLeft4 == true) {
            if(minion4.getPosition().x < 40) {
                minionMoveLeft4 = false;
            }
            if(minion4.getPosition().y >= cam.viewportHeight - 20) {
                minionMoveDown4 = false;
            }
            minion4.getPosition().add(-5, 5, 0);
        }
        if(minionMoveDown4 == true && minionMoveLeft4 == false) {
            if(minion4.getPosition().x >= cam.viewportWidth - 20) {
                minionMoveLeft4 = true;
            }
            if(minion4.getPosition().y >= cam.viewportHeight - 20) {
                minionMoveDown4 = false;
            }
            minion4.getPosition().add(5, 5, 0);
        }
        if(minionMoveDown4 == false && minionMoveLeft4 == true){
            if(minion4.getPosition().x < 40) {
                minionMoveLeft4 = false;
            }
            if(minion4.getPosition().y <= 40) {
                minionMoveDown4 = true;
            }
            minion4.getPosition().add(-5, -5, 0);
        }
        if(minionMoveDown4 == false && minionMoveLeft4 == false){
            if(minion4.getPosition().x >= cam.viewportWidth - 20) {
                minionMoveLeft4 = true;
            }
            if(minion4.getPosition().y <= 40) {
                minionMoveDown4 = true;
            }
            minion4.getPosition().add(5, -5, 0);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background.getTexture(),background.getPosition().x,background.getPosition().y, cam.viewportWidth, cam.viewportHeight + 10);
        sb.draw(background2.getTexture(),background2.getPosition().x,background2.getPosition().y, cam.viewportWidth, cam.viewportHeight + 10);



        if(boss.getStatus() == false) {
            sb.draw(boss.getTexture(), boss.getPosition().x, boss.getPosition().y, boss.getBounds().width/2, boss.getBounds().height/2, cam.viewportWidth / 10, cam.viewportHeight / 13, 1.0f, 1.0f, 180.0f);
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

            for(BossShot s : boss.getStraightShots()) {
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



        if(angel.getStatus() == false) {
            sb.draw(angel.getTexture(), angel.getPosition().x, angel.getPosition().y, angel.getBounds().width/2, angel.getBounds().height/2, cam.viewportWidth / 10, cam.viewportHeight/13,1.0f, 1.0f, 180.0f);
            for(AngelShot s : angel.getShots() ) {
                if (s.collides(boss.getBounds()) && s.hit() == false) {
                    sb.draw(dmg, boss.getPosition().x - boss.getBounds().width/2, boss.getPosition().y, 200,200);
                    s.setHit(true);
                    lightImpact.play(0.2f);
                    boss.loseHealth(1);
                } else if (s.hit() == false) {
                    s.setBounds(cam.viewportWidth / 40, cam.viewportHeight / 40);
                    sb.draw(s.getTexture(), s.getPosition().x, s.getPosition().y, 10.0f, 10.0f, cam.viewportWidth / 40, cam.viewportHeight / 40, 1.0f, 1.0f, 180.0f);
                }
            }
        }


        if(minion.getStatus() == false) {
            sb.draw(minion.getTexture(), minion.getPosition().x, minion.getPosition().y, 10.0f, 10.0f, cam.viewportWidth / 10, cam.viewportHeight / 13, 1.0f, 1.0f, 180.0f);
            if(minion.collides(angel.getBounds())){
                angel.loseHealth(5);
                minion.getPosition().set(200, 40, 0);
            }
            for (MinionShot s : minion.getShots()) {
                if (s.collides(angel.getBounds()) && s.hit() == false) {
                    s.setHit(true);
                    angel.loseHealth(1);
                } else if (s.hit() == false) {
                    s.setBounds(cam.viewportWidth / 20, cam.viewportHeight / 20);
                    sb.draw(s.getTexture(), s.getPosition().x, s.getPosition().y, cam.viewportWidth / 20, cam.viewportHeight / 20);
                }
            }
        }




        if(minion2.getStatus() == false && boss.getHealth() < 400) {
            sb.draw(minion2.getTexture(), minion2.getPosition().x, minion2.getPosition().y, 10.0f, 10.0f, cam.viewportWidth / 10, cam.viewportHeight / 13, 1.0f, 1.0f, 180.0f);
            if(minion2.collides(angel.getBounds())){
                angel.loseHealth(5);
                minion2.getPosition().set(300, 200, 0);
            }
            for(MinionShot s : minion2.getShots() ) {
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




        if(minion3.getStatus() == false && boss.getHealth() < 300) {
            sb.draw(minion3.getTexture(), minion3.getPosition().x, minion3.getPosition().y, 10.0f, 10.0f, cam.viewportWidth / 10, cam.viewportHeight / 13, 1.0f, 1.0f, 180.0f);
            if(minion3.collides(angel.getBounds())){
                angel.loseHealth(5);
                minion3.getPosition().set(800, 600, 0);
            }
            for(MinionShot s : minion3.getShots() ) {
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


        if(minion4.getStatus() == false && boss.getHealth() < 200) {
            sb.draw(minion4.getTexture(), minion4.getPosition().x, minion4.getPosition().y, 10.0f, 10.0f, cam.viewportWidth / 10, cam.viewportHeight / 13, 1.0f, 1.0f, 180.0f);
            if(minion4.collides(angel.getBounds())){
                angel.loseHealth(5);
                minion4.getPosition().set(100, 700, 0);
            }
            for(MinionShot s : minion4.getShots() ) {
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




        font.draw(sb, "Health: " + angel.getHealth(), 0, 0);
        font2.draw(sb, "Boss: " + boss.getHealth(), cam.viewportWidth/2, 0);


        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        background2.dispose();
        dmg.dispose();
        angel.dispose();
        minion.dispose();
        minion2.dispose();
        minion3.dispose();
        minion4.dispose();
        boss.dispose();
        lightImpact.dispose();
        System.out.println("Play disposed");
    }
}
