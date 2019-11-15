package com.kononowicz24.retrosnake2.layoutelems;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.kononowicz24.retrosnake2.RetroSnake;
import com.kononowicz24.retrosnake2.helpers.Disposable;
import com.kononowicz24.retrosnake2.helpers.Renderable;

/**
 * Created by k24 on 02.09.18.
 */

public class Boundary implements Renderable, Disposable {
    private Texture boundary;
    private RetroSnake rS;
    public Boundary(RetroSnake rS) {
        boundary = new Texture("bound.png");
        this.rS = rS;
    }

    @Override
    public void dispose() {
        boundary.dispose();
    }

    @Override
    public void render() {
        Vector2 p1 = new Vector2(rS.dX,rS.dY);
        Vector2 p2 = new Vector2(rS.dX*(rS.getxDimm()+1),rS.dY*(rS.getyDimm()+1));
        rS.getBatch().draw(boundary, p1.x, p1.y, p2.x-p1.x, 3);
        rS.getBatch().draw(boundary, p1.x, p1.y, 3, p2.y-p1.y);
        rS.getBatch().draw(boundary, p2.x, p2.y, p1.x-p2.x, -3);
        rS.getBatch().draw(boundary, p2.x, p2.y, -3, p1.y-p2.y);

    }
}
