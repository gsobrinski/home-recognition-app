package com.example.hr_app_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.TextView;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MlActivity extends AppCompatActivity {

    private static final int inputSize = 150;
    private static final String modelPath = "model.tflite";
    boolean cameraUpload;


    private Executor executor = Executors.newSingleThreadExecutor();
    Classifier classifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ml);

        setTitle("Model Results");

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
        // display results
        TextView secondEmpire = (TextView) findViewById(R.id.secondEmpire);
        TextView tudorRevival = (TextView) findViewById(R.id.tudorRevival);

        secondEmpire.setText("Second Empire: " + stringResults[0] + "%");
        tudorRevival.setText("Tudor Revival: " + stringResults[1] + "%");
    }
}