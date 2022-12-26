package com.example.dietapp;

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

public class CustomDiet extends AppCompatActivity {

    private TextView mDietsTextView;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String mUserId = user.getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_diet);
        mDietsTextView = (TextView) findViewById(R.id.diets_text_view);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userRef = database.getReference("users").child(mUserId).child("diets");
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mDietsTextView.setText("");
                for (DataSnapshot dietSnapshot : dataSnapshot.getChildren()) {
                    Diet diet = dietSnapshot.getValue(Diet.class);
                    mDietsTextView.append(diet.name + ": " + diet.amount + " servings\n");
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}