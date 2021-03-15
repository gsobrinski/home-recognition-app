package com.example.hr_app_1;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.os.Build;

import androidx.annotation.RequiresApi;

import org.tensorflow.lite.Interpreter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Classifier {

    // values for bitmap > bytebuffer method
    private static final int BATCH_SIZE = 1;
    private static final int PIXEL_SIZE = 3;
    private static final int IMAGE_MEAN = 128;
    private static final float IMAGE_STD = 128.0f;

    private final int inputSize;

    private List<String> labels;
    private final Interpreter interpreter;

    public Classifier(AssetManager assetManager, String modelPath, int inputSize) throws IOException {
        labels = getLabels();
        this.inputSize = inputSize;
        this.interpreter = new Interpreter(loadModelFile(assetManager, modelPath), new Interpreter.Options());

    }

    // TAKEN FROM: https://github.com/amitshekhariitbhu/Android-TensorFlow-Lite-Example
    // convert BitMap image to ByteBuffer format which will be used to pass data to the model
    private ByteBuffer convertBitmapToByteBuffer(Bitmap bitmap) {
        ByteBuffer byteBuffer;

        byteBuffer = ByteBuffer.allocateDirect(4 * BATCH_SIZE * inputSize * inputSize * PIXEL_SIZE);

        byteBuffer.order(ByteOrder.nativeOrder());
        int[] intValues = new int[inputSize * inputSize];
        bitmap.getPixels(intValues, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        int pixel = 0;
        for (int i = 0; i < inputSize; ++i) {
            for (int j = 0; j < inputSize; ++j) {
                final int val = intValues[pixel++];
                byteBuffer.putFloat((((val >> 16) & 0xFF) - IMAGE_MEAN) / IMAGE_STD);
                byteBuffer.putFloat((((val >> 8) & 0xFF) - IMAGE_MEAN) / IMAGE_STD);
                byteBuffer.putFloat((((val) & 0xFF) - IMAGE_MEAN) / IMAGE_STD);
            }
        }
        return byteBuffer;
    }

    // image recognition method
    public float[] recognizeImage(Bitmap bitmap) {
        ByteBuffer byteBuffer = convertBitmapToByteBuffer(bitmap);
        float[][] result = new float[1][labels.size()];
        interpreter.run(byteBuffer, result);
        float[] runResult = result[0];
        return runResult;
    }

    // get house architecture types in sorted List<String> format
    public List<String> getLabels() {
        // create labels array
        labels = new ArrayList<String>();
        labels.add("Second Empire");
        labels.add ("Tudor Revival");
        // sort labels alphabetically
        Collections.sort(labels);
        return labels;
    }

    // TAKEN FROM: https://github.com/amitshekhariitbhu/Android-TensorFlow-Lite-Example
    // load the model file
    private MappedByteBuffer loadModelFile(AssetManager assetManager, String modelPath) throws IOException {
        AssetFileDescriptor fileDescriptor = assetManager.openFd(modelPath);
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }
}