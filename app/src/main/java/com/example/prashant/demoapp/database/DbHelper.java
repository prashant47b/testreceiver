package com.example.prashant.demoapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by prashant on 5/3/2016.
 */
public class DbHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME="students";
    public static final String KEY_ID="_ID";
    public static final String KEY_NAME="NAME";
    public static final String KEY_MOBILE="MOBILE";

    public DbHelper(Context context) {
        super(context, "dbhelper.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query="CREATE TABLE "+TABLE_NAME+"("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+KEY_NAME+" TEXT ,"+KEY_MOBILE+" TEXT)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}
