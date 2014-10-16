package com.example.kristinhelgamagnusdottir.shakeit;


import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.util.Random;
/**
 * Created by kristinhelgamagnusdottir on 14/10/14.
 */
public class ShowJSON extends Activity implements View.OnClickListener{
    Movies movies = new Movies();
    Cocktails cocktails = new Cocktails();
    ChuckNorrisJokes chuckNorrisJokes = new ChuckNorrisJokes();
    TextView httpStuff;
    int activityNumb;
    String json;
    Random randGen = new Random();
    int rando = randGen.nextInt(100);
    Button aftur,tilbaka;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_movie);
        httpStuff = (TextView) findViewById(R.id.tvMovie);

        activityNumb = ((GlobalVariable) this.getApplication()).getActivityNumber();
        new Read().execute("text");

        aftur = (Button) findViewById(R.id.bAftur);
        tilbaka = (Button) findViewById(R.id.bBack);

        aftur.setOnClickListener(this);
        tilbaka.setOnClickListener(this);

    }



    public class Read extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                if(activityNumb == 1) {
                    json = movies.movieList(rando);
                }
                if(activityNumb == 2) {
                    json = cocktails.cocktailList(rando);
                }
                if(activityNumb == 3) {
                    json = chuckNorrisJokes.chuckNorris();
                }
                if(json == null) {
                    httpStuff.setText("Database not connected");
                }
                else {
                    return json;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String results) {
            httpStuff.setText(results);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bAftur:
                Intent bVR = new Intent("com.example.kristinhelgamagnusdottir.shakeit.ShowJSON");
                startActivity(bVR);
                break;
            case R.id.bBack:
                finish();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}