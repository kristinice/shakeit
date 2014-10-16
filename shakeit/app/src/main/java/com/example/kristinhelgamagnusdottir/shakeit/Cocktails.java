package com.example.kristinhelgamagnusdottir.shakeit;

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

/**
 * Created by Lenovo on 15.10.2014.
 */
public class Cocktails {
    HttpClient client = new DefaultHttpClient();;
    final static String URL = "https://raw.githubusercontent.com/teijo/iba-cocktails/master/recipes.json";

    public String cocktailList(int numb) throws ClientProtocolException, IOException, JSONException {
        StringBuilder url = new StringBuilder(URL);
        HttpGet get = new HttpGet(url.toString());
        HttpResponse r = client.execute(get);
        int status = r.getStatusLine().getStatusCode();
        if(status == 200) {
            HttpEntity e = r.getEntity();
            String data = EntityUtils.toString(e);
            JSONArray timeline = new JSONArray(data);
            JSONObject last = timeline.getJSONObject(numb);
            String lasts = last.getString("name");
            return lasts;
        }
        else {
            return null;
        }

    }
}
