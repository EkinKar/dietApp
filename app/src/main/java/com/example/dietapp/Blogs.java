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

    public void getBlog1Page(View v){
        startActivity(new Intent(Blogs.this, Blog1.class));

    }
//    public void getBlog2Page(View v){
//        startActivity(new Intent(Blogs.this, Blog2.class));
//    }
//    public void getBlog3Page(View v){
//        startActivity(new Intent(Blogs.this, Blog3.class));
//    }
//    public void getBlog4Page(View v){
//        startActivity(new Intent(Blogs.this, Blog4.class));
//    }
}
