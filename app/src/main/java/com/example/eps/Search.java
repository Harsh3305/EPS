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
    //static int updateIndex = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        SearchButton = findViewById(R.id.SearchButton);
        SearchTextView = findViewById(R.id.SearchTextView);

        SearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Got It", Snackbar.LENGTH_LONG).setBackgroundTint(getResources().getColor(R.color.colorPrimary))
                        .setTextColor(getResources().getColor(R.color.colorAccent))
                        .setAction("Action", null).show();
//                Product p = new Product();
//
//                p.NameOfProduct = SearchTextView.getText().toString();
//                p.NumberOfImage = "3";


//                CartAndPreviousOrder cart = new CartAndPreviousOrder();
//                cart.NameOfProduct = SearchTextView.getText().toString();
//                cart.isCart = "true";
//                Backend backend = new Backend();
//
//                String UID = backend.getToken();
//
//                // Write a message to the database
//                FirebaseDatabase database = FirebaseDatabase.getInstance();
//                DatabaseReference myRef = database.getReference(UID + "/MyCart/");
//
//                myRef.child(Integer.toString(updateIndex)).setValue(cart);
//
//                updateIndex++;

                //String s = SearchTextView.getText().toString();

//                myRef.child(s).setValue(s);



                String request = SearchTextView.getText().toString();


                                // Write a message to the database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Search/" );

                myRef.setValue(request);



            }
        });
    }
}