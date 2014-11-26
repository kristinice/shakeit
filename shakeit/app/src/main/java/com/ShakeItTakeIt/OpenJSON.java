package com.ShakeItTakeIt;

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
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;

public class OpenJSON extends Activity implements View.OnClickListener {

    Button GiveRandom, FaraTilBaka, sqlView;
    private ShakeListener mShaker;
    int activityNumb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

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
            for(int i=0; i<10; i++) {
                ((GlobalVariable) this.getApplication()).setCheckboxValues("Ekki notað", i);
            }
        }
        else if (activityNumb == 4){
            setContentView(R.layout.open_jokes);
        }
        else {
            setContentView(R.layout.givemetext);
        }

        GiveRandom = (Button) findViewById(R.id.bVilRandom);
        FaraTilBaka = (Button) findViewById(R.id.bTilBaka);

        GiveRandom.setOnClickListener(this);
        FaraTilBaka.setOnClickListener(this);
        if(activityNumb == 1) {
            sqlView = (Button) findViewById(R.id.bSQLopenView);
            sqlView.setOnClickListener(this);
        }

        mShaker = new ShakeListener(this);
        mShaker.setOnShakeListener(new ShakeListener.OnShakeListener () {
            public void onShake()
            {
                Intent bVR = new Intent(OpenJSON.this, ShowJSON.class);
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
            case R.id.bSQLopenView:
                Intent i = new Intent("com.ShakeItTakeIt.SQLView");
                //finish();
                startActivity(i);
                break;
            case R.id.bTilBaka:
                finish();
                break;
        }
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        if (activityNumb == 1) {
            // Check which checkbox was clicked
            switch (view.getId()) {
                case R.id.checkbox_crime:
                    if (checked) {
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("Crime", 0);
                    } else
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("", 0);
                    break;
                case R.id.checkbox_drama:
                    if (checked) {
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("Drama", 1);
                    } else
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("", 1);
                    break;
                case R.id.checkbox_adventure:
                    if (checked) {
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("Adventure", 2);
                    } else
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("", 2);
                    break;
                case R.id.checkbox_family:
                    if (checked) {
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("Family", 3);
                    } else
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("", 3);
                    break;
                case R.id.checkbox_scifi:
                    if (checked) {
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("Sci-Fi", 4);
                    } else
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("", 4);
                    break;
                case R.id.checkbox_comedy:
                    if (checked) {
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("Comedy", 5);
                    } else
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("", 5);
                    break;

            }
        }

        if (activityNumb == 2) {
            // Check which checkbox was clicked
            switch (view.getId()) {
                case R.id.checkbox_vodka:
                    if (checked) {
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("Vodka", 0);
                    } else
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("", 0);
                    break;
                case R.id.checkbox_rum:
                    if (checked) {
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("Rum", 1);;
                    } else
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("", 1);
                    break;
                case R.id.checkbox_wiskey:
                    if (checked) {
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("Whiskey", 2);
                    } else
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("", 2);
                    break;

            }
        }

        if (activityNumb == 3) {

            switch (view.getId()) {
                case R.id.checkbox_price1:
                    if (checked) {
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("1", 0);
                    } else
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("Ekki notað", 0);
                    break;
                case R.id.checkbox_price2:
                    if (checked)
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("2", 1);
                    else
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("Ekki notað", 1);
                    break;
                case R.id.checkbox_price3:
                    if (checked)
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("3", 2);
                    else
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("Ekki notað", 2);
                    break;
                case R.id.checkbox_price4:
                    if (checked)
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("4", 3);
                    else
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("Ekki notað", 3);
                    break;
                case R.id.postcode_101:
                    if (checked) {
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("101", 4);
                    } else
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("Ekki notað", 4);
                    break;
                case R.id.postcode_105:
                    if (checked) {
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("105", 5);
                    } else
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("Ekki notað", 5);
                    break;
                case R.id.postcode_110:
                    if (checked) {
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("110", 6);
                    } else
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("Ekki notað", 6);
                    break;
                case R.id.postcode_200:
                    if (checked) {
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("200", 7);
                    }else
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("Ekki notað", 7);
                    break;
                case R.id.postcode_220:
                    if (checked) {
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("220", 8);
                    }else
                        ((GlobalVariable) this.getApplication()).setCheckboxValues("Ekki notað", 8);
                    break;

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