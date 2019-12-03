package com.kononowicz24.letterssnake.playables;


import com.kononowicz24.letterssnake.LettersSnake;
import com.kononowicz24.letterssnake.helpers.FoodRandomizer;
import com.kononowicz24.letterssnake.helpers.SnakeDirection;
import com.kononowicz24.letterssnake.overlays.GameOverOverlay;

import java.util.ArrayList;

import static com.kononowicz24.letterssnake.helpers.SnakeDirection.DN;
import static com.kononowicz24.letterssnake.helpers.SnakeDirection.LH;
import static com.kononowicz24.letterssnake.helpers.SnakeDirection.RH;
import static com.kononowicz24.letterssnake.helpers.SnakeDirection.UP;

/**
 * Created by k24 on 02.09.18.
 */

public class PlayerSnake extends Snake {
    public PlayerSnake(LettersSnake lettersSnake, ArrayList<Food> foods, FoodRandomizer foodRandomizer, GameOverOverlay gameOverOverlay) {
        super(lettersSnake, foods, foodRandomizer, gameOverOverlay);
    }
    @Override
    public void dispose() {
        super.dispose();
    }
    @Override
    public void render() {
        super.render();
        lS.getFontManager().font1dX.draw(lS.getBatch(),String.valueOf(score), 1.1f*lS.dX, (int)((lS.getyDimm()+1.85)*lS.dY));
        if (this.size()>(0.06f*lS.getxDimm()*lS.getyDimm())) {
            int percent = (int)(100f*this.size()/(lS.getxDimm()*lS.getyDimm())); // 0.1 zeby nie bylo dokljone
            lS.getFontManager().font1dX.draw(lS.getBatch(), String.valueOf(percent), 7.1f*lS.dX, (int)((lS.getyDimm()+1.85)*lS.dY));
        }
    }
    @Override
    public void setDirection(SnakeDirection direction) {
        if (    ((direction == UP || direction == DN) && (this.direction==LH || this.direction==RH)) ||
                ((direction == LH || direction == RH) && (this.direction==UP || this.direction==DN))) {
            this.direction = direction;
        }
    }
}
