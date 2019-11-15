package com.kononowicz24.retrosnake2.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.kononowicz24.retrosnake2.RetroSnake;
import com.kononowicz24.retrosnake2.playables.Food;
import com.kononowicz24.retrosnake2.playables.GenericFood;
import com.kononowicz24.retrosnake2.playables.Part;
import com.kononowicz24.retrosnake2.playables.Snake;
import com.kononowicz24.retrosnake2.playables.SnakePart;
import com.kononowicz24.retrosnake2.playables.TimedPremiumFood;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by k24 on 17.01.19.
 */

public class FoodRandomizer {
    private RetroSnake rS;
    private ArrayList<Food> foods;
    private ArrayList<Snake> snakes;
    private int counter;
    private Vector2 screenDims;
    public FoodRandomizer(RetroSnake rS, ArrayList<Snake> snakes, ArrayList<Food> foods, Vector2 screenDims) {
        this.rS = rS;
        this.foods = foods;
        this.snakes = snakes;
        this.screenDims = screenDims;
        counter=0;
    }
    private ArrayList<Part> availablePositions() {
        ArrayList<Part> result = new ArrayList<Part>();
        for (int i=0; i<screenDims.x; i++) {
            for (int j=0; j<screenDims.y; j++) {
                result.add(new AbstractPart(rS, i, j));
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
        foods.add(new GenericFood(rS, (int)avFoodPos.get(ordinal).x, (int)avFoodPos.get(ordinal).y));
        if (counter%(random.nextInt(3)+4)==3) { //MORE RANDOMNESSSS
            ordinal = random.nextInt(availablePositions().size());
            int center = rS.getxDimm()*rS.getyDimm()/2;
            int length = random.nextInt(25)+20+(center*center*10/((snake.size()-center)*(snake.size()-center)+78500));// wtf magia
            //Gdx.app.log("RND", String.valueOf(length));
            foods.add(new TimedPremiumFood(rS, (int) avFoodPos.get(ordinal).x, (int) avFoodPos.get(ordinal).y, length));
        }
        counter++;
    }
}
