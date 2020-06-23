package com.example.eps.ui.gallery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eps.CategoryModel;
import com.example.eps.ProductDescription;
import com.example.eps.R;

import java.util.List;

public class NotigicationAdaptor  extends RecyclerView.Adapter<NotigicationAdaptor.ViewHolder> {

    List<NotificationModel> categoryModelList;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notificartion_screen_bar, parent, false);
        return new NotigicationAdaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Data Binding
        Bitmap Icon = (categoryModelList.get(position).getImage());
        String Name = categoryModelList.get(position).getName();
        int index = categoryModelList.get(position).getIndex();

        holder.setCategory(Name, position, Icon, index);

    }


    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }


    public NotigicationAdaptor(List<NotificationModel> categoryModelList) {
        this.categoryModelList = categoryModelList;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        private ImageView categoryIcon;
        private TextView categoryName;




        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            categoryIcon = itemView.findViewById(R.id.NotificationImage);
            categoryName = itemView.findViewById(R.id.NotificationName);

        }

        private void setCategoryIcon(Bitmap bitmap) {
            // TODO: set category Icon here;
            this.categoryIcon.setImageBitmap(bitmap);

        }

        public void setCategory(final String name, final int position, Bitmap bitmap, final int index) {
            categoryName.setText(name);
            categoryIcon.setImageBitmap(bitmap);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position != -1) {
                        // TODO: change ProductDescription to Notification Description
                        Intent categoryIntent = new Intent(itemView.getContext(),ProductDescription.class);
                        categoryIntent.putExtra("Name", Integer.toString(index));
                        itemView.getContext().startActivity(categoryIntent);
                    }
                }
            });
        }

    }
}
