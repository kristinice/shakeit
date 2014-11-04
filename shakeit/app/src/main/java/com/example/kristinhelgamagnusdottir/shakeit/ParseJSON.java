package com.example.kristinhelgamagnusdottir.shakeit;

/**
 * Höfundur: Sigurbjörn Jónsson
 * Útgáfa: 1.0
 * Dagsetning: 4 nóvember 2014
 *
 * Þessi klasi tekur við url af staðsetningu JSON skrár,
 * tekur hana inn í appið og opnar hana
 */

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ParseJSON {

    HttpClient client = new DefaultHttpClient();
    DefaultHttpClient httpClient = new DefaultHttpClient();

    //Notkun: httpResponse(URL);
    //Fyrir: URL er heimasíðuslóð fyrir JSON skrá.
    //Eftir: Skilað HttpResponse af JSON skránni.
    public String BuffReader(String URL) throws IOException, JSONException {

        HttpGet request = new HttpGet(URL);
        HttpResponse response = httpClient.execute(request);
        String buffstring = "";

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            InputStream instream = response.getEntity().getContent();
            BufferedReader r = new BufferedReader(new InputStreamReader(instream, "UTF-8"), 8000);
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
            instream.close();
            buffstring = total.toString();
        }
        return buffstring;

    }


        //StringBuilder url = new StringBuilder(URL);
        //HttpGet get = new HttpGet(url.toString());
        //HttpResponse r = client.execute(get);

        //return r;
}


