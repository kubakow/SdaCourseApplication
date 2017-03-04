package com.example.rent.myapplication.mvpPackage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rent.myapplication.R;

import org.w3c.dom.Text;

import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusAppCompatActivity;
@RequiresPresenter(MvpPresenter.class)
public class MvpActivity extends NucleusAppCompatActivity<MvpPresenter> {


    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);

        Button mvpButton = (Button) findViewById(R.id.mvp_button);
        result = (TextView) findViewById(R.id.mvp_text_view);
        mvpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().executeLongRunningTask();


            }
        });
    }

    public void setTextOnUiThread(final String text){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                result.setText(text);
            }
        });
    }

}
