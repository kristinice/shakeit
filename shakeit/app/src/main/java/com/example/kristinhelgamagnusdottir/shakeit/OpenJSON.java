package com.example.kristinhelgamagnusdottir.shakeit;

/**
 * Höfundur: Sindri Snær Rúnarsson
 * Útgáfa: 1.0
 * Dagsetning: 16. október 2014
 *
 * OpenJSON klasinn er milliliður StartActivity og ShowJSON. Hann tekur ekki við neinum upplýsingum
 * og skilar ekki neinum. Hann eingögnu opnast eftir að flokkur hefur verið valinn og opnar síðan
 * ShowJSON ef ýtt er á takkan “Give me a suggestion”. Hann er tengdur við give_me_text.xml skránna
 * til útlits.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

/**
 * Created by kristinhelgamagnusdottir on 13/10/14.
 */

public class OpenJSON extends Activity implements View.OnClickListener {

    Button GiveRandom, FaraTilBaka;
    private ShakeListener mShaker;
    GlobalVariable globalVariable = new GlobalVariable();
    int activityNumb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        activityNumb = ((GlobalVariable) this.getApplication()).getActivityNumber();
        //globalVariable.setRadioValue("");
        if(activityNumb == 1) {
            setContentView(R.layout.open_movies);
        }
        else if (activityNumb == 2) {
            setContentView(R.layout.open_cocktails);
        }
        else {
            setContentView(R.layout.givemetext);
        }

        GiveRandom = (Button) findViewById(R.id.bVilRandom);
        FaraTilBaka = (Button) findViewById(R.id.bTilBaka);

        //onRadioButtonClicked();

        GiveRandom.setOnClickListener(this);
        FaraTilBaka.setOnClickListener(this);
        final Vibrator vibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        mShaker = new ShakeListener(this);
        mShaker.setOnShakeListener(new ShakeListener.OnShakeListener () {
            public void onShake()
            {

                Intent bVR = new Intent(OpenJSON.this, ShowJSON.class);
                finish();
                startActivity(bVR);



            }
        });
    }

    public void onClick(View view) {
        // TODO Auto-generated method stub
        switch (view.getId()) {
            case R.id.bVilRandom:
                Intent bVR = new Intent(OpenJSON.this, ShowJSON.class);
                startActivity(bVR);
                break;
            case R.id.bTilBaka:
                finish();
                break;
        }
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.drama:
                if (checked)

                    ((GlobalVariable) this.getApplication()).setRadioValue("Drama");
                Toast.makeText(this, ((GlobalVariable) this.getApplication()).getRadioValue(), Toast.LENGTH_LONG).show();

                    // Pirates are the best
                    break;
            case R.id.crime:
                if (checked)
                    ((GlobalVariable) this.getApplication()).setRadioValue("Crime");
                    break;
            case R.id.adventure:
                if (checked)
                    ((GlobalVariable) this.getApplication()).setRadioValue("Adventure");
                break;
            case R.id.family:
                if (checked)
                    ((GlobalVariable) this.getApplication()).setRadioValue("Family");
                break;
            case R.id.scifi:
                if (checked)
                    ((GlobalVariable) this.getApplication()).setRadioValue("Sci-Fi");
                break;
        }
    }



    @Override
    protected void onPause() {
        mShaker.pause();
        super.onPause();

    }

    @Override
    public void onResume()
    {
        mShaker.resume();
        super.onResume();
    }

}