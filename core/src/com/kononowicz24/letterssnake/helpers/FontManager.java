package com.kononowicz24.letterssnake.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.kononowicz24.letterssnake.LettersSnake;

/**
 * Created by k24 on 19.01.19.
 */

public class FontManager implements Disposable{
    private FreeTypeFontGenerator mainMenuFontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    public BitmapFont font1dX;
    public BitmapFont font2dX_inv;
    public FontManager(LettersSnake lS) {
        mainMenuFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/PressStart2P.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int)(0.85f*lS.dX);
        parameter.color = new Color(0.24609375f, 0.27734375f, 0.234375f,1);
        font1dX = mainMenuFontGenerator.generateFont(parameter);

        parameter.size = (int)(2*0.85f*lS.dX);
        parameter.color = new Color(0.5546875f, 0.63671875f, 0.15625f, 1);
        font2dX_inv = mainMenuFontGenerator.generateFont(parameter);
        mainMenuFontGenerator.dispose();

    }

    @Override
    public void dispose() {
        font1dX.dispose();
        font2dX_inv.dispose();
    }
}
