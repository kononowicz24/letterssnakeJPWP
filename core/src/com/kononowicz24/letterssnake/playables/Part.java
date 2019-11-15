package com.kononowicz24.retrosnake2.playables;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.kononowicz24.retrosnake2.RetroSnake;
import com.kononowicz24.retrosnake2.helpers.Disposable;
import com.kononowicz24.retrosnake2.helpers.Renderable;

/**
 * Created by k24 on 02.09.18.
 */

public  abstract class Part extends Vector2 implements Renderable, Disposable {
    private Texture texture;
    protected RetroSnake rS;
    public Part(RetroSnake rS, int x, int y, Texture tex) {
        this.rS = rS;
        texture = tex;
        this.x = x;
        this.y = y;
    }
    @Override
    public void dispose() {
        this.getTexture().dispose();
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    @Override
    public void render() {
        rS.getBatch().draw(texture, (x+1)*rS.dX, (y+1)*rS.dY, rS.dX, rS.dY);
    }
}
