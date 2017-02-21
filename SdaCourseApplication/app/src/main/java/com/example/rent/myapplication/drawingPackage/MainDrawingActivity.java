package com.example.rent.myapplication.drawingPackage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.rent.myapplication.R;
import com.example.rent.myapplication.galleryPackage.GalleryActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainDrawingActivity extends AppCompatActivity {

    public static final String DRAWING_GALLERY_DIR = "drawing_gallery";
    private SimpleDrawingView simpleDrawingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawing_activity_main);
        simpleDrawingView = (SimpleDrawingView) findViewById(R.id.drawing_view);
    //    Button clearbutton = (Button) findViewById(R.id.clear_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

     /*   clearbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleDrawingView.clear();
            }
        });
*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.clear){
            simpleDrawingView.clear();
        }

        else if(item.getItemId()==R.id.save){
            saveDrawingToFile();
        }
        else if(item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        else if(item.getItemId()==R.id.gallery){
            Intent intent = new Intent(this, GalleryActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveDrawingToFile(){
        File drawingFile = new File(getDrawingGalleryDirectory(),createFileName());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(drawingFile);
            Bitmap bitmap = convertViewToBitmap(simpleDrawingView);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private File getDrawingGalleryDirectory(){
        return getExternalFilesDir(DRAWING_GALLERY_DIR);
    }

    private String createFileName(){
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return "Obrazek"+ timeStamp + ".png";
    }

    private Bitmap convertViewToBitmap(View view){
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(),view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return bitmap;
    }
}
