package com.kononowicz24.letterssnake.layoutelems;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Align;
import com.kononowicz24.letterssnake.LettersSnake;
import com.kononowicz24.letterssnake.helpers.Disposable;
import com.kononowicz24.letterssnake.helpers.Renderable;

/**
 * Created by k24 on 14.02.19.
 */

public class CenteredText implements Renderable, Disposable {
    private LettersSnake lS;
    private final String text;
    private final BitmapFont font;
    private final GlyphLayout layout;


    public CenteredText(LettersSnake lS, String text, BitmapFont bitmapFont, Color color, int xcent, int ycent, int xsize, int ysize) {
        this.lS = lS;
        this.text = text;
        this.font = bitmapFont;
        layout = new GlyphLayout(this.font, this.text, color, xsize, Align.center, true);
    }

    @Override
    public void dispose() {
    }

    @Override
    public void render() {

    }
}
