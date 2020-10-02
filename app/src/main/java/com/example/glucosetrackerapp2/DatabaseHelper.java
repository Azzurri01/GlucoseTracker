package com.example.glucosetrackerapp2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static String DATABASE_NAME = "Glucose.db";
    private static String TABLE_NAME = "Glucose_table";
    private static String COL_1 = "ID";
    private static String COL_2 = "Weight";
    private static String COL_3 = "Food";
    private static String COL_4 = "Exercise";
    private static String COL_5 = "Glucose_mmol";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, WEIGHT INTEGER, FOOD TEXT, EXERCISE TEXT, GLUCOSE_MMOL INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String Weight, String Food, String Exercise, String Glucose_Mmol)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2, Weight);
        contentValues.put(COL_3, Food);
        contentValues.put(COL_4, Exercise);
        contentValues.put(COL_5, Glucose_Mmol);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result==-1)
        {
            return false;
        }
            else
            {
                return true;
            }
    }

    public boolean updateData(String ID, String Weight, String Food, String Exercise, String Glucose_Mmol)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1, ID);
        contentValues.put(COL_2, Weight);
        contentValues.put(COL_3, Food);
        contentValues.put(COL_4, Exercise);
        contentValues.put(COL_5, Glucose_Mmol);

        db.update(TABLE_NAME, contentValues, "ID=?", new String[] {ID});
        return true;
    }

    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from " + TABLE_NAME, null);
        return result;
    }

    public Integer deleteData(String ID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID=?", new String[] {ID});
    }
}
