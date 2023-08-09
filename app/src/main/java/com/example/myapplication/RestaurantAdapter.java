package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantHolder> {

    ArrayList<Restaurant> arrayList;
    Context context;

    public RestaurantAdapter(Context context, ArrayList<Restaurant> arrayList)  {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RestaurantHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview, parent, false);

        return new RestaurantHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantHolder holder, int position) {

        Restaurant restaurant = arrayList.get(position);

        Glide.with(holder.itemView)
                .load(arrayList.get(position).getRes_img())
                .into(holder.img);
        holder.name.setText(restaurant.getRes_name());
        holder.kind.setText(restaurant.getRes_kind());
        holder.price.setText(String.valueOf(restaurant.getRes_price()));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class RestaurantHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView name;
        TextView kind;
        TextView price;

        public RestaurantHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.res_image);
            name = itemView.findViewById(R.id.res_name);
            kind = itemView.findViewById(R.id.res_kind);
            price = itemView.findViewById(R.id.res_price);

        }
    }

}

