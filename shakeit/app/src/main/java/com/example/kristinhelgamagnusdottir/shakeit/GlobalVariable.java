package com.example.kristinhelgamagnusdottir.shakeit;

/**
 * Höfundur: Sigurbjörn Jónsson
 * Útgáfa: 1.1
 * Dagsetning: 16. október 2014
 * Uppfærsla: 4.nóvember 2014
 *
 * Klasinn GlobalVariable gefur til kynna hvaða flokkur (kvikmyndir, hanastél eða brandarar)
 * hefur verið valinn. Hann segir einnig hvaða radio takkar hafa verið valdir.
 */

import android.app.Application;

public class GlobalVariable extends Application {
    private int activityNumber;
    private String radioValue;
    private String [] checkboxValues = new String[6];

    //Notkun:getActivityNumber();
    //Eftir: Skilar heiltölunni activityNumber
    public int getActivityNumber() {
        return activityNumber;
    }

    //Notkun: setActivityNumber(activityNumber);
    //Fyrir: activityNumber er heiltala.
    //Eftir: Breytt hefur verið gildinu á activityNumber.
    public void setActivityNumber(int activityNumber){
        this.activityNumber = activityNumber;
    }

    //Notkun:getRadioValue();
    //Eftir: Skilar strengnum radioValue
    public String getRadioValue() {
        return radioValue;
    }

    //Notkun: setRadioValue(radioValue);
    //Fyrir: radioValue er strengur.
    //Eftir: Breytt hefur verið gildinu á radioValue.
    public void setRadioValue(String radioValue){
        this.radioValue = radioValue;
    }

    //Notkun: setRadioValue(radioValue);
    //Fyrir: radioValue er strengur.
    //Eftir: Breytt hefur verið gildinu á radioValue.
    public void setCheckboxValues(String checkboxValue, int arrayNumber){
        this.checkboxValues[arrayNumber] = checkboxValue;
    }

    //Notkun:getRadioValue();
    //Eftir: Skilar strengnum radioValue
    public String [] getCheckboxValues() {
        return checkboxValues;
    }



}
