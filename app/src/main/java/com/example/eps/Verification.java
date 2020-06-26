package com.example.eps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Verification extends AppCompatActivity {
    Button EmailSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final FirebaseUser currentUser = mAuth.getCurrentUser();
        EmailSend = findViewById(R.id.EmailSend);

        EmailSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmailSend.setText("Resend verification email");
                if (currentUser.isEmailVerified()) {
                    finish();
                }
                currentUser.sendEmailVerification().addOnCompleteListener((Activity) v.getContext(), new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Verification.this, "Verification email is been sent to your Email account", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Verification.this, SignIn.class));
                            finish();

                            if (currentUser.isEmailVerified()) {
                                Toast.makeText(Verification.this, "Verification email is been sent to your Email account", Toast.LENGTH_SHORT).show();
                                finish();
                            }

                        }
                    }

                }).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Verification.this, "Verification email is been sent to your Email account", Toast.LENGTH_SHORT);

                    }
                });
            }
        });





    }
}