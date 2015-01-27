package com.ognom.gymlog;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ognom.database.DatabaseHelper;


public class HomeScreen extends ActionBarActivity {

    private final String TAG = "HS";

    public static DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home_screen);

        //final Button bAddExercise = (Button) findViewById(R.id.bAddExercise);
        createDBInstance(getApplicationContext());
        Log.d(TAG, "onCreate"); //Used for debugging.
    }

    public void addExercise(View view){
        Log.d(TAG, "Add Exercise-button clicked");

        Intent intent = new Intent(this, AddExercise.class);
        startActivity(intent);

    }

    public void createDBInstance(Context context){
        if(dbHelper == null){
            dbHelper = new DatabaseHelper(context);
            Log.d(TAG, "createDBInstance");
          }
        else
            Log.d(TAG, "Failed at createDBInstance");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
