package com.example.kristinhelgamagnusdottir.shakeit;

/**
 * Höfundur: Sigurbjörn Jónsson
 * Útgáfa: 1.1
 * Dagsetning: 16. október 2014
 *
 * Þessir klasar eru hönnuðir fyrir því hvað skal gera við hverja JSON skrá sem fæst af netinu.
 * ShowJSON notar klasana eftir að GlobalVariable hefur sagt hvaða klasi eigi að keyrast ef að
 * notandi hefur valið flokk. Movies nær í JSON af heimasvæði HÍ.
 */

import android.app.Activity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Random;

public class Movies extends Activity{

    ParseJSON parseJSON = new ParseJSON();
    final static String URL = "https://notendur.hi.is/~sij65/Hugbunadarverkefni%201/movies.json";

    //Notkun:randomNumber(n);
    //Fyrir: n er heiltala.
    //Eftir: Heiltala x sem er 0 <= x <= n
    public int randomNumber(int n) {
        Random randGen = new Random();
        return randGen.nextInt(n);
    }

    //Notkun: movieList(radioGenre);
    //Fyrir: radioGenre er strengur sem inniheldur genre sem notandi valdi
    //Eftir: Búið er að finna gildi úr JSON skrá sem uppfylti strenginn radioGenre
    public String [] movieList(String radioGenre) throws IOException, JSONException {

        HttpResponse r = parseJSON.httpResponse(URL);
        int status = r.getStatusLine().getStatusCode();

        if(status == 200) {
            HttpEntity e = r.getEntity();
            String data = EntityUtils.toString(e);
            JSONArray jsonArray = new JSONArray(data);
            boolean correct = false;
            String genres;
            JSONObject randomObject = jsonArray.getJSONObject(randomNumber(100));
            while(!correct){
                randomObject = jsonArray.getJSONObject(randomNumber(100));
                genres = randomObject.getString("genres");
                if(genres.contains(radioGenre)) {
                    correct = true;
                }
            }

            String [] jsonObject = new String[4];
            jsonObject[0] = randomObject.getString("title");
            jsonObject[1] = randomObject.getString("rank");
            jsonObject[2] = randomObject.getString("genres");
            String language = randomObject.getString("languages");
            String country = randomObject.getString("country");
            String runtime = randomObject.getString("runtime");
            jsonObject[3] = language +" / " + country + " / " + runtime;
            return jsonObject;
        }
        else {
            return null;
        }

    }
}
