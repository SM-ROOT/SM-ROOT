package com.example.appmo.Recipe;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appmo.R;

import java.util.List;


public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeHolder> {
    View view;

    List<Recipe> listRecipe;

    public RecipeAdapter(List<Recipe> listRecipe) {
        this.listRecipe = listRecipe;
    }

    @Override
    public RecipeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_recipe, parent, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        return new RecipeHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeHolder holder, int position) {
        holder.txtName.setText(listRecipe.get(position).getName());
        holder.txtQuantity.setText(listRecipe.get(position).getQuantity());
        holder.txtType.setText(listRecipe.get(position).getType());
        holder.txtIngredient.setText(listRecipe.get(position).getIngredient());
        holder.txtMeasure.setText(listRecipe.get(position).getMeasure());

    }

    @Override
    public int getItemCount() {
        return listRecipe.size();
    }

    public class RecipeHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtType,  txtQuantity, txtIngredient, txtMeasure;

        public RecipeHolder(View view) {
            super(view);
            txtName = view.findViewById(R.id.txtName);
            txtQuantity = view.findViewById(R.id.txtNumberAddres);
            txtType = view.findViewById(R.id.txtType);
            txtIngredient = view.findViewById(R.id.txtIngredient);
            txtMeasure = view.findViewById(R.id.txtMeasure);


        }
    }
}