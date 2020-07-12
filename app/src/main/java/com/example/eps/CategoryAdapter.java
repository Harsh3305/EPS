package com.example.eps;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    List<CategoryModel> categoryModelList;

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_short_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        // Data Binding
        Bitmap Icon = (categoryModelList.get(position).getImage());
        String Name = categoryModelList.get(position).getName();
        String Price = categoryModelList.get(position).getPrice();
        holder.setCategory(Name, position, Icon, Price);

    }


    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }


    public CategoryAdapter(List<CategoryModel> categoryModelList) {
        this.categoryModelList = categoryModelList;
    }






    public class ViewHolder extends RecyclerView.ViewHolder {





        private ImageView categoryIcon;
        private TextView categoryName;
        private TextView categoryPrice;


        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            categoryIcon = itemView.findViewById(R.id.ProductImageShortView);
            categoryName = itemView.findViewById(R.id.ProductNameShortView);
            categoryPrice = itemView.findViewById(R.id.ProductPriceShortView);


        }

        private void setCategoryIcon(int bitmap) {
            // TODO: set category Icon here;
            this.categoryIcon.setImageResource(bitmap);

        }

        private void setCategory(final String name, final int position, Bitmap bitmap, String price) {
            categoryName.setText(name);
            categoryIcon.setImageBitmap(bitmap);
            categoryPrice.setText(price);

            categoryIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position != -1) {
                        Intent categoryIntent = new Intent(itemView.getContext(),ProductDescription.class);
                        categoryIntent.putExtra("Name", Integer.toString(position));
                        itemView.getContext().startActivity(categoryIntent);
                    }
                }
            });

            categoryName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position != -1) {
                        Intent categoryIntent = new Intent(itemView.getContext(),ProductDescription.class);
                        categoryIntent.putExtra("Name", Integer.toString(position));
                        itemView.getContext().startActivity(categoryIntent);
                    }
                }
            });

            categoryPrice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position != -1) {
                        Intent categoryIntent = new Intent(itemView.getContext(),ProductDescription.class);
                        categoryIntent.putExtra("Name", Integer.toString(position));
                        itemView.getContext().startActivity(categoryIntent);
                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position != -1) {
                        Intent categoryIntent = new Intent(itemView.getContext(),ProductDescription.class);
                        categoryIntent.putExtra("Name", Integer.toString(position));
                        itemView.getContext().startActivity(categoryIntent);
                    }
                }
            });
        }

    }
}