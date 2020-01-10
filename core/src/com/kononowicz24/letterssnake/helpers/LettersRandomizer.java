package com.kononowicz24.letterssnake.helpers;

import com.badlogic.gdx.math.Vector2;
import com.kononowicz24.letterssnake.LettersSnake;
import com.kononowicz24.letterssnake.playables.Food;
import com.kononowicz24.letterssnake.playables.LetterFood;
import com.kononowicz24.letterssnake.playables.Part;
import com.kononowicz24.letterssnake.playables.Snake;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by k24 on 04.12.19.
 */

public class LettersRandomizer extends FoodRandomizer {
    private String letters = "AĄBCĆDEĘFGHIJKLŁMNŃOÓPQRSŚTUVWXYZŹŻ"; //todo maybe allow for change language
    private int count = 0;
    //private ArrayList<LetterFood> lettersOnScreen;
    private ArrayList<Boolean> lettersOnScreen;

    public LettersRandomizer(LettersSnake lS, ArrayList<Snake> snakes, ArrayList<Food> foods, Vector2 screenDims) {
        super(lS, snakes, foods, screenDims);
        lettersOnScreen = new ArrayList<Boolean>();
        for (int i=0; i<letters.length(); i++) {
            lettersOnScreen.add(Boolean.valueOf(false));
        }
    }

    @Override
    public void addFood(Snake snake) {
        ArrayList<Part> avFoodPos = availablePositions();
        Random random = new Random();
        int ordinal = random.nextInt(availablePositions().size());
        if (count>=letters.length()) {
            count=0;
            //todo google play achievement whole alphabet

        }
        //while (lettersOnScreen.get(count) == false) { //todo refactor
            foods.add(new LetterFood(lS, (int) avFoodPos.get(ordinal).x, (int) avFoodPos.get(ordinal).y, letters.charAt(count))); //todo check if not in

        //}
        count++; //todo checking if THIS letter is on the play field already

    }
    public void addFoodRandomly(Snake snake) {
        Random random = new Random();
        for (int i=0; i<3+random.nextInt(6); i++) {
            ArrayList<Part> avFoodPos = availablePositions();
            int ordinal = random.nextInt(avFoodPos.size());
            foods.add(new LetterFood(lS, (int) avFoodPos.get(ordinal).x, (int) avFoodPos.get(ordinal).y, letters.charAt(random.nextInt(letters.length()))));
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getLetters() {
        return letters;
    }

    public void setLetters(String letters) {
        this.letters = letters;
    }
}
