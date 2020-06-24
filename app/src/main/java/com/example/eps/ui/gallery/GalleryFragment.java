package com.example.eps.ui.gallery;

import android.content.Intent;
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
import com.example.eps.CategoryAdapter;
import com.example.eps.CategoryModel;
import com.example.eps.LoadingActivity;
import com.example.eps.ProductOverView;
import com.example.eps.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class GalleryFragment extends Fragment {
    //  Current Purchase //
    private GalleryViewModel galleryViewModel;
//    private Button SignOut;

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
        HashSet<Integer> set= new HashSet<>();
        Backend backend = new Backend();
        backend.CurrentOrders();

        startActivity(new Intent(root.getContext(), LoadingActivity.class));

        List<NotificationModel>NotificationModelList = new LinkedList<>();

        System.out.println(Backend.list.size());


        for (int i = 0; i < Backend.CurrentPur.size(); i++) {

            int index = Backend.CurrentPur.get(i).getIndex();
            if (index == -1) {
                continue;
            }
            else if (! set.contains(index)) {
                System.out.println(i);
//                Bitmap bitmap = Backend.CurrentPur.get(i).getMainBitmap();
//                String Name = Backend.CurrentPur.get(i).getNameOfProduct();
//
                Bitmap bitmap = Backend.list.get(index).getMainBitmap();
                String Name = Backend.list.get(index).getNameOfProduct();
                set.add(index);

                System.out.println(index);

                NotificationModelList.add(new NotificationModel(bitmap, Name, index));

            }

        }

        NotigicationAdaptor categoryAdapter = new NotigicationAdaptor(NotificationModelList);
        NotificationRecycleView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();





        return root;
    }
}