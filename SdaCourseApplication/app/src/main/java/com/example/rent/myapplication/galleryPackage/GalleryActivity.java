package com.example.rent.myapplication.galleryPackage;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.rent.myapplication.R;
import com.example.rent.myapplication.drawingPackage.MainDrawingActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by RENT on 2017-02-21.
 */

public class GalleryActivity extends AppCompatActivity{

    private GalleryAdapter pagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Gallery of Drawings");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_activity_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        File dir = getExternalFilesDir(MainDrawingActivity.DRAWING_GALLERY_DIR);
        File[] files = dir.listFiles();
        pagerAdapter = new GalleryAdapter(files);
        viewPager.setAdapter(pagerAdapter);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.gallery_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.delete: {
                pagerAdapter.deleteItemFromPosition(viewPager.getCurrentItem());
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
