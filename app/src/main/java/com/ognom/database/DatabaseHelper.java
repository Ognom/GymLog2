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
        super(context, dbName, null, 50);
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

        insertCategories(db); //Adds the default Categories to the Database.
        insertExercises(db); //Adds the default Exercises to the Database.

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


    //Inserts the default categories to the database.
    private void insertCategories(SQLiteDatabase db){
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
    }


    //Inserts the default exercises to the database.
    private void insertExercises(SQLiteDatabase db){

        ContentValues cv = new ContentValues();

        //Default back exercises
        cv.put(colExerciseName, "Marklyft");
        cv.put(colExerciseCategoryId, 1);
        db.execSQL("PRAGMA foreign_keys=ON");
        db.insert(exerciseTable, null, cv);

        cv.put(colExerciseName, "Stångrodd");
        cv.put(colExerciseCategoryId, 1);
        db.execSQL("PRAGMA foreign_keys=ON");
        db.insert(exerciseTable, null, cv);

        cv.put(colExerciseName, "Hantelrodd");
        cv.put(colExerciseCategoryId, 1);
        db.execSQL("PRAGMA foreign_keys=ON");
        db.insert(exerciseTable, null, cv);

        cv.put(colExerciseName, "Rygglyft");
        cv.put(colExerciseCategoryId, 1);
        db.execSQL("PRAGMA foreign_keys=ON");
        db.insert(exerciseTable, null, cv);

        cv.put(colExerciseName, "Chins");
        cv.put(colExerciseCategoryId, 1);
        db.execSQL("PRAGMA foreign_keys=ON");
        db.insert(exerciseTable, null, cv);

        //Default chest exercises
        cv.put(colExerciseName, "Bänkpress");
        cv.put(colExerciseCategoryId, 2);
        db.execSQL("PRAGMA foreign_keys=ON");
        db.insert(exerciseTable, null, cv);

        cv.put(colExerciseName, "Hantelpress");
        cv.put(colExerciseCategoryId, 2);
        db.execSQL("PRAGMA foreign_keys=ON");
        db.insert(exerciseTable, null, cv);

        cv.put(colExerciseName, "Decline hantelpress");
        cv.put(colExerciseCategoryId, 2);
        db.execSQL("PRAGMA foreign_keys=ON");
        db.insert(exerciseTable, null, cv);

        cv.put(colExerciseName, "Hantelflyes");
        cv.put(colExerciseCategoryId, 2);
        db.execSQL("PRAGMA foreign_keys=ON");
        db.insert(exerciseTable, null, cv);

        cv.put(colExerciseName, "Incline bänkpress");
        cv.put(colExerciseCategoryId, 2);
        db.execSQL("PRAGMA foreign_keys=ON");
        db.insert(exerciseTable, null, cv);

        cv.put(colExerciseName, "Kabelflyes");
        cv.put(colExerciseCategoryId, 2);
        db.execSQL("PRAGMA foreign_keys=ON");
        db.insert(exerciseTable, null, cv);

        //Default ab exercises
        cv.put(colExerciseName, "Crunches");
        cv.put(colExerciseCategoryId, 3);
        db.execSQL("PRAGMA foreign_keys=ON");
        db.insert(exerciseTable, null, cv);

        cv.put(colExerciseName, "Ab-wheel");
        cv.put(colExerciseCategoryId, 3);
        db.execSQL("PRAGMA foreign_keys=ON");
        db.insert(exerciseTable, null, cv);

        cv.put(colExerciseName, "Lutande sit-ups");
        cv.put(colExerciseCategoryId, 3);
        db.execSQL("PRAGMA foreign_keys=ON");
        db.insert(exerciseTable, null, cv);

        cv.put(colExerciseName, "Sidolyft med vikt");
        cv.put(colExerciseCategoryId, 3);
        db.execSQL("PRAGMA foreign_keys=ON");
        db.insert(exerciseTable, null, cv);

        cv.put(colExerciseName, "Cable crunches");
        cv.put(colExerciseCategoryId, 3);
        db.execSQL("PRAGMA foreign_keys=ON");
        db.insert(exerciseTable, null, cv);

        //Default shoulder exercises
        cv.put(colExerciseName, "Militärpress");
        cv.put(colExerciseCategoryId, 4);
        db.execSQL("PRAGMA foreign_keys=ON");
        db.insert(exerciseTable, null, cv);

        cv.put(colExerciseName, "Hantelpress");
        cv.put(colExerciseCategoryId, 4);
        db.execSQL("PRAGMA foreign_keys=ON");
        db.insert(exerciseTable, null, cv);

        cv.put(colExerciseName, "Arnold press");
        cv.put(colExerciseCategoryId, 4);
        db.execSQL("PRAGMA foreign_keys=ON");
        db.insert(exerciseTable, null, cv);

        cv.put(colExerciseName, "Militärpress, bakom");
        cv.put(colExerciseCategoryId, 4);
        db.execSQL("PRAGMA foreign_keys=ON");
        db.insert(exerciseTable, null, cv);

        cv.put(colExerciseName, "Hammercurl");
        cv.put(colExerciseCategoryId, 5);
        db.execSQL("PRAGMA foreign_keys=ON");
        db.insert(exerciseTable, null, cv);

        //Default biceps exercises
        cv.put(colExerciseName, "Stångcurl");
        cv.put(colExerciseCategoryId, 5);
        db.execSQL("PRAGMA foreign_keys=ON");
        db.insert(exerciseTable, null, cv);

        cv.put(colExerciseName, "21:an");
        cv.put(colExerciseCategoryId, 5);
        db.execSQL("PRAGMA foreign_keys=ON");
        db.insert(exerciseTable, null, cv);

        cv.put(colExerciseName, "Hantelcurl");
        cv.put(colExerciseCategoryId, 5);
        db.execSQL("PRAGMA foreign_keys=ON");
        db.insert(exerciseTable, null, cv);

        //Default triceps exercises
        cv.put(colExerciseName, "Smal bänkpress");
        cv.put(colExerciseCategoryId, 6);
        db.execSQL("PRAGMA foreign_keys=ON");
        db.insert(exerciseTable, null, cv);

        cv.put(colExerciseName, "Rep-extensions");
        cv.put(colExerciseCategoryId, 6);
        db.execSQL("PRAGMA foreign_keys=ON");
        db.insert(exerciseTable, null, cv);

        cv.put(colExerciseName, "Dips");
        cv.put(colExerciseCategoryId, 6);
        db.execSQL("PRAGMA foreign_keys=ON");
        db.insert(exerciseTable, null, cv);

        cv.put(colExerciseName, "Skull Crushers");
        cv.put(colExerciseCategoryId, 6);
        db.execSQL("PRAGMA foreign_keys=ON");
        db.insert(exerciseTable, null, cv);

        //Default leg exercises
        cv.put(colExerciseName, "Knäböj");
        cv.put(colExerciseCategoryId, 7);
        db.execSQL("PRAGMA foreign_keys=ON");
        db.insert(exerciseTable, null, cv);

        cv.put(colExerciseName, "Utfall med stång");
        cv.put(colExerciseCategoryId, 7);
        db.execSQL("PRAGMA foreign_keys=ON");
        db.insert(exerciseTable, null, cv);

        cv.put(colExerciseName, "Vadpress");
        cv.put(colExerciseCategoryId, 7);
        db.execSQL("PRAGMA foreign_keys=ON");
        db.insert(exerciseTable, null, cv);

        cv.put(colExerciseName, "Utfall med hantlar");
        cv.put(colExerciseCategoryId, 7);
        db.execSQL("PRAGMA foreign_keys=ON");
        db.insert(exerciseTable, null, cv);

        cv.put(colExerciseName, "Framsida lår, maskin");
        cv.put(colExerciseCategoryId, 7);
        db.execSQL("PRAGMA foreign_keys=ON");
        db.insert(exerciseTable, null, cv);

        cv.put(colExerciseName, "Baksida lår, maskin");
        cv.put(colExerciseCategoryId, 7);
        db.execSQL("PRAGMA foreign_keys=ON");
        db.insert(exerciseTable, null, cv);
    }


}
