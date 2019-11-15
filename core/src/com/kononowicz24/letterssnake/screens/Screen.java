package com.kononowicz24.retrosnake2.screens;

import com.badlogic.gdx.InputProcessor;

import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.kononowicz24.retrosnake2.helpers.Actionable;
import com.kononowicz24.retrosnake2.helpers.Disposable;
import com.kononowicz24.retrosnake2.helpers.Renderable;

/**
 * Created by k24 on 12.06.18.
 */

public interface Screen extends Renderable, Disposable, InputProcessor, GestureListener, Actionable {
}
