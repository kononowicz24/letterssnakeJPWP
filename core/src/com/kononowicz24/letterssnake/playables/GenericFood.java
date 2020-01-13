package com.kononowicz24.letterssnake.playables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.kononowicz24.letterssnake.LettersSnake;

/**
 * Created by k24 on 02.09.18.
 */

public class GenericFood extends Food {
    public GenericFood(LettersSnake lS, int x, int y, Texture tex) {
        super(lS, x, y, tex);
    }

    /**
     * Creates Generic food on screen (as on Nokia phone)
     * @param lS main game object
     * @param x x where to put it
     * @param y y where to put it
     */
    public GenericFood(LettersSnake lS, int x, int y) {
        super(lS, x, y, new Texture(Gdx.files.internal("food.png"))); ////
        this.setSound(Gdx.audio.newSound(Gdx.files.internal("sound.ogg")));
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public int value() {
        return lS.getLevel();
    }
}
