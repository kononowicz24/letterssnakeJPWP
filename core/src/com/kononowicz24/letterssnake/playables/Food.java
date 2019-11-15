package com.kononowicz24.retrosnake2.playables;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.kononowicz24.retrosnake2.RetroSnake;
import com.kononowicz24.retrosnake2.helpers.Disposable;
import com.kononowicz24.retrosnake2.helpers.Renderable;

import java.util.ArrayList;

/**
 * Created by k24 on 02.09.18.
 */

public abstract class Food extends Part implements Renderable, Disposable {
    private Sound sound;
    public Food(RetroSnake rS, int x, int y, Texture tex) {
        super(rS, x, y, tex);
    }

    @Override
    public void dispose() {
        super.dispose();
        sound.dispose();
    }

    @Override
    public void render() {
        super.render();
    }

    public Sound getSound() {
        return sound;
    }

    public void setSound(Sound sound) {
        this.sound = sound;
    }
    public abstract int value();
}
