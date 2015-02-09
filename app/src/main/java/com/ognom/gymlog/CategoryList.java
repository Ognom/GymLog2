package com.ognom.gymlog;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;


import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ognom.database.DatabaseController;
import com.ognom.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class CategoryList extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private final String TAG = "CategoryList";

    Map<String, ValueHolder> map;
    ArrayList<String> list = new ArrayList<>();
    ArrayAdapter<String> adapter;

    String markedCategory;
    ListView lvCategories;
    DatabaseController dbController;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_list);

        map = new HashMap<String, ValueHolder>();
        dbController = DatabaseController.initialize(getApplicationContext());
        lvCategories = (ListView) findViewById(R.id.lvCategories);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        lvCategories.setAdapter(adapter);

        addValues(adapter);

        lvCategories.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                markedCategory = String.valueOf(parent.getItemAtPosition(position));
                Intent intent = new Intent(CategoryList.this, ExerciseList.class);
                intent.putExtra("Category", markedCategory);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_exercise, menu);
        return true;
    }

    private void addValues(ArrayAdapter<String> aAdapter){
        Cursor c = dbController.GetAllCategories();
        if(c.moveToFirst()) {
            addValueToAdapter(aAdapter, c);
            while (c.moveToNext())
                addValueToAdapter(aAdapter, c);
        }
    }

    private void addValueToAdapter(ArrayAdapter<String> aAdapter, Cursor c) {
        ValueHolder holder = getValueHolder(c);
        aAdapter.add(holder.mName);
        map.put(holder.mName, holder);
    }

    public ValueHolder getValueHolder(Cursor aCursor)
    {
        Integer id = aCursor.getInt(aCursor.getColumnIndex(DatabaseHelper.Id));
        String name = aCursor.getString(aCursor.getColumnIndex(DatabaseHelper.colCategoryName));
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
