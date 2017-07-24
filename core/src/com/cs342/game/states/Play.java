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

import java.lang.Math;

import static com.badlogic.gdx.Input.Keys.X;


class Play extends State{

    private static final double PI = 3.14;

    private Angel angel;
    private Boss boss;




    private Background background;
    private  Background background2;
    private Texture bg;

    Play(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(true);
        angel = new Angel(20, 20);
        boss = new Boss(50, 50);
        bg = new Texture("background.jpg");
        background = new Background(bg, 0, 0, 0);
        background2 = new Background(bg,0, -LightBringer.HEIGHT, 0);
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
        background.move(0, 10f, 0);
        if(background.getPosition().y > LightBringer.HEIGHT) {
            background.getPosition().set(0, -LightBringer.HEIGHT + 20, 0);
        }
        background2.move(0,10f,0);
        if(background2.getPosition().y > LightBringer.HEIGHT) {
            background2.getPosition().set(0, -LightBringer.HEIGHT + 20, 0);
        }
        angel.update(dt);
        boss.update(dt);

        angel.shoot(0, -20f, 0, 5, dt);
        boss.shoot(0, 20f, 0, 5, dt);



    }


    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background.getTexture(),background.getPosition().x,background.getPosition().y, LightBringer.WIDTH, LightBringer.HEIGHT);
        sb.draw(background2.getTexture(),background2.getPosition().x,background2.getPosition().y, LightBringer.WIDTH, LightBringer.HEIGHT);
        sb.draw(angel.getTexture(), angel.getPosition().x, angel.getPosition().y, 10.0f, 10.0f, 25.0f, 20.0f,3.0f, 3.0f, 180.0f);


        for(AngelShot s : angel.getShots() ) {
            sb.draw(s.getTexture(), s.getPosition().x, s.getPosition().y, 10.0f, 10.0f, 10.0f, 10.0f, 3.0f, 3.0f, 180.0f);

        }

        for(BossShot s : boss.getShots() ) {
            sb.draw(s.getTexture(), s.getPosition().x, s.getPosition().y, 40,40);

        }


        sb.draw(boss.getTexture(), boss.getPosition().x, boss.getPosition().y, 10.0f, 10.0f, 25.0f, 20.0f, 3.0f, 3.0f, 180.0f);
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
