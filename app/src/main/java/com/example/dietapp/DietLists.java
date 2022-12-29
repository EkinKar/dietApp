package com.example.dietapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DietLists extends Activity {
    String dietType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diet_lists);
        Intent dietIntent = getIntent();
        double bmi = dietIntent.getDoubleExtra("BMI", 0.0);

        if (bmi < 18.5) {
            dietType = "diet1";
        } else if (bmi < 25) {
            dietType = "diet2";
        } else if (bmi < 30) {
            dietType = "diet3";
        } else if (bmi < 35) {
            dietType = "diet4";
        } else {
            dietType = "diet5";
        }
    }
    public void myDietList(View v) {
        // shows the user's diet if exists, redirects to the create diet list activity otherwise
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userRef = database.getReference("users").child(userId).child("diets");
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists() && snapshot.getValue() != null) {
                    startActivity(new Intent(DietLists.this, ShowDietPlan.class));
                } else {
                    Toast.makeText(DietLists.this, "You didn't create a diet plan yet.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(DietLists.this, CreateDietList.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }

    public void personalDiet(View v) {
        // shows the personalised diet list
        Intent personalIntent = new Intent(this, ShowPersonalPlan.class);
        personalIntent.putExtra("DIET_TYPE", dietType);
        startActivity(personalIntent);
    }
}