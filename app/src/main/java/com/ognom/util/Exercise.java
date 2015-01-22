/**
 * Created by Ognom on 2015-01-09.
 */

package com.ognom.util;

public class Exercise {

    private String name;
    private String category;

    public Exercise(String name, String category){
        setName(name);
        setCategory(category);
    }

    private void setName(String name){
        this.name = name;
    }

    private void setCategory(String category){
        this.category = category;
    }

}
