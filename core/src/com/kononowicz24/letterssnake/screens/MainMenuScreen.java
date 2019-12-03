package com.kononowicz24.letterssnake.screens;

import com.badlogic.gdx.math.Vector2;
import com.kononowicz24.letterssnake.LettersSnake;
import com.kononowicz24.letterssnake.layoutelems.MenuButton;

/**
 * Created by k24 on 12.06.18.
 */

public class MainMenuScreen implements Screen {
    private LettersSnake lS;
    private MenuButton playButton;
    private MenuButton multiButton;
    private MenuButton achievButton;
    private MenuButton boardsButton;
    private MenuButton settButton;
    private MenuButton aboutButton;

    public MainMenuScreen(LettersSnake lS) {
        this.lS = lS;
        playButton = new MenuButton(lS, "menu_play.png", MenuState.PLAYARENA, 3*lS.dX, 22*lS.dY);
        multiButton = new MenuButton(lS, "menu_multi.png", MenuState.PLAYARENA_MULTI, 13*lS.dX, 22*lS.dY);
        achievButton = new MenuButton(lS, "menu_achiev.png", MenuState.POP_ACHIEV, 3*lS.dX, 12*lS.dY);
        boardsButton = new MenuButton(lS, "menu_hisc.png", MenuState.POP_BOARDS, 13*lS.dX, 12*lS.dY);
        settButton = new MenuButton(lS, "menu_sett.png", MenuState.SETTINGS, 3*lS.dX, 2*lS.dY);
        aboutButton = new MenuButton(lS, "menu_about.png", MenuState.ABOUT, 13*lS.dX, 2*lS.dY);
    }

    @Override
    public void dispose() {
        playButton.dispose();
        multiButton.dispose();
        aboutButton.dispose();
        achievButton.dispose();
        settButton.dispose();
        boardsButton.dispose();
    }

    @Override
    public void render() {
        lS.getBatch().draw(lS.bgTexOn, 0,0,lS.xR, lS.yR);
        playButton.draw(lS.getBatch());
        multiButton.draw(lS.getBatch());
        achievButton.draw(lS.getBatch());
        boardsButton.draw(lS.getBatch());
        settButton.draw(lS.getBatch());
        aboutButton.draw(lS.getBatch());
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
        playButton.touch(screenX, screenY);
        multiButton.touch(screenX, screenY);
        achievButton.touch(screenX, screenY);
        boardsButton.touch(screenX, screenY);
        settButton.touch(screenX,screenY);
        aboutButton.touch(screenX,screenY);
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
