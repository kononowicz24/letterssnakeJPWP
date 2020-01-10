package com.kononowicz24.letterssnake.playables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.kononowicz24.letterssnake.LettersSnake;
import com.kononowicz24.letterssnake.helpers.AbstractPart;
import com.kononowicz24.letterssnake.helpers.Disposable;
import com.kononowicz24.letterssnake.helpers.FoodRandomizer;
import com.kononowicz24.letterssnake.helpers.LettersRandomizer;
import com.kononowicz24.letterssnake.helpers.Renderable;
import com.kononowicz24.letterssnake.helpers.SnakeDirection;
import com.kononowicz24.letterssnake.helpers.Steppable;
import com.kononowicz24.letterssnake.overlays.GameOverOverlay;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * Created by k24 on 02.09.18.
 */

public abstract class Snake extends ArrayList<SnakePart> implements Renderable, Disposable, Steppable {
    protected SnakeDirection direction;
    protected boolean dead=false;
    protected LettersSnake lS;
    protected LettersRandomizer lettersRandomizer;
    protected GameOverOverlay gameOverOverlay;
    protected ArrayList<Food> foods;
    protected int score;
    public Snake(LettersSnake lettersSnake, ArrayList<Food> foods, LettersRandomizer lettersRandomizer, GameOverOverlay gameOverOverlay) {
        lS = lettersSnake;
        score = 0;
        this.foods = foods;
        this.lettersRandomizer = lettersRandomizer;
        this.gameOverOverlay = gameOverOverlay;
        direction = SnakeDirection.RH;
        this.add(new SnakePart(lS, 7,6, new Texture("snake.png")));
        this.add(new SnakePart(lS, 6,6, new Texture("snake.png")));
        this.add(new SnakePart(lS, 5,6, new Texture("snake.png")));
        this.dead = false;
    }
    @Override
    public void dispose() {
        for (SnakePart snakePart: this) {
            snakePart.dispose();
        }
        lettersRandomizer.dispose();
    }

    @Override
    public void render() {
        for (SnakePart snakePart: this) {
            snakePart.render();
        }

    }

    public void step() {
        //if (1==1) return;
        SnakePart head = this.get(0);
        float newHeadx=head.x;
        float newHeady=head.y;
        switch (direction) {
            case UP: newHeady+=1; break;
            case DN: newHeady-=1; break;
            case LH: newHeadx-=1; break;
            case RH: newHeadx+=1; break;
        }
        if (newHeadx<0) newHeadx+=lS.getxDimm();
        if (newHeadx>=lS.getxDimm()) newHeadx-=lS.getxDimm(); //todo based on mode kill on bounds or not
        if (newHeady<0) newHeady+=lS.getyDimm();
        if (newHeady>=lS.getyDimm()) newHeady-=lS.getyDimm();
        AbstractPart abstractPart = new AbstractPart(lS, (int)newHeadx, (int)newHeady);//fixme vector2 would be ok
        if (belongs(abstractPart)) {
            this.die();
            lettersRandomizer.getTimer().stop();
            if (lS.getPreferenceRetriever().getIntPreference("LSJPWP_HISCORE")<score) {
                lS.getPreferenceRetriever().setIntPreference("LSJPWP_HISCORE", score);
            }
            Gdx.app.log("RS", "przegrana");
            gameOverOverlay.show(score);
        }
        this.add(0,new SnakePart(lS, (int)newHeadx, (int)newHeady, head.getTexture()));

        boolean consumed = false;
        ArrayList<Food> foodConsumed = new ArrayList<Food>();
        //for (Food food: foods) {
        for (Iterator<Food> iterator= foods.iterator(); iterator.hasNext();) {
            //Food food = iterator.next();
            consumed|=consume(iterator, foodConsumed); //one of each may be consumed -> OR gate
        }
        for(Food food: foodConsumed) { //zjedzone jedzenie usuwamy dopiero po sprawdzeniu i wylistowaniu ktore bylo zjedzone
            if (food instanceof LetterFood) {

                if (lettersRandomizer.getCount()-1 != lettersRandomizer.getLetters().indexOf(((LetterFood) food).getLetter())) {
                    score-=30;
                    lettersRandomizer.getErrorSound().play();
                    lettersRandomizer.setCount(lettersRandomizer.getCount()-1);
                }
                lettersRandomizer.addFood(this);
            }
            food.getSound().play();
            score+=food.value();
            food.dispose();
            foods.remove(food);
        }

        if (!consumed) {
            this.remove(this.size()-1); //jeżeli nie jadł to usuń ostatnią część
        } else {
            Gdx.app.log("EA", "consumed");
        }
    }

    private boolean belongs(Part part) {
        for (SnakePart snakePart:this) {
            if (snakePart.x == part.x && snakePart.y==part.y) return true;
        }
        return false;
    }

    public void setDirection(SnakeDirection direction) {

    }

    private boolean consume(Iterator<Food> iterator, ArrayList<Food> foodsConsumed) {
        Food food = iterator.next();
        if (this.get(0).x == food.x && this.get(0).y == food.y) {
            //score +=1; //todo - value to be taken from settings, based on level
            foodsConsumed.add(food);
            return true;
        }
        return false;
    }

    public void die() {
        dead = true;
    }
    public void unDie() {
        dead = false;
    }
    public boolean isDead() {
        return dead;
    }

    public int getScore() {
        return score;
    }
}
