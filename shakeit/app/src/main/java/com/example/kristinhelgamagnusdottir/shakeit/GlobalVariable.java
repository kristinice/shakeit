package com.example.kristinhelgamagnusdottir.shakeit;

import android.app.Application;

/**
 * Created by Lenovo on 15.10.2014.
 */
public class GlobalVariable extends Application {
    private int activityNumber;
    public int getActivityNumber() {
        return activityNumber;
    }
    public void setActivityNumber(int activityNumber){
        this.activityNumber = activityNumber;
    }
}
