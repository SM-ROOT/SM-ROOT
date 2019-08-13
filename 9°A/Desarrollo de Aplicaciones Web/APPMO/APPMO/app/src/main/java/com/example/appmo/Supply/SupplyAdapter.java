package com.example.appmo.Supply;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appmo.R;

import java.util.List;


public class SupplyAdapter extends RecyclerView.Adapter<SupplyAdapter.SupplyHolder> {
    public static ManagerFragmentSupply state;
    View view;
    List<Supply> listSuply;

    public SupplyAdapter(List<Supply> listSuply) {
        this.listSuply = listSuply;
    }

    @Override
    public SupplyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_supply, parent, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        return new SupplyHolder(view);
    }

    @Override
    public void onBindViewHolder(SupplyHolder holder, int position) {
        holder.txtRfc.setText(listSuply.get(position).getRfc());
        holder.txtPhone.setText(listSuply.get(position).getPhone());
        holder.txtMail.setText(listSuply.get(position).getMail());
        holder.txtAddres.setText(listSuply.get(position).getAddres());
        holder.txtNumberAddres.setText(listSuply.get(position).getNumAddres());
        holder.txtCp.setText(listSuply.get(position).getCp());
        holder.txtColony.setText(listSuply.get(position).getColony());
        holder.txtCity.setText(listSuply.get(position).getCity());
        holder.txtState.setText(listSuply.get(position).getState());

        holder.setOnClickListeners();

    }

    @Override
    public int getItemCount() {
        return listSuply.size();
    }

    public class SupplyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtRfc, txtPhone, txtMail, txtAddres, txtNumberAddres, txtCp, txtColony, txtCity, txtState;
        ImageView imConsult, imEdit, imDelete;
        Context context;

        public SupplyHolder(View view) {
            super(view);
            context = view.getContext();
            txtRfc = view.findViewById(R.id.txtName);
            txtPhone = view.findViewById(R.id.txtPhone);
            txtMail = view.findViewById(R.id.txtMeasure);
            txtAddres = view.findViewById(R.id.txtAddres);
            txtNumberAddres = view.findViewById(R.id.txtNumberAddres);
            txtCp = view.findViewById(R.id.txtCp);
            txtColony = view.findViewById(R.id.txtColony);
            txtCity = view.findViewById(R.id.txtCity);
            txtState = view.findViewById(R.id.txtState);
            imConsult = view.findViewById(R.id.imConsult);
            imEdit = view.findViewById(R.id.imEdit);
            imDelete = view.findViewById(R.id.imDelet);
        }

        void setOnClickListeners() {
            imConsult.setOnClickListener(this);
            imEdit.setOnClickListener(this);
            imDelete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.imConsult:
                    Intent intentSupply = new Intent(context, SupplyConsult.class);
                    intentSupply.putExtra("txtRfc", txtRfc.getText().toString());
                    intentSupply.putExtra("txtPhone", txtPhone.getText().toString());
                    intentSupply.putExtra("txtMail", txtMail.getText().toString());
                    intentSupply.putExtra("txtAddres", txtAddres.getText().toString());
                    intentSupply.putExtra("txtNumberAddres", txtNumberAddres.getText().toString());
                    intentSupply.putExtra("txtCp", txtCp.getText().toString());
                    intentSupply.putExtra("txtColony", txtColony.getText().toString());
                    intentSupply.putExtra("txtCity", txtCity.getText().toString());
                    intentSupply.putExtra("spnState", txtState.getText().toString());
                    context.startActivity(intentSupply);
                    break;

                case R.id.imEdit:
                    Intent intentEdit = new Intent(context, SupplyEdit.class);
                    intentEdit.putExtra("txtRfc", txtRfc.getText().toString());
                    intentEdit.putExtra("txtPhone", txtPhone.getText().toString());
                    intentEdit.putExtra("txtMail", txtMail.getText().toString());
                    intentEdit.putExtra("txtAddres", txtAddres.getText().toString());
                    intentEdit.putExtra("txtNumberAddres", txtNumberAddres.getText().toString());
                    intentEdit.putExtra("txtCp", txtCp.getText().toString());
                    intentEdit.putExtra("txtColony", txtColony.getText().toString());
                    intentEdit.putExtra("txtCity", txtCity.getText().toString());
                    context.startActivity(intentEdit);
                    break;

                case R.id.imDelet:
                    Toast.makeText(context, "Se obtuvo en el evento", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    private void changeFragment(ManagerFragmentSupply state) {
        this.state = ManagerFragmentSupply.setState(state);
        this.state.execute((MainContainerSupply) view.getContext());
    }
}