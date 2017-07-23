package com.cs342.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.Input.Keys;
import com.cs342.game.LightBringer;
import com.cs342.game.sprites.Angel;
import com.cs342.game.sprites.AngelShot;
import com.cs342.game.sprites.Boss;
import com.cs342.game.sprites.BossShot;

import java.lang.Math;

import static com.badlogic.gdx.Input.Keys.X;

/**
 * Created by Vincent on 7/17/17.
 */

public class Play extends State{

    private static final double PI = 3.14;

    private Angel angel;
    private Boss boss;
    private AngelShot angelShot;
    private Array<AngelShot> angelShots;
    private Array<BossShot> bossShots;
    private int timer;
    private double pattern;
    private Texture background;

    public Play(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(true);
        angel = new Angel(20, 20);
        boss = new Boss(50, 50);
        //angelShot = new AngelShot(((int) angel.getPosition().x), (int) angel.getPosition().y);
        angelShots = new Array<AngelShot>();
        bossShots = new Array<BossShot>();
        timer = 0;
        pattern = 0;

        background = new Texture("background.jpg");
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
        angel.update(dt);
        timer++;
        if(timer > 5) {
            angelShots.add(new AngelShot(((int) angel.getPosition().x -20), (int) angel.getPosition().y));
            bossShots.add(new BossShot(((int) boss.getPosition().x -20), (int) boss.getPosition().y));
            timer = 0;
        }
        for(int i = 0; i < angelShots.size; i++) {
            if(angelShots.get(i).getPosition().y < -300) {
                angelShots.removeIndex(i);
                //angelShots.get(i).dispose();
            }

            angelShots.get(i).move((float)Math.sin(pattern / 20) * 50, -50f, 0);


            pattern += (PI * (0.5));

            angelShots.get(i).update(dt);

        }

        for(int i = 0; i < bossShots.size; i++) {
            if(bossShots.get(i).getPosition().y > 900) {
                bossShots.removeIndex(i);
                //angelShots.get(i).dispose();
            }

            bossShots.get(i).move(0, 10f, 0);

            pattern += (PI * (0.5));

            bossShots.get(i).update(dt);
        }



        boss.update(dt);
    }


    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background,0,0, LightBringer.WIDTH, LightBringer.HEIGHT);
        sb.draw(angel.getTexture(), angel.getPosition().x, angel.getPosition().y, 10.0f, 10.0f, 25.0f, 20.0f,3.0f, 3.0f, 180.0f);


        for(AngelShot s : angelShots ) {
            sb.draw(s.getTexture(), s.getPosition().x, s.getPosition().y, 10.0f, 10.0f, 10.0f, 10.0f, 3.0f, 3.0f, 180.0f);

        }

        for(BossShot s : bossShots ) {
            sb.draw(s.getTexture(), s.getPosition().x, s.getPosition().y, 40,40);

        }

        sb.draw(boss.getTexture(), boss.getPosition().x, boss.getPosition().y, 10.0f, 10.0f, 25.0f, 20.0f, 3.0f, 3.0f, 180.0f);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        angel.dispose();

        boss.dispose();
        System.out.println("Play disposed");
    }
}
