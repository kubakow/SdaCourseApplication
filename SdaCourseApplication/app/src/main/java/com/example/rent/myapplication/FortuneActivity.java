package com.example.rent.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Color;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.squareup.seismic.ShakeDetector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class FortuneActivity extends AppCompatActivity implements ShakeDetector.Listener{
    private List<String> sayings = Arrays.asList("A bird does not sing because it has an answer. It sings because it has a song", "A filthy mouth will not utter decent language", "Deep doubts, deep wisdom. Small doubts, little wisdom");;
    private Random mRandom = new Random();
    private FrameLayout layer;
    private TextView chineseSaying;
    private Animator hidingAnimator;
    private Animator showingAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fortune);
        layer = (FrameLayout) findViewById(R.id.fortune_container);
        chineseSaying = (TextView) findViewById(R.id.chinese_saying);
        FrameLayout parentLayout = (FrameLayout) findViewById(R.id.activity_fortune);


        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        ShakeDetector sd = new ShakeDetector(this);
        sd.start(sensorManager);

        parentLayout.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    animateCircularReveal((int) event.getX(),(int) event.getY(), layer);
                }
            return true;

        }

    });
    }
    @Override
    public void hearShake() {
        animateCircularReveal(mRandom.nextInt(layer.getWidth()), mRandom.nextInt(layer.getHeight()), layer);
    }

    private void animateCircularReveal(int x, int y, final FrameLayout layer) {
        if((hidingAnimator != null && hidingAnimator.isRunning()) || (showingAnimator != null && showingAnimator.isRunning())){
            return;
        }
       if (layer.getVisibility() == View.VISIBLE) {
            hidingAnimator = ViewAnimationUtils
                    .createCircularReveal(layer, x, y, layer.getHeight(), 0);
            hidingAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    layer.setVisibility(View.INVISIBLE);
                }
            });
            hidingAnimator.start();

        } else {
            int color = Color.argb(255, mRandom.nextInt(255), mRandom.nextInt(255), mRandom.nextInt(255));
            layer.setBackgroundColor(color);

            showingAnimator = ViewAnimationUtils
                    .createCircularReveal(layer, x, y, 0F, layer.getHeight());
            layer.setVisibility(View.VISIBLE);
            chineseSaying.setText(sayings.get(mRandom.nextInt(sayings.size())));
            showingAnimator.start();
        }
    }

}


