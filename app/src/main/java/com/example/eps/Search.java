package com.example.eps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Search extends AppCompatActivity {
    private ImageButton SearchButton;
    private EditText SearchTextView;
    static int updateIndex = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        SearchButton = findViewById(R.id.SearchButton);
        SearchTextView = findViewById(R.id.SearchTextView);

        SearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Got It", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Product p = new Product();

                p.NameOfProduct = SearchTextView.getText().toString();
                p.NumberOfImage = "3";

                // Write a message to the database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Product/");

                myRef.child(Integer.toString(updateIndex)).setValue(p);

                updateIndex++;

                //String s = SearchTextView.getText().toString();

//                myRef.child(s).setValue(s);


            }
        });
    }
}