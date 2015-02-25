package com.ognom.gymlog;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;


public class CurrentExercise extends ActionBarActivity {

    private double weightChange = 2.5; //The value used to add or subtract current weight when the user presses the - or + button.
    private EditText etCurrentWeight;
    private ListView lvFinishedSets;
    private double currentWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_exercise);

        etCurrentWeight = (EditText)findViewById(R.id.etCurrentWeight);
    }

    //Called when the user clicks the plus-button. Edits the textview to its current value + weightChange.
    public void onClickPlus(View view){

    }

    //Called when the user clicks the minus-button. Edits the textview to its current value - weightChange.
    public void onClickMinus(View view){

    }


}
