package com.example.hr_app_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
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


    private Executor executor = Executors.newSingleThreadExecutor();
    Classifier classifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ml);

        setTitle("Model Results");

        Intent intent = getIntent();
        Bitmap modelImage = (Bitmap) intent.getParcelableExtra("BitmapImage");
        Bitmap displayImage = (Bitmap) intent.getParcelableExtra("BitmapImage");

        // create image for model
        modelImage = Bitmap.createScaledBitmap(modelImage, inputSize, inputSize, false);

        // create image for display
        Matrix matrix = new Matrix();
        matrix.postRotate(90);
        Bitmap rotatedBitmap = Bitmap.createBitmap(displayImage, 0, 0, displayImage.getWidth(), displayImage.getHeight(), matrix, true);

        // display the image
        ImageView imageView = (ImageView) findViewById(R.id.houseImage);
        imageView.setImageBitmap(rotatedBitmap);

        // initialize model
        try {
            classifier = new Classifier(getAssets(), modelPath, inputSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // get image recognition results
        float[] results = classifier.recognizeImage(modelImage);
//        for(int i = 0; i < results.length; i++) {
//            System.out.println(results[i]);
//        }
        // display results
        TextView secondEmpire = (TextView) findViewById(R.id.secondEmpire);
        TextView tudorRevival = (TextView) findViewById(R.id.tudorRevival);
        secondEmpire.setText("Second Empire: " + Float.toString(results[0]));
        tudorRevival.setText("Tudor Revival: " + Float.toString(results[1]));
    }
}