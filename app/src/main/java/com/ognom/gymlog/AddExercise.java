package com.ognom.gymlog;

import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.ognom.database.DatabaseController;

import java.util.ArrayList;


public class AddExercise extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private final String TAG = "AE";

    ArrayList<String> list = new ArrayList<>();
    ArrayAdapter<String> adapter;

    View v;
    boolean marked = false;
    String markedCategory;
    ListView lvExercises;
    DatabaseController dbController;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_exercise);

        dbController = DatabaseController.initialize(getApplicationContext());
        lvExercises = (ListView) findViewById(R.id.lvCategories);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        lvExercises.setAdapter(adapter);

        addValues(adapter);

        lvExercises.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                markedCategory = String.valueOf(parent.getItemAtPosition(position));
                Log.d(TAG, markedCategory);
                    if(!marked){
                        v = view;
                        view.setBackgroundResource(R.color.highlighted_text_material_dark);
                        marked=true;
                    }
                    else
                    {
                        v.setBackgroundResource(R.color.background_material_light);
                        v = view;
                        view.setBackgroundResource(R.color.highlighted_text_material_dark);
                    }
            }
        });

    }


    //This function is called when the user presses the "Add"-button.
    public void addExercise(View v){
        EditText et = (EditText) findViewById(R.id.etAddExercise);

        String exerciseName = "Exercise: "+et.getText().toString()+"\n";
        String markedCategory = "Category: "+this.markedCategory;

        dbController.InsertExercise(exerciseName, markedCategory);

        Log.d(TAG, "\n" +markedCategory+" "+exerciseName+markedCategory);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_exercise, menu);
        return true;
    }

    private void addValues(ArrayAdapter<String> a){

        Cursor c = dbController.GetAllCategories();
        if(c.moveToFirst()) {
            String s = c.getString(1);
            a.add(s);
            while (c.moveToNext())
                a.add(c.getString(1));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

}
