package com.example.eps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {

    EditText Email;
    EditText Pass;
    Button Signup;
    TextView Donthaveanaccount;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Email = findViewById(R.id.SignUpEmail);
        Pass = findViewById(R.id.SignUpPassword);
        Signup = findViewById(R.id.SignUpbutton);
        Donthaveanaccount = findViewById(R.id.AlreadyHaveAnAccount);
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                mAuth.createUserWithEmailAndPassword(Email.getText().toString(), Pass.getText().toString())
                        .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
//                                    Backend backend = new Backend();
//                                    backend.init();
//                                    // TODO: Call Waiting Screen
//                                    SystemClock.sleep(5000);
//                                    startActivity(new Intent(SignUp.this, Home.class));

                                    String UID = mAuth.getUid();
                                    String email = Email.getText().toString();

                                    User Usr = new User();
                                    Usr.Email = email;
                                    Usr.UID = UID;
                                    Backend backend = new Backend();
                                    backend.uploadUser(Usr);
                                    startActivity(new Intent(SignUp.this, MainActivity.class));
                                    finish();
                                } else {
                                    Snackbar.make(v, task.getException().toString(), Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                }
                            }
                        });
            }
        });
        Donthaveanaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this,SignIn.class));
                finish();
            }
        });
    }
}