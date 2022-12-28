package com.example.dietapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Result extends CalculateBmi {

    double bmi;
    TextView textBMIScore, textBMIScale, textBMIExplain;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        bmi = intent.getDoubleExtra("bmi", 0.0);
        String bmiString = String.format("%.2f", bmi);
        textBMIScore = findViewById(R.id.textBMIScore);
        textBMIScale = findViewById(R.id.textBMIScale);
        textBMIExplain = findViewById(R.id.textBMIExplain);

        if(bmi < 18.5){
            textBMIScore.setText("Your BMI score is: " + bmiString);
            textBMIScale.setText("You are underweight!");
            textBMIExplain.setText("Don't worry, we will help you to gain weight!");
        }
        else if(bmi < 25){
            textBMIScore.setText("Your BMI score is: " + bmiString);
            textBMIScale.setText("Your weight is normal.");
            textBMIExplain.setText("You can stay healthy by using the diet plan we created for you!");
        }
        else if(bmi < 30){
            textBMIScore.setText("Your BMI score is: " + bmiString);
            textBMIScale.setText("You are overweight!");
            textBMIExplain.setText("Don't worry, by using the diet plan we created for you will lose weight!");
        }
        else if(bmi < 35){
            textBMIScore.setText("Your BMI score is: " + bmiString);
            textBMIScale.setText("You are Obese!");
            textBMIExplain.setText("Don't worry, by using the diet plan we created for you will lose weight!");
        }
        else{
            textBMIScore.setText("Your BMI score is: " + bmiString);
            textBMIScale.setText("You are Extremely Obese!");
            textBMIExplain.setText("Your BMI score is over 35, you must use the diet plan we created for you!");
        }
    }

    public void getDietPlanPage(View v){
        startActivity(new Intent(Result.this, ShowDietPlan.class));

    }
}