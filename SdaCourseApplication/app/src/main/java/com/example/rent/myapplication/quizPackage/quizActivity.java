package com.example.rent.myapplication.quizPackage;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.rent.myapplication.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by RENT on 2017-02-25.
 */

public class QuizActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String INDEKS_KEY = "index_key";
    private int currentQuestionIndex =0;
    private ValueAnimator objectAnimator = null;
    private ProgressBar progressBar;
    public static final String CORRECT_ANSWERS = "correct_answers";
    public static final String INCORRECT_ANSWERS = "incorrect_answers";
    private int correctAnswers=0;
    private int incorrectAnswers=0;
    private QuizContainer quizContainer;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_layout);
        currentQuestionIndex = getIntent().getIntExtra(INDEKS_KEY,0);
        correctAnswers = getIntent().getIntExtra(CORRECT_ANSWERS,0);
        incorrectAnswers = getIntent().getIntExtra(INCORRECT_ANSWERS,0);

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

        new AsyncTask<String, Void, QuizContainer>(){
            @Override
            protected QuizContainer doInBackground(String... params) {
                String json;
                json = loadQuizJsonFromURL(params[0]);
                quizContainer = new Gson().fromJson(json, QuizContainer.class);
                return quizContainer;
            }

            @Override
            protected void onPostExecute(QuizContainer quizContainer) {
                displayResultOnScreen(quizContainer);
            }
        }.execute("https://sdacourse-cf739.firebaseio.com/sdacourse-cf739-export.json");


    }

    private void displayResultOnScreen(QuizContainer quizContainer) {
        this.quizContainer = quizContainer;
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

    private String loadQuizJsonFromURL(String stringUrl){
        StringBuilder buf = new StringBuilder();
        try {
            URL url = new URL(stringUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream json = httpURLConnection.getInputStream();
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
            ++correctAnswers;
        }else{playMusic("http://www.kakofonia.pl/PL/PLtele/zle.wav");
            v.setBackgroundColor(Color.RED);
        ++incorrectAnswers;}
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
            intent.putExtra(CORRECT_ANSWERS, correctAnswers);
            intent.putExtra(INCORRECT_ANSWERS, incorrectAnswers);
            startActivity(intent);
        }else{
            Intent intent2 = new Intent(QuizActivity.this, SummaryQuiz.class);
            intent2.putExtra(CORRECT_ANSWERS, correctAnswers);
            intent2.putExtra(INCORRECT_ANSWERS, incorrectAnswers);
            startActivity(intent2);
        }
    }
}