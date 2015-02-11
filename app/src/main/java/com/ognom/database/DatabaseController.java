package com.ognom.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseController {

    private static DatabaseController dc = null;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private RSCategories RScategories;
    private RSExercises RSexercises;

    private DatabaseController (Context context){
        this.dbHelper = new DatabaseHelper(context);
        RScategories = new RSCategories();
        RSexercises = new RSExercises();
    }

    //Starts a new instance of DatabaseController if no earlier instance exists.
    public static DatabaseController initialize(Context context){
        if(dc == null){
            dc = new DatabaseController(context);
        }
        return dc;
    }

    public static DatabaseController initialize(){
        if(dc == null){
            return null;
        }
        return dc;
    }

    public void InsertExercise(String aName, Integer aCategoryId){
        db = dbHelper.getReadableDatabase();
        RSexercises.insertExercise(aName, aCategoryId, db);
    }

    public void InsertExercise(String aName, String aCategoryName){
        db = dbHelper.getReadableDatabase();
        Integer categoryId = RScategories.getCategoryId(db, aCategoryName);
        RSexercises.insertExercise(aName, categoryId, db);
    }

    public Cursor GetAllCategories(){
        db = dbHelper.getReadableDatabase();
        Cursor c = RScategories.getAllCategories(db);
        return c;
    }

    public Cursor GetExercisesByCategory(String category){
        db = dbHelper.getReadableDatabase();
        Cursor c = RSexercises.getExerciseByCategoryName(category, db);
        return c;
    }

}
