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

import com.ognom.database.DatabaseHelper;
import com.ognom.util.Category; //Ta bort tillsammans med bogus add-metod.
import com.ognom.util.Exercise; //Ta bort som ovanstående.

import java.util.ArrayList;


public class AddExercise extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private final String TAG = "AE";

    ArrayList<String> list = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    View v;
    boolean marked = false;
    String markedCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_exercise);

        final ListView lvExercises = (ListView) findViewById(R.id.lvCategories);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
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

    public void addExercise(View v){
        EditText et = (EditText) findViewById(R.id.etAddExercise);

        Log.d(TAG, markedCategory+" "+et.getText());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_exercise, menu);
        return true;
    }

    private void addValues(ArrayAdapter<String> a){

        Cursor c = HomeScreen.dbHelper.getExerciseByCategory("Back");
        c.moveToFirst();
        String s = c.getString(1);
        a.add(s);
        if(c.moveToNext())
            a.add(c.getString(1));
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

    public void populateList(){
        String[] s = getResources().getStringArray(R.array.category_array);

    }

}
