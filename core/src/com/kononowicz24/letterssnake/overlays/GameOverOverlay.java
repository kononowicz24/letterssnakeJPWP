package com.kononowicz24.letterssnake.overlays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.kononowicz24.letterssnake.LettersSnake;
import com.kononowicz24.letterssnake.playables.Snake;

/**
 * Created by k24 on 20.01.19.
 */

public class GameOverOverlay extends Overlay {
    private Texture texture;
    //private Snake snake;
    public GameOverOverlay(LettersSnake lS) {
        super(lS);
        texture = new Texture(Gdx.files.internal("bound.png"));
        //this.snake=snake;
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
            lS.getFontManager().font2dX_inv.draw(lS.getBatch(), "Game Over", lS.dX*4, lS.dY*10);//todo localization

            lS.getBatch().draw(texture, lS.dX*2, lS.dY*19, (lS.getxDimm()-2)*lS.dX, 5*lS.dY );
            lS.getFontManager().font2dX_inv.draw(lS.getBatch(), String.valueOf(score), lS.dX*10, lS.dY*22);

            lS.getBatch().draw(texture, lS.dX*2, lS.dY*13, (lS.getxDimm()-2)*lS.dX, 5*lS.dY );
            lS.getFontManager().font2dX_inv.draw(lS.getBatch(), "Max: "+lS.getPreferenceRetriever().getIntPreference("LSJPWP_HISCORE"), lS.dX*5, lS.dY*16);
        }
    }

}

