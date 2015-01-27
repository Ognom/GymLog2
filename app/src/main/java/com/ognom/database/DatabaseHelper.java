package com.ognom.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper{

    static final String dbName = "mainDB";

    static final String exerciseTable = "Exercises";
    static final String categoryTable = "Category";

    static final String colExerciseID = "ExerciseID";
    static final String colExerciseName = "ExerciseName";
    static final String colExerciseCategory = "Category";

    static final String colCategoryID = "CategoryID";
    static final String colCategoryName = "CategoryName";

    static final String viewExercises = "ViewExercises";

    public DatabaseHelper(Context context){
        super(context, dbName, null, 37);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Creates the Category table
        db.execSQL("CREATE TABLE IF NOT EXISTS "+
        categoryTable + " ("+colCategoryID+ " INTEGER PRIMARY KEY, "+
        colCategoryName+ " TEXT)");

        //Creates the Exercise Table
        db.execSQL("CREATE TABLE IF NOT EXISTS "+
        exerciseTable + "("
        +colExerciseID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
        colExerciseName+" TEXT, "+
        colExerciseCategory+ " INTEGER NOT NULL, " +
        "FOREIGN KEY ("+colExerciseCategory+") REFERENCES "+
        categoryTable+" ("+colCategoryName+"));"
        );


        //Trigger to ensure that when an exercise is created, the chosen category exists in the Category table
        db.execSQL("CREATE TRIGGER IF NOT EXISTS fk_empCategory_CategoryID " +
        " BEFORE INSERT "+
        " ON "+exerciseTable+

        " FOR EACH ROW BEGIN"+
        " SELECT CASE WHEN ((SELECT "+colCategoryID+" FROM "+categoryTable+
        " WHERE "+colCategoryID+"=new."+colCategoryID+" ) IS NULL)"+
        " THEN RAISE (ABORT,'Foreign Key Violation') END;"+
        " END;");


        db.execSQL("CREATE VIEW IF NOT EXISTS "+viewExercises+
        " AS SELECT "+exerciseTable+"."+colExerciseID+" AS _id,"+
        " "+exerciseTable+"."+colExerciseName+","+
        " "+categoryTable+"."+colCategoryName+
        " FROM "+exerciseTable+" JOIN "+categoryTable+
        " ON "+exerciseTable+"."+colExerciseCategory+" ="+categoryTable+"."+colCategoryID);


        insertValues(db); //Adds some values to the Database.

    }

    private void insertValues(SQLiteDatabase db){
        ContentValues cv = new ContentValues();

        cv.put(colCategoryID, 1);
        cv.put(colCategoryName, "Back");
        db.insert(categoryTable, colCategoryID, cv);

        cv.clear();

        cv.put(colExerciseID, 1);
        cv.put(colExerciseName, "Marklyft");
        cv.put(colExerciseCategory, "Back");
        db.insert(exerciseTable, colExerciseID, cv);

        cv.clear();

        cv.put(colCategoryID, 2);
        cv.put(colCategoryName, "Chest");
        db.insert(categoryTable, colCategoryID, cv);

        cv.clear();

        cv.put(colCategoryID, 3);
        cv.put(colCategoryName, "Abs");
        db.insert(categoryTable, colCategoryID, cv);

        cv.clear();

        cv.put(colCategoryID, 4);
        cv.put(colCategoryName, "Shoulders");
        db.insert(categoryTable, colCategoryID, cv);

        cv.clear();

        cv.put(colCategoryID, 5);
        cv.put(colCategoryName, "Biceps");
        db.insert(categoryTable, colCategoryID, cv);

        cv.clear();

        cv.put(colCategoryID, 6);
        cv.put(colCategoryName, "Triceps");
        db.insert(categoryTable, colCategoryID, cv);

        cv.clear();

        cv.put(colCategoryID, 7);
        cv.put(colCategoryName, "Legs");
        db.insert(categoryTable, colCategoryID, cv);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+exerciseTable);
        db.execSQL("DROP TABLE IF EXISTS "+categoryTable);

        db.execSQL("DROP TRIGGER IF EXISTS category_id_trigger");
        db.execSQL("DROP TRIGGER IF EXISTS category_id_trigger2");
        db.execSQL("DROP TRIGGER IF EXISTS fk_empCategory_CategoryID");
        db.execSQL("DROP VIEW IF EXISTS "+viewExercises);
        onCreate(db);
    }

}
