package com.example.dietapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CalculateBmi extends Activity {
    EditText height_input;
    EditText weight_input;
    double height, weight, bmi;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_bmi);

        height_input = findViewById(R.id.InputHeight);
        weight_input = findViewById(R.id.InputWeight);

    }

    public double calculateBMI(double height, double weight) {
        System.out.println();
        return (weight / (height * height) * 100) / 100;
    }

    public void getResultPage(View v) {
        height = (Double.parseDouble(height_input.getText().toString())) / 100;
        weight = Double.parseDouble(weight_input.getText().toString());
        bmi = calculateBMI(height, weight);
        Intent intent = new Intent(CalculateBmi.this, Result.class);
        intent.putExtra("bmi", bmi);
        startActivity(intent);
    }
}
