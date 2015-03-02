package com.ognom.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.Calendar;

public class RSWorkouts {

    private String TAG = "RSWorkouts";

    public RSWorkouts(){

    }

    private void insertWorkout(SQLiteDatabase db){
        Calendar c = Calendar.getInstance();
        int d = c.get(Calendar.DATE);

        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.colDate, d);
        db.insertWithOnConflict(DatabaseHelper.workoutTable, null, cv, SQLiteDatabase.CONFLICT_REPLACE);

        Log.d(TAG, "Tried inserting " + d + "in workouts");
    }
}
