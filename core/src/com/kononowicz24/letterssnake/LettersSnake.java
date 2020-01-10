package com.kononowicz24.letterssnake;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.kononowicz24.letterssnake.helpers.FontManager;
import com.kononowicz24.letterssnake.interfaces.PlayServices;
import com.kononowicz24.letterssnake.interfaces.PreferenceRetriever;
import com.kononowicz24.letterssnake.screens.IntroScreen;
import com.kononowicz24.letterssnake.screens.MainMenuScreen;
import com.kononowicz24.letterssnake.screens.Screen;
import com.kononowicz24.letterssnake.screens.MenuState;
import com.kononowicz24.letterssnake.screens.SingleplayerScreen;


public class LettersSnake extends ApplicationAdapter {
	private SpriteBatch batch;
    private FontManager fontManager;
    private PreferenceRetriever preferenceRetriever;
    private PlayServices playServices;
	public Texture bgTexOff;
	public Texture bgTexOn;
	public Texture emptyTexture;
	public int xR;
	public int yR;
    public float dX;
    public float dY;
	private int xDimm=22, yDimm=39; //todo, moze bedzie mozna zrobic zmienne wymiary planszy, nie wiem
	private Screen screen;

    public LettersSnake(PreferenceRetriever preferenceRetriever, PlayServices playServices) {
        this.preferenceRetriever = preferenceRetriever;
        this.playServices = playServices;
    }

    public void changeScreen(MenuState state) {
        switch (state) {
            case INTRO: {
                screen.dispose();
                screen = new IntroScreen(this);
                break;
            }
            case MAIN_MENU: {
                screen.dispose();
                screen = new MainMenuScreen(this);
                break;
            }
			case PLAYARENA: {
				screen.dispose();
				screen = new SingleplayerScreen(this);
				break;
			}
        }
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(screen);
        inputMultiplexer.addProcessor(new GestureDetector(screen));
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

	@Override
	public void create () {
		xR=Gdx.graphics.getWidth();
		yR=Gdx.graphics.getHeight();
        dX=xR*20/480f;
        dY=yR*20/840f;
		batch = new SpriteBatch();
        fontManager = new FontManager(this);
		bgTexOff = new Texture("background_dim.png");
		bgTexOn = new Texture("background_light.png");
		emptyTexture = new Texture("food.png");
        screen = new MainMenuScreen(this);
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(screen);
        inputMultiplexer.addProcessor(new GestureDetector(screen));
        Gdx.input.setInputProcessor(inputMultiplexer);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		screen.render();
		batch.end();
	}
	
	@Override
	public void dispose () {
        screen.dispose();
		batch.dispose();
		bgTexOff.dispose();
		bgTexOn.dispose();
		emptyTexture.dispose();
	}

    public int getxDimm() {
        return xDimm;
    }

    public int getyDimm() {
        return yDimm;
    }

    public SpriteBatch getBatch() {
		return batch;
	}
	public Texture getEmptyTexture() {
		return emptyTexture;
	}
    public FontManager getFontManager() {
        return fontManager;
    }
    public int getLevel() {
        //return 7;
        return getPreferenceRetriever().getIntPreference("LSJPWP_LEVEL");
    }

    public PreferenceRetriever getPreferenceRetriever() {
        return preferenceRetriever;
    }

    public PlayServices getPlayServices() {
        return playServices;
    }
}
