package com.example.appmo.Order;

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


public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.RouteHolder> {
    View view;

    List<Order> listOrder;

    public OrderAdapter(List<Order> listOrder) {
        this.listOrder = listOrder;
    }

    @Override
    public RouteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_order, parent, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        return new RouteHolder(view);
    }

    @Override
    public void onBindViewHolder(RouteHolder holder, int position) {
        holder.txtDate.setText(listOrder.get(position).getDate());
        holder.txtName.setText(listOrder.get(position).getNameOrder());
        holder.txtDestination.setText(listOrder.get(position).getDestinationOrder());
        holder.txtType.setText(listOrder.get(position).getType());
        holder.txtNameBread.setText(listOrder.get(position).getNameBread());
        holder.txtQuantity.setText(listOrder.get(position).getQualitityOrder());
        holder.setOnClickListener();
    }

    @Override
    public int getItemCount() {
        return listOrder.size();
    }

    public class RouteHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtName, txtDate,  txtDestination, txtType, txtNameBread, txtQuantity;
        ImageView imConsult;
        Context context;

        public RouteHolder(View view) {
            super(view);
            context = view.getContext();
            txtDate = view.findViewById(R.id.txtDate);
            txtName = view.findViewById(R.id.txtName);
            txtDestination = view.findViewById(R.id.txtPhone);
            txtType = view.findViewById(R.id.txtType);
            txtNameBread = view.findViewById(R.id.txtNameBread);
            txtQuantity = view.findViewById(R.id.txtNumberAddres);
            imConsult = view.findViewById(R.id.imConsult);
        }

        public void setOnClickListener() {
            imConsult.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.imConsult:
                    Intent intentConsult= new Intent(context,ConsultOrder.class);
                    intentConsult.putExtra("txtName", txtName.getText());
                    intentConsult.putExtra("txtDestination", txtDestination.getText());
                    context.startActivity(intentConsult);
                    break;
            }
        }
    }
}