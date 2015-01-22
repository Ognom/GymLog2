/**
 * Created by Ognom on 2015-01-14.
 */

package com.ognom.util;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private ArrayList<Exercise> listOfExercises;
    private String name;

    public Category(Exercise e, String name){
        listOfExercises = new ArrayList<Exercise>();
        this.name = name;
        listOfExercises.add(e);
    }

    public Category(String name){
        listOfExercises = new ArrayList<Exercise>();
    }

    public void addExercise(Exercise e){
        listOfExercises.add(e);
    }

    public String getName(){
        return this.name;
    }

    public ArrayList<Exercise> getList(){
        return this.listOfExercises;
    }

}
