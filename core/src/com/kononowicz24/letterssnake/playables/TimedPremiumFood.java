package com.kononowicz24.retrosnake2.playables;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.kononowicz24.retrosnake2.RetroSnake;
import com.kononowicz24.retrosnake2.helpers.RandomAssetGenerator;
import com.kononowicz24.retrosnake2.helpers.Steppable;

/**
 * Created by k24 on 02.09.18.
 */

public class TimedPremiumFood extends Food implements Steppable {
    private int time;
    private int remainingTime;
    public TimedPremiumFood(RetroSnake rS, int x, int y, Texture tex, int time) {
        super(rS, x, y, tex);
        this.time=time;
    }
    public TimedPremiumFood(RetroSnake rS, int x, int y, int time) {
        super(rS, x, y, new Texture(Gdx.files.internal(RandomAssetGenerator.choose(RandomAssetGenerator.generate("premiumfood", 3)))));
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
        rS.getBatch().draw(this.getTexture(),(11)*rS.dX, (rS.getyDimm()+1)*rS.dY+2, rS.dX, rS.dY);//2 to offset żeby w bande nie wchodziło
        rS.getFontManager().font1dX.draw(rS.getBatch(),String.valueOf(time), 12.1f*rS.dX, (int)((rS.getyDimm()+1.85)*rS.dY));
    }

    public int getTime() {
        return time;
    }

    @Override
    public int value() {
        return (int)((Math.pow(4,0.1f*rS.getLevel())-1)*1.5f*(1/500f*(time-50)*(time-50)+4)+rS.getLevel()); //to nie magia, to matematyka
    }
}
