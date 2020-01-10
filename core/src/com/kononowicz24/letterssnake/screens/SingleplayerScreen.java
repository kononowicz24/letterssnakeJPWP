package com.kononowicz24.letterssnake.screens;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.kononowicz24.letterssnake.LettersSnake;
import com.kononowicz24.letterssnake.helpers.FoodRandomizer;
import com.kononowicz24.letterssnake.helpers.LettersRandomizer;
import com.kononowicz24.letterssnake.helpers.SnakeDirection;
import com.kononowicz24.letterssnake.helpers.Steppable;
import com.kononowicz24.letterssnake.layoutelems.Boundary;
import com.kononowicz24.letterssnake.overlays.GameOverOverlay;
import com.kononowicz24.letterssnake.playables.Food;
import com.kononowicz24.letterssnake.playables.PlayerSnake;
import com.kononowicz24.letterssnake.playables.Snake;
import com.kononowicz24.letterssnake.playables.TimedPremiumFood;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by k24 on 08.08.18.
 */

public class SingleplayerScreen implements Screen {
    private LettersSnake lS;
    private Snake playersSnake;
    private ArrayList<Food> foods;
    private ArrayList<Snake> snakes;
    private LettersRandomizer lettersRandomizer;
    private GameOverOverlay gameOverOverlay;
    private Boundary boundary;
    private int speed;
    private long millis;
    private long stepDuration;
     // na sztywno, znaleźć metodą prób i błedów

    public SingleplayerScreen(LettersSnake lS) {
        this.lS = lS;
        boundary = new Boundary(lS);
        foods = new ArrayList<Food>();
        snakes = new ArrayList<Snake>();
        millis = TimeUtils.millis();
        lettersRandomizer = new LettersRandomizer(lS, snakes, foods, new Vector2(lS.getxDimm(), lS.getyDimm()));
        gameOverOverlay = new GameOverOverlay(lS);
        gameOverOverlay.hide();
        playersSnake = new PlayerSnake(lS, foods, lettersRandomizer, gameOverOverlay);
        snakes.add(playersSnake);
        stepDuration = 1000L/lS.getPreferenceRetriever().getIntPreference("LSJPWP_LEVEL"); //
        lettersRandomizer.addFood(playersSnake);
        lettersRandomizer.addFoodRandomly(playersSnake);
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
        lS.getBatch().draw(lS.bgTexOn, 0, 0, lS.xR, lS.yR);
        for (Snake snake: snakes) {
            snake.render();
        }
        //playersSnake.render();
        for (Food food : foods) {
            food.render();
        }
        boundary.render();
        lettersRandomizer.render();

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

    public LettersRandomizer getLettersRandomizer() {
        return lettersRandomizer;
    }

    @Override
    public void goBack() {
        if (playersSnake.isDead()) lS.changeScreen(MenuState.PLAYARENA);
        else
            lS.changeScreen(MenuState.MAIN_MENU);
    }

    @Override
    public void goReturn() {

    }

    @Override
    public void goExit() {

    }

    @Override
    public void goRestart() {
        lS.changeScreen(MenuState.PLAYARENA);
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

    public long getStepDuration() {
        return stepDuration;
    }

    public void setStepDuration(long stepDuration) {
        this.stepDuration = stepDuration;
    }
}
