package com.kononowicz24.retrosnake2.layoutelems;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.kononowicz24.retrosnake2.RetroSnake;
import com.kononowicz24.retrosnake2.helpers.*;
import com.kononowicz24.retrosnake2.screens.MenuState;


/**
 * Created by k24 on 13.06.18.
 */

public class MenuButton extends Sprite implements Disposable, Renderable {
    private RetroSnake rS;
    private MenuState state;
    private Rectangle area;
    public MenuButton(RetroSnake rS, String texture, MenuState state, float x, float y) {
        super(new Texture(texture));
        this.rS=rS;
        this.state=state;
        this.setX(x);
        this.setY(y);
        this.setOrigin(0,0);
        this.setScale(8*rS.dX/this.getTexture().getWidth(), 8*rS.dY/this.getTexture().getHeight());
        area = new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
    @Override
    public void dispose() {
        this.getTexture().dispose();
    }

    @Override
    public void render() {
        this.draw(rS.getBatch());
    }
    public void touch(float x, float y) {
        if (area.contains(x, rS.yR-y)) {
            switch (state) {
                case PLAYARENA: {
                    rS.changeScreen(MenuState.PLAYARENA);
                    break;
                }
                case PLAYARENA_MULTI: {
                    rS.changeScreen(MenuState.PLAYARENA_MULTI);
                    break;
                }
                case POP_ACHIEV: {
                    break;
                }
                case POP_BOARDS: {
                    break;
                }
                case ABOUT: {
                    rS.changeScreen(MenuState.ABOUT);
                    break;
                }
                case SETTINGS: {
                    rS.changeScreen(MenuState.SETTINGS);
                }
            }
        }
    }
}
