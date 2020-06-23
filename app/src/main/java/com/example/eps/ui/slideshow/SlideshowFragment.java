package com.example.eps.ui.slideshow;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private RecyclerView CartRecycleView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
//        final TextView textView = root.findViewById(R.id.text_slideshow);
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });

        /////////////////// categoryRecyclerView

        CartRecycleView = root.findViewById(R.id.CartRecycleView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        CartRecycleView.setLayoutManager(linearLayoutManager);


        List<NotificationModel> NotificationModelList = new LinkedList<>();

//        NotificationModelList.add(new NotificationModel(R.mipmap.ic_launcher, "1"));
//        NotificationModelList.add(new NotificationModel(R.mipmap.ic_launcher, "2"));
//        NotificationModelList.add(new NotificationModel(R.mipmap.ic_launcher, "3"));
//        NotificationModelList.add(new NotificationModel(R.mipmap.ic_launcher, "4"));
//        NotificationModelList.add(new NotificationModel(R.mipmap.ic_launcher, "5"));
//        NotificationModelList.add(new NotificationModel(R.mipmap.ic_launcher, "6"));
//        NotificationModelList.add(new NotificationModel(R.mipmap.ic_launcher, "7"));
//        NotificationModelList.add(new NotificationModel(R.mipmap.ic_launcher, "8"));
//        NotificationModelList.add(new NotificationModel(R.mipmap.ic_launcher, "9"));
//        NotificationModelList.add(new NotificationModel(R.mipmap.ic_launcher, "10"));
//        NotificationModelList.add(new NotificationModel(R.mipmap.ic_launcher, "11"));
//        NotificationModelList.add(new NotificationModel(R.mipmap.ic_launcher, "12"));
//        NotificationModelList.add(new NotificationModel(R.mipmap.ic_launcher, "13"));
//        NotificationModelList.add(new NotificationModel(R.mipmap.ic_launcher, "14"));
//        NotificationModelList.add(new NotificationModel(R.mipmap.ic_launcher, "15"));
//        NotificationModelList.add(new NotificationModel(R.mipmap.ic_launcher, "16"));
//        NotificationModelList.add(new NotificationModel(R.mipmap.ic_launcher, "17"));
//        NotificationModelList.add(new NotificationModel(R.mipmap.ic_launcher, "18"));


        LinkedList<Integer> list = Backend.Cart;
        System.out.println(list.size());

        for (int i = 0; i < list.size(); i++) {
            int index = list.get(i);
            ProductOverView product = Backend.list.get(index);
            Bitmap bitmap = product.getMainBitmap();
            String Name = product.getNameOfProduct();
            System.out.println(i);
            NotificationModelList.add(new NotificationModel(bitmap, Name));

        }



        NotigicationAdaptor categoryAdapter = new NotigicationAdaptor(NotificationModelList);
        CartRecycleView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();


        return root;
    }
}