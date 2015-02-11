package com.ognom.gymlog;


import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.ognom.database.DatabaseController;
import com.ognom.database.RSCategories;
import com.ognom.database.RSExercises;

public class AddExerciseWindow extends ActionBarActivity{

    private EditText etExerciseName;
    private Spinner spCategories;
    private String categoryChosen;
    private String exerciseName;

    private String TAG = "AEW";
    private DatabaseController dc;

    @Override
    protected void onCreate (Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_exercise_window);

        etExerciseName = (EditText) findViewById(R.id.etExerciseName);
        spCategories = (Spinner) findViewById(R.id.spCategories);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, CategoryList.listOfCategories);
        spCategories.setAdapter(adapter);

    }

    //Called when the cancel button is clicked. Returns to the parent activity (ExerciseList).
    public void cancelButtonClicked(View view){
        finish();
    }

    public void confirmButtonClicked(View view){
        exerciseName = etExerciseName.getText().toString();
        categoryChosen = spCategories.getSelectedItem().toString();

        Log.d(TAG, categoryChosen+ " " + exerciseName);

        dc = dc.initialize(this);
        dc.InsertExercise(exerciseName, categoryChosen);
    }

}
