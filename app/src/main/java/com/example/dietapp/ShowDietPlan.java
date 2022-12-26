package com.example.dietapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShowDietPlan extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private TextView textFoodPersonalized;
    private TextView textAmountPersonalized;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_diet_plan);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("users/" + userId + "/diets");
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
                }
                textFoodPersonalized.setText(sbFood.toString());
                textAmountPersonalized.setText(sbAmount.toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}