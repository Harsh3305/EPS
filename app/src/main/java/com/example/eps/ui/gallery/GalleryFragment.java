package com.example.eps.ui.gallery;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eps.Backend;
import com.example.eps.CategoryAdapter;
import com.example.eps.CategoryModel;
import com.example.eps.ProductOverView;
import com.example.eps.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private RecyclerView NotificationRecycleView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);


        /////////////////// categoryRecyclerView

        NotificationRecycleView = root.findViewById(R.id.RecycleViewNotification);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        NotificationRecycleView.setLayoutManager(linearLayoutManager);


        List<NotificationModel>NotificationModelList = new LinkedList<>();
//
//
//
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
//
//        NotigicationAdaptor categoryAdapter = new NotigicationAdaptor(NotificationModelList);
//        NotificationRecycleView.setAdapter(categoryAdapter);
//        categoryAdapter.notifyDataSetChanged();
//


        LinkedList<Integer> list = Backend.PreviousOrder;
        System.out.println(Backend.list.size());


        for (int i = 0; i < list.size(); i++) {
            int index = list.get(i);
            System.out.println(i);
            ProductOverView product = Backend.list.get(index);
            Bitmap bitmap = product.getMainBitmap();
            String Name = product.getNameOfProduct();

            NotificationModelList.add(new NotificationModel(bitmap, Name));

        }

        NotigicationAdaptor categoryAdapter = new NotigicationAdaptor(NotificationModelList);
        NotificationRecycleView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();




        return root;
    }
}