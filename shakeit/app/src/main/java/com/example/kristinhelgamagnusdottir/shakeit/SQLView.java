package com.example.kristinhelgamagnusdottir.shakeit;

/**
 * Created by kristinhelgamagnusdottir on 13/11/14.
 */


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SQLView extends Activity implements OnClickListener {

    Button removebutton;
    EditText id_remove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlview);
        TextView tv = (TextView) findViewById(R.id.tvSQLinfo);
        Favorites info = new Favorites(this);

        info.open();
        String data = info.getData();
        info.close();
        tv.setText(data);


        removebutton = (Button) findViewById(R.id.bRemove);
        id_remove = (EditText) findViewById(R.id.etIdDelete);

        removebutton.setOnClickListener(this);
    }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.bRemove:
                    try {
                        String sRow1 = id_remove.getText().toString();
                        long lRow1 = Long.parseLong(sRow1);
                        Favorites ex1 = new Favorites(this);
                        ex1.open();
                        ex1.deleteEntry(lRow1);
                        ex1.close();
                        Intent i = new Intent("com.example.kristinhelgamagnusdottir.shakeit.SQLView");
                        startActivity(i);
                        break;
                    } catch (Exception e) {

                        String error = e.toString();
                        Dialog d = new Dialog(this);
                        d.setTitle("Error came up!");
                        TextView tv = new TextView(this);
                        tv.setText(error);
                        d.setContentView(tv);
                        d.show();
                    }
                    break;
            }
        }

    }
