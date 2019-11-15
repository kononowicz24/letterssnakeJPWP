package com.kononowicz24.retrosnake2.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.kononowicz24.retrosnake2.RetroSnake;
import com.kononowicz24.retrosnake2.layoutelems.MenuButton;

/**
 * Created by k24 on 12.06.18.
 */

public class MainMenuScreen implements Screen {
    private RetroSnake rS;
    private MenuButton playButton;
    private MenuButton multiButton;
    private MenuButton achievButton;
    private MenuButton boardsButton;
    private MenuButton settButton;
    private MenuButton aboutButton;

    public MainMenuScreen(RetroSnake rS) {
        this.rS = rS;
        playButton = new MenuButton(rS, "menu_play.png", MenuState.PLAYARENA, 3*rS.dX, 22*rS.dY);
        multiButton = new MenuButton(rS, "menu_multi.png", MenuState.PLAYARENA_MULTI, 13*rS.dX, 22*rS.dY);
        achievButton = new MenuButton(rS, "menu_achiev.png", MenuState.POP_ACHIEV, 3*rS.dX, 12*rS.dY);
        boardsButton = new MenuButton(rS, "menu_hisc.png", MenuState.POP_BOARDS, 13*rS.dX, 12*rS.dY);
        settButton = new MenuButton(rS, "menu_sett.png", MenuState.SETTINGS, 3*rS.dX, 2*rS.dY);
        aboutButton = new MenuButton(rS, "menu_about.png", MenuState.ABOUT, 13*rS.dX, 2*rS.dY);
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
        rS.getBatch().draw(rS.bgTexOn, 0,0,rS.xR, rS.yR);
        playButton.draw(rS.getBatch());
        multiButton.draw(rS.getBatch());
        achievButton.draw(rS.getBatch());
        boardsButton.draw(rS.getBatch());
        settButton.draw(rS.getBatch());
        aboutButton.draw(rS.getBatch());
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
