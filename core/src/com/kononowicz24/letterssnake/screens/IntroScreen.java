package com.kononowicz24.retrosnake2.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.kononowicz24.retrosnake2.RetroSnake;

/**
 * Created by k24 on 12.06.18.
 */

public class IntroScreen implements Screen {
    private RetroSnake rS;
    private float timer1, animTimer1;
    private Texture logoK24;
    private Texture logoRs2;
    private Texture snakeTexture;
    private Animation snakeAnimation;
    private final int FRAME_COLS = 3;
    private final int FRAME_ROWS = 6;
    private TextureRegion[] snakeFrames;
    public IntroScreen(RetroSnake rS) {
        this.rS = rS;
        logoK24 = new Texture(Gdx.files.internal("logok24.png"));
        logoRs2 = new Texture(Gdx.files.internal("logors2.png"));
        snakeTexture = new Texture(Gdx.files.internal("2anim.png"));
        TextureRegion[][] tmp = TextureRegion.split(snakeTexture,
                snakeTexture.getWidth() / FRAME_COLS,
                snakeTexture.getHeight() / FRAME_ROWS);

        // Place the regions into a 1D array in the correct order, starting from the top
        // left, going across first. The Animation constructor requires a 1D array.
        snakeFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                snakeFrames[index++] = tmp[i][j];
            }
        }
        snakeAnimation = new Animation(0.07f, snakeFrames);
        timer1 = 0.0f;
        animTimer1 = 0.0f;
    }
    @Override
    public void dispose() {
        logoK24.dispose();
        logoRs2.dispose();
        snakeTexture.dispose();
    }
    @Override
    public void render() {
        timer1+=Gdx.graphics.getDeltaTime();
        if (timer1<2.0f || (timer1>2.2f && timer1<2.4f) || (timer1>2.7f && timer1<2.85f)) {//flickerowanie tła przez chwile
            rS.getBatch().draw(rS.bgTexOff, 0, 0, rS.xR, rS.yR);
        } else {
            rS.getBatch().draw(rS.bgTexOn, 0, 0, rS.xR, rS.yR);
        }//
        if (timer1<5.00f) {
            rS.getBatch().draw(logoK24, 0, 0, rS.xR, rS.yR);
        } else {
            rS.getBatch().draw(logoRs2, 0, 0, rS.xR, rS.yR);
            TextureRegion currentFrame = snakeAnimation.getKeyFrame(animTimer1, true);
            rS.getBatch().draw(currentFrame, rS.xR/3.0f, rS.yR/3.0f, rS.xR/3.0f, rS.xR/3.0f);
        }
        if (timer1>10.0f) {
            rS.changeScreen(MenuState.MAIN_MENU);
        }
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
