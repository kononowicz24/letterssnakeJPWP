package com.kononowicz24.retrosnake2.playables;

import com.badlogic.gdx.graphics.Texture;
import com.kononowicz24.retrosnake2.RetroSnake;

/**
 * Created by k24 on 06.09.18.
 */

public class SnakePart extends Part {
    public SnakePart(RetroSnake rS, int x, int y, Texture tex) {
        super(rS, x, y, tex);
    }
    @Override
    public void dispose() {
        this.getTexture().dispose();
        super.dispose();
    }

    @Override
    public void render() {
        super.render();
    }
}
