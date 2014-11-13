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
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

/**
 * Created by kristinhelgamagnusdottir on 13/10/14.
 */

public class OpenJSON extends Activity implements View.OnClickListener {

    Button GiveRandom, FaraTilBaka;
    private ShakeListener mShaker;
    GlobalVariable globalVariable = new GlobalVariable();
    int activityNumb;
    String [] jebb = new String[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        activityNumb = ((GlobalVariable) this.getApplication()).getActivityNumber();
        ((GlobalVariable) this.getApplication()).setRadioValue("");
        for(int i=0; i<6; i++) {
            ((GlobalVariable) this.getApplication()).setCheckboxValues("", i);
        }
        if(activityNumb == 1) {
            setContentView(R.layout.open_movies);
        }
        else if (activityNumb == 2) {
            setContentView(R.layout.open_cocktails);
        }
        else if (activityNumb == 3) {
            setContentView(R.layout.open_restaurants);
        }
        else {
            setContentView(R.layout.givemetext);
        }

        GiveRandom = (Button) findViewById(R.id.bVilRandom);
        FaraTilBaka = (Button) findViewById(R.id.bTilBaka);

        //onRadioButtonClicked();

        GiveRandom.setOnClickListener(this);
        FaraTilBaka.setOnClickListener(this);

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

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        if(activityNumb == 1) {
            // Check which checkbox was clicked
            switch (view.getId()) {
                case R.id.checkbox_crime:
                    if (checked) {
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("Crime", 0);
                        jebb = ((GlobalVariable) this.getApplication()).getCheckboxValues();
                        Toast.makeText(getApplicationContext(),jebb[0] , Toast.LENGTH_SHORT).show();
                    }
                    else
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("", 0);
                    break;
                case R.id.checkbox_drama:
                    if (checked) {
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("Drama", 1);
                        jebb = ((GlobalVariable) this.getApplication()).getCheckboxValues();
                        Toast.makeText(getApplicationContext(),jebb[1] , Toast.LENGTH_SHORT).show();
                    }
                    else
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("", 1);
                    break;
                case R.id.checkbox_adventure:
                    if (checked) {
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("Adventure", 2);
                        jebb = ((GlobalVariable) this.getApplication()).getCheckboxValues();
                        Toast.makeText(getApplicationContext(), jebb[2], Toast.LENGTH_SHORT).show();
                    }
                    else
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("", 2);
                    break;
                case R.id.checkbox_family:
                    if (checked) {
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("Family", 3);
                        jebb = ((GlobalVariable) this.getApplication()).getCheckboxValues();
                        Toast.makeText(getApplicationContext(),jebb[3] , Toast.LENGTH_SHORT).show();
                    }
                    else
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("", 3);
                    break;
                case R.id.checkbox_scifi:
                    if (checked) {
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("Sci-Fi", 4);
                        jebb = ((GlobalVariable) this.getApplication()).getCheckboxValues();
                        Toast.makeText(getApplicationContext(),jebb[4] , Toast.LENGTH_SHORT).show();
                    }
                    else
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("", 4);
                    break;
                case R.id.checkbox_comedy:
                    if (checked) {
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("Comedy", 5);
                        jebb = ((GlobalVariable) this.getApplication()).getCheckboxValues();
                        Toast.makeText(getApplicationContext(),jebb[5] , Toast.LENGTH_SHORT).show();
                    }
                    else
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("", 5);
                    break;
                // TODO: Veggie sandwich
            }
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