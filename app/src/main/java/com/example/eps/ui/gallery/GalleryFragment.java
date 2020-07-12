package com.example.eps.ui.gallery;

import android.content.Context;
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
import com.example.eps.JavaMailAPI;
import com.example.eps.LoadingActivity;
import com.example.eps.ProductOverView;
import com.example.eps.PurchaseActivity;
import com.example.eps.R;
import com.example.eps.Settings;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class GalleryFragment extends Fragment {
    //  Current Purchase //
    private GalleryViewModel galleryViewModel;
//    private Button SignOut;

    private RecyclerView NotificationRecycleView;
    public static List<NotificationModel>NotificationModelList = new LinkedList<>();

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

        NotigicationAdaptor categoryAdapter = new NotigicationAdaptor(NotificationModelList);
        NotificationRecycleView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();
        context = getContext();
        update();
        return root;
    }
    public static void update () {
        HashSet<Integer> set= new HashSet<>();
        Backend backend = new Backend();
        backend.CurrentOrders();

//        startActivity(new Intent(root.getContext(), LoadingActivity.class));

        NotificationModelList = new LinkedList<>();

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
//                Bitmap bitmap = Backend.list.get(index).getMainBitmap();
                String Name = Backend.list.get(index).getNameOfProduct();
                set.add(index);

                System.out.println(index);
                //TODO: change
                NotificationModelList.add(new NotificationModel(null, Name, index));
            }
        }
    }
    private static Context context;
    public static void cancellationEmail (String name) {
        Backend backend = new Backend();

        JavaMailAPI mailAPI = new JavaMailAPI(context,backend.mAuth.getCurrentUser().getEmail(), "Cancellation Request for " + name, "You have cancel "  + name + ". For  any feedback, " +
                "please contact us. Our email ID is harshverma3305@gmail.com or call us at 9654499559. You can cancel your product with in 5 days of purchase");
        mailAPI.execute();

        JavaMailAPI mailAPI1 = new JavaMailAPI(context, "harshverma3305@gmail.com", "Cancellation request by  " + backend.mAuth.getCurrentUser().getEmail()+" for "+ name, " User Email = " +backend.mAuth.getCurrentUser().getEmail() + " User UID = "+
                backend.mAuth.getCurrentUser().getUid()+ " Product cancel = " + name );

        // TODO: add address
        mailAPI1.execute();
    }
}