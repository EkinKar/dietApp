package com.example.dietapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Blogs extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blogs);
    }

    public void goBlog1(View v){
        startActivity(new Intent(Blogs.this, Blog1.class));

    }

    public void goBlog2(View v){
        startActivity(new Intent(Blogs.this, Blog2.class));

    }

    public void goBlog3(View v){
        startActivity(new Intent(Blogs.this, Blog3.class));

    }

    public void goBlog4(View v){
        startActivity(new Intent(Blogs.this, Blog4.class));

    }
}
