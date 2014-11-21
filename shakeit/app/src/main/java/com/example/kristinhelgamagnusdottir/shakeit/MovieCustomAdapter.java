package com.example.kristinhelgamagnusdottir.shakeit;

/**
 * Created by kristinhelgamagnusdottir on 18/11/14.
 */

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MovieCustomAdapter extends ArrayAdapter<Movie> {
    Context context;
    int layoutResourceId;
    ArrayList<Movie> data = new ArrayList<Movie>();

    public MovieCustomAdapter(Context context, int layoutResourceId,
                              ArrayList<Movie> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        MovieHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new MovieHolder();
            holder.textTitle = (TextView) row.findViewById(R.id.textView1);
            holder.btnDelete = (Button) row.findViewById(R.id.button2);
            row.setTag(holder);
        } else {
            holder = (MovieHolder) row.getTag();
        }
        Movie movie = data.get(position);
        holder.textTitle.setText(movie.getTitle());

        holder.btnDelete.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Log.i("Delete Button Clicked", "**********");
                Toast.makeText(context, "This movie has been removed from your list",
                        Toast.LENGTH_LONG).show();


                Movie movie = data.get(position);
                String lRow1 = movie.getTitle().toString();
                Favorites ex1 = new Favorites(context);
                ex1.open();
                ex1.deleteEntry(lRow1);
                ex1.close();

            }
        });
        return row;

    }

    static class MovieHolder {
        TextView textTitle;
        Button btnDelete;
    }
}