package com.example.kristinhelgamagnusdottir.shakeit;

/**
 * Created by kristinhelgamagnusdottir on 13/11/14.
 */


import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import java.util.ArrayList;

public class SQLView extends Activity {

    ListView movieList;
    MovieCustomAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        Favorites info = new Favorites(this);

        info.open();
        ArrayList movieArray = info.getData();
        info.close();

        movieAdapter = new MovieCustomAdapter(SQLView.this, R.layout.row, movieArray);
        movieList = (ListView) findViewById(R.id.listView);
        movieList.setItemsCanFocus(false);
        movieList.setAdapter(movieAdapter);

    }

}
