package com.example.kristinhelgamagnusdottir.shakeit;

/**
 * Höfundur: Sigurbjörn Jónsson
 * Útgáfa: 1.1
 * Dagsetning: 16. október 2014
 *
 * Þessi klasi ákveður hvað eigi að taka úr cocktail JSON skránni sem fæst af netinu og birta.
 * Klasinn notar sér opnun frá ParseJSON til þess að geta unnið með upplýsingarnar.
 */

import android.annotation.TargetApi;
import android.os.Build;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Cocktails {

    ParseJSON parseJSON = new ParseJSON();
    final static String URL = "https://notendur.hi.is/ssr9/hugbunadarverkefni/cocktails.json";
    ArrayList al = new ArrayList();

    //Notkun:randomNumber(n);
    //Fyrir: n er heiltala
    //Eftir: Heiltala x sem er 0 <= x <= n
    public int randomNumber(int n) {
        Random randGen = new Random();
        return randGen.nextInt(n);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    //Notkun: cocktailList(checkboxValue);
    //Fyrir: checkboxValue er fylki strengja sem inniheldur síur sem notandi valdi
    //Eftir: Búið er að taka hluti úr JSON og strengja þá til prentunar
    public String [] cocktailList(String [] checkboxValue) throws IOException, JSONException {
        String data = parseJSON.BuffReader(URL);

        if(!data.equals("")) {

            JSONArray jsonArray = new JSONArray(data);
            JSONObject randomObject;

            al = selectedValues(jsonArray,checkboxValue);
            int m = Integer.parseInt(al.get(randomNumber(al.size()-1)).toString());

            randomObject = jsonArray.getJSONObject(m);

            String [] jsonObject = new String[10];
            jsonObject[0] = randomObject.getString("name");
            jsonObject[1] = randomObject.getString("category");
            jsonObject[2] = getIngredients(randomObject);

            return jsonObject;
        }
        else {
            return null;
        }
    }

    //Notkun: stringTrim(stringToTrim)
    //Fyrir: stringToTrim er strengur sem á eftir að laga útlit á
    //Eftir: Skilað er streng sem búið er að laga útlit á.
    public String stringTrim(String [] afengi) {
        String finalString = Arrays.toString(afengi)
                .replace(",", "")
                .replace("[", "")
                .replace("]", "")
                .replace("null", "")
                .trim();
        return finalString;
    }

    //Notkun: ArrayList(jsonArray, checkboxValue);
    //Fyrir: jsonArray er innhald jsonSkránnar og
    //      checkboxValue er fylki strengja sem inniheldur síur sem notandi valdi
    //Eftir: Búið er að sía út val notenda út jsonArray í arrayList.
    public ArrayList selectedValues(JSONArray jsonArray, String [] checkboxValue)throws JSONException {
        JSONObject randomObject;
        ArrayList arrayList = new ArrayList();
        String ingredient = "";

        for(int i=0; i<jsonArray.length();i++) {
            randomObject = jsonArray.getJSONObject(i);
            ingredient = randomObject.toString();
            if(ingredient.contains(checkboxValue[0]) && ingredient.contains(checkboxValue[1]) && ingredient.contains(checkboxValue[2])){
                arrayList.add(i);
            }
        }
        if(arrayList.size() <= 1) {
            arrayList.add(1);
            arrayList.add(2);

        }
        return arrayList;
    }
    //Notkun: getIngrediens(randomObject);
    //Fyrir: randomObject er object út json skrá
    //Eftir: Skilar streng innra innhaldi randomObjects
    public String getIngredients(JSONObject randomObject) throws JSONException {
        JSONArray jsonArrayIngredients = randomObject.getJSONArray("ingredients");
        String [] afengi = new String[10];
        String finalString;

        for(int i = 0; i < jsonArrayIngredients.length(); i++) {
            JSONObject jObjIngredients = jsonArrayIngredients.getJSONObject(i);
            if (jObjIngredients.has("cl")) {
                afengi[i] = jObjIngredients.getString("cl") + " cl of " + jObjIngredients.getString("ingredient") + "\n";
            } else {
                break;
            }

        }
        finalString = stringTrim(afengi);
        return finalString;

    }
}
