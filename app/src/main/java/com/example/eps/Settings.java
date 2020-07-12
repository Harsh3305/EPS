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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Settings extends AppCompatActivity {

    EditText SettingName;
    EditText SettingEmail;
    EditText SettingAddress;
    Button SettingSubmit;
    EditText SettingPhone;
    public static String Address = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SettingName = findViewById(R.id.SettingName);
        SettingEmail = findViewById(R.id.SettingEmail);
        SettingPhone = findViewById(R.id.SettingPhone);
        final Backend backend = new Backend();
        String Email = backend.getUser().getEmail();
        User user = backend.getUserInfo();
        getUserInfo();
        SettingEmail.setText(Email);
        SettingEmail.setEnabled(false);
        SettingEmail.setBackgroundColor(getResources().getColor(R.color.Grey));
        SettingSubmit = findViewById(R.id.SettingSubmit);
        SettingAddress = findViewById(R.id.SettingAddress);

        if (user != null) {
            SettingPhone.setText(user.Phone);
            SettingAddress.setText(user.Address);
            SettingName.setText(user.Name);
        }
        else {
            System.out.println("null");
        }
        SettingSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();

                user.Address = SettingAddress.getText().toString();
                user.Name = SettingName.getText().toString();
                user.setPhone(SettingPhone.getText().toString());
//                user.UID uid =
                if (user.Address == null || user.Address.equals("") || user.Name == null || user.Name.equals("") || user.Phone == null || user.Phone.equals("")) {
                    Snackbar.make(v, "Please enter a valid Information", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else {
                    Backend backend = new Backend();
                    user.UID = backend.getToken();
                    backend.uploadUser(user);
                    Toast.makeText(Settings.this, "Your Data is send to our servers :) Thank you", Toast.LENGTH_SHORT).show();
                    Address =user.Address;
                    finish();
                }
            }
        });
    }
    public void getUserInfo() {
        Backend backend = new Backend();
        String UID = backend.getToken();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(UID + "/UserInfo");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                User user = dataSnapshot.getValue(User.class);
                SettingName.setText(user.Name);
                SettingAddress.setText(user.Address);
                SettingPhone.setText(user.getPhone());
                SettingSubmit.setText("Confirm");
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }
}