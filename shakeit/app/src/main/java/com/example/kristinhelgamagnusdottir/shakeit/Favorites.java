package com.example.kristinhelgamagnusdottir.shakeit;

/**
 * Created by kristinhelgamagnusdottir on 12/11/14.
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;
import android.view.View;
import android.view.View.OnClickListener;

import java.util.ArrayList;

public class Favorites {

    public static final String KEY_ROWID = "_id";
    public static final String KEY_TITLE = "movie_title";


    private static final String DATABASE_NAME = "Favoritesdb";
    private static final String DATABASE_TABLE = "movieTable";
    private static final int DATABASE_VERSION = 1;

    private DbHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    ArrayList<Movie> movieArray = new ArrayList<Movie>();




    private static class DbHelper extends SQLiteOpenHelper{

        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
                            KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            KEY_TITLE + " TEXT NOT NULL);"
            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }

    public Favorites(Context c){
        ourContext = c;
    }

    public Favorites open() throws SQLException{
        ourHelper = new DbHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        ourHelper.close();
    }

    public long createEntry(String title) {
        // TODO Auto-generated method stub
        ContentValues cv = new ContentValues();
        cv.put(KEY_TITLE, title);
        return ourDatabase.insert(DATABASE_TABLE, null, cv);
    }

    public ArrayList getData() {
        // TODO Auto-generated method stub
        String[] columns = new String[]{ KEY_ROWID, KEY_TITLE};
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
        String result = "";

        int iRow = c.getColumnIndex(KEY_ROWID);
        int iTitle = c.getColumnIndex(KEY_TITLE);


        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            result = result + c.getString(iRow) + " " + c.getString(iTitle) + "\n";

            movieArray.add(new Movie(c.getString(iTitle)));
        }

        return movieArray;
    }

    public String getTitle(long l) throws SQLException{
        // TODO Auto-generated method stub
        String[] columns = new String[]{ KEY_ROWID, KEY_TITLE};
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" + l, null, null, null, null);
        if (c != null){
            c.moveToFirst();
            String title = c.getString(1);
            return title;
        }
        return null;
    }

    public void updateEntry(long lRow, String mTitle) throws SQLException{
        // TODO Auto-generated method stub
        ContentValues cvUpdate = new ContentValues();
        cvUpdate.put(KEY_TITLE, mTitle);
        ourDatabase.update(DATABASE_TABLE, cvUpdate, KEY_ROWID + "=" + lRow, null);
    }
    public void deleteEntry(String lRow1) throws SQLException{
        // TODO Auto-generated method stub
        ourDatabase.delete(DATABASE_TABLE, KEY_TITLE + "=" + "'" + lRow1 + "'", null);
    }

}
