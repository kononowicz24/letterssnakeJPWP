package com.kononowicz24.letterssnake.playables;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.kononowicz24.letterssnake.LettersSnake;
import com.kononowicz24.letterssnake.helpers.Disposable;
import com.kononowicz24.letterssnake.helpers.Renderable;

/**
 * Created by k24 on 02.09.18.
 */

public  abstract class Part extends Vector2 implements Renderable, Disposable {
    private Texture texture;
    protected LettersSnake lS;
    public Part(LettersSnake lS, int x, int y, Texture tex) {
        this.lS = lS;
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
        lS.getBatch().draw(texture, (x+1)*lS.dX, (y+1)*lS.dY, lS.dX, lS.dY);
    }
}
