package com.example.groupproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, "Userdata.db", null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE Userdetails(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, first_name VARCHAR, surname VARCHAR, username VARCHAR, password VARCHAR, role VARCHAR)");
        DB.execSQL("CREATE TABLE Assignments(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, title VARCHAR, description VARCHAR, subject_name VARCHAR)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("DROP TABLE IF EXISTS Userdetails");
        DB.execSQL("DROP TABLE IF EXISTS Assignments");
    }
    // Insert into Users table
    public Boolean insertUserData(String firstName, String surname, String username, String password, String role){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("first_name", firstName);
        contentValues.put("surname", surname);
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("role", role);
        long result=DB.insert("Userdetails",null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    // Update users table
    public Boolean updateUserData(String firstName, String surname, String username, String password, String role){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("first_name", firstName);
        contentValues.put("surname", surname);
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("role", role);

        Cursor cursor=DB.rawQuery("Select * from Userdetails where username=?", new String[]{username});
        if(cursor.getCount()>0) {
            long result = DB.update("Userdetails", contentValues, "name=?",
                    new String[]{username});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{

            return false;
        }
    }
    // Delete from users table
    public Boolean deleteUserData(String username){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("SELECT * FROM Userdetails WHERE username=?", new String[]{username});
        if(cursor.getCount()>0) {
            long result = DB.delete("Userdetails", "name=?", new String[]{username});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }
    // Fetch from users table
    public Cursor getUserData(){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("SELECT * FROM Userdetails", null);

        return cursor;
    }
    // Check if username and password exist in db (login)
    public Boolean checkUsernamePassword(String username, String password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("SELECT * FROM Userdetails WHERE username=? AND password=?", new String[]{username, password});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
    // Insert into Assignments table
    public Boolean insertAssignmentDetails(String title, String description, String subjectName){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("title", title);
        contentValues.put("description", description);
        contentValues.put("subject_name", subjectName);

        long result=DB.insert("Assignments",null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    // Fetch from users table
    public Cursor getAssignmentData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Assignments", null);

        return cursor;
    }
}
