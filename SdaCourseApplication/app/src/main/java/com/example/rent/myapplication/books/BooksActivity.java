package com.example.rent.myapplication.books;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.rent.myapplication.R;


public class BooksActivity extends AppCompatActivity{

    private BooksAdapter booksAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_view);
        ViewPager booksPager = (ViewPager) findViewById(R.id.book_pager);
        int[] drawableIds = {R.drawable.clean, R.drawable.effective};



    }
}
