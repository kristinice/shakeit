package com.example.kristinhelgamagnusdottir.shakeit;

/**
 * Höfundur: Kristín Helga Magnúsdóttir
 * Útgáfa: 1.0
 * Dagsetning: 16. október 2014
 *
 * Show JSON inniheldur annan klasa inn í sér sem heitir Read. Read er AsyncTask klasi til þess að
 * geta náð í JSON file-a á öðrum Thread meðan forritið er í gangi á aðal keyrslu Thread.
 * GlobalVariable  segir Read hvaða JSON eigi að keyrast hverju sinni. ShowJSON prentar síðan
 * upplýsingarnar sem Movies,Cocktails eða ChuckNorrisJokes hafa gefið. Klasinn getur síðan keyrt
 * sig aftur ef ýtt er á “Give me another suggestion”.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.util.Random;

public class ShowJSON extends Activity implements View.OnClickListener{
    Movies movies = new Movies();
    Cocktails cocktails = new Cocktails();
    ChuckNorrisJokes chuckNorrisJokes = new ChuckNorrisJokes();
    TextView httpStuff;
    TextView httpStuff2;
    TextView httpStuff3;
    int activityNumb;
    String [] json = new String[3];
    Random randGen = new Random();
    int randoMovies = randGen.nextInt(100);
    int randoCocktails = randGen.nextInt(77);
    Button aftur,tilbaka;
    private ShakeListener mShaker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityNumb = ((GlobalVariable) this.getApplication()).getActivityNumber();
        if(activityNumb == 1) {
            setContentView(R.layout.results_movies);
            httpStuff = (TextView) findViewById(R.id.tvMovie);
            httpStuff2 = (TextView) findViewById(R.id.tvMovie2);
        }
        if(activityNumb == 2) {
            setContentView(R.layout.results_cocktail);
            httpStuff = (TextView) findViewById(R.id.tvCocktails);
            httpStuff2 = (TextView) findViewById(R.id.tvCocktails2);
            httpStuff3 = (TextView) findViewById(R.id.tvCocktails3);
        }

        new Read().execute();

        aftur = (Button) findViewById(R.id.bAftur);
        tilbaka = (Button) findViewById(R.id.bBack);

        aftur.setOnClickListener(this);
        tilbaka.setOnClickListener(this);

        final Vibrator vibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        mShaker = new ShakeListener(this);
        mShaker.setOnShakeListener(new ShakeListener.OnShakeListener () {
            public void onShake()
            {

                //vibe.vibrate(100);

                Intent bVR = new Intent("com.example.kristinhelgamagnusdottir.shakeit.ShowJSON");
                finish();
                startActivity(bVR);

            }
        });
    }

    public class Read extends AsyncTask<String [], Integer, String []> {

        @Override
        protected String [] doInBackground(String []... params) {
            try {
                if(activityNumb == 1) {
                    json = movies.movieList(randoMovies);
                }
                if(activityNumb == 2) {
                    json = cocktails.cocktailList(randoCocktails);
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
        protected void onPostExecute(String [] results) {
            try {
                httpStuff.setText(results[0]);
                httpStuff2.setText(results[1]);
                if (results[2] != null) {
                    httpStuff3.setText(results[2]);
                }
            }
            catch (NullPointerException e) {
                e.printStackTrace();
                httpStuff.setText("Shake again!");
            }
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bAftur:
                Intent bVR = new Intent("com.example.kristinhelgamagnusdottir.shakeit.ShowJSON");
                finish();
                startActivity(bVR);
                break;
            case R.id.bBack:
                finish();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mShaker.pause();
    }
}