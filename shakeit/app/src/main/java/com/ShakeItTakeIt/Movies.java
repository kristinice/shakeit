package com.ShakeItTakeIt;

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

    //Notkun: movieList(checkboxValue);
    //Fyrir: checkboxValue er fylki strengja sem inniheldur síur sem notandi valdi
    //Eftir: Búið er að taka hluti úr JSON og strengja þá til prentunar
    public String [] movieList(String [] checkboxValue) throws IOException, JSONException {
        String data = parseJSON.BuffReader(URL);
        if(data != "") {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject randomObject;
            al = selectedValues(jsonArray,checkboxValue);

            int m = Integer.parseInt(al.get(randomNumber(al.size()-1)).toString());
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

    //Notkun: ArrayList(jsonArray, checkboxValue);
    //Fyrir: jsonArray er innhald jsonSkránnar og
    //      checkboxValue er fylki strengja sem inniheldur síur sem notandi valdi
    //Eftir: Búið er að sía út val notenda út jsonArray í arrayList.
    public ArrayList selectedValues(JSONArray jsonArray, String [] checkboxValue)throws JSONException {
        String genres;
        JSONObject randomObject;
        ArrayList arrayList = new ArrayList();
        for(int i=0; i<jsonArray.length();i++) {
            randomObject = jsonArray.getJSONObject(i);
            genres = randomObject.getString("genres");
            if((genres.contains(checkboxValue[0])) && (genres.contains(checkboxValue[1]))&&
                    (genres.contains(checkboxValue[2])) && (genres.contains(checkboxValue[3])) &&
                    (genres.contains(checkboxValue[4]))&&(genres.contains(checkboxValue[5]))){
                arrayList.add(i);
            }
        }
        if(arrayList.size() <= 1) {
            arrayList.add(1);
        }
        return arrayList;
    }
}