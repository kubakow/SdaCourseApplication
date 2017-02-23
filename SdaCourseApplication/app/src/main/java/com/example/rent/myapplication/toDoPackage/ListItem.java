package com.example.rent.myapplication.toDoPackage;

/**
 * Created by RENT on 2017-02-23.
 */

public class ListItem {
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    String text;
    boolean checked;

    public ListItem(String text){
        this.text = text;
    }
}
