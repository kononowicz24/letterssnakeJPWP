package com.kononowicz24.retrosnake2.playables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.kononowicz24.retrosnake2.RetroSnake;
import com.kononowicz24.retrosnake2.helpers.AbstractPart;
import com.kononowicz24.retrosnake2.helpers.Disposable;
import com.kononowicz24.retrosnake2.helpers.FoodRandomizer;
import com.kononowicz24.retrosnake2.helpers.Renderable;
import com.kononowicz24.retrosnake2.helpers.SnakeDirection;
import com.kononowicz24.retrosnake2.helpers.Steppable;
import com.kononowicz24.retrosnake2.overlays.GameOverOverlay;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * Created by k24 on 02.09.18.
 */

public abstract class Snake extends ArrayList<SnakePart> implements Renderable, Disposable, Steppable {
    protected SnakeDirection direction;
    protected boolean dead=false;
    protected RetroSnake rS;
    protected FoodRandomizer foodRandomizer;
    protected GameOverOverlay gameOverOverlay;
    protected ArrayList<Food> foods;
    protected int score;
    public Snake(RetroSnake retroSnake, ArrayList<Food> foods, FoodRandomizer foodRandomizer, GameOverOverlay gameOverOverlay) {
        rS = retroSnake;
        score = 0;
        this.foods = foods;
        this.foodRandomizer = foodRandomizer;
        this.gameOverOverlay = gameOverOverlay;
        direction = SnakeDirection.RH;
        this.add(new SnakePart(rS, 7,6, new Texture("snake.png")));
        this.add(new SnakePart(rS, 6,6, new Texture("snake.png")));
        this.add(new SnakePart(rS, 5,6, new Texture("snake.png")));
        this.dead = false;
    }
    @Override
    public void dispose() {
        for (SnakePart snakePart: this) {
            snakePart.dispose();
        }
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
        if (newHeadx<0) newHeadx+=rS.getxDimm();
        if (newHeadx>=rS.getxDimm()) newHeadx-=rS.getxDimm(); //todo based on mode kill on bounds or not
        if (newHeady<0) newHeady+=rS.getyDimm();
        if (newHeady>=rS.getyDimm()) newHeady-=rS.getyDimm();
        AbstractPart abstractPart = new AbstractPart(rS, (int)newHeadx, (int)newHeady);//fixme vector2 would be ok
        if (belongs(abstractPart)) {
            //todo przegrana
            this.die();
            Gdx.app.log("RS", "przegrana");
            gameOverOverlay.show();
        }
        this.add(0,new SnakePart(rS, (int)newHeadx, (int)newHeady, head.getTexture()));

        boolean consumed = false;
        ArrayList<Food> foodConsumed = new ArrayList<Food>();
        //for (Food food: foods) {
        for (Iterator<Food> iterator= foods.iterator(); iterator.hasNext();) {
            //Food food = iterator.next();
            consumed|=consume(iterator, foodConsumed); //one of each may be consumed -> OR gate
        }
        for(Food food: foodConsumed) { //zjedzone jedzenie usuwamy dopiero po sprawdzeniu i wylistowaniu ktore bylo zjedzone
            if (food instanceof GenericFood)
                foodRandomizer.addFood(this);
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
}
