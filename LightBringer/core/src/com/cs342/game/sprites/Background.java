package com.cs342.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class Background {
    private Texture background;
    private Vector3 position;

    public Background(Texture texture, float x, float y, float z) {
        this.background = texture;
        position = new Vector3(x, y , z);
    }

    public void move(float x, float y, float z) {
        position.add(x, y, z);
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return background;
    }

    public void dispose() {
        background.dispose();
    }


}
