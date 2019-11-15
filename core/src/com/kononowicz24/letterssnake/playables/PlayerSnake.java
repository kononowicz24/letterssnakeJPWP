package com.kononowicz24.retrosnake2.playables;


import com.kononowicz24.retrosnake2.RetroSnake;
import com.kononowicz24.retrosnake2.helpers.FoodRandomizer;
import com.kononowicz24.retrosnake2.helpers.SnakeDirection;
import com.kononowicz24.retrosnake2.overlays.GameOverOverlay;

import java.util.ArrayList;

import static com.kononowicz24.retrosnake2.helpers.SnakeDirection.DN;
import static com.kononowicz24.retrosnake2.helpers.SnakeDirection.LH;
import static com.kononowicz24.retrosnake2.helpers.SnakeDirection.RH;
import static com.kononowicz24.retrosnake2.helpers.SnakeDirection.UP;

/**
 * Created by k24 on 02.09.18.
 */

public class PlayerSnake extends Snake {
    public PlayerSnake(RetroSnake retroSnake, ArrayList<Food> foods, FoodRandomizer foodRandomizer, GameOverOverlay gameOverOverlay) {
        super(retroSnake, foods, foodRandomizer, gameOverOverlay);
    }
    @Override
    public void dispose() {
        super.dispose();
    }
    @Override
    public void render() {
        super.render();
        rS.getFontManager().font1dX.draw(rS.getBatch(),String.valueOf(score), 1.1f*rS.dX, (int)((rS.getyDimm()+1.85)*rS.dY));
        if (this.size()>(0.06f*rS.getxDimm()*rS.getyDimm())) {
            int percent = (int)(100f*this.size()/(rS.getxDimm()*rS.getyDimm())); // 0.1 zeby nie bylo dokljone
            rS.getFontManager().font1dX.draw(rS.getBatch(), String.valueOf(percent), 7.1f*rS.dX, (int)((rS.getyDimm()+1.85)*rS.dY));
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
