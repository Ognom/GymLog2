package com.ognom.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ognom.util.Exercise;

public class RSExercises {

    private String mSelectString = "SELECT * FROM Exercises WHERE (CategoryId = (SELECT _id FROM Category WHERE CategoryName = ?))";

    public RSExercises(){

    }

    public int UpdateExercise(Exercise exercise, SQLiteDatabase db){
        ContentValues cv = new ContentValues();

        cv.put(DatabaseHelper.colExerciseName, exercise.getName());
        cv.put(DatabaseHelper.colExerciseCategoryId, exercise.getCategory());

        return db.update(DatabaseHelper.exerciseTable, cv, DatabaseHelper.colExerciseName+"=?",
                new String[]{
                        String.valueOf(exercise.getId())});
    }

    public void DeleteExercise(SQLiteDatabase db, Exercise exercise){
        db.delete(DatabaseHelper.exerciseTable, DatabaseHelper.colExerciseName+"=?", new String[] {String.valueOf(exercise.getId())});
        db.close();
    }

    public Cursor getExerciseByCategoryName(String aCategoryName, SQLiteDatabase db){
        String whereClause = createCategoryNameWhereClause(aCategoryName);
        Cursor c = db.rawQuery(mSelectString, new String[]{aCategoryName});

        return c;
    }

    public Cursor getExerciseById(Integer aCategoryId, SQLiteDatabase db){
        Cursor c = db.rawQuery(mSelectString, new String[]{"*", DatabaseHelper.exerciseTable, "_id = " + aCategoryId});
        return c;
    }

    private String createCategoryNameWhereClause(String aCategoryName) {
        return DatabaseHelper.colExerciseCategoryId + " = (SELECT _id FROM " + DatabaseHelper.categoryTable + " WHERE " + DatabaseHelper.colCategoryName + " = "  +aCategoryName+ ")";
    }

    public void insertExercise(String aName, Integer aCategoryId, SQLiteDatabase db){
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.colExerciseName, aName);
        cv.put(DatabaseHelper.colExerciseCategoryId, aCategoryId);
        db.execSQL("PRAGMA foreign_keys=ON");
        db.insertWithOnConflict(DatabaseHelper.exerciseTable, null, cv, SQLiteDatabase.CONFLICT_REPLACE);
    }

}
