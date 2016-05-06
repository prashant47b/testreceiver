package com.example.prashant.demoapp.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.prashant.demoapp.database.DbHelper;

/**
 * Created by prashant on 5/3/2016.
 */
public class DataProvider extends ContentProvider {

    private SQLiteDatabase database;

    @Override
    public boolean onCreate() {
        DbHelper dbHelper = new DbHelper(getContext());
        database = dbHelper.getWritableDatabase();
        return database != null;
    }

    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings2, String s2) {
        Cursor cursor = database.query(DbHelper.TABLE_NAME, null, null, null, null, null, null);
        return cursor != null ? cursor : null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        try {
            long rowId = database.insert(DbHelper.TABLE_NAME, null, contentValues);
            if (rowId > 0) {
                Uri _uri = ContentUris.withAppendedId(uri, rowId);
                getContext().getContentResolver().notifyChange(_uri, null);
                return _uri;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        int delete_id = database.delete(DbHelper.TABLE_NAME, s, strings);
        return delete_id > 0 ? delete_id : 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        int update_id = database.update(DbHelper.TABLE_NAME, contentValues, s, strings);
        return update_id > 0 ? update_id : 0;
    }
}
