package com.example.rent.myapplication.books;

import android.support.annotation.DrawableRes;

/**
 * Created by RENT on 2017-03-02.
 */

public class Book {



    @DrawableRes
    private int drawableResourceId;
    private String title;
    private int id;
    private boolean isRead;

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public int getId() {
        return id;
    }

    public Book(int id, @DrawableRes int drawableResourceId, String title){
        this.id = id;
        this.drawableResourceId = drawableResourceId;
        this.title = title;
    }

    public int getDrawableResourceId() {
        return drawableResourceId;
    }

    public String getTitle() {
        return title;
    }
}
