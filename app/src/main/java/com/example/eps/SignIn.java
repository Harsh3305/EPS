package com.example.eps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity {
    EditText Email;
    EditText Pass;
    Button Signin;
    TextView Donthaveanaccount;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Email = findViewById(R.id.SignInEmail);
        Pass = findViewById(R.id.SignInPassword);
        Signin = findViewById(R.id.SignInbutton);

        Donthaveanaccount = findViewById(R.id.DontHaveAnAccount);
        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                mAuth.signInWithEmailAndPassword(Email.getText().toString(), Pass.getText().toString())
                        .addOnCompleteListener(SignIn.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
//                                    Backend backend = new Backend();
//                                    backend.init();
                                    // TODO: Call Waiting Screen
                                    SystemClock.sleep(5000);
                                    Signin.setEnabled(false);;
                                    Signin.setBackgroundColor(getResources().getColor(R.color.Grey));
                                    startActivity(new Intent(SignIn.this, MainActivity.class));
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
                startActivity(new Intent(SignIn.this, SignUp.class));
                finish();
            }
        });
    }
}