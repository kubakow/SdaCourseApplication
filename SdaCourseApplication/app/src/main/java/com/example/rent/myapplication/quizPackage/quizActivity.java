package com.example.rent.myapplication.quizPackage;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.rent.myapplication.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by RENT on 2017-02-25.
 */

public class QuizActivity extends AppCompatActivity {




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_layout);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.quiz_layout_progress_bar);
        ValueAnimator objectAnimator = ObjectAnimator.ofInt(0,30);
        objectAnimator.setDuration(30000);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progressBar.setProgress((Integer) animation.getAnimatedValue());
            }
        });
        objectAnimator.start();
        String json;
            json = loadQuizJson();
        Toast.makeText(this, json, Toast.LENGTH_LONG).show();
    }

    private String loadQuizJson(){
        StringBuilder buf = new StringBuilder();
        try {
            InputStream json = getAssets().open("quiz_data.json");
            BufferedReader in = new BufferedReader(new InputStreamReader(json, "UTF-8"));
            String str;
            while((str = in.readLine())!= null){
                buf.append(str);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buf.toString();
    }


}
