package com.example.dietapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShowPersonalPlan extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private TextView textFoodPersonalized;
    private TextView textAmountPersonalized;
    int totalCal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_personal_plan);
        database = FirebaseDatabase.getInstance();
        Intent intent = getIntent();
        String dietType = intent.getStringExtra("DIET_TYPE");
        reference = database.getReference("diets/" + dietType);
        System.out.println(reference);
        textFoodPersonalized = findViewById(R.id.textFoodPersonalized);
        textAmountPersonalized = findViewById(R.id.textAmountPersonalized);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                StringBuilder sbFood = new StringBuilder();
                StringBuilder sbAmount = new StringBuilder();
                for (DataSnapshot dietSnapshot : dataSnapshot.getChildren()) {
                    Diet diet = dietSnapshot.getValue(Diet.class);
                    sbFood.append(diet.getName()).append("\n");
                    sbAmount.append(diet.getAmount()).append("\n");
                    totalCal += diet.getCalories() * diet.getAmount();
                }
                textFoodPersonalized.setText(sbFood.toString());
                textAmountPersonalized.setText(sbAmount.toString());
                System.out.println(totalCal);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}