package com.example.kristinhelgamagnusdottir.shakeit;

/**
 * Höfundur: Sigurbjörn Jónsson
 * Útgáfa: 1.0
 * Dagsetning: 16. október 2014
 *
 * Klasinn GlobalVariable gefur til kynna hvaða flokkur (kvikmyndir, hanastél eða brandarar)
 * hefur verið valinn.
 */

import android.app.Application;

/**
 * Created by Lenovo on 15.10.2014.
 */
public class GlobalVariable extends Application {
    private int activityNumber;
    private String radioValue;

    public int getActivityNumber() {
        return activityNumber;
    }
    public void setActivityNumber(int activityNumber){
        this.activityNumber = activityNumber;
    }

    public String getRadioValue() {
        return radioValue;
    }
    public void setRadioValue(String radioValue){
        this.radioValue = radioValue;
    }
}
