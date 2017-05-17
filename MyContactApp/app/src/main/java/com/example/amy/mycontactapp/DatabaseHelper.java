package com.example.amy.mycontactapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.StrictMode;

/**
 * Created by Amy on 5/11/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Contact.db"; //constant
    public static final String TABLE_NAME = "contact_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "NUMBER: ";
    public static final String COL_4 = "AGE: ";
    public static int id = 1;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT)");//space after command
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String number, String age){
        SQLiteDatabase db = this.getWritableDatabase();
        //ContentValues contentValues = new ContentValues();
        //contentValues.put(COL_1, id);
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put(COL_2, name);
        //ContentValues contentValues3 = new ContentValues();
        //contentValues3.put(COL_3, number);
        //ContentValues contentValues4 = new ContentValues();
        //contentValues4.put(COL_4, age);

        //long result = db.insert(TABLE_NAME, null, contentValues);
        long result2 = db.insert(TABLE_NAME, null, contentValues2);
        //long result3 = db.insert(TABLE_NAME, null, contentValues3);
        //long result4 = db.insert(TABLE_NAME, null, contentValues4);
        if(result2 == -1 ) return false;
        else
        {
            id++;
            return true;
        }
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
}
