package com.kononowicz24.letterssnake.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.kononowicz24.letterssnake.LettersSnake;
import com.kononowicz24.letterssnake.interfaces.Achievement;
import com.kononowicz24.letterssnake.playables.Food;
import com.kononowicz24.letterssnake.playables.LetterFood;
import com.kononowicz24.letterssnake.playables.Part;
import com.kononowicz24.letterssnake.playables.Snake;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * Created by k24 on 04.12.19.
 */

public class LettersRandomizer extends FoodRandomizer implements Renderable, Disposable{
    private String letters = "AĄBCĆDEĘFGHIJKLŁMNŃOÓPQRSŚTUVWXYZŹŻ"; //todo maybe allow for change language
    private int count = 0;
    private Timer timer;
    private Sound errorSound;
    //private ArrayList<LetterFood> lettersOnScreen;
    private ArrayList<Boolean> lettersOnScreen;

    /**
     * Creates Letter Food randomizer
     * @param lS main game object
     * @param snakes list of snakes on the field
     * @param foods list of foods on the field
     * @param screenDims vector2 field size
     */
    public LettersRandomizer(LettersSnake lS, ArrayList<Snake> snakes, ArrayList<Food> foods, Vector2 screenDims) {
        super(lS, snakes, foods, screenDims);
        lettersOnScreen = new ArrayList<Boolean>();
        for (int i=0; i<letters.length(); i++) {
            lettersOnScreen.add(Boolean.valueOf(false));
        }
        timer = new Timer(lS);
        this.setSound(Gdx.audio.newSound(Gdx.files.internal("sound.ogg")));
    }

    /**
     * Adds Letter Food to the field
     * @param snake not used
     */
    @Override
    public void addFood(Snake snake) {
        ArrayList<Part> avFoodPos = availablePositions();
        Random random = new Random();
        int ordinal = random.nextInt(availablePositions().size());
        if (count==1) {
            timer.reset();
            timer.start();
        }
        if (count>=letters.length()) {
            count=0;
            //todo google play achievement whole alphabet
            lS.getPlayServices().unlockAchievement(Achievement.SEVENYROLD);
            timer.stop();
        }
        //while (lettersOnScreen.get(count) == false) { //todo refactor
            foods.add(new LetterFood(lS, (int) avFoodPos.get(ordinal).x, (int) avFoodPos.get(ordinal).y, letters.charAt(count))); //todo check if not in

        //}
        count++; //todo checking if THIS letter is on the play field already

    }

    /**
     * Adds random number of letters on the field at the begginnig of the game
     * @param snake not used
     */
    public void addFoodRandomly(Snake snake) {
        Random random = new Random();
        for (int i=0; i<4+random.nextInt(7); i++) {
            ArrayList<Part> avFoodPos = availablePositions();
            int ordinal = random.nextInt(avFoodPos.size());
            foods.add(new LetterFood(lS, (int) avFoodPos.get(ordinal).x, (int) avFoodPos.get(ordinal).y, letters.charAt(random.nextInt(letters.length()))));
        }
    }

    /**
     * Returns ordinal which letter of alphabet is used now
     * @return int ordinal
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets current letter
     * @param count value to be set
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Returns String containing all available letters in alphabetical order
     * @return
     */
    public String getLetters() {
        return letters;
    }

    public void setLetters(String letters) {
        this.letters = letters;
    }

    /**
     * Renders current letter to be eaten at the top of the screen
     */
    @Override
    public void render() {
        timer.render();
        lS.getFontManager().font1dX.draw(lS.getBatch(),letters.substring(count-1, count), 14.1f*lS.dX, (int)((lS.getyDimm()+1.85)*lS.dY));
    }

    /**
     * Returns timer instance which keeps time of eating whole alphabet
     * @return timer
     */
    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    /**
     * Sets sound asset played on eating wrong letter
     * @param sound
     */
    public void setSound(Sound sound) {
        this.errorSound = sound;
    }

    /**
     * Returns error sound instance
     * @return sound played on eating wrong letter
     */
    public Sound getErrorSound() {
        return errorSound;
    }

    /**
     * Called on game close, required by LibGDX
     */
    @Override
    public void dispose() {
        errorSound.dispose();
        timer.dispose();
    }
}
