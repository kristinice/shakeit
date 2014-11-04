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
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;

import java.io.IOException;

public class ParseJSON {

    HttpClient client = new DefaultHttpClient();

    //Notkun: httpResponse(URL);
    //Fyrir: URL er heimasíðuslóð fyrir JSON skrá.
    //Eftir: Skilað HttpResponse af JSON skránni.
    public HttpResponse httpResponse(String URL) throws ClientProtocolException, IOException, JSONException {
        StringBuilder url = new StringBuilder(URL);
        HttpGet get = new HttpGet(url.toString());
        HttpResponse r = client.execute(get);

        return r;
    }

}
