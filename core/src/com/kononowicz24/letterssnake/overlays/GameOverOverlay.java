package com.kononowicz24.letterssnake.overlays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.kononowicz24.letterssnake.LettersSnake;

/**
 * Created by k24 on 20.01.19.
 */

public class GameOverOverlay extends Overlay {
    private Texture texture;
    public GameOverOverlay(LettersSnake lS) {
        super(lS);
        texture = new Texture(Gdx.files.internal("bound.png"));
    }
    @Override
    public void dispose() {
        super.dispose();
        texture.dispose();
    }

    @Override
    public void render() {
        if (visible) {
            lS.getBatch().draw(texture, lS.dX*2, lS.dY*7, (lS.getxDimm()-2)*lS.dX, 5*lS.dY );
            lS.getFontManager().font2dX_inv.draw(lS.getBatch(), "Game Over", lS.dX*3, lS.dY*10);//todo localization
        }
    }

}

