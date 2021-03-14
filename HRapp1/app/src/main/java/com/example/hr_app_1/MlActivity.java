package com.example.hr_app_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

public class MlActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ml);

        setTitle("Model Results");

        Intent intent = getIntent();
        Bitmap image = (Bitmap) intent.getParcelableExtra("BitmapImage");

        ImageView imageview = (ImageView) findViewById(R.id.houseImage);
        imageview.setImageBitmap(image);
    }
}