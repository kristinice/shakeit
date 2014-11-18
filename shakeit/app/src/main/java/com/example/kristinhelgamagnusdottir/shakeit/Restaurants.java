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
        String data = parseJSON.BuffReader(URL);

        if(data != "") {
            JSONArray jsonArray = new JSONArray(data);
            boolean correct = false;

            String price;
            JSONObject randomObject = jsonArray.getJSONObject(randomNumber(jsonArray.length()));
            while(!correct){
                randomObject = jsonArray.getJSONObject(randomNumber(jsonArray.length()));
                price = randomObject.getString("price");
                if(price.contains(radioGenre)) {



                                        correct = true;
                }
            }

            String [] jsonObject = new String[4];
            jsonObject[0] = randomObject.getString("name");
            String Price = randomObject.getString("price")
                    .replace("[", "")
                    .replace("]", "")
                    .replace("\"", "")
                    .replace(",", ", ")
                    .replace("null", "");
            if (Price.contains("1, 2, 3, 4")){
               jsonObject[1] = Price.replace("1, 2, 3, 4", "Less than 1300 kr. - More than 4000 kr.");
            }
            else if (Price.contains("1, 2, 3")){
                jsonObject[1] = Price.replace("1, 2, 3", "Less than 1300 kr. - 4000 kr.");
            }
            else if (Price.contains("2, 3, 4")){
                jsonObject[1] = Price.replace("2, 3, 4", "1300 kr. - More than 4000 kr.");
            }
            else if (Price.contains("1, 2")){
                jsonObject[1] = Price.replace("1, 2", "Less than 1300 kr. - 2500 kr.");
            }
            else if (Price.contains("2, 3")){
                jsonObject[1] = Price.replace("2, 3", "1300 kr. - 4000 kr.");
            }
            else if (Price.contains("3, 4")){
                jsonObject[1] = Price.replace("3, 4", "2500 kr. - More than 4000 kr.");
            }
            else if (Price.contains("1")){
                jsonObject[1] = Price.replace("1", "Less than 1300 kr.");
            }
            else if (Price.contains("2")){
                jsonObject[1] = Price.replace("2", "1300 kr. - 2500 kr.");
            }
            else if (Price.contains("3")){
                jsonObject[1] = Price.replace("3", "2500 kr. - 4000 kr.");
            }
            else if (Price.contains("4")){
                jsonObject[1] = Price.replace("4", "More than 4000 kr.");
            }
            else {
                jsonObject[1] = randomObject.getString("price");
            }
            jsonObject[2] = randomObject.getString("number");

            return jsonObject;
        }
        else {
            return null;
        }

    }
}