package com.example.rent.myapplication.quizPackage;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rent.myapplication.R;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by RENT on 2017-02-25.
 */

public class QuizActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String INDEKS_KEY = "index_key";
    private int currentQuestionIndex =0;
    private ValueAnimator objectAnimator = null;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_layout);
        currentQuestionIndex = getIntent().getIntExtra(INDEKS_KEY,0);

        progressBar = (ProgressBar) findViewById(R.id.quiz_layout_progress_bar);

        objectAnimator = ObjectAnimator.ofInt(0,30);
        objectAnimator.setDuration(30000);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progressBar.setProgress((Integer) animation.getAnimatedValue());
            }
        });
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
            goToNextQuestion();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        objectAnimator.start();
        String json;
            json = loadQuizJson();
            QuizContainer quizContainer = new Gson().fromJson(json, QuizContainer.class);

        TextView questionText = (TextView) findViewById(R.id.quiz_layout_question);
        questionText.setText(quizContainer.getQuestions().get(currentQuestionIndex).getQuestion());
        questionText.setTextSize(20);
        questionText.setIncludeFontPadding(true);

        Button answerAbutton, answerBbutton, answerCbutton, answerDbutton;

        SingleAnswer answerA = quizContainer.getQuestions().get(currentQuestionIndex).getAnswers().get(0);
        answerAbutton = (Button) findViewById(R.id.quiz_layout_answer_a);
        answerAbutton.setText(answerA.getText());
        answerAbutton.setTag(answerA.isCorrect());

        SingleAnswer answerB = quizContainer.getQuestions().get(currentQuestionIndex).getAnswers().get(1);
        answerBbutton = (Button) findViewById(R.id.quiz_layout_answer_b);
        answerBbutton.setText(answerB.getText());
        answerBbutton.setTag(answerB.isCorrect());

        SingleAnswer answerC = quizContainer.getQuestions().get(currentQuestionIndex).getAnswers().get(2);
        answerCbutton = (Button) findViewById(R.id.quiz_layout_answer_c);
        answerCbutton.setText(answerC.getText());
        answerCbutton.setTag(answerC.isCorrect());

        SingleAnswer answerD = quizContainer.getQuestions().get(currentQuestionIndex).getAnswers().get(3);
        answerDbutton = (Button) findViewById(R.id.quiz_layout_answer_d);
        answerDbutton.setText(answerD.getText());
        answerDbutton.setTag(answerD.isCorrect());

        answerAbutton.setOnClickListener(this);
        answerBbutton.setOnClickListener(this);
        answerCbutton.setOnClickListener(this);
        answerDbutton.setOnClickListener(this);
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
    private void playMusic(String uri){

        MediaPlayer player = new MediaPlayer();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);

        try {
            player.setDataSource(uri);
            player.prepare();
            player.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {

        if((Boolean)v.getTag()){
            playMusic("http://www.kakofonia.pl/PL/PLtele/odp.wav");
            v.setBackgroundColor(Color.GREEN);
        }else{playMusic("http://www.kakofonia.pl/PL/PLtele/zle.wav");}
        v.postDelayed(new Runnable() {
            @Override
            public void run() {
            progressBar.setProgress(0);
            goToNextQuestion();
            }
        },3000);
    }

    private void goToNextQuestion(){

        Intent intent = new Intent(QuizActivity.this, QuizActivity.class);
        if(currentQuestionIndex<1) {
            intent.putExtra(INDEKS_KEY, ++currentQuestionIndex);
            startActivity(intent);
        }
    }
}
