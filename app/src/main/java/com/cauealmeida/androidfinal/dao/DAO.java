package com.cauealmeida.androidfinal.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.database.sqlite.SQLiteDatabase.CREATE_IF_NECESSARY;
import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

/**
 * Created by cauealmeida on 3/19/17.
 */

public class DAO extends SQLiteOpenHelper {

    public DAO(Context context) {
        super(context, "users.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String sql = "CREATE TABLE IF NOT EXISTS TAB_HEROES ("
                + "ID INTEGER primary key AUTOINCREMENT,"
                + "NAME TEXT, BRAND TEXT )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void delete(String id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("TAB_HEROES", "ID = ?", new String[]{id});
    }
}
