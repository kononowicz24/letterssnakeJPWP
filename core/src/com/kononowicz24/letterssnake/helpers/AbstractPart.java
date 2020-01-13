package com.kononowicz24.letterssnake.helpers;

import com.kononowicz24.letterssnake.LettersSnake;
import com.kononowicz24.letterssnake.playables.Part;

/**
 * Created by k24 on 17.11.19.
 */

public class AbstractPart extends Part {
    /**
     * Creates AbstractPart - instance which helps iterating through Part instances
     * @param lS main game object
     * @param x x position on the field
     * @param y y position on the field
     */
    public AbstractPart(LettersSnake lS, int x, int y) {
        super(lS, x, y, lS.getEmptyTexture());
    }
}
