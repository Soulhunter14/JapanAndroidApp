package soul.japan.sqlLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xavi_ on 19/11/2017.
 */

public class JapanSQLiteHelper extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "japanDatabase";

    // Contacts table name
    private static final String TABLE_KANJIS = "Kanjis";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_KANJI = "Kanji";

    public JapanSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_KANJIS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_KANJI + " TEXT)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KANJIS);

        // Create tables again
        onCreate(db);
    }

    public boolean addKanji(Kanji kanji){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_KANJI, kanji.getKanji()); // Contact Name

        // Inserting Row
        db.insert(TABLE_KANJIS, null, values);
        db.close(); // Closing database connection
        return true;
    }

    public List<Kanji> getAllKanjis() {
        List<Kanji> kanjiList = new ArrayList<Kanji>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_KANJIS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Kanji kanji = new Kanji();
                kanji.setID(Integer.parseInt(cursor.getString(0)));
                kanji.setKanji(cursor.getString(1));
                // Adding contact to list
                kanjiList.add(kanji);
            } while (cursor.moveToNext());
        }

        // return contact list
        return kanjiList;

    }


}
