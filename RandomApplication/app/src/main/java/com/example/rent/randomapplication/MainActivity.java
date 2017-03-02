package com.example.rent.randomapplication;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button animButton = (Button) findViewById(R.id.anim_button);
        ObjectAnimator moveRight = ObjectAnimator.ofFloat(animButton, View.TRANSLATION_X,0,400);
        moveRight.setDuration(1000);
        ObjectAnimator rotateAround = ObjectAnimator.ofFloat(animButton,View.ROTATION,0,360);
        moveRight.setDuration(500);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(moveRight,rotateAround);
        animatorSet.start();
    }
}
