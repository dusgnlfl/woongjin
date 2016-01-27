package com.wjthinkbig.ColorStudy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yeonsang on 2016-01-26.
 */
public class DBmanager extends SQLiteOpenHelper {
    public DBmanager(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE FIRST_COLOR( _id INTEGER PRIMARY KEY AUTOINCREMENT, first INTEGER, second INTEGER, result INTEGER);");
        db.execSQL("CREATE TABLE SECOND_COLOR( _id INTEGER PRIMARY KEY AUTOINCREMENT, first INTEGER, second INTEGER, third INTEGER, " +
                "result INTEGER);");
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public void insert(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }
}
