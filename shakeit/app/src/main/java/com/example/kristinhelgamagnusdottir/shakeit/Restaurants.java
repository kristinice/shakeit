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
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Lenovo on 15.10.2014.
 */
public class Restaurants extends Activity{
    GlobalVariable globalVariable = new GlobalVariable();
    HttpClient client = new DefaultHttpClient();
    final static String URL = "https://notendur.hi.is/ssr9/hugbunadarverkefni/veitingastadir.json";
    //String stl = ((GlobalVariable) this.getApplication()).getRadioValue();

    public int randomNumber(int n) {
        Random randGen = new Random();
        int randoMovies = randGen.nextInt(n);
        return randoMovies;
    }

    public int getLengthOfArray(JSONArray jsonArray) {
        int lengthofArray = jsonArray.length();
        return lengthofArray;
    }

    public String [] restaurantList(String radioGenre) throws ClientProtocolException, IOException, JSONException {
        StringBuilder url = new StringBuilder(URL);
        HttpGet get = new HttpGet(url.toString());
        HttpResponse r = client.execute(get);
        int status = r.getStatusLine().getStatusCode();
        if(status == 200) {
            HttpEntity e = r.getEntity();
            String data = EntityUtils.toString(e);
            JSONArray timeline = new JSONArray(data);
            boolean correct = false;
            //int randomNumber = randomNumber(getLengthOfArray(timeline));

            String title, price;
            JSONObject last = timeline.getJSONObject(randomNumber(100));
            while(!correct){
                last = timeline.getJSONObject(randomNumber(100));
                price = last.getString("price");
                if(price.contains(radioGenre)) {
                    correct = true;
                }
            }
            //JSONObject last = timeline.getJSONObject(randomNumber(getLengthOfArray(timeline)));
            String [] lasts = new String[4];
            lasts[0] = last.getString("name");
            lasts[1] = last.getString("price")
                    .replace("[", "")
                    .replace("]", "")
                    .replace("\"", "")
                    .replace(",", ", ")
                    .replace("null", "");
            lasts[2] = last.getString("number");

            return lasts;
        }
        else {
            return null;
        }

    }
}