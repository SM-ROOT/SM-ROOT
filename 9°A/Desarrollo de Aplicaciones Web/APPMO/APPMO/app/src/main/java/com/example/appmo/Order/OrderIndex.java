package com.example.appmo.Order;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.appmo.R;
import com.example.appmo.infoIP;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class OrderIndex extends Fragment  implements Response.Listener<JSONObject>, Response.ErrorListener {
    String ip = new infoIP().getIp();
    View view;
    RecyclerView recyclerView;
    ArrayList<Order> listOrder;
    ProgressDialog progress;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;


    public OrderIndex() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_index_order, container, false);
        recicleViewOrder();
        loadWebService();
        return view;
    }

    private void recicleViewOrder() {
        listOrder = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerViewOrder);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setHasFixedSize(true);

        request = Volley.newRequestQueue(getContext());
    }

    private void loadWebService() {
        progress = new ProgressDialog(getContext());
        progress.setMessage(getString(R.string.load));
        progress.show();

        String url = "http://" + ip + "/appmo/listOrder.php";

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);
    }

    public void onResponse(JSONObject response) {
        Order order = null;

        JSONArray json = response.optJSONArray("order");
        try {
            for (int i = 0; i < json.length(); i++) {
                order = new Order("", "", "", "",
                        "", "");
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);
                order.setNameOrder(jsonObject.optString("nameClient"));
                order.setDate(jsonObject.optString("date"));
                order.setDestinationOrder(jsonObject.getString("destination"));


                listOrder.add(order);

            }

            progress.hide();
            OrderAdapter orderAdapter = new OrderAdapter(listOrder);
            recyclerView.setAdapter(orderAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), getString(R.string.insusessfull), Toast.LENGTH_LONG).show();
            progress.hide();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }
}
