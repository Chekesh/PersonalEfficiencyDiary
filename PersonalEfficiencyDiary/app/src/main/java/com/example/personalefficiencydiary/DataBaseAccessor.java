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
    // Основные данные базы
    private static final String DATABASE_NAME = "db.db";
    private static final int DB_VERSION = 3;

    // таблицы
    private static final String TABLE_RECORDS = "RECORDS";

    // столбцы таблицы RECORDS
    private static final String RECORDS_ID = "id";
    private static final String RECORDS_DATE = "date";
    private static final String RECORDS_RATING = "rating";
    private static final String RECORDS_EVALUATION = "Evaluation";
    private static final String RECORDS_HOW_MACH = "HowMuch";
    private static final String RECORDS_HOW_MATH_NOT = "HowMuchNot";
    private static final String RECORDS_COMENT = "Coment";

    public DataBaseAccessor(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_RECORDS + "("
                + RECORDS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + RECORDS_DATE + " TEXT,"
                + RECORDS_RATING + " TEXT,"
                + RECORDS_EVALUATION + " TEXT,"
                + RECORDS_HOW_MACH + " TEXT,"
                + RECORDS_HOW_MATH_NOT + " TEXT,"
                + RECORDS_COMENT + " TEXT);");
        db.execSQL("INSERT INTO " + TABLE_RECORDS + " values(1,'2023-09-30','5','10','5','2','Сегодня был день рождения моего брата')");
        db.execSQL("INSERT INTO " + TABLE_RECORDS + " values(2,'2023-10-09','2','4','2','5','Сегодня я впал в дипресиию')");
        db.execSQL("INSERT INTO " + TABLE_RECORDS + " values(3,'2023-10-15','5','10','2','0','Сегодня я вышел из дипресии')");
        db.execSQL("INSERT INTO " + TABLE_RECORDS + " values(4,'2023-10-20','4','7','3','3','Среднестатистический день')");
    }

    public void updateNote(int id, String date,String rating, String evaluation,String howmach, String howmachnor,String coment)
    {
        // выполнить запрос на обновление БД
        getReadableDatabase().execSQL("UPDATE "+ TABLE_RECORDS
                + " SET "
                + RECORDS_DATE + "='" + date + "', "
                + RECORDS_RATING + "='" + rating + "', "
                + RECORDS_EVALUATION + "='" + evaluation + "', "
                + RECORDS_HOW_MACH + "='" + howmach + "', "
                + RECORDS_HOW_MATH_NOT + "='" + howmachnor + "', "
                + RECORDS_COMENT + "='" + coment + "'"
                + " WHERE "
                + RECORDS_ID + "=" + id);
    }

    public SimpleCursorAdapter getCursorAdapter(Context context, int layout, int[] viewIds)
    {
        // запрос данных для отображения
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_RECORDS,null);

        // какие столбцы и в каком порядке показывать в listview
        String[] columns = new  String[] {RECORDS_DATE,RECORDS_RATING};

        // создание адаптера
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(context,layout,cursor,columns,viewIds,0);
        return  adapter;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try
        {
            //удалить старую таблицу
            db.execSQL("DROP TABLE " + TABLE_RECORDS);
        }
        catch (Exception exception)
        {

        }
        finally {
            //создать новую и заполнить ее
            onCreate(db);
        }
    }
}