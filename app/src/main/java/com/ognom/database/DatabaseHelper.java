package com.ognom.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper{

    static final String dbName = "mainDB";

    public static final String exerciseTable = "Exercises";
    public static final String categoryTable = "Category";
    public static final String workoutTable = "Workouts";
    public static final String workoutExerciseTable = "WorkoutExercises";
    public static final String setTable ="Sets";

    public static final String Id = "_id"; //Used for all tables. Cursor adapter requires a column called _id.

    public static final String colExerciseName = "ExerciseName";
    public static final String colExerciseCategoryId = "CategoryId";

    public static final String colCategoryName = "CategoryName";

    public static final String colDate = "Date";

    public static final String colExerciseIndex = "ExerciseIndex";
    public static final String colExerciseId = "ExerciseId";
    public static final String colWorkoutId = "WorkoutId";

    public static final String colRepetitions = "Repetitions";
    public static final String colWeight = "Weight";
    public static final String colSetIndex = "SetIndex";
    public static final String colWorkoutExerciseId = "WorkoutId";

    public DatabaseHelper(Context context){
        super(context, dbName, null, 48);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Creates the Category table
        db.execSQL("CREATE TABLE IF NOT EXISTS "+
        categoryTable + " ("
        +Id+ " INTEGER PRIMARY KEY, "
        +colCategoryName+ " TEXT);"
        );


        //Creates the Exercise Table
        db.execSQL("CREATE TABLE IF NOT EXISTS "+
        exerciseTable + "("
        +Id+" INTEGER PRIMARY KEY, "
        +colExerciseName+" TEXT, "+
        colExerciseCategoryId+ " INTEGER, " +
        "FOREIGN KEY ("+colExerciseCategoryId+") REFERENCES "+
        categoryTable+" ("+Id+"));"
        );

        //Creates the Workout table. Used for storing dates.
        db.execSQL("CREATE TABLE IF NOT EXISTS "+
        workoutTable + "("
        +Id+ " INTEGER PRIMARY KEY, "
        +colDate+ " TEXT);"
        );

        db.execSQL("CREATE TABLE IF NOT EXISTS "+
        workoutExerciseTable + "("
        +Id+" INTEGER PRIMARY KEY, "
        +colExerciseIndex+ " INTEGER, "
        +colExerciseId+ " INTEGER, "
        +colWorkoutId+ " INTEGER, "
        +"FOREIGN KEY ("+colExerciseId+") REFERENCES "+exerciseTable+" ("+Id+"), "
        +"FOREIGN KEY ("+colWorkoutId+") REFERENCES "+workoutTable+" ("+Id+"));"
        );

        db.execSQL("CREATE TABLE IF NOT EXISTS "+
        setTable + "("
        +Id+" INTEGER PRIMARY KEY, "
        +colRepetitions+" INTEGER, "
        +colWeight+" INTEGER, "
        +colSetIndex+" INTEGER, "
        +colWorkoutExerciseId+" INTEGER, "
        +"FOREIGN KEY ("+colWorkoutExerciseId+") REFERENCES "+workoutExerciseTable+" ("+Id+"));"

        );


        //Trigger to ensure that when an exercise is created, the chosen category exists in the Category table
        /*db.execSQL("CREATE TRIGGER IF NOT EXISTS fk_empCategory_CategoryID " +
        " BEFORE INSERT "+
        " ON "+exerciseTable+

        " FOR EACH ROW BEGIN"+
        " SELECT CASE WHEN ((SELECT "+colCategoryName+" FROM "+categoryTable+
        " WHERE "+colCategoryName+"=new."+colCategoryName+" ) IS NULL)"+
        " THEN RAISE (ABORT,'Foreign Key Violation') END;"+
        " END;");


        db.execSQL("CREATE VIEW IF NOT EXISTS "+viewExercises+
        " AS SELECT "+exerciseTable+"."+colExerciseName+" AS _id,"+
        " "+exerciseTable+"."+colExerciseName+","+
        " "+categoryTable+"."+colCategoryName+
        " FROM "+exerciseTable+" JOIN "+categoryTable+
        " ON "+exerciseTable+"."+colExerciseCategoryId+" ="+categoryTable+"."+colCategoryName);
        */

        insertValues(db); //Adds some values to the Database.

    }

    private void insertValues(SQLiteDatabase db){
        ContentValues cv = new ContentValues();

        cv.put(Id, 1);
        cv.put(colCategoryName, "Back");
        db.insert(categoryTable, null, cv);

        cv.put(Id, 2);
        cv.put(colCategoryName, "Chest");
        db.insert(categoryTable, null, cv);

        cv.put(Id, 3);
        cv.put(colCategoryName, "Abs");
        db.insert(categoryTable, null, cv);

        cv.put(Id, 4);
        cv.put(colCategoryName, "Shoulders");
        db.insert(categoryTable, null, cv);

        cv.put(Id, 5);
        cv.put(colCategoryName, "Biceps");
        db.insert(categoryTable, null, cv);

        cv.put(Id, 6);
        cv.put(colCategoryName, "Triceps");
        db.insert(categoryTable, null, cv);

        cv.put(Id, 7);
        cv.put(colCategoryName, "Legs");
        db.insert(categoryTable, null, cv);

        cv.clear();

        cv.put(colExerciseName, "Marklyft");
        cv.put(colExerciseCategoryId, 1);
        db.execSQL("PRAGMA foreign_keys=ON");
        db.insert(exerciseTable, null, cv);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+exerciseTable);
        db.execSQL("DROP TABLE IF EXISTS "+categoryTable);
        db.execSQL("DROP TABLE IF EXISTS "+workoutTable);
        db.execSQL("DROP TABLE IF EXISTS "+workoutExerciseTable);
        db.execSQL("DROP TABLE IF EXISTS "+setTable);

        onCreate(db);
    }

}
