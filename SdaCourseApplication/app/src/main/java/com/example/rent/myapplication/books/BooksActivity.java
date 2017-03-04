package com.example.rent.myapplication.books;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.example.rent.myapplication.MainActivity;
import com.example.rent.myapplication.R;

import java.util.ArrayList;
import java.util.List;


public class BooksActivity extends AppCompatActivity {

    private List<Book> books;
    private BooksAdapter booksAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_view);
        ViewPager booksPager = (ViewPager) findViewById(R.id.book_pager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(booksPager);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        books = new ArrayList<>();
        Book cleanCode = new Book(1, R.drawable.clean, "Clean code");
        cleanCode.setRead(sharedPreferences.getBoolean(String.valueOf(cleanCode.getId()),false));
        books.add(cleanCode);
        Book effectiveJava = new Book(2, R.drawable.effective, "Effective java");
        effectiveJava.setRead(sharedPreferences.getBoolean(String.valueOf(effectiveJava.getId()),false));
        books.add(effectiveJava);
        Book headFirst = new Book(3, R.drawable.headfirst, "Head First: Design Patterns");
        headFirst.setRead(sharedPreferences.getBoolean(String.valueOf(headFirst.getId()),false));
        books.add(headFirst);



        booksAdapter = new BooksAdapter(books, sharedPreferences);
        booksPager.setAdapter(booksAdapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                onBackPressed();
                break;
            }
        }

        return super.onOptionsItemSelected(item);

    }

}