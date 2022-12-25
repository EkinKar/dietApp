package com.example.dietapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AllFoods extends AppCompatActivity {

    private String[] foodAmount = {"0","1","2","3","4","5"};
    private Spinner spinnerFoodAmount;
    private ArrayAdapter<String> dataAdapterFoodAmount;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_foods);

        spinnerFoodAmount = (Spinner) findViewById(R.id.spinner);
        dataAdapterFoodAmount = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,foodAmount);
        dataAdapterFoodAmount.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFoodAmount.setAdapter(dataAdapterFoodAmount);
        spinnerFoodAmount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getBaseContext(), ""+spinnerFoodAmount.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}
