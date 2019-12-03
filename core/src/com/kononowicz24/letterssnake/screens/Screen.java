package com.kononowicz24.letterssnake.screens;

import com.badlogic.gdx.InputProcessor;

import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.kononowicz24.letterssnake.helpers.Actionable;
import com.kononowicz24.letterssnake.helpers.Disposable;
import com.kononowicz24.letterssnake.helpers.Renderable;

/**
 * Created by k24 on 12.06.18.
 */

public interface Screen extends Renderable, Disposable, InputProcessor, GestureListener, Actionable {
}
