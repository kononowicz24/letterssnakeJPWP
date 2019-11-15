package com.kononowicz24.retrosnake2.layoutelems;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Align;
import com.kononowicz24.retrosnake2.RetroSnake;
import com.kononowicz24.retrosnake2.helpers.Disposable;
import com.kononowicz24.retrosnake2.helpers.Renderable;

/**
 * Created by k24 on 14.02.19.
 */

public class CenteredText implements Renderable, Disposable {
    private RetroSnake rS;
    private final String text;
    private final BitmapFont font;
    private final GlyphLayout layout;


    public CenteredText(RetroSnake rS, String text, BitmapFont bitmapFont, Color color, int xcent, int ycent, int xsize, int ysize) {
        this.rS = rS;
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
