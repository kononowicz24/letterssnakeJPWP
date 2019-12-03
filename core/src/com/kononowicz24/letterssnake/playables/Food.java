package com.kononowicz24.letterssnake.playables;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.kononowicz24.letterssnake.LettersSnake;
import com.kononowicz24.letterssnake.helpers.Disposable;
import com.kononowicz24.letterssnake.helpers.Renderable;

/**
 * Created by k24 on 02.09.18.
 */

public abstract class Food extends Part implements Renderable, Disposable {
    private Sound sound;
    public Food(LettersSnake lS, int x, int y, Texture tex) {
        super(lS, x, y, tex);
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
