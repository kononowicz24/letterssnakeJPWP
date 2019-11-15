package com.kononowicz24.retrosnake2.overlays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.kononowicz24.retrosnake2.RetroSnake;

/**
 * Created by k24 on 20.01.19.
 */

public class GameOverOverlay extends Overlay {
    private Texture texture;
    public GameOverOverlay(RetroSnake rS) {
        super(rS);
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
            rS.getBatch().draw(texture, rS.dX*2, rS.dY*7, (rS.getxDimm()-2)*rS.dX, 5*rS.dY );
            rS.getFontManager().font2dX_inv.draw(rS.getBatch(), "Game Over", rS.dX*3, rS.dY*10);//todo localization
        }
    }

}

