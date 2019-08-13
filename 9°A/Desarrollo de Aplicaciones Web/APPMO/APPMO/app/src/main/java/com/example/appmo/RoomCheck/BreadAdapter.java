package com.example.appmo.RoomCheck;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appmo.R;

import java.util.List;


public class BreadAdapter extends RecyclerView.Adapter<BreadAdapter.BreadHolder> {
    View view;

    List<Bread> listBread;

    public BreadAdapter(List<Bread> listBread) {
        this.listBread = listBread;
    }

    @Override
    public BreadHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_bread, parent, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        return new BreadHolder(view);
    }

    @Override
    public void onBindViewHolder(BreadHolder holder, int position) {
        holder.txtName.setText(listBread.get(position).getName());
        holder.txtType.setText(listBread.get(position).getType());
        holder.txtQuantity.setText(listBread.get(position).getQuantity());

    }

    @Override
    public int getItemCount() {
        return listBread.size();
    }

    public class BreadHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtType,  txtQuantity;

        public BreadHolder(View view) {
            super(view);
            txtName = view.findViewById(R.id.txtName);
            txtType = view.findViewById(R.id.txtType);
            txtQuantity = view.findViewById(R.id.txtNumberAddres);



        }
    }
}