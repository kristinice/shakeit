package com.ShakeItTakeIt;

/**
 * Höfundur: Kristín Helga Magnúsdóttir
 * Útgáfa: 1.2
 * Dagsetning: 18. nóvember 2014
 *
 * MovieCustomAdapter sér um að birta gögn úr gagngrunninum Favoritesdb í listaútliti. Hann heldur
 * einnig utan um hlustara á delete-takka ef notandinn vill eyða út myndum sem hann hefur þegar
 * vistað.
 */

import android.app.Activity;
import android.content.Context;
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

    /**
     * Notkun: new MovieCustomAdapter(context, layout, fylki)
     * Fyrir: context eru upplýsingar úr SQLView um á hvaða formi á að birta gögnin, layout er
     * útlitið sem setja á inn í fyrir birtingu. Fylki er fylki með öllum gögnum.
     * Eftir: Búið er að fylla inn í breytur klasans MovieCustomAdapter hér að ofan
     */
    public MovieCustomAdapter(Context context, int layoutResourceId,
                              ArrayList<Movie> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    /**
     * Eftir: Búið er að gefa hverri línu listans yfir vistaðar kvikmyndir gildi.
     */
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

        /**
         * Notkun: Ýtt á takka "delete" í listanum
         * Fyrir: Lista sem sýnir vistaðar myndir
         * Eftir: Valinni línu hefur verið eytt úr gagnagrunninum (með hjálp Favorites-klasans).
         */
        holder.btnDelete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
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