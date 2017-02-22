package com.example.rent.databindingexample;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by RENT on 2017-02-22.
 */

public class ImageUrlBindingAdapter {

    @BindingAdapter("loadUrl")
    public static void bindImage(ImageView imageView, String url){
        Glide.with(imageView.getContext())
                .load(url)
                .into(imageView);
    }

}
