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
    private boolean mDietsExist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diet_lists);
        mDietsExist = false;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String userId = user.getUid();
        DatabaseReference userRef = database.getReference("users").child(userId).child("diets");

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    mDietsExist = true;
                } else {
                    mDietsExist = false;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void personalizedDiet(View v) {
        if (mDietsExist) {
            startActivity(new Intent(DietLists.this, ShowDietPlan.class));
        } else {
            Toast.makeText(DietLists.this, "You didn't create a diet plan yet.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(DietLists.this, AllFoods.class));
        }
    }
}