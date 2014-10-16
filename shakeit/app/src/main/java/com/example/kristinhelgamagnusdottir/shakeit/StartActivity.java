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

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class StartActivity extends ListActivity {

    String classes[] = { "Movies", "Cocktails", "Chuck Norris Jokes"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        //fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setListAdapter(new ArrayAdapter<String>(StartActivity.this,
                android.R.layout.simple_list_item_1, classes));
    }
    //int activityNumb = ((GlobalVariable) this.getApplication()).getActivityNumber();
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);
        String choice = classes[position];
        if(choice.equals("Movies")) {
            ((GlobalVariable) this.getApplication()).setActivityNumber(1);
        }
        if(choice.equals("Cocktails")) {
            ((GlobalVariable) this.getApplication()).setActivityNumber(2);
        }
        if(choice.equals("Chuck Norris Jokes")) {
            ((GlobalVariable) this.getApplication()).setActivityNumber(3);
        }

        Intent ourIntent = new Intent(StartActivity.this, OpenJSON.class);
        startActivity(ourIntent);
    }

}
