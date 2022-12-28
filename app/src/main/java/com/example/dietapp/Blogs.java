package com.example.dietapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Blogs extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blogs);
    }
    public void goBlog1(View v){
        startActivity(new Intent(Blogs.this, Blog1.class));

    }
}
