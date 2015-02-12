package com.ognom.gymlog;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.ognom.database.DatabaseController;
import com.ognom.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ExerciseList extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private final String TAG = "CategoryList";

    Map<String, ValueHolder> map;
    ArrayList<String> list = new ArrayList<>();
    ArrayAdapter<String> adapter;

    View v;
    boolean marked = false;
    String markedCategory;
    ListView lvExercises;
    DatabaseController dbController;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_list);

        Intent i = getIntent();
        category = i.getStringExtra("Category");

        map = new HashMap<String, ValueHolder>();
        dbController = DatabaseController.initialize(getApplicationContext());
        lvExercises = (ListView) findViewById(R.id.lvExercises);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        addValues(adapter);
        lvExercises.setAdapter(adapter);



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

    @Override
    protected void onResume(){
        super.onResume();
    }


    //Called when AddExerciseWindows confirm button is clicked. Adds the exercise to the adapter.
    protected void onActivityResult (int RequestCode, int resultCode, Intent data){
        if(RequestCode == 1){
            if(resultCode == RESULT_OK){
                String s = data.getStringExtra("exerciseAdded");
                adapter.add(s);

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_exercise, menu);
        return true;
    }

    private void addValues(ArrayAdapter<String> aAdapter){
        Cursor c = dbController.GetExercisesByCategory(category);
        if(c.moveToFirst()) {
            addValueToAdapter(aAdapter, c);
            while (c.moveToNext())
                addValueToAdapter(aAdapter, c);
        }
    }


    //Called when the + - button is clicked. startActivityForResult is used to pass a String value between the activities.
    public void addNewExercise(View view){
        Intent intent = new Intent(this, AddExerciseWindow.class);
        startActivityForResult(intent, 1);
    }

    private void addValueToAdapter(ArrayAdapter<String> aAdapter, Cursor c) {
        ValueHolder holder = getValueHolder(c);
        aAdapter.add(holder.mName);
        map.put(holder.mName, holder);
    }

    public ValueHolder getValueHolder(Cursor aCursor)
    {
        Integer id = aCursor.getInt(aCursor.getColumnIndex(DatabaseHelper.Id));
        String name = aCursor.getString(aCursor.getColumnIndex(DatabaseHelper.colExerciseName));
        return new ValueHolder(id, name);
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

    private class ValueHolder {

        public Integer mId;
        public String mName;

        public ValueHolder(Integer aId, String aName)
        {
            mName = aName;
            mId = aId;
        }

    }

}
