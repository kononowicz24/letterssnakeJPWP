package com.kononowicz24.letterssnake.layoutelems;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.kononowicz24.letterssnake.LettersSnake;
import com.kononowicz24.letterssnake.helpers.Disposable;
import com.kononowicz24.letterssnake.helpers.Renderable;

/**
 * Created by k24 on 02.09.18.
 */

public class Boundary implements Renderable, Disposable {
    private Texture boundary;
    private LettersSnake lS;

    /**
     * Creates black box instance used to create boundary
     * @param lS main game object
     */
    public Boundary(LettersSnake lS) {
        boundary = new Texture("bound.png");
        this.lS = lS;
    }

    /**
     * called on exit, required by LibGDX
     */
    @Override
    public void dispose() {
        boundary.dispose();
    }

    /**
     * Renders a box sized by p1,p2
     */
    @Override
    public void render() {
        Vector2 p1 = new Vector2(lS.dX,lS.dY);
        Vector2 p2 = new Vector2(lS.dX*(lS.getxDimm()+1),lS.dY*(lS.getyDimm()+1));
        lS.getBatch().draw(boundary, p1.x, p1.y, p2.x-p1.x, 3);
        lS.getBatch().draw(boundary, p1.x, p1.y, 3, p2.y-p1.y);
        lS.getBatch().draw(boundary, p2.x, p2.y, p1.x-p2.x, -3);
        lS.getBatch().draw(boundary, p2.x, p2.y, -3, p1.y-p2.y);

    }
}
