package com.cs342.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Class to control the animations of the sprites.
 */

public class Animation {

    private Array<TextureRegion> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int frame;

    /**
     * Constructor to create Animation cycle.
     *
     * @param region TextureRegion Takes in sprite sheet.
     * @param frameCount int Number of sprites in the sprite sheet.
     * @param cycleTime float Speed of cycling through sprites.
     */
    public Animation(TextureRegion region, int frameCount, float cycleTime) {
        frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth() / frameCount;
        for(int i = 0; i < frameCount; i++) {
            frames.add(new TextureRegion(region, i * frameWidth, 0,frameWidth, region.getRegionHeight()));
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }

    /**
     * Method to update the animation
     *
     * @param dt float Takes in update of time.
     */
    public void update(float dt) {
        currentFrameTime += dt;
        if(currentFrameTime > maxFrameTime) {
            frame++;
            currentFrameTime = 0;
        }

        if(frame >= frameCount) {
            frame = 0;
        }
    }

    /**
     * Method to return the frame the animation is at.
     *
     * @return TextureRegion Returns the specific frame.
     */
    public TextureRegion getFrame() {
        return frames.get(frame);
    }
}
