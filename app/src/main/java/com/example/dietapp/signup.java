package com.example.dietapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends Activity {
    public EditText firstNameInput, surnameInput, emailInput, passwordInput;
    public String name, surname, email, password;
    public FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference usersRef = database.getReference("users");

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sigup);
        firstNameInput = findViewById(R.id.InputNameSignUp);
        surnameInput = findViewById(R.id.InputSurnameSignUp);
        emailInput = findViewById(R.id.InputEmailSignUp);
        passwordInput = findViewById(R.id.InputPasswordSignUp);

        mAuth = FirebaseAuth.getInstance();

    }

    public void signUp(View v){
        name = firstNameInput.getText().toString();
        surname = surnameInput.getText().toString();
        email = emailInput.getText().toString();
        password = passwordInput.getText().toString();

        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(surname) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                FirebaseUser user = mAuth.getCurrentUser();
                                String uid = user.getUid();
                                usersRef.child(uid).child("name").setValue(name);
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(name)
                                        .build();
                                user.updateProfile(profileUpdates);
                                startActivity(new Intent(signup.this, signin.class));
                            }
                            else {
                                Toast.makeText(signup.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

        }else{
            Toast.makeText(this,"Fields cant be empty", Toast.LENGTH_SHORT).show();
        }
    }
}

