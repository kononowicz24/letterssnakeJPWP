package com.kononowicz24.retrosnake2.playables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.kononowicz24.retrosnake2.RetroSnake;

/**
 * Created by k24 on 02.09.18.
 */

public class GenericFood extends Food {
    public GenericFood(RetroSnake rS, int x, int y, Texture tex) {
        super(rS, x, y, tex);
    }

    public GenericFood(RetroSnake rS, int x, int y) {
        super(rS, x, y, new Texture(Gdx.files.internal("food.png"))); ////
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
        return rS.getLevel();
    }
}
