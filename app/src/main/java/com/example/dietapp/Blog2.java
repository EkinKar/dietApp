package com.example.dietapp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;
//Mert
public class Blog2 extends Activity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_blog2);

        imageView = findViewById(R.id.blog2Image);

        // Create and start the background thread
        Thread thread = new Thread(new DownloadImageRunnable());
        thread.start();
    }

    private class DownloadImageRunnable implements Runnable {

        @Override
        public void run() {
            // Download the image
            try {
                URL url = new URL("https://diettogo.com/data/fe/image/is-your-diet-healthy_HEADER.jpg");

                InputStream inputStream = url.openStream();
                final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                // Update the ImageView on the main thread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(bitmap);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}