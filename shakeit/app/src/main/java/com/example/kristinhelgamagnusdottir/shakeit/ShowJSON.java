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
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;

public class ShowJSON extends Activity implements View.OnClickListener{

    Movies movies = new Movies();
    Cocktails cocktails = new Cocktails();
    Restaurants restaurants = new Restaurants();

    TextView textView, textView2, textView3, textView4;
    int activityNumb;
    String stl;
    String [] jsonObject = new String[3];

    Button aftur,tilbaka;
    private ShakeListener mShaker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityNumb = ((GlobalVariable) this.getApplication()).getActivityNumber();
        stl = ((GlobalVariable) this.getApplication()).getRadioValue();
        if(activityNumb == 1) {
            setContentView(R.layout.results_movies);
            textView = (TextView) findViewById(R.id.tvMovie);
            textView2 = (TextView) findViewById(R.id.tvMovie2);
            textView3 = (TextView) findViewById(R.id.tvMovie3);
            textView4 = (TextView) findViewById(R.id.tvMovie4);
        }
        if(activityNumb == 2) {
            setContentView(R.layout.results_cocktail);
            textView = (TextView) findViewById(R.id.tvCocktails);
            textView2 = (TextView) findViewById(R.id.tvCocktails2);
            textView3 = (TextView) findViewById(R.id.tvCocktails3);
        }
        if(activityNumb == 3) {
            setContentView(R.layout.results_restaurants);
            textView = (TextView) findViewById(R.id.tvRestaurants);
            textView2 = (TextView) findViewById(R.id.tvRestaurants2);
            textView3 = (TextView) findViewById(R.id.tvRestaurants3);
        }

        new Read().execute();

        aftur = (Button) findViewById(R.id.bAftur);
        tilbaka = (Button) findViewById(R.id.bBack);

        aftur.setOnClickListener(this);
        tilbaka.setOnClickListener(this);

        mShaker = new ShakeListener(this);
        mShaker.setOnShakeListener(new ShakeListener.OnShakeListener () {
            public void onShake()
            {


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
                    jsonObject = movies.movieList(stl);
                }
                if(activityNumb == 2) {
                    jsonObject = cocktails.cocktailList(stl);
                }
                if(activityNumb == 3) {
                    jsonObject = restaurants.restaurantList(stl);
                }

                if(jsonObject == null) {
                    textView.setText("Database not connected");
                }
                else {
                     return jsonObject;
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
                textView.setText(results[0]);
                textView2.setText(results[1]);
                if (results[2] != null) {
                    textView3.setText(results[2]);
                }
                if (results[3] != null) {
                    textView4.setText(results[3]);
                }

            }
            catch (NullPointerException e) {
                e.printStackTrace();
                textView.setText("Shake again!");
            }
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bAftur:
                //Intent bVR = new Intent("com.example.kristinhelgamagnusdottir.shakeit.ShowJSON");
                //finish();
                //startActi vity(bVR);
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