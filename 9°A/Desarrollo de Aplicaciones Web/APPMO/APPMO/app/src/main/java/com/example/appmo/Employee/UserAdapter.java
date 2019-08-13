package com.example.appmo.Employee;

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


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {
    View view;

    List<User> userList;

    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_user, parent, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(UserHolder holder, int position) {
        holder.txtName.setText(userList.get(position).getName());
        holder.txtSubNamePather.setText(userList.get(position).getSubNamePather());
        holder.txtSubNameMother.setText(userList.get(position).getSubNameMother());
        holder.txtPhone.setText(userList.get(position).getPhone());
        holder.txtType.setText(userList.get(position).getType());
        holder.txtAddres.setText(userList.get(position).getAddres());
        holder.txtNumberAddres.setText(userList.get(position).getAddresNumber());
        holder.txtCp.setText(userList.get(position).getCp());
        holder.txtColony.setText(userList.get(position).getColony());
        holder.txtCity.setText(userList.get(position).getCity());
        holder.txtState.setText(userList.get(position).getState());
        holder.setOnClickListeners();


    }

    @Override
    public int getItemCount() {
        return userList.size();
    }



    public class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtName, txtSubNamePather,  txtSubNameMother, txtPhone, txtType,  txtAddres, txtNumberAddres,
                txtCp, txtColony, txtCity, txtState;
        ImageView imConsult;
        Context context;

        public UserHolder(View view) {
            super(view);
            context= view.getContext();
            txtName = view.findViewById(R.id.txtName);
            txtSubNamePather = view.findViewById(R.id.txtSubNamePather);
            txtSubNameMother = view.findViewById(R.id.txtSubNameMother);
            txtPhone = view.findViewById(R.id.txtPhone);
            txtType = view.findViewById(R.id.txtType);
            txtAddres = view.findViewById(R.id.txtAddres);
            txtNumberAddres = view.findViewById(R.id.txtNumberAddres);
            txtCp = view.findViewById(R.id.txtCp);
            txtColony = view.findViewById(R.id.txtColony);
            txtCity = view.findViewById(R.id.txtCity);
            txtState = view.findViewById(R.id.txtState);
            imConsult = view.findViewById(R.id.imConsult);
        }

        void setOnClickListeners() {
        imConsult.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.imConsult:
                    Intent intentUser = new Intent(context, ConsultUser.class);
                    intentUser.putExtra("txtName", txtName.getText());
                    intentUser.putExtra("txtSubNamePather", txtSubNamePather.getText());
                    intentUser.putExtra("txtSubNameMother", txtSubNameMother.getText());
                    intentUser.putExtra("txtPhone", txtPhone.getText());
                    intentUser.putExtra("txtType", txtType.getText());
                    intentUser.putExtra("txtAddres", txtAddres.getText().toString());
                    intentUser.putExtra("txtNumberAddres", txtNumberAddres.getText().toString());
                    intentUser.putExtra("txtCp", txtCp.getText().toString());
                    intentUser.putExtra("txtColony", txtColony.getText().toString());
                    intentUser.putExtra("txtCity", txtCity.getText().toString());
                    intentUser.putExtra("txtState", txtState.getText().toString());
                    context.startActivity(intentUser);
                    break;
            }
        }
    }
}