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
    int activityNumb;
    String [] json = new String[3];
    String json2;
    Random randGen = new Random();
    int rando = randGen.nextInt(100);
    Button aftur,tilbaka;
    private ShakeListener mShaker;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_movie);
        httpStuff = (TextView) findViewById(R.id.tvMovie);
        httpStuff2 = (TextView) findViewById(R.id.tvMovie2);

        activityNumb = ((GlobalVariable) this.getApplication()).getActivityNumber();
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
                    json = movies.movieList(rando);
                    json2 = movies.movieList2(rando);
                }
                if(activityNumb == 2) {
                    json = cocktails.cocktailList(rando);
                    json2 = cocktails.cocktailList2(rando);
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
            httpStuff.setText(results[0]);
            httpStuff2.setText(results[1]);
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
        mShaker.pause();
    }
}