package com.kononowicz24.letterssnake.helpers;

import com.badlogic.gdx.math.Vector2;
import com.kononowicz24.letterssnake.LettersSnake;
import com.kononowicz24.letterssnake.playables.Food;
import com.kononowicz24.letterssnake.playables.GenericFood;
import com.kononowicz24.letterssnake.playables.Part;
import com.kononowicz24.letterssnake.playables.Snake;
import com.kononowicz24.letterssnake.playables.SnakePart;
import com.kononowicz24.letterssnake.playables.TimedPremiumFood;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by k24 on 17.01.19.
 */

public class FoodRandomizer {
    protected LettersSnake lS;
    protected ArrayList<Food> foods;
    protected ArrayList<Snake> snakes;
    private int counter;
    protected Vector2 screenDims;
    public FoodRandomizer(LettersSnake lS, ArrayList<Snake> snakes, ArrayList<Food> foods, Vector2 screenDims) {
        this.lS = lS;
        this.foods = foods;
        this.snakes = snakes;
        this.screenDims = screenDims;
        counter=0;
    }
    protected ArrayList<Part> availablePositions() {
        ArrayList<Part> result = new ArrayList<Part>();
        for (int i=0; i<screenDims.x; i++) {
            for (int j=0; j<screenDims.y; j++) {
                result.add(new AbstractPart(lS, i, j));
            }
        }
        for (Snake snake: snakes) {
            for (SnakePart snakePart: snake) {
                //for (Part resultPart: result) {
                for (Iterator<Part> iterator = result.iterator(); iterator.hasNext();) { //inaczej nie działało, teraz wygląda git
                    Part resultPart = iterator.next();
                    if (snakePart.x == resultPart.x && snakePart.y == resultPart.y) {
                        iterator.remove();
                        //Gdx.app.log("RS2", "usunieto snaki, wuuec ok");
                    }
                }
            }
        }
        for (Food food: foods) {
            //for (Part resultPart: result) {
            for (Iterator<Part> iterator = result.iterator(); iterator.hasNext();) { //inaczej nie dziala
                Part resultPart = iterator.next();
                if (food.x == resultPart.x && food.y == resultPart.y) {
                    iterator.remove();
                    //Gdx.app.log("RS2", "usunieto jedzenie, wuuec ok");
                }
            }
        }
        return result;
    }
    public void addFood(Snake snake) {
        ArrayList<Part> avFoodPos = availablePositions();
        Random random = new Random();
        int ordinal = random.nextInt(availablePositions().size());
        foods.add(new GenericFood(lS, (int)avFoodPos.get(ordinal).x, (int)avFoodPos.get(ordinal).y));
        if (counter%(random.nextInt(3)+4)==3) { //MORE RANDOMNESSSS
            ordinal = random.nextInt(availablePositions().size());
            int center = lS.getxDimm()*lS.getyDimm()/2;
            int length = random.nextInt(25)+20+(center*center*10/((snake.size()-center)*(snake.size()-center)+78500));// wtf magia
            //Gdx.app.log("RND", String.valueOf(length));
            foods.add(new TimedPremiumFood(lS, (int) avFoodPos.get(ordinal).x, (int) avFoodPos.get(ordinal).y, length));
        }
        counter++;
    }
}
