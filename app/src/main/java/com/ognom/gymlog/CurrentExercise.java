package com.ognom.gymlog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


public class CurrentExercise extends ActionBarActivity {

    private double weightChange = 2.5; //The value used to add or subtract current weight when the user presses the - or + button.
    private EditText etCurrentWeight, etReps;
    private ListView lvFinishedSets;
    private double currentWeight = 0.0;
    private Button update, cancel;

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_exercise);

        etCurrentWeight = (EditText)findViewById(R.id.etCurrentWeight);
        etCurrentWeight.setText(Double.toString(currentWeight)); //Initial weight of 0.0

        etReps = (EditText) findViewById(R.id.etReps);
        etReps.setText("0");

        lvFinishedSets = (ListView) findViewById(R.id.lvCompletedSets);
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
        lvFinishedSets.setAdapter(adapter);

        lvFinishedSets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setButtonsVisible();
            }
        });


        //Sets the buttons to invisible. Will only be visible when the user wishes to update a completed set (not default).
        cancel = (Button) findViewById(R.id.bCancel);

        update = (Button) findViewById(R.id.bUpdate);

        Intent i = getIntent();
        String exercise = i.getStringExtra("Exercise");
        setTitle(exercise);
    }

    //Called when the user clicks the plus-button. Edits the textview to its current value + weightChange.
    public void onClickPlus(View view){
        double d = getCurrentWeight()+weightChange;
        etCurrentWeight.setText(Double.toString(d));
    }

    //Called when the user clicks the minus-button. Edits the textview to its current value - weightChange.
    public void onClickMinus(View view){
        double d = getCurrentWeight()-weightChange;
        if(d < 0) //Makes sure we don't get negative values
            d = 0.0;
        etCurrentWeight.setText(Double.toString(d));
    }

    //Called when the user clicks the Add set-button. Displays the newly added set in a listview and stores the set in the database.
    public void addSet(View view){
        double weight = getCurrentWeight();
        int reps = getCurrentReps();

        String s = reps + " x " + currentWeight + " kgs";
        adapter.add(s);

        //TODO: Add code that puts the added set in the database

    }

    //Called when the update button is clicked.
    public void updateSet(View view){
        setButtonsInvisible();

    }

    //Called when the cancel button is clicked.
    public void cancelButton (View view){
        setButtonsInvisible();
    }


    //Returns the current value in the editText as a double.
    private double getCurrentWeight(){
        return Double.parseDouble(etCurrentWeight.getText().toString());
    }

    private int getCurrentReps(){
        return Integer.parseInt(etReps.getText().toString());
    }

    //Makes both buttons visible to the user. Called whenever a set is clicked in the listview.
    private void setButtonsVisible(){
        cancel.setVisibility(View.VISIBLE);
        update.setVisibility(View.VISIBLE);
    }

    //Makes both buttons invisible to the user. Called when the user is done updating a finished set or cancels.
    private void setButtonsInvisible(){
        cancel.setVisibility(View.GONE);
        update.setVisibility(View.GONE);
    }

}
