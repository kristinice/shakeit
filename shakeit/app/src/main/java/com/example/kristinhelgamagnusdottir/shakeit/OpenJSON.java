package com.example.kristinhelgamagnusdottir.shakeit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by kristinhelgamagnusdottir on 13/10/14.
 */

public class OpenJSON extends Activity implements View.OnClickListener {

    Button GiveRandom, FaraTilBaka;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.givemetext);

        GiveRandom = (Button) findViewById(R.id.bVilRandom);
        FaraTilBaka = (Button) findViewById(R.id.bTilBaka);

        GiveRandom.setOnClickListener(this);
        FaraTilBaka.setOnClickListener(this);
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

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}