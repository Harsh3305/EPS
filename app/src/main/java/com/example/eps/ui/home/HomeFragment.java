package com.example.eps.ui.home;

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
import com.example.eps.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView categoryRecyclerView;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        /////////////////// categoryRecyclerView

        categoryRecyclerView = root.findViewById(R.id.RecycleViewHome);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        categoryRecyclerView.setLayoutManager(linearLayoutManager);

        List<CategoryModel> categoryModelList = new ArrayList<>();

//        categoryModelList.add(new CategoryModel(R.mipmap.ic_launcher, "1"));
//        categoryModelList.add(new CategoryModel(R.mipmap.ic_launcher, "2"));
//        categoryModelList.add(new CategoryModel(R.mipmap.ic_launcher, "3"));
//        categoryModelList.add(new CategoryModel(R.mipmap.ic_launcher, "4"));
//        categoryModelList.add(new CategoryModel(R.mipmap.ic_launcher, "5"));
//        categoryModelList.add(new CategoryModel(R.mipmap.ic_launcher, "6"));
//        categoryModelList.add(new CategoryModel(R.mipmap.ic_launcher, "7"));
//        categoryModelList.add(new CategoryModel(R.mipmap.ic_launcher, "8"));
//
//        categoryModelList.add(new CategoryModel(R.mipmap.ic_launcher, "9"));
//        categoryModelList.add(new CategoryModel(R.mipmap.ic_launcher, "10"));
//        categoryModelList.add(new CategoryModel(R.mipmap.ic_launcher, "11"));
//        categoryModelList.add(new CategoryModel(R.mipmap.ic_launcher, "12"));
//        categoryModelList.add(new CategoryModel(R.mipmap.ic_launcher, "13"));
//        categoryModelList.add(new CategoryModel(R.mipmap.ic_launcher, "14"));
//        categoryModelList.add(new CategoryModel(R.mipmap.ic_launcher, "15"));
//        categoryModelList.add(new CategoryModel(R.mipmap.ic_launcher, "16"));

        for (int i = 0; i < Backend.list.size(); i++) {
            categoryModelList.add(new CategoryModel(Backend.list.get(i).getMainBitmap(), Backend.list.get(i).getNameOfProduct()));
        }


        CategoryAdapter categoryAdapter = new CategoryAdapter(categoryModelList);
        categoryRecyclerView .setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();


        return root;
    }
}