package com.example.appmo.Purchases;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.appmo.R;
import com.example.appmo.infoIP;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddPurchase extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {
    public static ManagerFragmentRoomStock state;
    String ip = new infoIP().getIp();
    View view;
    Button btnShowDate, btnSavePurchase;
    TextView txtDate;
    DatePicker datePicker;

    ProgressDialog progress;
    ArrayList<String> name;
    ArrayList<String> bread;


    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    public AddPurchase() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_purchase, container, false);
        getDate();
        savePurchase();
        return view;
    }

    private void savePurchase() {
        btnSavePurchase = view.findViewById(R.id.btnSavePurchase);
        request = Volley.newRequestQueue(getContext());
        btnSavePurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { 
                validation();
            }
        });

    }

    private void validation() {
        if (txtDate.getText().length()<=0){
            txtDate.setError("Vefirique la información");
        } else {
            confirmationSave();
        }
    }

    private void confirmationSave() {
        AlertDialog.Builder alertSSave = new AlertDialog.Builder(getContext());
        alertSSave.setTitle("Seguro que desea guardar");
        alertSSave.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cargarWebService();

            }
        });
        alertSSave.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertSSave.create().show();
    }

    private void cargarWebService() {
        progress = new ProgressDialog(getContext());
        progress.setMessage(getString(R.string.load));
        progress.show();
        String url = "http://"+ ip + "/appmo/add_Purchase.php?purchaseDate=" + txtDate.getText().toString();
        url = url.replace(" ", "%20");

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);
    }

    private void getDate() {

        datePicker = view.findViewById(R.id.dpDate);
        txtDate = view.findViewById(R.id.tvShowDate);
        btnShowDate = view.findViewById(R.id.btnShowDate);
        btnShowDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtDate.setText(String.valueOf(datePicker.getYear() + "-"
                                + String.valueOf(datePicker.getMonth() + 1 + "-"
                                + String.valueOf(datePicker.getDayOfMonth()))
                        )

                );
            }
        });

    }

    @Override
    public void onResponse(JSONObject response) {
        changeFragment (ManagerFragmentRoomStock.PRODUCTADD);
        progress.hide();

    }

    private void changeFragment(ManagerFragmentRoomStock state) {
        this.state = ManagerFragmentRoomStock.setState(state);
        this.state.execute((MainContainerRoomStock) getContext());
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }
}
