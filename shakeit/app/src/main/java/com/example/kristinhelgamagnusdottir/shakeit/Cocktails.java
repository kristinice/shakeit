package com.example.kristinhelgamagnusdottir.shakeit;

/**
 * Höfundur: Sigurbjörn Jónsson
 * Útgáfa: 1.0
 * Dagsetning: 16. október 2014
 *
 * Þessir klasar eru hönnuðir fyrir því hvað skal gera við hverja JSON skrá sem fæst af netinu.
 * ShowJSON notar klasana eftir að GlobalVariable hefur sagt hvaða klasi eigi að keyrast ef að
 * notandi hefur valið flokk. Cocktails nær í JSON af heimasvæði HÍ.
 */

import android.annotation.TargetApi;
import android.os.Build;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Cocktails {

    ParseJSON parseJSON = new ParseJSON();
    final static String URL = "https://notendur.hi.is/ssr9/hugbunadarverkefni/cocktails.json";

    //Notkun:randomNumber(n);
    //Fyrir: n er heiltala
    //Eftir: Heiltala x sem er 0 <= x <= n
    public int randomNumber(int n) {
        Random randGen = new Random();
        return randGen.nextInt(n);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    //Notkun: cocktailsList(radioGenre);
    //Fyrir: radioGenre er strengur sem inniheldur genre sem notandi valdi
    //Eftir: Búið er að finna gildi úr JSON skrá sem uppfylti strenginn radioGenre
    public String [] cocktailList(String radioGenre) throws ClientProtocolException, IOException, JSONException {
        HttpResponse r = parseJSON.httpResponse(URL);
        int status = r.getStatusLine().getStatusCode();
        if(status == 200) {
            HttpEntity e = r.getEntity();
            String data = EntityUtils.toString(e);
            JSONArray jsonArray = new JSONArray(data);
            boolean correct = false;

            String ingredient;
            String [] ingredients = new String[5];

            JSONObject randomObject = jsonArray.getJSONObject(randomNumber(jsonArray.length()));

            while(!correct){
                randomObject = jsonArray.getJSONObject(randomNumber(jsonArray.length()));
                JSONArray jsonArrayIngredients = randomObject.getJSONArray("ingredients");

                for(int i = 0; i < jsonArrayIngredients.length(); i++)
                {
                    JSONObject randomObjectIngredients = jsonArrayIngredients.getJSONObject(i);

                    if (randomObjectIngredients.has("ingredient")) {
                        ingredient = randomObjectIngredients.getString("ingredient");
                        if (ingredient.contains(radioGenre)) {
                            correct = true;
                        }
                        else {
                            continue;
                        }
                    } else {
                        break;
                    }

                }

            }


            JSONArray jsonArrayIngredients = randomObject.getJSONArray("ingredients");

            String [] jsonObject = new String[10];
            String [] afengi = new String[5];
            jsonObject[0] = randomObject.getString("name");
            jsonObject[1] = randomObject.getString("category");

            for(int i = 0; i < jsonArrayIngredients.length(); i++)
            {
                JSONObject last2 = jsonArrayIngredients.getJSONObject(i);

                if (last2.has("cl")) {
                    afengi[i] = last2.getString("cl") + " cl of " + last2.getString("ingredient") + "\n";
                } else {
                    break;
                }

            }

            String strengur = Arrays.toString(afengi)
                    .replace(",", "")
                    .replace("[", "")
                    .replace("]", "")
                    .replace("null", "")
                    .trim();

            jsonObject[2] = strengur;


            return jsonObject;
        }
        else {
            return null;
        }
    }

}
