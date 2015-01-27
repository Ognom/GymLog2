package com.ognom.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ognom.util.Exercise;

public class RSExercises {

    public RSExercises(){

    }

    public int UpdateExercise(Exercise exercise, SQLiteDatabase db){
        ContentValues cv = new ContentValues();

        cv.put(DatabaseHelper.colExerciseName, exercise.getName());
        cv.put(DatabaseHelper.colExerciseCategory, exercise.getCategory());

        return db.update(DatabaseHelper.exerciseTable, cv, DatabaseHelper.colExerciseID+"=?",
                new String[]{
                        String.valueOf(exercise.getId())});
    }

    public void DeleteExercise(SQLiteDatabase db, Exercise exercise){
        db.delete(DatabaseHelper.exerciseTable, DatabaseHelper.colExerciseID+"=?", new String[] {String.valueOf(exercise.getId())});
        db.close();
    }

    public Cursor getExerciseByCategory(String Category, SQLiteDatabase db){
        String[] columns = new String[]{DatabaseHelper.colExerciseName, DatabaseHelper.colExerciseCategory};
        Cursor c = db.query(DatabaseHelper.exerciseTable, columns, DatabaseHelper.colExerciseCategory+"=?",
                new String[]{Category}, null, null, null);
        return c;
    }

    public void insertExercise(String name, String Category, SQLiteDatabase db){
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.colExerciseName, name);
        cv.put(DatabaseHelper.colExerciseCategory, Category);
        db.insert(DatabaseHelper.exerciseTable, DatabaseHelper.colExerciseID, cv);
    }

}
