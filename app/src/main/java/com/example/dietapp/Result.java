package com.example.dietapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Result extends CalculateBmi {

    double bmi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        double bmi = intent.getDoubleExtra("bmi", 0.0);
        Toast.makeText(Result.this, String.valueOf(bmi), Toast.LENGTH_SHORT).show();
    }

    public void getDietPlanPage(View v){
        startActivity(new Intent(Result.this, ShowDietPlan.class));

    }
}