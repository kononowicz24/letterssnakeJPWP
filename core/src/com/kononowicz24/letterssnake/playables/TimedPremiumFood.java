package com.kononowicz24.letterssnake.playables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.kononowicz24.letterssnake.LettersSnake;
import com.kononowicz24.letterssnake.helpers.RandomAssetGenerator;
import com.kononowicz24.letterssnake.helpers.Steppable;

/**
 * Created by k24 on 02.09.18.
 */

public class TimedPremiumFood extends Food implements Steppable {
    private int time;
    private int remainingTime;
    public TimedPremiumFood(LettersSnake lS, int x, int y, Texture tex, int time) {
        super(lS, x, y, tex);
        this.time=time;
    }
    public TimedPremiumFood(LettersSnake lS, int x, int y, int time) {
        super(lS, x, y, new Texture(Gdx.files.internal(RandomAssetGenerator.choose(RandomAssetGenerator.generate("premiumfood", 3)))));
        this.time=time;
        this.setSound(Gdx.audio.newSound(Gdx.files.internal("sound3.ogg")));
    }

    @Override
    public void step() {
        time-=1;
        //if (time<1) dispose();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void render() {
        super.render();
        lS.getBatch().draw(this.getTexture(),(11)*lS.dX, (lS.getyDimm()+1)*lS.dY+2, lS.dX, lS.dY);//2 to offset żeby w bande nie wchodziło
        lS.getFontManager().font1dX.draw(lS.getBatch(),String.valueOf(time), 12.1f*lS.dX, (int)((lS.getyDimm()+1.85)*lS.dY));
    }

    public int getTime() {
        return time;
    }

    @Override
    public int value() {
        return (int)((Math.pow(4,0.1f*lS.getLevel())-1)*1.5f*(1/500f*(time-50)*(time-50)+4)+lS.getLevel()); //to nie magia, to matematyka
    }
}
