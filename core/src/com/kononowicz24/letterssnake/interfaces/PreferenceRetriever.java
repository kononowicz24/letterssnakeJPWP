package com.kononowicz24.letterssnake.interfaces;

/**
 * Created by k24 on 29.12.17.
 */

public interface PreferenceRetriever {
    public void setIntPreference(String token, int value);
    public int getIntPreference(String token);
    public void setStringPreference(PrefStringTokens token, String value);
    public String getStringPreference(PrefStringTokens prefStringTokens);
    public void hideVirtualButtons();
    public String getVersionName();
    public int getVersionCode();
}
