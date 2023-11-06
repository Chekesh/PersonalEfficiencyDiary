package com.example.personalefficiencydiary;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.Nullable;

import java.io.File;

public class DataBaseAccessor extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "db.db";
    private static final int DB_VERSION = 3;

    private static final String TABLE_NOTE = "NOTE";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TIME = "time";
    private static final String COLUMN_RATING = "rating";
    private static final String COLUMN_COUNT = "count";
    private static final String COLUMN_COMM = "comment";

    public DataBaseAccessor(Context context) {
        super(context, TABLE_NOTE, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NOTE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TIME + " TEXT,"
                + COLUMN_RATING + " TEXT,"
                + COLUMN_COUNT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}