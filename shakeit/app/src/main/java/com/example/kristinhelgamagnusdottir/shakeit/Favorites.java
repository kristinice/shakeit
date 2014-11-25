package com.example.kristinhelgamagnusdottir.shakeit;

/**
 * Höfundur: Kristín Helga Magnúsdóttir
 * Útgáfa: 1.2
 * Dagsetning: 18. nóvember 2014
 *
 * Klasinn Favorites sér að mestu leiti um gagnagrunnsvinnuna.
 * Hann býr til gagnagrunn þegar notandinn nær í forritið í fyrsta skiptið. Hann skilgreinir einnig
 * föll til að opna, loka, setja inn í, sækja gögn og eyða gögnum úr gagnagrunninum.
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

        /**
         * Notkun: DbHelper(context)
         * Fyrir: context eru upplýsingar um stöðu í ShowJason - hvaða mynd hefur verið birt
         * Eftir: hægt er að fara að vinna í gagnagrunninum
         */
        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        /**
         * Notkun: Gerist sjálfkrafa þegar forritinu er startað í fyrsta skipti
         * Eftir: Búið er að búa til nýjan gagnagrunn sem heldur utan um vistaðar myndir
         */
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
                            KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            KEY_TITLE + " TEXT NOT NULL);"
            );
        }

        /**
         * Notkun: Gerist sjálfkrafa ef ný útgáfa af gagnagrunni hefur verið gefin út og sótt
         * Eftir: Gamla gagnagrunninum hefur verið eytt, nýr er stofnaður.
         */
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

    /**
     * Notkun: database.open()
     * Fyrir: database er gagnagrunnur
     * Eftir: database hefur verið opnaður svo hægt er að vinna í honum
     */
    public Favorites open() throws SQLException{
        ourHelper = new DbHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }
    /**
     * Notkun: database.close()
     * Fyrir: database er gagnagrunnur sem hefur verið opnaður
     * Eftir: database hefur verið lokað
     */
    public void close(){
        ourHelper.close();
    }

    /**
     * Notkun: createEntry(x)
     * Fyrir: x er titill myndarinnar sem á að setja inn í gagnagrunninn
     * Eftir: Ný röð hefur orðið til í gagnagrunninum sem inniheldur unique id og titil myndarinnar
     */
    public long createEntry(String title) {
        // TODO Auto-generated method stub
        ContentValues cv = new ContentValues();
        cv.put(KEY_TITLE, title);
        return ourDatabase.insert(DATABASE_TABLE, null, cv);
    }

    /**
     * Notkun: database.getData()
     * Fyrir: database er gagnagrunnur með tveimur dálkum sem sækja á gögn úr
     * Eftir: Skilar fylki með titlum og id-um myndarinnar
     */
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

    /**
     * Notkun: deleteEntry(x)
     * Fyrir: x er titill myndarinnar sem á að eyða úr gagnagrunninum
     * Eftir: Röð gagnagrunnsins sem innihélt mynd x er ekki lengur í gagnagrunninum
     */
    public void deleteEntry(String lRow1) throws SQLException{
        // TODO Auto-generated method stub
        ourDatabase.delete(DATABASE_TABLE, KEY_TITLE + "=" + "'" + lRow1 + "'", null);

    }

}
