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

    /**
     * Creates menu button
     * @param lS main game object
     * @param texture texture of the button
     * @param state action to go to after pressing
     * @param x x where to render
     * @param y y where to render
     */
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

    /**
     * Required by LibGDX
     */
    @Override
    public void dispose() {
        this.getTexture().dispose();
    }

    /**
     * renders button on screen
     */
    @Override
    public void render() {
        this.draw(lS.getBatch());
    }

    /**
     * Tells what to do on touching the button
     * @param x x where touched the screen
     * @param y y where touched the screen
     *
     */
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
