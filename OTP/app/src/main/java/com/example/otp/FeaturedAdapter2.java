package com.example.otp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FeaturedAdapter2 extends RecyclerView.Adapter<FeaturedAdapter2.FeaturedViewHolder2> {
    ArrayList<FeaturedHelperClass2> featuredlocations2;

    public FeaturedAdapter2(ArrayList<FeaturedHelperClass2> featuredlocations2) {
        this.featuredlocations2 = featuredlocations2;
    }

    @NonNull
    @Override
    public FeaturedViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_carddesign2,parent,false);
        FeaturedViewHolder2 featuredViewHolder2 = new FeaturedViewHolder2(view);
        return featuredViewHolder2;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder2 holder, int position) {
        FeaturedHelperClass2 featuredHelperClass2 = featuredlocations2.get(position);
        holder.text1.setText(featuredHelperClass2.getText1());
        holder.text2.setText(featuredHelperClass2.getText2());
    }

    @Override
    public int getItemCount() {
        return featuredlocations2.size();
    }

    public static class FeaturedViewHolder2 extends RecyclerView.ViewHolder{
        TextView text1,text2;

        public FeaturedViewHolder2(@NonNull View itemView) {
            super(itemView);

            //Hooks
            text1 = itemView.findViewById(R.id.features_text1);
            text2 = itemView.findViewById(R.id.features_text2);
        }
    }
}
