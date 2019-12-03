package com.kononowicz24.letterssnake.playables;

import com.badlogic.gdx.graphics.Texture;
import com.kononowicz24.letterssnake.LettersSnake;
import com.kononowicz24.letterssnake.helpers.Disposable;
import com.kononowicz24.letterssnake.helpers.Renderable;

/**
 * Created by k24 on 02.09.18.
 */

public class Obstacle extends Part implements Renderable, Disposable {
    public Obstacle(LettersSnake lS, int x, int y, Texture tex) {
        super(lS, x, y, tex);
    }
    @Override
    public void dispose() {
    }

    @Override
    public void render() {

    }
}
