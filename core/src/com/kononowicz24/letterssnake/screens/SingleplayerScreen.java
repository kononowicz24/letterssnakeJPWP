package com.kononowicz24.retrosnake2.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.kononowicz24.retrosnake2.RetroSnake;
import com.kononowicz24.retrosnake2.helpers.FoodRandomizer;
import com.kononowicz24.retrosnake2.helpers.SnakeDirection;
import com.kononowicz24.retrosnake2.helpers.Steppable;
import com.kononowicz24.retrosnake2.layoutelems.Boundary;
import com.kononowicz24.retrosnake2.overlays.GameOverOverlay;
import com.kononowicz24.retrosnake2.playables.Food;
import com.kononowicz24.retrosnake2.playables.PlayerSnake;
import com.kononowicz24.retrosnake2.playables.Snake;
import com.kononowicz24.retrosnake2.playables.TimedPremiumFood;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by k24 on 08.08.18.
 */

public class SingleplayerScreen implements Screen {
    private RetroSnake rS;
    private Snake playersSnake;
    private ArrayList<Food> foods;
    private ArrayList<Snake> snakes;
    private FoodRandomizer foodRandomizer;
    private GameOverOverlay gameOverOverlay;
    private Boundary boundary;
    private int speed;
    private long millis;
    private long stepDuration;
     // na sztywno, znaleźć metodą prób i błedów

    public SingleplayerScreen(RetroSnake rS) {
        this.rS = rS;
        boundary = new Boundary(rS);
        foods = new ArrayList<Food>();
        snakes = new ArrayList<Snake>();
        millis = TimeUtils.millis();
        foodRandomizer = new FoodRandomizer(rS, snakes, foods, new Vector2(rS.getxDimm(), rS.getyDimm()));
        gameOverOverlay = new GameOverOverlay(rS);
        gameOverOverlay.hide();
        playersSnake = new PlayerSnake(rS, foods, foodRandomizer, gameOverOverlay);
        snakes.add(playersSnake);
        stepDuration = 180L;
        foodRandomizer.addFood(playersSnake);

    }

    public ArrayList<Snake> getSnakes() {
        return snakes;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (!gameOverOverlay.isVisible()) {

        } else {
            gameOverOverlay.touchDown(screenX, screenY, pointer, button);
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        if (!gameOverOverlay.isVisible()) {
            if (Math.abs(velocityX) > Math.abs(velocityY)) {
                if (velocityX > 10.0f) {
                    playersSnake.setDirection(SnakeDirection.RH);
                } else if (velocityX < -10.0f) {
                    playersSnake.setDirection(SnakeDirection.LH);
                }
            } else {
                if (velocityY > 10.0f) {
                    playersSnake.setDirection(SnakeDirection.DN);
                } else if (velocityY < -10.0f) {
                    playersSnake.setDirection(SnakeDirection.UP);
                }
            }
        } else {

        }
            return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }

    @Override
    public void dispose() {
        for (Snake snake: snakes) {
            snake.dispose();
        }
        for (Food food : foods) {
            food.dispose();
        }
        boundary.dispose();
        gameOverOverlay.dispose();
    }

    @Override
    public void render() {
        rS.getBatch().draw(rS.bgTexOn, 0, 0, rS.xR, rS.yR);
        for (Snake snake: snakes) {
            snake.render();
        }
        //playersSnake.render();
        for (Food food : foods) {
            food.render();
        }
        boundary.render();

        if (TimeUtils.timeSinceMillis(millis)>stepDuration) {//zamienione moze zadziala
            if (playersSnake.isDead() == false) {
                playersSnake.step();
                //for (Food food : foods) {
                for (Iterator<Food> iterator = foods.iterator(); iterator.hasNext(); ) {
                    Food food = iterator.next();
                    if (food instanceof Steppable) {
                        ((Steppable) food).step();
                        if (food instanceof TimedPremiumFood) {
                            if (((TimedPremiumFood) food).getTime() < 1) {
                                food.dispose();
                                iterator.remove();
                            }
                        }
                    }
                }
            }
            millis = TimeUtils.millis();
        }
        gameOverOverlay.render();
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public FoodRandomizer getFoodRandomizer() {
        return foodRandomizer;
    }

    @Override
    public void goBack() {

    }

    @Override
    public void goReturn() {

    }

    @Override
    public void goExit() {

    }

    @Override
    public void goRestart() {
        rS.changeScreen(MenuState.PLAYARENA);
    }

    @Override
    public void goHide() {

    }

    @Override
    public void goShare() {

    }

    @Override
    public void goSave() {

    }

    @Override
    public void goCancel() {

    }
}
