package com.example.rent.materialdesignintro;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.PatternMatcher;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in_screen);
        final EditText loginText = (EditText) findViewById(R.id.login_field);
        EditText passwordText = (EditText) findViewById(R.id.password_field);
        Button loginButton = (Button) findViewById(R.id.login_button);
        final TextInputLayout loginInputLayout = (TextInputLayout) findViewById(R.id.login_input_layout);
        TextInputLayout passwordInputLayout = (TextInputLayout) findViewById(R.id.password_input_layout);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = loginText.getText().toString();
                String patternString = ".*@.*";
                Pattern pattern = Pattern.compile(patternString);
                Matcher matcher = pattern.matcher(text);
                boolean matches = matcher.matches();
                if(!matches){
                   loginInputLayout.setError("LOL, to nie jest email");
                }
                else{
                    loginInputLayout.setError(null);
                    Snackbar.make(v,"Login data correct", Snackbar.LENGTH_INDEFINITE).setAction("Close", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }).show();
                }
            }
        });










        /*setContentView(R.layout.activity_main);
        final TextView helloText = (TextView) findViewById(R.id.hello);
        final ValueAnimator animator = ObjectAnimator.ofFloat(0, 130);
        animator.setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                helloText.setElevation((float)animation.getAnimatedValue());
            }
        });
        helloText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getElevation()==0){
                    animator.start();
                }
                else{
                    animator.reverse();
                }
            }
        });*/
    }
}
