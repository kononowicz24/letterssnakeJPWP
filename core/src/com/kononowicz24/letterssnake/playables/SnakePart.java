package com.kononowicz24.letterssnake.playables;

import com.badlogic.gdx.graphics.Texture;
import com.kononowicz24.letterssnake.LettersSnake;

/**
 * Created by k24 on 06.09.18.
 */

public class SnakePart extends Part {
    public SnakePart(LettersSnake lS, int x, int y, Texture tex) {
        super(lS, x, y, tex);
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
