package com.ognom.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DatabaseController {

    private static DatabaseController dc = null;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private RSCategories RScategories;
    private RSExercises RSexercises;

    private String TAG = "DatabaseController";

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

    //Returns true if the exercise was successfully added to the database. False if not (an exercise with that name already exists)
    public boolean InsertExercise(String aName, String aCategoryName){
        db = dbHelper.getReadableDatabase();
        Integer categoryId = RScategories.getCategoryId(db, aCategoryName);
        if(RSexercises.exerciseExists(aName, db) || aName == ""){
            Log.d(TAG, "An exercise with that name already exists");
            return false;
        }
        RSexercises.insertExercise(aName, categoryId, db);
        return true;
    }

    public Cursor GetAllCategories(){
        db = dbHelper.getReadableDatabase();
        return RScategories.getAllCategories(db);
    }

    public Cursor GetExercisesByCategory(String category){
        db = dbHelper.getReadableDatabase();
        return RSexercises.getExerciseByCategoryName(category, db);
    }

}
