package com.example.appmo.Route;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.appmo.R;
import com.example.appmo.infoIP;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;


public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.RouteHolder>  {
    View view;
    List<Route> routeList;


    String ip = new infoIP().getIp();
    RecyclerView recyclerView;
    ArrayList<Route> listRoute;
    ProgressDialog progress;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    public RouteAdapter(List<Route> routeList) {
        this.routeList = routeList;
    }


    @Override
    public RouteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_route, parent, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        return new RouteHolder(view);
    }

    @Override
    public void onBindViewHolder(RouteHolder holder, int position) {
        holder.txtName.setText(routeList.get(position).getName());
        holder.txtCurator.setText(routeList.get(position).getCurator());
        holder.setOnClickListener();


    }

    @Override
    public int getItemCount() {
        return routeList.size();
    }

    public class RouteHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtName, txtCurator;
        ImageView imConsult;
        Context context;

        public RouteHolder(View view) {
            super(view);
            context = view.getContext();
            txtName = view.findViewById(R.id.txtName);
            txtCurator = view.findViewById(R.id.txtCurator);
            imConsult = view.findViewById(R.id.imConsult);
        }

        public void setOnClickListener() {
            imConsult.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.imConsult:
                    Intent intentRoute;
                    break;
            }
        }
    }





}