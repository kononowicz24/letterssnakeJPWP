package com.kononowicz24.retrosnake2.playables;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.kononowicz24.retrosnake2.RetroSnake;
import com.kononowicz24.retrosnake2.helpers.Disposable;
import com.kononowicz24.retrosnake2.helpers.Renderable;

/**
 * Created by k24 on 02.09.18.
 */

public class Obstacle extends Part implements Renderable, Disposable {
    public Obstacle(RetroSnake rS, int x, int y, Texture tex) {
        super(rS, x, y, tex);
    }
    @Override
    public void dispose() {
    }

    @Override
    public void render() {

    }
}
