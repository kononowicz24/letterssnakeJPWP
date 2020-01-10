package com.kononowicz24.letterssnake.playables;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.kononowicz24.letterssnake.LettersSnake;

/**
 * Created by k24 on 03.12.19.
 */

public class LetterFood extends Food {
    private char letter;
    public LetterFood(LettersSnake lS, int x, int y, Texture tex, char letter) {
        super(lS, x, y, tex);
        this.letter = letter;
    }

    public LetterFood(LettersSnake lS, int x, int y, char letter) {
        super(lS, x, y, new Texture(Gdx.files.internal("food.png"))); ////
        this.setSound(Gdx.audio.newSound(Gdx.files.internal("sound.ogg")));
        this.letter = letter;
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void render() {
        //super.render();//todo change completely
        //render letter
        lS.getFontManager().font1dX.draw(lS.getBatch(),String.valueOf(letter), (x+1)*lS.dX, (int)((y+1.85)*lS.dY));
    }

    @Override
    public int value() {
        return lS.getPreferenceRetriever().getIntPreference("LSJPWP_LEVEL");
    }

    public char getLetter() {
        return letter;
    }
}
