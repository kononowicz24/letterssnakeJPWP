package com.kononowicz24.letterssnake.layoutelems;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.kononowicz24.letterssnake.LettersSnake;
import com.kononowicz24.letterssnake.helpers.*;
import com.kononowicz24.letterssnake.screens.MenuState;


/**
 * Created by k24 on 13.06.18.
 */

public class MenuButton extends Sprite implements Disposable, Renderable {
    private LettersSnake lS;
    private MenuState state;
    private Rectangle area;
    public MenuButton(LettersSnake lS, String texture, MenuState state, float x, float y) {
        super(new Texture(texture));
        this.lS=lS;
        this.state=state;
        this.setX(x);
        this.setY(y);
        this.setOrigin(0,0);
        this.setScale(8*lS.dX/this.getTexture().getWidth(), 8*lS.dY/this.getTexture().getHeight());
        area = new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
    @Override
    public void dispose() {
        this.getTexture().dispose();
    }

    @Override
    public void render() {
        this.draw(lS.getBatch());
    }
    public void touch(float x, float y) {
        if (area.contains(x, lS.yR-y)) {
            switch (state) {
                case PLAYARENA: {
                    lS.changeScreen(MenuState.PLAYARENA);
                    break;
                }
                case PLAYARENA_MULTI: {
                    lS.changeScreen(MenuState.PLAYARENA_MULTI);
                    break;
                }
                case POP_ACHIEV: {
                    lS.getPlayServices().showAchievements();
                    break;
                }
                case POP_BOARDS: {
                    lS.getPlayServices().showScore();
                    break;
                }
                case ABOUT: {
                    lS.changeScreen(MenuState.ABOUT);
                    break;
                }
                case SETTINGS: {
                    lS.changeScreen(MenuState.SETTINGS);
                }
            }
        }
    }
}
