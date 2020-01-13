package com.kononowicz24.letterssnake.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Disposable;
import com.kononowicz24.letterssnake.LettersSnake;

import java.util.Locale;

/**
 * Created by k24 on 03.01.18.
 */

public class Timer implements Disposable, Renderable {
    private LettersSnake lS;
    private float time=0;
    private boolean running=false;
    private boolean finished=false;
    private boolean falseStart =false;

    /**
     * Creates timer instance
     * @param lS1 main game object
     */
    public Timer(LettersSnake lS1) {
        lS=lS1;
    }

    /**
     * Renders time on the top of game screen and increases time every frame
     */
    public void render() {
        time+= (running?1.0f:0.0f) *Gdx.graphics.getDeltaTime();
        if (time>=0 && isRunning()) lS.getFontManager().font1dX.draw(lS.getBatch(),String.valueOf(String.format(Locale.UK, "%.2f", time)), 7.1f*lS.dX, (int)((lS.getyDimm()+1.85)*lS.dY));
    }

    /**
     * Stops time counting
     */
    public void stop() {
        running=false;
    }

    /**
     * Starts time counting
     */
    public void start() {
        running=true;
    }

    /**
     * Sets timer to 0s
     */
    public void reset() {time=0;}
    public float getTime() {
        return time;
    }

    /**
     * Called on exit, required by LibGDX
     */
    @Override
    public void dispose() {
        //menuButton.dispose();
        //restartButton.dispose();
    }

    /**
     * Tells if the timer is running
     * @return true if running
     */
    public boolean isRunning() {
        return running;
    }
    public void finish() {
        finished=true;
    }
    public void setFalseStart(boolean fs) {
        falseStart =fs;}

}
