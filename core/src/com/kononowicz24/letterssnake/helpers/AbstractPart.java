package com.kononowicz24.letterssnake.helpers;

import com.kononowicz24.letterssnake.LettersSnake;
import com.kononowicz24.letterssnake.playables.Part;

/**
 * Created by k24 on 17.01.19.
 */

public class AbstractPart extends Part {
    public AbstractPart(LettersSnake lS, int x, int y) {
        super(lS, x, y, lS.getEmptyTexture());
    }
}
