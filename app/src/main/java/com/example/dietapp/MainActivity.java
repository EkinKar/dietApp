package com.example.dietapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
// Ekin
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void signInPage(View v){
        startActivity(new Intent(MainActivity.this, SignIn.class));
    }
    public void signUpPage(View v){
        startActivity(new Intent(MainActivity.this, SignUp.class));

    }
}