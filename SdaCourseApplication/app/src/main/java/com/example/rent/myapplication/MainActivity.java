package com.example.rent.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rent.myapplication.drawingPackage.MainDrawingActivity;
import com.example.rent.myapplication.mvpPackage.MvpActivity;
import com.example.rent.myapplication.quizPackage.QuizActivity;
import com.example.rent.myapplication.toDoPackage.ToDoListActivity;

public class MainActivity extends AppCompatActivity {
    public static final String NOTES_KEY = "notes";
    private DrawerLayout drawerLayout;
private ActionBarDrawerToggle drawerToggle;
private TextView paintText;
private TextView toDoListText;
    private TextView quizText;
    private TextView soothsayerText;
    private TextView mvpText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_from_left);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        paintText = (TextView) findViewById(R.id.rysowanie);
        paintText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainDrawingActivity.class);
                startActivity(intent);
            }
        });
        toDoListText = (TextView) findViewById(R.id.to_do_list_link);
        toDoListText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ToDoListActivity.class);
                startActivity(intent);
            }
        });

        quizText = (TextView) findViewById(R.id.quiz_app_link);
        quizText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), QuizActivity.class);
                startActivity(intent);
            }
        });
        soothsayerText = (TextView) findViewById(R.id.soothsayer_app_link);
        soothsayerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FortuneActivity.class);
                startActivity(intent);
            }
        });

        mvpText = (TextView) findViewById(R.id.mvp_app_link);
        mvpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MvpActivity.class);
                startActivity(intent);
            }
        });

        final EditText editText = (EditText) findViewById(R.id.my_note_edittext);
        editText.setText(readText());
        Button saveButton = (Button) findViewById(R.id.save_note_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveText(editText.getText().toString());
            }
        });


    }

    private String readText(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        return sharedPreferences.getString(NOTES_KEY, "");
    }
    private void saveText(String text){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.edit().putString(NOTES_KEY, text).apply();
    }
    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home: {
                if(drawerLayout.isDrawerOpen(Gravity.START)){
                    drawerLayout.closeDrawer(Gravity.START);
                }else{
                    drawerLayout.openDrawer(Gravity.START);
                }
                break;
            }
        }

        return super.onOptionsItemSelected(item);

    }
}
