package com.example.rent.myapplication.galleryPackage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.rent.myapplication.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by RENT on 2017-02-21.
 */

public class GalleryAdapter extends PagerAdapter {

    private File[] files;

    public GalleryAdapter(File[] files){
        this.files = files;
    }

    @Override
    public int getCount() {
        return files.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View view = inflater.inflate(R.layout.single_view_pager_layout, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.single_pager_image_view);

        try {
            FileInputStream fileInputStream = new FileInputStream(files[position]);
            Bitmap bitmap = BitmapFactory.decodeStream(fileInputStream);
            imageView.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
     container.addView(view);
     return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public void deleteItemFromPosition(int position){

        if(position<files.length) {
            List<File> list = new ArrayList<>(Arrays.asList(files));
            list.get(position).delete();
            list.remove(position);

            File[] newFiles = new File[list.size()];
            list.toArray(newFiles);
            files = newFiles;
            notifyDataSetChanged();
        }
    }


}
