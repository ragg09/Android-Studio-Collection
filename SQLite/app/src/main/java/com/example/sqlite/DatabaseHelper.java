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

    //DATABASE NAME
    private static final String DB = "SampleDB";

    //STUDENT TABLE VARIABLES
    private static final String STUDENT_TABLE_NAME = "student";
    private static final String STUDENT_NAME = "name";
    private static final String STUDENT_LNAME = "lname";
    private static final String STUDENT_COURSE = "course";




    public DatabaseHelper(@Nullable Context context) {
        //SETTING DATABASE
        super(context, DB, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //CREATING TABLE
        String createTable = "CREATE TABLE "+STUDENT_TABLE_NAME+"(id INTEGER PRIMARY KEY AUTOINCREMENT, "+STUDENT_NAME+" TEXT, "+STUDENT_LNAME+" TEXT, "+STUDENT_COURSE+" TEXT)";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE_NAME);
        onCreate(db);
    }

    //ADDING NG DATA
    public  boolean addStudentData(String name, String lname, String course){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(STUDENT_NAME, name);
        contentValues.put(STUDENT_LNAME, lname);
        contentValues.put(STUDENT_COURSE, course);

        Log.d(TAG, "AddData: Adding "+ name + " to "+ STUDENT_NAME);

        long result = db.insert(STUDENT_TABLE_NAME, null, contentValues);

        if(result == -1){
            return false;
        }else{
            return true;
        }

    }

    //UPDATE NG DATA
    public void updateStudentData(int id, String name, String lname, String course){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+STUDENT_TABLE_NAME+" SET name = '"+name+"', lname = '"+lname+"', course = '"+course+"' WHERE id = "+id;
        db.execSQL(query);
    }

    //DELETE NG DATA
    public void deleteStudentData(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM "+STUDENT_TABLE_NAME+" WHERE id="+id;
        db.execSQL(query);
    }

    //GETTING LAHAT NG DATA, PANG VIEW PURPOSES
    public Cursor getStudentData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " +  STUDENT_TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;

    }

    //GETTING SPECIFIC DATA USING NAME, PERO ID ANG BALIK || TAKE NOTE KUNG ANONG PARAM ANG NEED
    public Cursor getStudentID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM student WHERE name = '"+name+"'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }




}
