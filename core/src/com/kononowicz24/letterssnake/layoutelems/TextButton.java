package com.kononowicz24.letterssnake.layoutelems;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.kononowicz24.letterssnake.LettersSnake;
import com.kononowicz24.letterssnake.helpers.Action;
import com.kononowicz24.letterssnake.helpers.Disposable;
import com.kononowicz24.letterssnake.helpers.Renderable;
import com.kononowicz24.letterssnake.screens.Screen;


/**
 * Created by k24 on 13.06.18.
 */

public class TextButton extends Sprite implements Disposable, Renderable {
    private LettersSnake lS;
    private Action action;
    private Rectangle area;
    private Screen activeScreen;
    public TextButton(LettersSnake lS, String napis, Action action, float x, float y, Screen activeScreen) {
        super(new Texture("boundary.png"));
        this.lS=lS;
        this.action=action;
        this.activeScreen = activeScreen;
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
            switch (action) {
                case BACK:
            }
        }
    }
}
