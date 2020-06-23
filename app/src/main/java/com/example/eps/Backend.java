package com.example.eps;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.SystemClock;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Backend {

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    Bitmap bitmap;

    HashMap<String, Integer> MapOfProduct = new HashMap<>();

    public boolean AuthNew(String email, String password, final Activity o) {
        if (email.length() ==0 || password.length() == 0) {
            return false;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(o, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {


                            Snackbar.make(o.getCurrentFocus(), task.getException().toString(), Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();

                            updateUI(null);
                        }
                    }
                });

        return isAuth;

    }

    public FirebaseUser getUser() {

        return  mAuth.getCurrentUser();
    }

    public String getToken() {
        return mAuth.getUid();
    }



    private static boolean isAuth;
    private void updateUI(FirebaseUser user) {
        isAuth = (user != null);
    }

    public boolean AuthExist(String email, String password, final Activity activity) {
        isAuth = false;
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            isAuth = true;
                        } else {

                            Snackbar.make(activity.getCurrentFocus(), task.getException().toString(), Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();

                            isAuth = false;


                        }
                    }
                });

        return isAuth;
    }

    public void init() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Product/");


        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                long size = dataSnapshot.getChildrenCount();

                String Path = "Product/";

                for (int i = 0; i < size; i++) {
                    ReadDatabase(Path + i);
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        Cart();

    }

    public void ReadDatabase(String Path) {
        // Read from the database
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(Path);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                Product value = dataSnapshot.getValue(Product.class);


                String nameOfProduct = value.getNameOfProduct();
                String Detail = value.Description;
                String Price = value.Price;
                String NumberOfImages = value.NumberOfImage;

                int index = list.size();
                list.add(new ProductOverView(nameOfProduct, Price, Detail));
                MapOfProduct.put(value.NameOfProduct, index);

                int num = Integer.parseInt(NumberOfImages);

                //for (int i = 0; i < num; i++) {
                    String Path = "Product/" + nameOfProduct + "/"  + "0.png" ;
                    getStorage(Path, index);
                //}
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
    }

    static public ArrayList<ProductOverView> list = new ArrayList<>();

    private StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();
    public void getStorage(String Path, final int index) {
        StorageReference  ref = mStorageRef.child(Path);
        ref.getBytes(1024*1024).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                list.get(index).setMainBitmap(bitmap);
            }
        });
    }


    public void Cart ()  {


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String UID = getToken();
        final String Path = UID + "/MyCart/";
        DatabaseReference myRef = database.getReference(Path);



        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                long size = dataSnapshot.getChildrenCount();

                for (int i = 0; i < size; i++) {

                    ReadCart(Path + i);

                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });



    }

    public static LinkedList<Integer> Cart = new LinkedList<>();
    public static  LinkedList<Integer> PreviousOrder = new LinkedList<>();

    public void ReadCart(String Path) {
        // Read from the database
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(Path);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                CartAndPreviousOrder value = dataSnapshot.getValue(CartAndPreviousOrder.class);

                String nameOfProduct = value.getNameOfProduct();
                String Detail = value.Description;
                String isCart = value.isCart;

                System.out.println();
                System.out.println(nameOfProduct);
                System.out.println();


                if (MapOfProduct.get(nameOfProduct)== null) {
                    return;
                }

                int index = MapOfProduct.get(nameOfProduct);
                if (isCart.equals("true")) {
                    Cart.add(index);
                }
                else {
                    PreviousOrder.add(index);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
    }



}
