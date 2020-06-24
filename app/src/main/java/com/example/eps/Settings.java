package com.example.eps;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class Settings extends AppCompatActivity {

    EditText SettingName;
    EditText SettingEmail;
    EditText SettingAddress;
    Button SettingSubmit;
    EditText SettingPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        SettingName = findViewById(R.id.SettingName);
        SettingEmail = findViewById(R.id.SettingEmail);
        SettingPhone = findViewById(R.id.SettingPhone);
        Backend backend = new Backend();
        String Email = backend.getUser().getEmail();
        SettingEmail.setText(Email);
        SettingEmail.setEnabled(false);
        SettingEmail.setBackgroundColor(getResources().getColor(R.color.Grey));
        SettingSubmit = findViewById(R.id.SettingSubmit);
        SettingAddress = findViewById(R.id.SettingAddress);

        SettingSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();

                user.Address = SettingAddress.getText().toString();
                user.Name = SettingName.getText().toString();
                user.setPhone(SettingPhone.getText().toString());

                if (user.Address == null || user.Address.equals("") || user.Name == null || user.Name.equals("") || user.Phone == null || user.Phone.equals("")) {
                    Snackbar.make(v, "Please enter a valid Information", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else {
                    Backend backend = new Backend();
                    backend.uploadUser(user);
                    Toast.makeText(Settings.this, "Your Data is send to our servers :) Thank you", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });


    }
}