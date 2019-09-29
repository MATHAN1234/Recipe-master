package com.example.recipe_app;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.recipe_app.Model.Fooddata;

import java.util.ArrayList;
import java.util.List;

public class Myadapter extends RecyclerView.Adapter<FoodViewHolder> {

    private Context mcontext;
    private List<Fooddata> mfoodlist;

    public Myadapter(Context mcontext, List<Fooddata> mfoodlist) {
        this.mcontext = mcontext;
        this.mfoodlist = mfoodlist;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_row, viewGroup, false);
            return new FoodViewHolder(mView);
    }
    @Override
    public void onBindViewHolder(@NonNull final FoodViewHolder foodViewHolder, int i) {
            Glide.with(mcontext)
            .load(mfoodlist.get(i).getItemImage())
            .into(foodViewHolder.imageView);

        foodViewHolder.mtitle.setText(mfoodlist.get(i).getItemName());
        foodViewHolder.mCategory.setText(mfoodlist.get(i).getItemCategory());

        foodViewHolder.mcardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext,detailActivity.class);

                intent.putExtra("username", mfoodlist.get(foodViewHolder.getAdapterPosition()).getUsername());
                intent.putExtra("Image", mfoodlist.get(foodViewHolder.getAdapterPosition()).getItemImage());
                intent.putExtra("Category", mfoodlist.get(foodViewHolder.getAdapterPosition()).getItemCategory());
                intent.putExtra("Name", mfoodlist.get(foodViewHolder.getAdapterPosition()).getItemName());
                intent.putExtra("inga", mfoodlist.get(foodViewHolder.getAdapterPosition()).getIteming1());
                intent.putExtra("ingb", mfoodlist.get(foodViewHolder.getAdapterPosition()).getIteming2());
                intent.putExtra("ingc", mfoodlist.get(foodViewHolder.getAdapterPosition()).getIteming3());
                intent.putExtra("ingd", mfoodlist.get(foodViewHolder.getAdapterPosition()).getIteming4());
                intent.putExtra("stepa", mfoodlist.get(foodViewHolder.getAdapterPosition()).getStep1());
                intent.putExtra("stepb", mfoodlist.get(foodViewHolder.getAdapterPosition()).getStep2());
                intent.putExtra("stepc", mfoodlist.get(foodViewHolder.getAdapterPosition()).getStep3());
                intent.putExtra("stepd", mfoodlist.get(foodViewHolder.getAdapterPosition()).getStep4());
                intent.putExtra("keyValue", mfoodlist.get(foodViewHolder.getAdapterPosition()).getKey());
                mcontext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mfoodlist.size();
    }

    public void filteredList(ArrayList<Fooddata> filterList) {
        mfoodlist = filterList;
        notifyDataSetChanged();
    }
}

class FoodViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView mtitle, mCategory;
        CardView mcardView;

    public FoodViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.ivImage);
        mtitle = itemView.findViewById(R.id.tvtitle);
        mcardView = itemView.findViewById(R.id.mycardview);
        mCategory = itemView.findViewById(R.id.tvCategory);
    }
}
