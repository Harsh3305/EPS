package com.example.eps.ui.home;

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
import com.example.eps.CategoryAdapter;
import com.example.eps.CategoryModel;
import com.example.eps.ProductOverView;
import com.example.eps.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView categoryRecyclerView;
    private HomeViewModel homeViewModel;
//    private static final String TAG = "Home";
//    private AdView mAdView;

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

        for (int i = 0; i < Backend.list.size(); i++) {
            categoryModelList.add(new CategoryModel(Backend.list.get(i).getMainBitmap(), Backend.list.get(i).getNameOfProduct(),Backend.list.get(i).getPrice()));
        }

        CategoryAdapter categoryAdapter = new CategoryAdapter(categoryModelList);
        categoryRecyclerView .setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();

//        mAdView = root.findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

        return root;
    }
}