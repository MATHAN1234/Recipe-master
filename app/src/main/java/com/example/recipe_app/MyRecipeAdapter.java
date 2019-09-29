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

public class MyRecipeAdapter extends RecyclerView.Adapter<RecipeFoodViewHolder> {

    private Context mcontext;
    private List<Fooddata> mfoodlist;

    public MyRecipeAdapter(Context mcontext, List<Fooddata> mfoodlist) {
        this.mcontext = mcontext;
        this.mfoodlist = mfoodlist;
    }

    @NonNull
    @Override
    public RecipeFoodViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_myreciperow, viewGroup, false);
        return new RecipeFoodViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecipeFoodViewHolder foodViewHolder, int i) {

        Glide.with(mcontext)
                .load(mfoodlist.get(i).getItemImage())
                .into(foodViewHolder.imageView);

        foodViewHolder.mtitle.setText(mfoodlist.get(i).getItemName());
        foodViewHolder.mCategory.setText(mfoodlist.get(i).getItemCategory());

        foodViewHolder.mcardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, MyRecipeDetailActivity.class);

                intent.putExtra("username1", mfoodlist.get(foodViewHolder.getAdapterPosition()).getUsername());
                intent.putExtra("Image1", mfoodlist.get(foodViewHolder.getAdapterPosition()).getItemImage());
                intent.putExtra("Category1", mfoodlist.get(foodViewHolder.getAdapterPosition()).getItemCategory());
                intent.putExtra("Name1", mfoodlist.get(foodViewHolder.getAdapterPosition()).getItemName());
                intent.putExtra("inga1", mfoodlist.get(foodViewHolder.getAdapterPosition()).getIteming1());
                intent.putExtra("ingb1", mfoodlist.get(foodViewHolder.getAdapterPosition()).getIteming2());
                intent.putExtra("ingc1", mfoodlist.get(foodViewHolder.getAdapterPosition()).getIteming3());
                intent.putExtra("ingd1", mfoodlist.get(foodViewHolder.getAdapterPosition()).getIteming4());
                intent.putExtra("stepa1", mfoodlist.get(foodViewHolder.getAdapterPosition()).getStep1());
                intent.putExtra("stepb1", mfoodlist.get(foodViewHolder.getAdapterPosition()).getStep2());
                intent.putExtra("stepc1", mfoodlist.get(foodViewHolder.getAdapterPosition()).getStep3());
                intent.putExtra("stepd1", mfoodlist.get(foodViewHolder.getAdapterPosition()).getStep4());
                intent.putExtra("keyValue1", mfoodlist.get(foodViewHolder.getAdapterPosition()).getKey());

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
class RecipeFoodViewHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView mtitle, mCategory;
    CardView mcardView;

    public RecipeFoodViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.ivImage);
        mtitle = itemView.findViewById(R.id.tvtitle);
        mcardView = itemView.findViewById(R.id.mycardview);
        mCategory = itemView.findViewById(R.id.tvCategory);
    }
}
