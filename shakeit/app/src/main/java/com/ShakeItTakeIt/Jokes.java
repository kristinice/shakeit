package com.ShakeItTakeIt;

/**
 * Höfundur: Sindri Snær Rúnarsson
 * Útgáfa: 1.0
 * Dagsetning: 25. nóvember 2014
 *
 * Þessi klasi ákveður hvað eigi að taka úr JSON skránni sem fæst af netinu og birta.
 * Klasinn notar sér opnun frá ParseJSON til þess að geta unnið með upplýsingarnar.
 *
 */

import android.app.Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Random;

public class Jokes extends Activity{

    ParseJSON parseJSON = new ParseJSON();
    final static String URL = "https://notendur.hi.is/ssr9/hugbunadarverkefni/jokes.json";
    //Notkun:randomNumber(n);
    //Fyrir: n er heiltala
    //Eftir: Heiltala x sem er 0 <= x <= n
    public int randomNumber(int n) {
        Random randGen = new Random();
        return randGen.nextInt(n);
    }

    //Notkun: JokesList();
    //Eftir: Búið er að finna gildi úr JSON skrá og skila strengjum.
    public String [] jokesList() throws IOException, JSONException {
        String data = parseJSON.BuffReader(URL);

        if(!data.equals("")) {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject randomObject;
            randomObject = jsonArray.getJSONObject(randomNumber(jsonArray.length()));
            String [] jsonObject = new String[4];
            jsonObject[0] = randomObject.getString("joke")
                    .replace("%%", "\n");
            return jsonObject;
        }
        else {
            return null;
        }

    }
}