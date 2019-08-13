package com.example.appmo.Route;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appmo.R;
import com.example.appmo.infoIP;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RouteAdd extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {
    public static ManagerFragmentRoute state;
    View view;
    String ip = new infoIP().getIp();
    EditText txtName;
    Button btnSave;
    private Spinner spName ;
    ProgressDialog progress;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    ArrayList<String> name;


    public RouteAdd() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_add_route, container, false);
        showToolBar();
        saveProduct();
        backButton();
        listName();
        structureName();
        return view;
    }

    private void saveProduct() {
        txtName = view.findViewById(R.id.txtName);
        request = Volley.newRequestQueue(getContext());
        btnSave = view.findViewById(R.id.btnSaveCurator);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validationText();
            }
        });
    }

    private void saveProductAler() {
        AlertDialog.Builder alertSaveRoute = new AlertDialog.Builder(getContext());
        alertSaveRoute.setTitle(getString(R.string.rouT));
        alertSaveRoute.setMessage(getString(R.string.rouM));
        alertSaveRoute.setPositiveButton(getString(R.string.accept), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cargarWebService();
                changeFragment(ManagerFragmentRoute.ROUTE_INDEX);
            }
        });
        alertSaveRoute.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertSaveRoute.create().show();
    }

    private void validationText() {
        if (txtName.getText().length() <= 0){
            txtName.setError(getString(R.string.error));

        } else {
            saveProductAler();
        }
    }

    private void alertBack() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle(getString(R.string.proT));
        alertDialog.setMessage(getString(R.string.proMM));
        alertDialog.setPositiveButton(getString(R.string.accept), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                changeFragment(ManagerFragmentRoute.ROUTE_INDEX);
            }
        });
        alertDialog.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.create().show();
    }

    private void cargarWebService() {
        progress = new ProgressDialog(getContext());
        progress.setMessage(getString(R.string.load));
        progress.show();
        String url = "http://" + ip + "/appmo/addRoute.php?name=" + txtName.getText().toString() +
                "&curator=" + spName.getSelectedItem().toString() ;

        url = url.replace(" ", "%20");

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);
    }

    private void structureName() {
        name = new ArrayList<>();
        spName = view.findViewById(R.id.txtCurator);
        spName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String fruta = spName.getItemAtPosition(spName.getSelectedItemPosition()).toString();
                //Toast.makeText(getContext(), fruta, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });
    }

    public void listName() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, infoIP.URL_WEB_SERVICES_USER+ "listar-fruta.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("user");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                String nameU = jsonObject1.getString("name");
                                name.add(nameU);
                            }
                            spName.setAdapter(new ArrayAdapter<String>(getContext(),
                                    android.R.layout.simple_spinner_dropdown_item, name));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progress.hide();
        Toast.makeText(getContext(), getString(R.string.insusessfull) + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        progress.hide();
    }

    private void showToolBar() {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        final ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.addRoute));
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
            actionBar.setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertBack();
                }
            });
        }

    }

    private void backButton() {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @SuppressLint("WrongConstant")
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    return true;
                }
                return false;
            }
        });
    }

    private void changeFragment(ManagerFragmentRoute state) {
        this.state = ManagerFragmentRoute.setState(state);
        this.state.execute((MainContainerRoute) getContext());
    }


}
