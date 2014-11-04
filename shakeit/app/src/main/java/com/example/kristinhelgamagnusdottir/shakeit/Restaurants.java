package com.example.kristinhelgamagnusdottir.shakeit;

/**
 * Höfundur: Sindri Snær Rúnarsson
 * Útgáfa: 1.0
 * Dagsetning: 28. október 2014
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

public class Restaurants extends Activity{

    ParseJSON parseJSON = new ParseJSON();
    final static String URL = "https://notendur.hi.is/ssr9/hugbunadarverkefni/veitingastadir.json";

    //Notkun:randomNumber(n);
    //Fyrir: n er heiltala
    //Eftir: Heiltala x sem er 0 <= x <= n
    public int randomNumber(int n) {
        Random randGen = new Random();
        return randGen.nextInt(n);
    }

    //Notkun: restaurantList(radioGenre);
    //Fyrir: radioGenre er strengur sem inniheldur genre sem notandi valdi
    //Eftir: Búið er að finna gildi úr JSON skrá sem uppfylti strenginn radioGenre
    public String [] restaurantList(String radioGenre) throws IOException, JSONException {
        HttpResponse r = parseJSON.httpResponse(URL);
        int status = r.getStatusLine().getStatusCode();
        if(status == 200) {
            HttpEntity e = r.getEntity();
            String data = EntityUtils.toString(e);
            JSONArray jsonArray = new JSONArray(data);
            boolean correct = false;

            String price;
            JSONObject randomObject = jsonArray.getJSONObject(randomNumber(121));
            while(!correct){
                randomObject = jsonArray.getJSONObject(randomNumber(121));
                price = randomObject.getString("price");
                if(price.contains(radioGenre)) {
                    correct = true;
                }
            }

            String [] jsonObject = new String[4];
            jsonObject[0] = randomObject.getString("name");
            jsonObject[1] = randomObject.getString("price")
                    .replace("[", "")
                    .replace("]", "")
                    .replace("\"", "")
                    .replace(",", ", ")
                    .replace("null", "");
            jsonObject[2] = randomObject.getString("number");

            return jsonObject;
        }
        else {
            return null;
        }

    }
}