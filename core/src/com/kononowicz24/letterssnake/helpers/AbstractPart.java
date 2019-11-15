package com.kononowicz24.retrosnake2.helpers;

import com.badlogic.gdx.graphics.Texture;
import com.kononowicz24.retrosnake2.RetroSnake;
import com.kononowicz24.retrosnake2.playables.Part;

/**
 * Created by k24 on 17.01.19.
 */

public class AbstractPart extends Part {
    public AbstractPart(RetroSnake rS, int x, int y) {
        super(rS, x, y, rS.getEmptyTexture());
    }
}
