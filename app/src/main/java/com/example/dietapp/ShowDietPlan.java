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
// Ekin
public class ShowDietPlan extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private TextView textFoodPersonalized;
    private TextView textAmountPersonalized;
    private TextView textTotalCal;
    int totalCal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_diet_plan);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();
        database = FirebaseDatabase.getInstance();
        // shows the diet of the user
        reference = database.getReference("users/" + userId + "/diets");
        textFoodPersonalized = findViewById(R.id.textFoodPersonalized);
        textAmountPersonalized = findViewById(R.id.textAmountPersonalized);
        textTotalCal = findViewById(R.id.textTotalCall);
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
                textTotalCal.setText("Total Calories: " + String.valueOf(totalCal));
                System.out.println(totalCal);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}