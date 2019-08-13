package com.example.appmo.Purchases;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appmo.R;

import java.util.List;


public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.ProductHolder> {
    View view;

    List<Material> listProduct;

    public MaterialAdapter(List<Material> listProduct) {
        this.listProduct = listProduct;
    }

    @Override
    public ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_product, parent, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductHolder holder, int position) {
        holder.txtName.setText(listProduct.get(position).getName());
        holder.txtShowDate.setText(listProduct.get(position).getDate());
        holder.txtQuantity.setText(listProduct.get(position).getQuantity());
        holder.txtType.setText(listProduct.get(position).getMeasure());
        holder.txtCoste.setText(listProduct.get(position).getCoste());
        holder.setOnClickListener();

    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtName, txtShowDate,  txtQuantity, txtType, txtCoste;
        ImageView imConsult, imEdit;
        Context context;

        public ProductHolder(View view) {
            super(view);
            context = view.getContext();
            txtShowDate = view.findViewById(R.id.txtShowDate);
            txtName = view.findViewById(R.id.txtName);
            txtQuantity = view.findViewById(R.id.txtNumberAddres);
            txtType = view.findViewById(R.id.txtType);
            txtCoste = view.findViewById(R.id.txtCp);
            imConsult = view.findViewById(R.id.imConsult);
            imEdit= view.findViewById(R.id.imEdit);
        }

        public void setOnClickListener() {
            imConsult.setOnClickListener(this);
            imEdit.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.imConsult:
                    Intent intentConsultMaterial = new Intent(context, MaterialConsult.class);
                    intentConsultMaterial.putExtra("txtName", txtName.getText().toString());
                    intentConsultMaterial.putExtra("txtQuantity", txtQuantity.getText().toString());
                    intentConsultMaterial.putExtra("txtType", txtType.getText().toString());
                    intentConsultMaterial.putExtra("txtCoste", txtCoste.getText().toString());
                    context.startActivity(intentConsultMaterial);
                    break;

                case R.id.imEdit:
                    Intent intentEditMaterial = new Intent(context, MaterialEdit.class);
                    intentEditMaterial.putExtra("txtName", txtName.getText().toString());
                    intentEditMaterial.putExtra("txtQuantity", txtQuantity.getText().toString());
                    intentEditMaterial.putExtra("txtCoste", txtCoste.getText().toString());
                    context.startActivity(intentEditMaterial);
                    break;
            }
        }
    }
}