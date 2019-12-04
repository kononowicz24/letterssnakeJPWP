package com.kononowicz24.letterssnake.helpers;

import com.badlogic.gdx.math.Vector2;
import com.kononowicz24.letterssnake.LettersSnake;
import com.kononowicz24.letterssnake.playables.Food;
import com.kononowicz24.letterssnake.playables.GenericFood;
import com.kononowicz24.letterssnake.playables.LetterFood;
import com.kononowicz24.letterssnake.playables.Part;
import com.kononowicz24.letterssnake.playables.Snake;
import com.kononowicz24.letterssnake.playables.TimedPremiumFood;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by k24 on 04.12.19.
 */

public class LettersRandomizer extends FoodRandomizer {
    private String letters = "abcdefghijklmnopqrstuvwxyz"; //todo maybe allow for change language
    private int count = 0;
    private ArrayList<LetterFood> lettersOnScreen;

    public LettersRandomizer(LettersSnake lS, ArrayList<Snake> snakes, ArrayList<Food> foods, Vector2 screenDims) {
        super(lS, snakes, foods, screenDims);
        lettersOnScreen = new ArrayList<LetterFood>();
    }

    @Override
    public void addFood(Snake snake) {
        ArrayList<Part> avFoodPos = availablePositions();
        Random random = new Random();
        int ordinal = random.nextInt(availablePositions().size());
        foods.add(new LetterFood(lS, (int)avFoodPos.get(ordinal).x, (int)avFoodPos.get(ordinal).y, letters.charAt(count))); //todo check if not in
    }
    public void addFoodRandomly(Snake snake) {
        ArrayList<Part> avFoodPos = availablePositions();
        Random random = new Random();
        int ordinal = random.nextInt(availablePositions().size());
        foods.add(new LetterFood(lS, (int)avFoodPos.get(ordinal).x, (int)avFoodPos.get(ordinal).y, letters.charAt(random.nextInt(letters.length()))));
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
