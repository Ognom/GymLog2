package com.ognom.gymlog;


import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.ognom.database.RSCategories;

public class AddExerciseWindow extends ActionBarActivity{

    private Button bConfirm, bCancel;
    private EditText etExerciseName;
    private Spinner spCategories;

    @Override
    protected void onCreate (Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_exercise_window);

        etExerciseName = (EditText) findViewById(R.id.etExerciseName);
        spCategories = (Spinner) findViewById(R.id.spCategories);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, CategoryList.listOfCategories);
        spCategories.setAdapter(adapter);

    }

}
