package com.example.kristinhelgamagnusdottir.shakeit;

/**
 * Höfundur: Emil Ragnarsson
 * Útgáfa: 1.0
 * Dagsetning: 16. október 2014
 *
 * Klasinn StartActivity er upphafsklasi forritsins. Hann útbýr lista þar sem hægt er að velja flokk
 * sem notandinn vill fá tilviljanakennda uppástungu um. Hann vísar síðan í klasann OpenJSON eftir
 * að notandi hefur valið sér flokk.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class StartActivity extends Activity implements View.OnClickListener{
    ImageView imageMovies;
    ImageView imageCocktail;
    ImageView imageRestaurants;
    ImageView imageJokes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);


        //fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_start);

        imageMovies = (ImageView) findViewById(R.id.imageMovies);
        imageMovies.setOnClickListener(this);

        imageCocktail = (ImageView) findViewById(R.id.imageCocktail);
        imageCocktail.setOnClickListener(this);

        imageRestaurants = (ImageView) findViewById(R.id.imageRestaurants);
        imageRestaurants.setOnClickListener(this);

        imageJokes = (ImageView) findViewById(R.id.imageJokes);
        imageJokes.setOnClickListener(this);

    }

    private void onClickMovies() {
        ((GlobalVariable) this.getApplication()).setActivityNumber(1);
        Intent ourIntent = new Intent(StartActivity.this, OpenJSON.class);
        startActivity(ourIntent);
    }

    private void onClickCocktails() {
        ((GlobalVariable) this.getApplication()).setActivityNumber(2);
        Intent ourIntent = new Intent(StartActivity.this, OpenJSON.class);
        startActivity(ourIntent);
    }

    private void onClickRestaurants() {
        ((GlobalVariable) this.getApplication()).setActivityNumber(3);
        Intent ourIntent = new Intent(StartActivity.this, OpenJSON.class);
        startActivity(ourIntent);
    }
    private void onClickJokes() {
        ((GlobalVariable) this.getApplication()).setActivityNumber(4);
        Intent ourIntent = new Intent(StartActivity.this, OpenJSON.class);
        startActivity(ourIntent);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.imageMovies:
                onClickMovies();
                break;

            case R.id.imageCocktail:
                onClickCocktails();
                break;
            case R.id.imageRestaurants:
                onClickRestaurants();
                break;
            case R.id.imageJokes:
                onClickJokes();
                break;
        }
    }
}
