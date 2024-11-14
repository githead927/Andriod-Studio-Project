package com.example.otp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.FeaturedViewHolder> {
    Context context;
    ArrayList<FeaturedHelperClass> featuredlocations = new ArrayList<>();

    /*public FeaturedAdapter(ArrayList<FeaturedHelperClass> featuredlocations) {
        this.featuredlocations = featuredlocations;
    }*/

    public FeaturedAdapter(Context context, ArrayList<FeaturedHelperClass> featuredlocations) {
        this.context = context;
        this.featuredlocations = featuredlocations;
    }

    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_carddesign, parent, false);
        FeaturedViewHolder featuredViewHolder = new FeaturedViewHolder(view);
        return featuredViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {
        holder.image.setImageResource(featuredlocations.get(position).getImage());
        holder.names.setText(featuredlocations.get(position).getNames());
        holder.desc.setText(featuredlocations.get(position).getDesc());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,bikeinfo.class);
                intent.putExtra("image",featuredlocations.get(position).getImage());
                intent.putExtra("names",featuredlocations.get(position).getNames());
                intent.putExtra("desc",featuredlocations.get(position).getDesc());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return featuredlocations.size();
    }

    public static class FeaturedViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView names, desc;
        CardView cardView;

        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            image = itemView.findViewById(R.id.featured_bikes);
            names = itemView.findViewById(R.id.featured_bikes_names);
            desc = itemView.findViewById(R.id.featured_bikes_desc);
            cardView = itemView.findViewById(R.id.featured_carddesign);
        }
    }
}
