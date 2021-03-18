package com.example.hr_app_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MlActivity extends AppCompatActivity {

    private static final int inputSize = 150;
    private static final String modelPath = "model.tflite";
    boolean cameraUpload;
    GridView gridView;
    int[] houseImages;
    ArrayList<String> houseTypes;
    ArrayList<String> mlResults;


    private Executor executor = Executors.newSingleThreadExecutor();
    Classifier classifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ml);

        setTitle("Model Results");
        createArrays();

        Intent intent = getIntent();

        Bitmap modelImage = null;
        Bitmap displayImage = null;

        // camera upload
        if(intent.hasExtra("BitmapImage")) {
            modelImage = (Bitmap) intent.getParcelableExtra("BitmapImage");
            displayImage = (Bitmap) intent.getParcelableExtra("BitmapImage");
            cameraUpload = true;
        // file upload
        } else if(intent.hasExtra("UriImage")) {
            Uri imageUri = (Uri) intent.getParcelableExtra("UriImage");
            try {
                modelImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                displayImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            cameraUpload = false;
        }

        // create image for model
        modelImage = Bitmap.createScaledBitmap(modelImage, inputSize, inputSize, false);

//        if(cameraUpload) {
//            // create image for display
//            Matrix matrix = new Matrix();
//            matrix.postRotate(90);
//            displayImage = Bitmap.createBitmap(displayImage, 0, 0, displayImage.getWidth(), displayImage.getHeight(), matrix, true);
//        }

        // display the image
        ImageView imageView = (ImageView) findViewById(R.id.houseImage);
        imageView.setImageBitmap(displayImage);

        // initialize model
        try {
            classifier = new Classifier(getAssets(), modelPath, inputSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // get image recognition results
        float[] results = classifier.recognizeImage(modelImage);
        String[] stringResults = new String[results.length];
        for(int i = 0; i < results.length; i++) {
            results[i] *= 100;
            stringResults[i] = String.format("%.2f", results[i]);
        }

        // second empire
        mlResults.add(stringResults[0] + "%");
        // tudor revival
        mlResults.add(stringResults[1] + "%");
        // queen anne
        mlResults.add("0%");
        // colonial revival
        mlResults.add("0%");
        // craftsman
        mlResults.add("0%");
        //neoclassical
        mlResults.add("0%");

        // retrieve gridview from the xml
        gridView = findViewById(R.id.gridView);

        // create the custom adapter and attach it to the gridView
        ImageGridAdapter adapter = new ImageGridAdapter(this, houseTypes, mlResults, houseImages);
        gridView.setAdapter(adapter);

    }

    private void createArrays() {
        // create house type name/image arrays
        houseTypes = new ArrayList<>();
        houseTypes.add("Second Empire");
        houseTypes.add("Tudor Revival");
        houseTypes.add("Queen Anne");
        houseTypes.add("Colonial Revival");
        houseTypes.add("Craftsman");
        houseTypes.add("Neoclassical");
        houseImages = new int[]{R.drawable.secondempire, R.drawable.tudorrevival, R.drawable.queenanne, R.drawable.colonialrevival, R.drawable.craftsman, R.drawable.neoclassical};
        mlResults = new ArrayList<>();
    }
}