package com.example.kristinhelgamagnusdottir.shakeit;

/**
 * Höfundur: Sindri Snær Rúnarsson
 * Útgáfa: 1.0
 * Dagsetning: 28. október 2014
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
import java.util.ArrayList;
import java.util.Random;

public class Restaurants extends Activity{

    ParseJSON parseJSON = new ParseJSON();
    final static String URL = "https://notendur.hi.is/ssr9/hugbunadarverkefni/veitingastadir.json";
    ArrayList al = new ArrayList();
    ArrayList al2 = new ArrayList();
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
    public String [] restaurantList(String [] checkboxValue) throws IOException, JSONException {
        String data = parseJSON.BuffReader(URL);

        if(!data.equals("")) {
            JSONArray jsonArray = new JSONArray(data);
            String price;
            String branch;
            JSONObject randomObject;
            JSONArray randomArrey;
            for(int i=0; i<jsonArray.length();i++) {
                randomObject = jsonArray.getJSONObject(i);
                price = randomObject.getString("price");
                branch = randomObject.toString();

                if((branch.contains(checkboxValue[4])) || (branch.contains(checkboxValue[5])) ||
                    (branch.contains(checkboxValue[6])) || (branch.contains(checkboxValue[7]))
                    || (branch.contains(checkboxValue[8])))
                {
                    if(price.contains(checkboxValue[0]) || price.contains(checkboxValue[1]) ||
                            price.contains(checkboxValue[2]) || price.contains(checkboxValue[3])) {
                        al.add(i);
                    }


                }


            }


            if(al.size() <= 1) {
                al.add(1);
                al.add(2);
            }



            int m = Integer.parseInt(al.get(randomNumber(al.size()-1)).toString());


            randomObject = jsonArray.getJSONObject(m);


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
            String Branch = randomObject.getString("branch")
                    .replace("[", "")
                    .replace("]", "")
                    .replace("\"", "")
                    .replace(",", ", ")
                    .replace("{", "")
                    .replace("},", "\n")
                    .replace("}", "")
                    .replace(":", ": ")
                    .replace("null", "");
            jsonObject[2] = Branch;
            jsonObject[3] = randomObject.getString("number");

            return jsonObject;
        }
        else {
            return null;
        }

    }
}