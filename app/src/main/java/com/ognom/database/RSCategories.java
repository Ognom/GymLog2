package com.ognom.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class RSCategories {

    public RSCategories(){

    }

    public Cursor getAllCategories(SQLiteDatabase db){
        Cursor c = db.rawQuery("SELECT "+DatabaseHelper.colCategoryID+" as _id,"
                +DatabaseHelper.colCategoryName+" from "+DatabaseHelper.categoryTable, new String[] {});

        return c;
    }
}
