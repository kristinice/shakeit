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
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Lenovo on 15.10.2014.
 */
public class Cocktails {
    HttpClient client = new DefaultHttpClient();;
    final static String URL = "https://notendur.hi.is/ssr9/hugbunadarverkefni/cocktails.json";

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public String [] cocktailList(int numb) throws ClientProtocolException, IOException, JSONException {
        StringBuilder url = new StringBuilder(URL);
        HttpGet get = new HttpGet(url.toString());
        HttpResponse r = client.execute(get);
        int status = r.getStatusLine().getStatusCode();
        if(status == 200) {
            HttpEntity e = r.getEntity();
            String data = EntityUtils.toString(e);
            JSONArray timeline = new JSONArray(data);
            JSONObject last = timeline.getJSONObject(numb);
            JSONArray timeline2 = last.getJSONArray("ingredients");

            String [] lasts = new String[10];
            String [] afengi = new String[5];
            lasts[0] = last.getString("name");
            lasts[1] = last.getString("glass");

            for(int i = 0; i < timeline2 .length(); i++)
            {
                JSONObject last2 = timeline2.getJSONObject(i);
                afengi[i] = last2.getString("cl") + " cl of " + last2.getString("ingredient") + "\n";
            }

            String strengur = Arrays.toString(afengi)
                    .replace(",", "")
                    .replace("[", "")
                    .replace("]", "")
                    .replace("null", "")
                    .trim();

            lasts[2] = strengur;


            return lasts;
        }
        else {
            return null;
        }
    }

}
