package com.example.appmo.Order;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

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
public class OrderAdd extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {
    public static ManagerFragmentIndex state;
    String ip = new infoIP().getIp();

    View view;
    EditText  txtDestination, txtQuantity;
    TextView tvShowDate, txtDate;
    DatePicker datePicker;
    private Spinner spnName,  spnType, spnNameBread;
    Button btnSave;
    ImageView ivGetDate;
    ProgressDialog progress;
    ArrayList<String> name;
    ArrayList<String> bread;


    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;


    public OrderAdd() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_order, container, false);
        saveOrder();
        showToolBar();
        setHasOptionsMenu(true);
        backButton();
        structureName();
        getDate();
        //spineerType();
        //spineerNameBread();
        listName();
        listNameBread();
        structureNameBread();
        return view;
    }

    private void showToolBar() {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        final ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.orderAdd));
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

    private void alertBack() {
        android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(
                getContext());
        alertDialogBuilder.setTitle(getString(R.string.alertExit));
        alertDialogBuilder.setPositiveButton(getString(R.string.accept), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent goMainIndex = new Intent(getContext(), MainContainerIndex.class);
                startActivity(goMainIndex);
            }
        })
                .setNegativeButton(getString(R.string.cancel),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });

        android.app.AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }



    private void saveOrder() {
        txtDestination = view.findViewById(R.id.txtPhone);
        spnType = view.findViewById(R.id.spType);
        txtQuantity = view.findViewById(R.id.txtNumberAddres);
        txtDate = view.findViewById(R.id.tvShowDate);

        request = Volley.newRequestQueue(getContext());

        btnSave = view.findViewById(R.id.btnSaveOrden);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarWebService();
                            }
        });


    }

    private void getDate() {
        datePicker = view.findViewById(R.id.datePicker);
        txtDate = view.findViewById(R.id.tvShowDate);
        ivGetDate = view.findViewById(R.id.ivGetDate);
        ivGetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtDate.setText(String.valueOf(datePicker.getYear() + "-"
                                + String.valueOf(datePicker.getMonth() + 1 + "-"
                                + String.valueOf(datePicker.getDayOfMonth()))
                        )

                );
            }
        });

//        tvShowDate = view.findViewById(R.id.tvShowDate);
//        ivGetDate = view.findViewById(R.id.ivGetDate);
//        ivGetDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Calendar calendar = Calendar.getInstance();
//                String corruntDate = DateFormat.getDateInstance().format(calendar.getTime());
//
//                tvShowDate.setText(corruntDate);
//            }
//        });
    }

    private void cargarWebService() {
        progress = new ProgressDialog(getContext());
        progress.setMessage(getString(R.string.load));
        progress.show();

        String url = "http://" + ip + "/appmo/addOrder.php?name=" +spnName.getSelectedItem().toString() +
                "&destination=" + txtDestination.getText().toString() +
                "&type=" + spnType.getSelectedItem().toString() +
                "&namebread=" + spnNameBread.getSelectedItem().toString() +
                "&quantity=" + txtQuantity.getText().toString() +
                "&date=" + txtDate.getText().toString() ;



        url = url.replace(" ", "%20");

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);
    }

    private void structureName() {
        name = new ArrayList<>();
        spnName = view.findViewById(R.id.spnName);
        spnName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String fruta = spnName.getItemAtPosition(spnName.getSelectedItemPosition()).toString();
                //Toast.makeText(getContext(), fruta, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });
    }

    private void structureNameBread() {
        bread = new ArrayList<>();
        spnNameBread = view.findViewById(R.id.spnNameBread);
        spnNameBread.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String fruta = spnNameBread.getItemAtPosition(spnNameBread.getSelectedItemPosition()).toString();
                //Toast.makeText(getContext(), fruta, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });
    }

    public void listNameBread() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, infoIP.URL_WEB_SERVICES_BREAD+ "list_bread.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("bread");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                String nameU = jsonObject1.getString("name");
                                bread.add(nameU);
                            }
                            spnNameBread.setAdapter(new ArrayAdapter<String>(getContext(),
                                    android.R.layout.simple_spinner_dropdown_item, bread));
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


    public void listName() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, infoIP.URL_WEB_SERVICES_CLIENT + "list_client.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("client");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                String nameU = jsonObject1.getString("name");
                                name.add(nameU);
                            }
                            spnName.setAdapter(new ArrayAdapter<String>(getContext(),
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
    public void onResponse(JSONObject response) {
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progress.hide();
        Intent goMainIndex = new Intent(getContext(), MainContainerIndex.class);
        startActivity(goMainIndex);
    }

    private void changeFragment(ManagerFragmentIndex state) {
        this.state = ManagerFragmentIndex.setState(state);
        this.state.execute((MainContainerIndex) getContext());
    }

}
