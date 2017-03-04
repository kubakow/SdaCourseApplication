package com.example.rent.myapplication.quizPackage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rent.myapplication.R;

public class SummaryQuiz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_quiz);
        TextView resultOk = (TextView) findViewById(R.id.result_ok);
        TextView resultNotOk = (TextView) findViewById(R.id.result_not_ok);

        resultOk.setText("Poprawnych odpowiedzi: "+ getIntent().getIntExtra(QuizActivity.CORRECT_ANSWERS, 0));
        resultNotOk.setText("Niepoprawnych odpowiedzi: "+ getIntent().getIntExtra(QuizActivity.INCORRECT_ANSWERS, 0));

        Button tryAgain = (Button) findViewById(R.id.try_again);
        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SummaryQuiz.this, QuizActivity.class);
                startActivity(intent);
            }
        });
    }
}
