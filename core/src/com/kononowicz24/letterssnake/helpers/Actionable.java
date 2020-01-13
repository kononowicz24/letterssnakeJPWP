package com.kononowicz24.letterssnake.helpers;

/**
 * Created by k24 on 15.11.19.
 */

public interface Actionable {
    /**
     * Allows screens to take go back action
     */
    void goBack();
    void goReturn();
    void goExit();
    void goRestart();
    void goHide();
    void goShare();
    void goSave();
    void goCancel();
}
