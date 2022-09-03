package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG= "DatabaseHelper";

    private static final String DB = "SampleDB";

    //STUDENT TABLE VARIABLES
    private static final String STUDENT_TABLE_NAME = "student";
    private static final String STUDENT_NAME = "name";




    public DatabaseHelper(@Nullable Context context) {

        super(context, DB, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE "+STUDENT_TABLE_NAME+"(id INTEGER PRIMARY KEY AUTOINCREMENT, "+STUDENT_NAME+" TEXT)";
        db.execSQL(createTable);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE_NAME);
        onCreate(db);
    }

    //adding data to db
    public  boolean addStudentData(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(STUDENT_NAME, name);

        Log.d(TAG, "AddData: Adding "+ name + " to "+ STUDENT_NAME);

        long result = db.insert(STUDENT_TABLE_NAME, null, contentValues);

        if(result == -1){
            return false;
        }else{
            return true;
        }

    }

    public Cursor getStudentData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " +  STUDENT_TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;

    }




}
