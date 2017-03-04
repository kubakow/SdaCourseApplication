package com.example.rent.myapplication.books;

import android.content.SharedPreferences;
import android.media.Image;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.example.rent.myapplication.R;

import java.util.List;

/**
 * Created by RENT on 2017-03-02.
 */

public class BooksAdapter extends PagerAdapter {

    private List<Book> books;
    private SharedPreferences sharedPreferences;

    BooksAdapter(List<Book> books, SharedPreferences sharedPreferences){
        this.books = books;
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View view = inflater.inflate(R.layout.books_single_view_pager, container, false);
        ImageView image = (ImageView) view.findViewById(R.id.single_pager_book_from_library);
        image.setImageResource(books.get(position).getDrawableResourceId());
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.book_is_read);
        checkBox.setOnCheckedChangeListener(null);
        checkBox.setChecked(books.get(position).isRead());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                books.get(position).setRead(isChecked);
                sharedPreferences.edit().putBoolean(String.valueOf(books.get(position).getId()),books.get(position).isRead()).apply();
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return books.get(position).getTitle();
    }
}
