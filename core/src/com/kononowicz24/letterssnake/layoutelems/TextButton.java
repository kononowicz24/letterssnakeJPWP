package com.kononowicz24.retrosnake2.layoutelems;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.kononowicz24.retrosnake2.RetroSnake;
import com.kononowicz24.retrosnake2.helpers.Action;
import com.kononowicz24.retrosnake2.helpers.Disposable;
import com.kononowicz24.retrosnake2.helpers.Renderable;
import com.kononowicz24.retrosnake2.screens.MenuState;
import com.kononowicz24.retrosnake2.screens.Screen;


/**
 * Created by k24 on 13.06.18.
 */

public class TextButton extends Sprite implements Disposable, Renderable {
    private RetroSnake rS;
    private Action action;
    private Rectangle area;
    private Screen activeScreen;
    public TextButton(RetroSnake rS, String napis, Action action, float x, float y, Screen activeScreen) {
        super(new Texture("boundary.png"));
        this.rS=rS;
        this.action=action;
        this.activeScreen = activeScreen;
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
            switch (action) {
                case BACK:
            }
        }
    }
}
