package com.ognom.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ognom.util.Exercise;

/**
 * Created by Ognom on 2015-01-22.
 */



public class DatabaseHelper extends SQLiteOpenHelper{

    static final String dbName = "mainDB";

    static final String exerciseTable = "Exercises";
    static final String categoryTable = "Category";

    static final String colID = "ExerciseID";
    static final String colName = "ExerciseName";
    static final String colCategory = "Category";

    static final String colCategoryID = "CategoryID";
    static final String colCategoryName = "CategoryName";

    static final String viewExercises = "ViewExercises";

    private SQLiteDatabase db;

    public DatabaseHelper(Context context){

        super(context, dbName, null, 33);
    }


    //Creates an instance of the Database, fills it with desired tables and returns the DB.
    public void createDatabase(){
        db = getWritableDatabase();
        onCreate(db);
    }

    private SQLiteDatabase getDB(){
        return this.db;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Creates the Category table
        db.execSQL("CREATE TABLE IF NOT EXISTS " + categoryTable + " ("+colCategoryID+ " INTEGER PRIMARY KEY, "+
        colCategoryName+ " TEXT)");

        //Creates the Exercise Table
        db.execSQL("CREATE TABLE IF NOT EXISTS "+ exerciseTable + "("
        +colID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
        colName+" TEXT, "+colCategory+
        " INTEGER NOT NULL ,FOREIGN KEY ("+colCategory+") REFERENCES "
        +categoryTable+" ("+colCategoryID+"));");

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
        " AS SELECT "+exerciseTable+"."+colID+" AS _id,"+
        " "+exerciseTable+"."+colName+","+
        " "+categoryTable+"."+colCategoryName+""+ //TODO: WTF ""?
        " FROM "+exerciseTable+" JOIN "+categoryTable+
        " ON "+exerciseTable+"."+colCategory+" ="+categoryTable+"."+colCategoryID);

        insertValues(db); //Adds some values to the Database.

    }

    public int UpdateExercise(Exercise exercise){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(colName, exercise.getName());
        cv.put(colCategory, exercise.getCategory());

        return db.update(exerciseTable, cv, colID+"=?",
          new String[]{
          String.valueOf(exercise.getId())});
    }

    public void DeleteExercise(Exercise exercise){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(exerciseTable, colID+"=?", new String[] {String.valueOf(exercise.getId())});
        db.close();
    }

    Cursor getAllCategories(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT "+colCategoryID+" as _id,"
        +colCategoryName+" from "+categoryTable, new String[] {});

        return c;
    }

    public Cursor getExerciseByCategory(String Category){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[]{"_id", colName, colCategoryName};
        Cursor c = db.query(viewExercises, columns, colCategoryName+"=?",
                new String[]{Category}, null, null, null);
        return c;
    }

    private void insertValues(SQLiteDatabase db){
        ContentValues cv = new ContentValues();

        cv.put(colCategoryID, 1);
        cv.put(colCategoryName, "Back");
        db.insert(categoryTable, colCategoryID, cv);

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

        //db.close();
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
