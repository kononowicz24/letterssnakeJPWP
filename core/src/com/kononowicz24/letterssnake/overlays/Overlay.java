package com.kononowicz24.letterssnake.overlays;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.kononowicz24.letterssnake.LettersSnake;
import com.kononowicz24.letterssnake.helpers.Disposable;
import com.kononowicz24.letterssnake.helpers.Hideable;
import com.kononowicz24.letterssnake.helpers.Renderable;


/**
 * Created by k24 on 20.01.19.
 */

public abstract class Overlay implements Renderable, Disposable, Hideable, InputProcessor, GestureListener {
    protected boolean visible;
    protected boolean semiVisible;
    protected LettersSnake lS;
    public Overlay(LettersSnake lS) {
        this.lS = lS;
        visible = false;
        semiVisible = false;
    }

    public boolean isVisible() {
        return visible;
    }

    public boolean isSemiVisible() {
        return semiVisible;
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
    public void dispose() {
        hide();
    }

    @Override
    public void hide() {
        visible = false;
    }

    @Override
    public void show() {
        visible = true;
    }

    @Override
    public void semiVisible() {

    }

    @Override
    public void render() {
        ;
    }
}
