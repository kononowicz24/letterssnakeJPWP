package com.kononowicz24.letterssnake.interfaces;

/**
 * Created by k24 on 02.02.18.
 */

public interface PlayServices {
    public void signIn();
    public void signOut();
    public void rateGame();
    public void unlockAchievement(Achievement achievement);
    public void submitScore(long highScore);
    public void submitTime(float Time);
    public void showAchievements();
    public void showScore();
    public boolean isSignedIn();
}
