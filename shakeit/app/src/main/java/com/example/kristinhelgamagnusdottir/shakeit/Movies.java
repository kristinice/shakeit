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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Movies extends Activity{

    ParseJSON parseJSON = new ParseJSON();
    final static String URL = "https://notendur.hi.is/~sij65/Hugbunadarverkefni%201/movies.json";
    ArrayList al = new ArrayList();
    ArrayList al2 = new ArrayList();

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
    public String [] movieList(String [] checkboxValue) throws IOException, JSONException {

        String data = parseJSON.BuffReader(URL);

        if(data != "") {

            JSONArray jsonArray = new JSONArray(data);
            String genres;

            JSONObject randomObject = jsonArray.getJSONObject(randomNumber(jsonArray.length()));

            for(int i=0; i<jsonArray.length();i++) {
                randomObject = jsonArray.getJSONObject(i);
                genres = randomObject.getString("genres");
                if((genres.contains(checkboxValue[0])) && (genres.contains(checkboxValue[1]))&&
                (genres.contains(checkboxValue[2])) && (genres.contains(checkboxValue[3])) &&
                        (genres.contains(checkboxValue[4]))&&(genres.contains(checkboxValue[5]))){
                    al.add(i);

                }
            }




            int m = Integer.parseInt(al.get(randomNumber(al.size()-1)).toString());
            String [] tilraun = new String[4];
            tilraun[0] = al.get(0).toString();
            tilraun[1] = Integer.toString(randomNumber(26));
            tilraun[2] = checkboxValue[2];
            tilraun[3] = Integer.toString(al.size());



            randomObject = jsonArray.getJSONObject(m);

            String [] jsonObject = new String[4];
            jsonObject[0] = randomObject.getString("title");
            jsonObject[1] = randomObject.getString("rank");
            jsonObject[2] = randomObject.getString("genres");
            String language = randomObject.getString("languages");
            String country = randomObject.getString("country");
            String runtime = randomObject.getString("runtime");
            jsonObject[3] = language +"\n" + country + "\n" + runtime;
            return jsonObject;
        }
        else {
            return null;
        }

    }
}
