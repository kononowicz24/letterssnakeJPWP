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
    public Timer(LettersSnake lS1) {
        lS=lS1;
    }
    public void render() {
        time+= (running?1.0f:0.0f) *Gdx.graphics.getDeltaTime();
        if (time>=0 && isRunning()) lS.getFontManager().font1dX.draw(lS.getBatch(),String.valueOf(String.format(Locale.UK, "%.2f", time)), 7.1f*lS.dX, (int)((lS.getyDimm()+1.85)*lS.dY));
    }
    public void stop() {
        running=false;
    }
    public void start() {
        running=true;
    }
    public void reset() {time=0;}
    public float getTime() {
        return time;
    }

    @Override
    public void dispose() {
        //menuButton.dispose();
        //restartButton.dispose();
    }

    public boolean isRunning() {
        return running;
    }
    public void finish() {
        finished=true;
    }
    public void setFalseStart(boolean fs) {
        falseStart =fs;}

}
