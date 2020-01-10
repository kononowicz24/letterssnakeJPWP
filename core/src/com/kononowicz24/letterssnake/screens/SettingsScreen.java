package com.kononowicz24.letterssnake.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.kononowicz24.letterssnake.LettersSnake;

/**
 * Created by k24 on 12.06.18.
 */

public class SettingsScreen implements Screen {
    private LettersSnake lS;
    private Texture texture;
    private Rectangle area;
    public SettingsScreen(LettersSnake lS) {
        this.lS = lS;
        texture = new Texture(Gdx.files.internal("bound.png"));
        area = new Rectangle(lS.dX*2, lS.dY*19, (lS.getxDimm()-2)*lS.dX, 5*lS.dY);
    }
    @Override
    public void dispose() {
        texture.dispose();
    }
    @Override
    public void render() {
        lS.getBatch().draw(lS.bgTexOn, 0, 0, lS.xR, lS.yR);
        lS.getBatch().draw(texture, lS.dX*2, lS.dY*7, (lS.getxDimm()-2)*lS.dX, 5*lS.dY );
        lS.getFontManager().font2dX_inv.draw(lS.getBatch(), "Settings", lS.dX*6, lS.dY*10);

        lS.getBatch().draw(texture, lS.dX*2, lS.dY*19, (lS.getxDimm()-2)*lS.dX, 5*lS.dY );
        lS.getFontManager().font2dX_inv.draw(lS.getBatch(), "Level:"+lS.getPreferenceRetriever().getIntPreference("LSJPWP_LEVEL"), lS.dX*6, lS.dY*22);
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
        if (area.contains(x, lS.yR-y)) {
            int level = lS.getPreferenceRetriever().getIntPreference("LSJPWP_LEVEL");
            level++;
            if (level>10) level=1;
            lS.getPreferenceRetriever().setIntPreference("LSJPWP_LEVEL", level);
        }
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
