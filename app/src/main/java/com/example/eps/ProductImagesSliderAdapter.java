package com.example.eps;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.eps.ui.gallery.NotigicationAdaptor;

import java.util.List;

public class ProductImagesSliderAdapter extends RecyclerView.Adapter<ProductImagesSliderAdapter.ViewHolder> {

    private List<ProductImagesSliderModel> SliderModel;

    public ProductImagesSliderAdapter(List sliderModel) {
        SliderModel = sliderModel;
    }

    @NonNull
    @Override
    public ProductImagesSliderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ads_layout, parent, false);
        return new ProductImagesSliderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductImagesSliderAdapter.ViewHolder holder, int position) {
        // Data Binding
        Bitmap Icon = (SliderModel.get(position).getBanner());

        holder.setCategory(position, Icon);

    }

    @Override
    public int getItemCount() {
        return SliderModel.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {


        private ImageView categoryIcon;



        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            categoryIcon = itemView.findViewById(R.id.ProductImages);
        }

        private void setCategoryIcon(int bitmap) {
            // TODO: set category Icon here;
            this.categoryIcon.setImageResource(bitmap);

        }

        public  void setCategory(final int position, Bitmap bitmap) {

            categoryIcon.setImageBitmap(bitmap);
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (position != -1) {
//                        // TODO: change ProductDescription to Notification Description
//                        Intent categoryIntent = new Intent(itemView.getContext(),ProductDescription.class);
//                        categoryIntent.putExtra("Name", Integer.toString(position));
//                        itemView.getContext().startActivity(categoryIntent);
//                    }
//                }
//            });
        }

    }

}
