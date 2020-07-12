package com.example.eps.ui.slideshow;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eps.Backend;
import com.example.eps.ProductOverView;
import com.example.eps.R;
import com.example.eps.ui.gallery.NotificationModel;
import com.example.eps.ui.gallery.NotigicationAdaptor;
import com.google.android.material.snackbar.Snackbar;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private RecyclerView CartRecycleView;
    Button BuyAllButton;
    private Button SignOut;
    // MY Cart //


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });
//
//        BuyAllButton = root.findViewById(R.id.BuyAllButton);
//
//
//        /////////////////// categoryRecyclerView
//
//        CartRecycleView = root.findViewById(R.id.CartRecycleView);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        CartRecycleView.setLayoutManager(linearLayoutManager);
//
//
//        List<NotificationModel> NotificationModelList = new LinkedList<>();
//
//        final LinkedList<Integer> list = Backend.Cart;
//        System.out.println(list.size());
//
//        for (int i = 0; i < list.size(); i++) {
//            int index = list.get(i);
//            ProductOverView product = Backend.list.get(index);
//            Bitmap bitmap = product.getMainBitmap();
//            String Name = product.getNameOfProduct();
//            System.out.println(i);
//            NotificationModelList.add(new NotificationModel(bitmap, Name, index));
//
//        }
//
//
//        NotigicationAdaptor categoryAdapter = new NotigicationAdaptor(NotificationModelList);
//        CartRecycleView.setAdapter(categoryAdapter);
//        categoryAdapter.notifyDataSetChanged();
//
//
//        BuyAllButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                for (int i = 0; i < list.size(); i++) {
//                    int index = list.get(i);
//                    Backend backend = new Backend();
//                    backend.purchase(index);
//                    backend.removeFromCart(index);
//                }
//            }
//        });
        SignOut = root.findViewById(R.id.SignOut);

        SignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "See you soon", Toast.LENGTH_LONG).show();
                Backend backend = new Backend();
                backend.signOut();
                Snackbar.make(v, "Please close this app as soon as possible", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        return root;
    }
}