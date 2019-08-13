package com.example.appmo.Client;

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


public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ClientHolder> {

    /**
     * Adaptador de la vista
     **/
    View view;

    List<Client> listClient;

    public ClientAdapter(List<Client> listClient) {
        this.listClient = listClient;
    }

    @Override
    public ClientHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_client, parent, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        return new ClientHolder(view);
    }

    /**
     * Obtención de los String del modulo
     **/
    @Override
    public void onBindViewHolder(ClientHolder holder, int position) {
        holder.txtName.setText(listClient.get(position).getName());
        holder.txtPhone.setText(listClient.get(position).getPhone());
        holder.txtAddres.setText(listClient.get(position).getAdrres());
        holder.txtNumberAddres.setText(listClient.get(position).getNumberAdrres());
        holder.txtCp.setText(listClient.get(position).getCp());
        holder.txtColony.setText(listClient.get(position).getColony());
        holder.txtCity.setText(listClient.get(position).getCity());
        holder.txtState.setText(listClient.get(position).getState());
        holder.setOnclickListener();
    }

    @Override
    public int getItemCount() {
        return listClient.size();
    }

    /**
     * Mapeo del modelo
     **/

    public class ClientHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtName, txtPhone, txtAddres, txtNumberAddres, txtCp,
                txtColony, txtCity, txtState;
        ImageView imgConsult, imEdit;
        Context context;

        public ClientHolder(View view) {
            super(view);
            context = view.getContext();
            txtName = view.findViewById(R.id.txtName);
            txtPhone = view.findViewById(R.id.txtPhone);
            txtAddres = view.findViewById(R.id.txtAddres);
            txtNumberAddres = view.findViewById(R.id.txtNumberAddres);
            txtCp = view.findViewById(R.id.txtCp);
            txtColony = view.findViewById(R.id.txtColony);
            txtCity = view.findViewById(R.id.txtCity);
            txtState = view.findViewById(R.id.txtState);
            imgConsult = view.findViewById(R.id.imConsult);
            imEdit = view.findViewById(R.id.imEdit);

        }

        /**
         * Evento de los iconos de Consultar y Editar
         **/
        public void setOnclickListener() {
            imgConsult.setOnClickListener(this);
            imEdit.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.imConsult:
                    Intent intentConsultSupply = new Intent(context, ClientConsult.class);
                    intentConsultSupply.putExtra("txtName", txtName.getText().toString());
                    intentConsultSupply.putExtra("txtPhone", txtPhone.getText().toString());
                    intentConsultSupply.putExtra("txtAddres", txtAddres.getText().toString());
                    intentConsultSupply.putExtra("txtNumberAddres", txtNumberAddres.getText().toString());
                    intentConsultSupply.putExtra("txtCp", txtCp.getText().toString());
                    intentConsultSupply.putExtra("txtColony", txtColony.getText().toString());
                    intentConsultSupply.putExtra("txtCity", txtCity.getText().toString());
                    intentConsultSupply.putExtra("txtState", txtState.getText().toString());
                    context.startActivity(intentConsultSupply);
                    break;

                case R.id.imEdit:
                    Intent intentEditSupply = new Intent(context, ClientEdit.class);
                    intentEditSupply.putExtra("txtName", txtName.getText().toString());
                    intentEditSupply.putExtra("txtPhone", txtPhone.getText().toString());
                    intentEditSupply.putExtra("txtAddres", txtAddres.getText().toString());
                    intentEditSupply.putExtra("txtNumberAddres", txtNumberAddres.getText().toString());
                    intentEditSupply.putExtra("txtCp", txtCp.getText().toString());
                    intentEditSupply.putExtra("txtColony", txtColony.getText().toString());
                    intentEditSupply.putExtra("txtCity", txtCity.getText().toString());
                    intentEditSupply.putExtra("txtState", txtState.getText().toString());
                    context.startActivity(intentEditSupply);
                    break;
            }
        }
    }
}