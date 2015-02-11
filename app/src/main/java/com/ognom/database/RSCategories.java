package com.ognom.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class RSCategories {

    public RSCategories(){

    }

    public Cursor getAllCategories(SQLiteDatabase db){
        Cursor c = db.rawQuery("SELECT * FROM " + DatabaseHelper.categoryTable, new String[] {});

        return c;
    }

    public Integer getCategoryId(SQLiteDatabase db, String categoryName){
        Cursor c = db.rawQuery("SELECT _id FROM " + DatabaseHelper.categoryTable + " WHERE " + DatabaseHelper.colCategoryName+ " = ?", new String[]{categoryName});
        c.moveToFirst();
        return c.getInt(0);
    }

}
