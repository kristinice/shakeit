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

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.app.Dialog;
import org.json.JSONException;

import java.io.IOException;

public class ShowJSON extends Activity implements View.OnClickListener{

    Movies movies = new Movies();
    Cocktails cocktails = new Cocktails();
    Restaurants restaurants = new Restaurants();
    Jokes jokes = new Jokes();

    TextView textView, textView2, textView3, textView4;
    int activityNumb;
    String stl;
    String [] checkboxValue;
    String [] jsonObject = new String[3];

    Button aftur,tilbaka,favorite,share;
    private ShakeListener mShaker;

    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        activityNumb = ((GlobalVariable) this.getApplication()).getActivityNumber();
        stl = ((GlobalVariable) this.getApplication()).getRadioValue();
        checkboxValue = new String[6];
        checkboxValue = ((GlobalVariable) this.getApplication()).getCheckboxValues();
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
            textView4 = (TextView) findViewById(R.id.tvRestaurants4);
        }
        if(activityNumb == 4) {
            setContentView(R.layout.results_jokes);
            textView = (TextView) findViewById(R.id.tvJokes);
        }

        new Read().execute();

        aftur = (Button) findViewById(R.id.bAftur);
        tilbaka = (Button) findViewById(R.id.bBack);
        favorite = (Button) findViewById(R.id.bFavorite);
        share = (Button) findViewById(R.id.bShare);

        aftur.setOnClickListener(this);
        tilbaka.setOnClickListener(this);

        if(activityNumb == 1) {
            share.setOnClickListener(this);
            favorite.setOnClickListener(this);
        }
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

    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    public class Read extends AsyncTask<String [], Integer, String []> {
        @Override
        protected String [] doInBackground(String []... params) {
            try {
                if(activityNumb == 1) {
                    jsonObject = movies.movieList(checkboxValue);
                }
                if(activityNumb == 2) {
                    jsonObject = cocktails.cocktailList(checkboxValue);
                }
                if(activityNumb == 3) {
                    jsonObject = restaurants.restaurantList(checkboxValue);
                }
                if (activityNumb == 4){
                    jsonObject = jokes.jokesList();
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
                if (results[1] != null) {
                    textView2.setText(results[1]);
                }
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

    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.bFavorite:
                boolean didItWork = true;
                try {
                    String title = textView.getText().toString();

                    Favorites entry = new Favorites(ShowJSON.this);
                    entry.open();
                    entry.createEntry(title);
                    entry.close();

                } catch (Exception e) {
                    didItWork = false;
                    String error = e.toString();
                    Dialog d = new Dialog(this);
                    d.setTitle("Error came up!");
                    TextView tv = new TextView(this);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.show();
                } finally {
                    if (didItWork) {
                        Dialog d = new Dialog(this);
                        d.setTitle("Worked!");
                        TextView tv = new TextView(this);
                        tv.setText("Success");
                        d.setContentView(tv);
                        d.show();
                    }
                }
                break;
            case R.id.bAftur:
                Intent bVR = new Intent("com.example.kristinhelgamagnusdottir.shakeit.ShowJSON");
                finish();
                startActivity(bVR);
                break;
            case R.id.bShare:
                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                share.setType("text/plain");
                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

                share.putExtra(Intent.EXTRA_SUBJECT, "ShakeIt TakeIt");
                share.putExtra(Intent.EXTRA_TEXT, "Ég var að prófa ShakeIt TakeIt, það er snilld!");

                startActivity(Intent.createChooser(share, "Share link!"));
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