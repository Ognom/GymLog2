/**
 * Created by Ognom on 2015-01-09.
 */

package com.ognom.util;

public class Exercise {

    private String name;
    private String category;
    private int id;

    public Exercise(String name, String category, int id){
        setName(name);
        setCategory(category);
        setId(id);
    }

    private void setName(String name){
        this.name = name;
    }

    private void setId(int id){
        this.id = id;
    }

    private void setCategory(String category){
        this.category = category;
    }

    public String getName(){
        return this.name;
    }

    public String getCategory(){
        return this.category;
    }

    public int getId(){
        return this.id;
    }

}
