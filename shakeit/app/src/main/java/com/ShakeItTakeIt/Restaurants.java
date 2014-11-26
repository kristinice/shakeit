package com.ShakeItTakeIt;

/**
 * Höfundur: Sindri Snær Rúnarsson
 * Útgáfa: 1.2
 * Dagsetning: 28. október 2014
 *
 * Þessi klasi ákveður hvað eigi að taka úr restaurant JSON skránni sem fæst af netinu og birta.
 * Klasinn notar sér opnun frá ParseJSON til þess að geta unnið með upplýsingarnar.
 *
 */

import android.app.Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Restaurants extends Activity {

    ParseJSON parseJSON = new ParseJSON();
    final static String URL = "https://notendur.hi.is/ssr9/hugbunadarverkefni/veitingastadir.json";
    ArrayList al = new ArrayList();

    //Notkun:randomNumber(n);
    //Fyrir: n er heiltala
    //Eftir: Heiltala x sem er 0 <= x <= n
    public int randomNumber(int n) {
        Random randGen = new Random();
        return randGen.nextInt(n);
    }

    //Notkun: restaurantList(checkboxValue);
    //Fyrir: checkboxValue er fylki strengja sem inniheldur síur sem notandi valdi
    //Eftir: Búið er að taka hluti úr JSON og strengja þá til prentunar
    public String[] restaurantList(String[] checkboxValue) throws IOException, JSONException {
        String data = parseJSON.BuffReader(URL);
        if (!data.equals("")) {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject randomObject;
            al = selectedValues(jsonArray,checkboxValue);
            int m = Integer.parseInt(al.get(randomNumber(al.size() - 1)).toString());
            randomObject = jsonArray.getJSONObject(m);


            String[] jsonObject = new String[4];
            jsonObject[0] = randomObject.getString("name");
            jsonObject[1] = printJSONObject1(stringTrim(randomObject.getString("price")));
            jsonObject[2] = stringTrim(randomObject.getString("branch"));
            jsonObject[3] = randomObject.getString("number");

            return jsonObject;
        } else {
            return null;
        }

    }

    //Notkun: ArrayList(jsonArray, checkboxValue);
    //Fyrir: jsonArray er innhald jsonSkránnar og
    //      checkboxValue er fylki strengja sem inniheldur síur sem notandi valdi
    //Eftir: Búið er að sía út val notenda út jsonArray í arrayList.
    public ArrayList selectedValues(JSONArray jsonArray, String[] checkboxValue) throws JSONException {
        String price;
        String branch;
        JSONObject randomObject;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jsonArray.length(); i++) {
            randomObject = jsonArray.getJSONObject(i);
            price = randomObject.getString("price");
            branch = randomObject.toString();
            if ((branch.contains(checkboxValue[4])) || (branch.contains(checkboxValue[5])) ||
                    (branch.contains(checkboxValue[6])) || (branch.contains(checkboxValue[7]))
                    || (branch.contains(checkboxValue[8]))) {
                if (price.contains(checkboxValue[0]) || price.contains(checkboxValue[1]) ||
                        price.contains(checkboxValue[2]) || price.contains(checkboxValue[3])) {
                    arrayList.add(i);
                }
            }
        }
        if(arrayList.size()<=1){
            arrayList.add(1);
            arrayList.add(2);
        }
        return arrayList;
    }
    //Notkun: stringTrim(stringToTrim)
    //Fyrir: stringToTrim er strengur sem á eftir að laga útlit á
    //Eftir: Skilað er streng sem búið er að laga útlit á.
    public String stringTrim(String stringToTrim) {
        String finalString = stringToTrim
                .replace("[", "")
                .replace("]", "")
                .replace("\"", "")
                .replace(",", ", ")
                .replace("{", "")
                .replace("},", "\n")
                .replace("}", "")
                .replace(":", ": ")
                .replace("null", "");
        return finalString;
    }

    //Notkun: printJSONObject1(Price)
    //Fyrir: Price er strengur sem inniheldur verðupplýsingar.
    //Eftir: Skilað er streng sem búið er að laga útlit á.
    public String printJSONObject1(String Price) {
        if (Price.contains("1, 2, 3, 4")){
            return Price.replace("1, 2, 3, 4", "Less than 1300 kr. - More than 4000 kr.");
        }
        else if (Price.contains("1, 2, 3")){
            return Price.replace("1, 2, 3", "Less than 1300 kr. - 4000 kr.");
        }
        else if (Price.contains("2, 3, 4")){
            return Price.replace("2, 3, 4", "1300 kr. - More than 4000 kr.");
        }
        else if (Price.contains("1, 2")){
            return Price.replace("1, 2", "Less than 1300 kr. - 2500 kr.");
        }
        else if (Price.contains("2, 3")){
            return Price.replace("2, 3", "1300 kr. - 4000 kr.");
        }
        else if (Price.contains("3, 4")){
            return Price.replace("3, 4", "2500 kr. - More than 4000 kr.");
        }
        else if (Price.contains("1")){
            return Price.replace("1", "Less than 1300 kr.");
        }
        else if (Price.contains("2")){
            return Price.replace("2", "1300 kr. - 2500 kr.");
        }
        else if (Price.contains("3")){
            return Price.replace("3", "2500 kr. - 4000 kr.");
        }
        else if (Price.contains("4")){
            return Price.replace("4", "More than 4000 kr.");
        }
        return "Óvíst verð";
    }
}