package com.example.personalefficiencydiary;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.Nullable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DataBaseAccessor extends SQLiteOpenHelper {
    // Основные данные базы
    private static final String DATABASE_NAME = "db.db";
    private static final int DB_VERSION = 7;

    // таблицы
    private static final String TABLE_RECORDS = "RECORDS";

    // столбцы таблицы RECORDS
    private static final String RECORDS_ID = "_id";
    private static final String RECORDS_DATE = "date";
    private static final String RECORDS_RATING = "rating";
    private static final String RECORDS_HOW_MACH = "HowMuch";
    private static final String RECORDS_HOW_MATH_NOT = "HowMuchNot";
    private static final String RECORDS_COMENT = "Coment";

    public DataBaseAccessor(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_RECORDS + "(" +
                RECORDS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                RECORDS_DATE + " TEXT UNIQUE, " +
                RECORDS_RATING + " TEXT, " +
                RECORDS_HOW_MACH + " TEXT, " +
                RECORDS_HOW_MATH_NOT + " TEXT, " +
                RECORDS_COMENT + " TEXT);");
        db.execSQL("INSERT INTO " + TABLE_RECORDS + " values(1,'2023-09-30','5','5','2','Сегодня был день рождения моего брата')");
        db.execSQL("INSERT INTO " + TABLE_RECORDS + " values(2,'2023-10-09','2','2','5','Сегодня я впал в дипресиию')");
        db.execSQL("INSERT INTO " + TABLE_RECORDS + " values(3,'2023-10-15','5','2','0','Сегодня я вышел из дипресии')");
        db.execSQL("INSERT INTO " + TABLE_RECORDS + " values(4,'2023-10-20','4','3','3','Среднестатистический день')");
    }


    @SuppressLint("Range")
    public List<String> selectNote(String date) {
        List<String> resultList = new ArrayList<>();
        String[] selectionArgs = {date};
        Cursor cursor = getReadableDatabase().rawQuery("SElECT " + RECORDS_RATING + ", " +
                RECORDS_HOW_MACH + ", " +
                RECORDS_HOW_MATH_NOT + ", " +
                RECORDS_COMENT + " FROM " +
                TABLE_RECORDS + " WHERE " +
                RECORDS_DATE + " = ?;",
                selectionArgs);
        if(cursor.moveToFirst()) {
            do{
                resultList.add(cursor.getString(cursor.getColumnIndex(RECORDS_RATING)));
                resultList.add(cursor.getString(cursor.getColumnIndex(RECORDS_HOW_MACH)));
                resultList.add(cursor.getString(cursor.getColumnIndex(RECORDS_HOW_MATH_NOT)));
                resultList.add(cursor.getString(cursor.getColumnIndex(RECORDS_COMENT)));

            } while (cursor.moveToNext());
        }

        cursor.close();

        return resultList;
    }

    public void NewNote(String date){
        ContentValues values = new ContentValues();
        values.put(RECORDS_DATE, date);
        values.put(RECORDS_DATE, date);
        values.put(RECORDS_RATING, " ");
        values.put(RECORDS_HOW_MACH, " ");
        values.put(RECORDS_HOW_MATH_NOT, " ");
        values.put(RECORDS_COMENT, " ");

        getWritableDatabase().insert(TABLE_RECORDS, null, values);

    }

    public void updateNote(String date, String rating, String howmach, String howmachnor, String coment) {
        ContentValues values = new ContentValues();
        values.put(RECORDS_RATING, rating);
        values.put(RECORDS_HOW_MACH, howmach);
        values.put(RECORDS_HOW_MATH_NOT, howmachnor);
        values.put(RECORDS_COMENT, coment);

        getReadableDatabase().update(TABLE_RECORDS, values, RECORDS_DATE + "=?", new String[]{date});
    }

    public SimpleCursorAdapter getCursorAdapter(Context context, int layout, int[] viewIds) {
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_RECORDS, null);

        String[] columns = new String[]{RECORDS_DATE, RECORDS_RATING};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(context, layout, cursor, columns, viewIds, 0);
        return adapter;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("DROP TABLE " + TABLE_RECORDS);
        } catch (Exception exception) {

        } finally {
            onCreate(db);
        }
    }
}